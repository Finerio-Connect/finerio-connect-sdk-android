package com.finerioconnect.sdk.ui.adapters.categories

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.finerioconnect.sdk.R
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.databinding.ItemCategoryGroupBinding
import com.finerioconnect.sdk.databinding.ItemSubcategoryChildBinding
import com.finerioconnect.sdk.extensions.findImageIdentifier
import com.finerioconnect.sdk.extensions.typeFace
import com.finerioconnect.sdk.models.FCCategory
import com.finerioconnect.sdk.shared.enums.FCCategoryType
import com.finerioconnect.sdk.ui.adapters.categories.models.CategoryAdapterModel
import com.finerioconnect.sdk.ui.listeners.OnFCSubcategoryListener
import com.finerioconnect.sdk.utils.ImageViewUtils

class CategoriesAdapter(
    private val categories: MutableList<CategoryAdapterModel>,
    private val categoryType: FCCategoryType?,
    private val expandableListView: ExpandableListView?,
    private val subCategoryListener: OnFCSubcategoryListener?,
    private val hasToRemoveImage: Boolean
) : BaseExpandableListAdapter() {
    var requireCheck: Boolean = false
    var cameFromChild: Boolean = false

    override fun getGroupCount(): Int {
        return categories.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        if (requireSubcategories()) {
            return categories[groupPosition].subCategories?.size!!
        }
        return 0
    }

    override fun getGroup(groupPosition: Int): CategoryAdapterModel {
        return categories[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): CategoryAdapterModel? {
        if (requireSubcategories()) {
            return categories[groupPosition].subCategories?.get(childPosition)
        }
        return null
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        view: View?, parent: ViewGroup?
    ): View? {
        var convertedView = view
        if (parent != null) {
            convertedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_group, parent, false)
        }

        val binding = convertedView?.let { ItemCategoryGroupBinding.bind(it) }
        if (binding != null) {
            if (parent != null) {
                setImageIndicator(binding, isExpanded, parent.context, groupPosition)
            }
            setUpGroup(binding, groupPosition)
        }

        return convertedView
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isExpanded: Boolean, view: View?, parent: ViewGroup?
    ): View? {
        var convertedView = view
        if (requireSubcategories()) {
            if (parent != null) {
                convertedView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_subcategory_child, parent, false)
            }

            val binding = convertedView?.let { ItemSubcategoryChildBinding.bind(it) }
            if (binding != null) {
                setUpChildren(binding, groupPosition, childPosition)
            }
        }

        return convertedView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    private fun setImageIndicator(
        binding: ItemCategoryGroupBinding,
        isExpanded: Boolean,
        context: Context?,
        groupPosition: Int
    ) {
        if (!categories[groupPosition].subCategories.isNullOrEmpty()) {
            var indicatorImage =
                context?.let { ContextCompat.getDrawable(context, R.drawable.ic_arrow_down) }
            binding.imageViewIndicator.setImageDrawable(indicatorImage)
            if (isExpanded) {
                indicatorImage =
                    context?.let { ContextCompat.getDrawable(context, R.drawable.ic_arrow_up) }
                binding.imageViewIndicator.setImageDrawable(indicatorImage)
            }
        }
    }

    @SuppressLint("Range")
    private fun setUpGroup(binding: ItemCategoryGroupBinding, groupPosition: Int) {
        binding.linearLayoutCategory.setBackgroundColor(
            Color.parseColor(getGroup(groupPosition).backgroundColor)
        )
        val textColor = ContextCompat.getColor(
            binding.textViewCategoryName.context, R.color.white
        )
        configText(
            binding.textViewCategoryName, categories[groupPosition].name,
            textColor, -1
        )
        setCategoryImage(binding.imageViewCategoryIcon, categories[groupPosition])
        val font = FinerioConnectSDK.shared.fonts.bold
        binding.textViewCategoryName.typeFace(font, Typeface.BOLD)
        setCheckboxGroup(binding, groupPosition)
    }

    private fun setCheckboxGroup(binding: ItemCategoryGroupBinding, groupPosition: Int) {
        categories[groupPosition].checkbox = binding.checkboxCategory
        binding.checkboxCategory.isChecked = categories[groupPosition].isChecked

        if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY) {
            binding.checkboxCategory.visibility = View.GONE
        }

        if (requireCheck && !categories[groupPosition].isDisplayedOnScreen) {
            categories[groupPosition].isDisplayedOnScreen = true
            categories[groupPosition].checkbox?.isChecked = true
            categories[groupPosition].isChecked = true
        }

        binding.checkboxCategory.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!cameFromChild) {
                    expandableListView?.expandGroup(groupPosition)
                    categories[groupPosition].isChecked = isChecked

                    if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_CATEGORY ||
                        categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY ||
                        categoryType == FCCategoryType.SHOW_ONLY_CATEGORIES
                    ) {
                        for (index in categories.indices) {
                            if (index != groupPosition) {
                                categories[index].isChecked = false
                                categories[index].checkbox?.isChecked = false
                                for (indexChild in categories[index].subCategories?.indices!!) {
                                    categories[index].subCategories?.get(indexChild)
                                        ?.checkbox?.isChecked = false
                                }
                            }
                        }
                    }

                    selectAllChildren(groupPosition)
                }
            } else {
                if (!cameFromChild) {
                    expandableListView?.collapseGroup(groupPosition)
                    categories[groupPosition].isChecked = false
                    categories[groupPosition].checkbox?.isChecked = false

                    if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_CATEGORY ||
                        categoryType == FCCategoryType.SHOW_ALL_MULTI_SELECTION
                    ) {
                        for (indexChild in categories[groupPosition].subCategories?.indices!!) {
                            val currentSubCategory =
                                categories[groupPosition].subCategories?.get(indexChild)
                            if (currentSubCategory?.isChecked == true) {
                                currentSubCategory.checkbox?.isChecked = false
                                currentSubCategory.isChecked = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun selectAllChildren(groupPosition: Int) {
        for (indexChild in categories[groupPosition].subCategories?.indices!!) {
            categories[groupPosition].subCategories?.get(indexChild)
                ?.checkbox?.isChecked = true
            categories[groupPosition].subCategories?.get(indexChild)
                ?.isChecked = true
        }
    }

    private fun setUpChildren(
        binding: ItemSubcategoryChildBinding,
        groupPosition: Int, childPosition: Int
    ) {
        val textColor = ContextCompat.getColor(
            binding.checkboxSubcategory.context, R.color.black
        )
        val currentCategory = categories[groupPosition].subCategories?.get(childPosition)
        configText(
            binding.checkboxSubcategory, currentCategory?.name ?: "",
            textColor, -1
        )
        val font = FinerioConnectSDK.shared.fonts.regular
        binding.checkboxSubcategory.typeFace(font, Typeface.NORMAL)
        setUpCheckboxChild(binding, groupPosition, childPosition)
    }

    private fun setUpCheckboxChild(
        binding: ItemSubcategoryChildBinding,
        groupPosition: Int, childPosition: Int
    ) {
        if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY) {
            hideCheckbox(binding);
        }

        categories[groupPosition].subCategories?.get(childPosition)?.checkbox =
            binding.checkboxSubcategory
        binding.checkboxSubcategory.isChecked = categories[groupPosition]
            .subCategories!![childPosition].isChecked

        if (requireCheck && !categories[groupPosition]
                .subCategories!![childPosition].isDisplayedOnScreen
        ) {
            categories[groupPosition]
                .subCategories!![childPosition].isDisplayedOnScreen = true
            categories[groupPosition]
                .subCategories!![childPosition].checkbox?.isChecked = true
            categories[groupPosition]
                .subCategories!![childPosition].isChecked = true
        }

        binding.checkboxSubcategory.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY) {
                    for (indexParent in categories.indices) {
                        for (indexChild in categories[indexParent].subCategories?.indices!!) {
                            if (indexChild != childPosition || groupPosition != indexParent) {
                                categories[indexParent].subCategories!![indexChild]
                                    .isChecked = false
                                categories[indexParent].subCategories?.get(indexChild)?.checkbox
                                    ?.isChecked = false
                            }
                        }
                    }

                    val subcategorySelected =
                        categories[groupPosition].subCategories!![childPosition]
                    subCategoryListener?.didSelectedSubcategory(subcategorySelected.toFCModel())
                } else {
                    categories[groupPosition].subCategories!![childPosition].isChecked = true

                    // Turn on parent checkboxes from child
                    if (categoryType != FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY) {
                        if (categories[groupPosition].checkbox?.isChecked == false) {
                            cameFromChild = true
                            categories[groupPosition].checkbox?.isChecked = true
                            categories[groupPosition].isChecked = true
                            cameFromChild = false
                        }
                    }

                    // Turn off others parents in case of one category selection
                    if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_CATEGORY) {
                        for (indexParent in categories.indices) {
                            if (categories[groupPosition].id != categories[indexParent].id) {
                                if (categories[indexParent].checkbox?.isChecked == true) {
                                    categories[indexParent].checkbox?.isChecked = false
                                    categories[indexParent].isChecked = false

                                    for (indexChild in categories[indexParent].subCategories?.indices!!) {
                                        if (categories[indexParent].subCategories!![indexChild]
                                                .isChecked
                                        ) {
                                            categories[indexParent].subCategories!![indexChild]
                                                .isChecked = false
                                            categories[indexParent].subCategories?.get(indexChild)?.checkbox
                                                ?.isChecked = false
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                categories[groupPosition].subCategories!![childPosition].isChecked = false
                categories[groupPosition].subCategories?.get(childPosition)?.checkbox
                    ?.isChecked = false

                checkHasChildren(groupPosition)
            }
        }
    }

    private fun hideCheckbox(binding: ItemSubcategoryChildBinding) {
        binding.checkboxSubcategory.buttonDrawable = ContextCompat.getDrawable(
            binding.checkboxSubcategory.context,
            android.R.color.transparent
        )
        binding.checkboxSubcategory.background = ContextCompat.getDrawable(
            binding.checkboxSubcategory.context,
            android.R.color.transparent
        )
    }

    private fun checkHasChildren(groupPosition: Int) {
        if (categoryType == FCCategoryType.SHOW_ALL_MULTI_SELECTION) {
            for (indexChild in categories[groupPosition].subCategories?.indices!!) {
                if (categories[groupPosition].subCategories!![indexChild].isChecked) {
                    break
                }

                //remove parent if not has children, just for multi-selection
                if (indexChild == categories[groupPosition].subCategories?.size!! - 1) {
                    cameFromChild = true
                    categories[groupPosition].checkbox?.isChecked = false
                    categories[groupPosition].isChecked = false
                    cameFromChild = false
                }
            }
        }
    }

    private fun setCategoryImage(
        categoryCategoryImageView: ImageView,
        category: CategoryAdapterModel
    ) {
        if (hasToRemoveImage) {
            categoryCategoryImageView.visibility = View.GONE
        } else {
            ImageViewUtils.setResource(category.image?.name, categoryCategoryImageView, category.image?.tintColor)
        }
    }

    private fun configText(
        label: TextView, value: String, color: Int, bgColor: Int
    ) {
        label.apply {
            text = value
            setTextColor(color)
            if (bgColor != -1) setBackgroundColor(bgColor)
        }
    }

    private fun requireSubcategories(): Boolean {
        if (categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_CATEGORY ||
            categoryType == FCCategoryType.SHOW_ALL_ONLY_ONE_SUBCATEGORY ||
            categoryType == FCCategoryType.SHOW_ALL_MULTI_SELECTION
        ) {
            return true
        }
        return false
    }

    fun getCategoriesSelected(): ArrayList<FCCategory> {
        val categoriesFC: ArrayList<FCCategory> = ArrayList()
        for (index in categories.indices) {
            if (categories[index].checkbox?.isChecked == true) {
                categoriesFC.add(categories[index].toFCModel())

                val subcategoryFC: ArrayList<FCCategory> = arrayListOf()
                for (indexSub in categories[index].subCategories?.indices!!) {
                    val currentSubcategory = categories[index].subCategories?.get(indexSub)
                    if (currentSubcategory?.isChecked == true) {
                        subcategoryFC.add(currentSubcategory.toFCModel())
                    }
                }

                for (indexParent in categoriesFC.indices) {
                    if (categories[index].id == categoriesFC[indexParent].id) {
                        categoriesFC[indexParent].subCategories = subcategoryFC
                    }
                }
            }

        }
        return categoriesFC
    }

    fun getCategorySelected(): FCCategory? {
        var categoryFC: FCCategory? = null
        val subcategoryFC: ArrayList<FCCategory> = arrayListOf()
        for (index in categories.indices) {
            if (categories[index].checkbox?.isChecked == true) {
                categoryFC = categories[index].toFCModel()

                for (indexSub in categories[index].subCategories?.indices!!) {
                    val currentSubcategory = categories[index].subCategories?.get(indexSub)
                    if (currentSubcategory?.isChecked == true) {
                        subcategoryFC.add(currentSubcategory.toFCModel())
                    }
                }
                categoryFC.subCategories = subcategoryFC
                break
            }
        }
        return categoryFC
    }

    fun setFilters(isChecked: Boolean) {
        checkItFilters(isChecked)
        requireCheck = isChecked
    }

    private fun checkItFilters(isChecked: Boolean) {
        for (index in categories.indices) {
            categories[index].checkbox?.isChecked = isChecked
            categories[index].isChecked = isChecked
            for (indexSub in categories[index].subCategories?.indices!!) {
                val currentSubcategory = categories[index].subCategories?.get(indexSub)
                currentSubcategory?.checkbox?.isChecked = isChecked
                currentSubcategory?.isChecked = isChecked
            }
        }
    }

    private fun CategoryAdapterModel.toFCModel() = FCCategory(
        id = id,
        name = name,
        backgroundColor = backgroundColor,
        textColor = textColor,
        image = image,
        parentId = parentId,
        parentCategory = null,
        subCategories = null,
    )
}