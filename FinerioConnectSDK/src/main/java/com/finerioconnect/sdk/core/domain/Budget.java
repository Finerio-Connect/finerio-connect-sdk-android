package com.finerioconnect.sdk.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Budget implements FinerioConnectDomain {

  private String id;
  private String name;
  private BigDecimal amount;
  private BigDecimal spent;
  private BigDecimal leftToSpend;
  private BigDecimal warningPercentage;
  private List<Budget> subBudgets;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId( String id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public BigDecimal getSpent() {
    return spent;
  }

  public void setSpent( BigDecimal spent ) {
    this.spent = spent;
  }

  public BigDecimal getLeftToSpend() {
    return leftToSpend;
  }

  public void setLeftToSpend( BigDecimal leftToSpend ) {
    this.leftToSpend = leftToSpend;
  }

  public BigDecimal getWarningPercentage() {
    return warningPercentage;
  }

  public void setWarningPercentage( BigDecimal warningPercentage ) {
    this.warningPercentage = warningPercentage;
  }

  public List<Budget> getSubBudgets() {
    return subBudgets;
  }

  public void setSubBudgets( List<Budget> subBudgets ) {
    this.subBudgets = subBudgets;
  }

}
