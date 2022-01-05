package com.finerioconnect.sdk.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements FinerioConnectDomain {

  private String id;
  private Date date;
  private String description;
  private CategoryItem categoryItem;
  private BigDecimal amount;
  private Boolean notConsidered;
  private Account account;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId( String id ) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate( Date date ) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription( String description ) {
    this.description = description;
  }

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

  public Boolean getNotConsidered() {
    return notConsidered;
  }

  public void setNotConsidered( Boolean notConsidered ) {
    this.notConsidered = notConsidered;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount( Account account ) {
    this.account = account;
  }

}
