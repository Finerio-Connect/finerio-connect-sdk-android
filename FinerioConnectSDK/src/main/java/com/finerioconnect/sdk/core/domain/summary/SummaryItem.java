package com.finerioconnect.sdk.core.domain.summary;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SummaryItem {

  public enum SummaryItemType {
    INCOME,
    EXPENSE
  }

  private Date date;
  private BigDecimal amount;
  private List<SummaryCategory> categories;
  private SummaryItemType type;

  public Date getDate() {
    return date;
  }

  public void setDate( Date date ) {
    this.date = date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public List<SummaryCategory> getCategories() {
    return categories;
  }

  public void setCategories( List<SummaryCategory> categories ) {
    this.categories = categories;
  }

  public SummaryItemType getType() {
    return type;
  }

  public void setType( SummaryItemType type ) {
    this.type = type;
  }

}
