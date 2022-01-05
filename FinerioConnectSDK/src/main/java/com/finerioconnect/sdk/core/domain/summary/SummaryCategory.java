package com.finerioconnect.sdk.core.domain.summary;

import com.finerioconnect.sdk.core.domain.CategoryItem;

import java.math.BigDecimal;
import java.util.List;

public class SummaryCategory {

  private CategoryItem categoryItem;
  private BigDecimal amount;
  private List<SummarySubCategory> subCategories;
  private SummaryCategoryListener listener;

  public CategoryItem getCategoryItem() {
    return categoryItem;
  }

  public void setCategoryItem( CategoryItem categoryItem ) {
    this.categoryItem = categoryItem;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public List<SummarySubCategory> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories( List<SummarySubCategory> subCategories ) {
    this.subCategories = subCategories;
  }

  public SummaryCategoryListener getListener() {
    return listener;
  }

  public void setListener( SummaryCategoryListener listener ) {
    this.listener = listener;
  }

}
