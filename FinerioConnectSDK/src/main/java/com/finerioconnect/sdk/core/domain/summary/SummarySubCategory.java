package com.finerioconnect.sdk.core.domain.summary;

import com.finerioconnect.sdk.core.domain.CategoryItem;

import java.math.BigDecimal;
import java.util.List;

public class SummarySubCategory {

  private CategoryItem categoryItem;
  private BigDecimal amount;
  private List<TransactionsByDate> transactionsByDate;
  private SummarySubCategoryListener listener;

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

  public List<TransactionsByDate> getTransactionsByDate() {
    return transactionsByDate;
  }

  public void setTransactionsByDate( List<TransactionsByDate> transactionsByDate ) {
    this.transactionsByDate = transactionsByDate;
  }

  public SummarySubCategoryListener getListener() {
    return listener;
  }

  public void setListener( SummarySubCategoryListener listener ) {
    this.listener = listener;
  }

}
