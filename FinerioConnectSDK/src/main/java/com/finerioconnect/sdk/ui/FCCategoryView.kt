package com.finerioconnect.sdk.ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.core.category.CategoryPalette
import com.finerioconnect.sdk.core.category.CategoryTexts
import com.finerioconnect.sdk.databinding.PartialCategoryViewBinding
import com.finerioconnect.sdk.extensions.typeFace
import com.finerioconnect.sdk.models.FCCategory
import com.finerioconnect.sdk.shared.enums.FCCategoryType
import com.finerioconnect.sdk.ui.adapters.categories.CategoriesAdapter
import com.finerioconnect.sdk.ui.adapters.categories.models.CategoryAdapterModel
import com.finerioconnect.sdk.ui.base.CategoryBaseView
import com.finerioconnect.sdk.ui.listeners.OnFCCategoriesListener
import com.finerioconnect.sdk.ui.listeners.OnFCCategoryListener
import com.finerioconnect.sdk.ui.listeners.OnFCSubcategoryListener

class FCCategoryView(context: Context, attrs: AttributeSet? = null) :
    CategoryBaseView(context, attrs) {
    private var categoriesAdapter: ArrayList<CategoryAdapterModel> = ArrayList()
    private lateinit var mAdapter: CategoriesAdapter

    private var listenerCategories: OnFCCategoriesListener? = null
    private var listenerCategory: OnFCCategoryListener? = null
    private var listenerSubcategory: OnFCSubcategoryListener? = null
    private var categoryType: FCCategoryType? = null
    private var texts: CategoryTexts? = null
    private var palette: CategoryPalette? = null

    private val mBinding =
        PartialCategoryViewBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )

    fun setUpView(
        categories: List<FCCategory>?,
        categoryType: FCCategoryType?,
        categoriesFiltered: List<FCCategory>? = null
    ) {
        this.categoryType = categoryType
        mBinding.constraintLayoutCategories.visibility = VISIBLE

        checkSDKCoreConfiguration()
        setUpInputs()
        convertCategoriesToAdapterModel(categories, categoriesFiltered)
        setUpAdapter(categoriesAdapter, categoryType)
        configureInputsListener()

        if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY) {
            hideInputs()
        }
    }

    fun setTexts(texts: CategoryTexts?) {
        this.texts = texts
    }

    fun setPalette(palette: CategoryPalette?) {
        this.palette = palette
    }

    private fun hideInputs() {
        mBinding.constraintLayoutCategoryInputs.visibility = GONE
    }

    private fun setUpInputs() = with(mBinding) {
        val font = FinerioConnectSDK.shared.fonts.regular
        buttonSelect.text = resources.getString(R.string.select)
        buttonClear.text = resources.getString(R.string.clear)
        if (texts != null) {
            buttonSelect.text = texts?.selectButtonTitle
            buttonClear.text = texts?.clearButtonTitle
        }

        buttonSelect.typeFace(font, Typeface.NORMAL)
        buttonClear.typeFace(font, Typeface.NORMAL)
        buttonClear.setTextColor(
            ContextCompat.getColor(context, R.color.colorDarkBlue)
        )
        buttonClear.setBackgroundColor(
            ContextCompat.getColor(context, android.R.color.transparent)
        )
        buttonSelect.setTextColor(
            ContextCompat.getColor(context, R.color.white)
        )
        buttonSelect.setBackgroundColor(
            ContextCompat.getColor(context, R.color.colorDarkBlue)
        )

        if (palette != null) {
            if (palette!!.selectButtonText != -1) buttonSelect.setTextColor(palette!!.selectButtonText)
            if (palette!!.clearButtonText != -1) {
                buttonClear.setTextColor(palette!!.clearButtonText)
            }
            if (palette!!.selectButtonBg != -1) buttonSelect.setBackgroundColor(palette!!.selectButtonBg)
            if (palette!!.clearButtonBg != -1) buttonClear.setBackgroundColor(palette!!.clearButtonBg)
        }
    }

    fun setListener(listenerCategories: OnFCCategoriesListener?) {
        this.listenerCategories = listenerCategories
    }

    fun setListener(listenerCategory: OnFCCategoryListener?) {
        this.listenerCategory = listenerCategory
    }

    fun setListener(listenerSubcategory: OnFCSubcategoryListener?) {
        this.listenerSubcategory = listenerSubcategory
    }

    private fun convertCategoriesToAdapterModel(
        categories: List<FCCategory>?,
        categoriesFiltered: List<FCCategory>?
    ) {
        if (categories != null) {
            for (index in categories.indices) {
                categoriesAdapter.add(categories[index].toCategoryAdapter())

                if (categories[index].subCategories != null) {
                    for (indexSub in categories[index].subCategories?.indices!!) {
                        categories[index].subCategories?.get(indexSub)?.let {
                            categoriesAdapter[index].subCategories?.add(
                                it.toCategoryAdapter()
                            )
                        }
                    }
                    checkFilter(categoriesFiltered, categoriesAdapter[index])
                }
            }
        }
    }

    private fun checkFilter(
        categoriesFiltered: List<FCCategory>?,
        category: CategoryAdapterModel?
    ) {
        if (!categoriesFiltered.isNullOrEmpty()) {
            for (index in categoriesFiltered.indices) {
                val categoryFilter = categoriesFiltered[index]
                if (categoryFilter.id == category?.id) {
                    category.isChecked = true

                    if (!categoryFilter.subCategories.isNullOrEmpty()) {
                        for (indexSubFilter in categoryFilter.subCategories?.indices!!) {
                            val subCategoryFilter =
                                categoryFilter.subCategories?.get(indexSubFilter)
                            for (indexSub in category.subCategories?.indices!!) {
                                if (subCategoryFilter != null) {
                                    if (subCategoryFilter.id == category.subCategories!![indexSub].id) {
                                        category.subCategories!![indexSub].isChecked = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                    break
                }
            }
        }
    }

    private fun configureInputsListener() = with(mBinding) {
        buttonSelect.setOnClickListener {
            when (categoryType) {
                FCCategoryType.SHOW_ALL_ONLY_ONE_CATEGORY ->
                    listenerCategory?.didSelectedCategory(mAdapter.getCategorySelected())
                FCCategoryType.SHOW_ALL_MULTI_SELECTION ->
                    listenerCategories?.didSelectedCategories(mAdapter.getCategoriesSelected())
                else ->
                    listenerCategory?.didSelectedCategory(mAdapter.getCategorySelected())
            }
        }

        buttonClear.setOnClickListener {
            mAdapter.setFilters(false)
        }
    }

    private fun setUpAdapter(
        categories: ArrayList<CategoryAdapterModel>?,
        categoryType: FCCategoryType?
    ) {
        if (categories != null) {
            val hasToRemoveImage = hasToRemoveImage(categories)

            mAdapter = CategoriesAdapter(
                categories.toMutableList(),
                categoryType,
                mBinding.expandableListViewCategory,
                listenerSubcategory,
                hasToRemoveImage
            )
            mBinding.expandableListViewCategory.setAdapter(mAdapter)
        }
    }

    private fun hasToRemoveImage(categories: ArrayList<CategoryAdapterModel>): Boolean {
        var removeImage = false
        for(category in categories) {
            if (category.image?.name.isNullOrEmpty() && category.image?.tintColor == null) {
                removeImage = true
                break
            }
        }

        return removeImage
    }

    private fun FCCategory.toCategoryAdapter() = CategoryAdapterModel(
        id = id,
        name = name,
        backgroundColor = backgroundColor,
        textColor = textColor,
        image = image,
        parentId = parentId,
        isChecked = false,
        checkbox = null,
        subCategories = arrayListOf()
    )
}