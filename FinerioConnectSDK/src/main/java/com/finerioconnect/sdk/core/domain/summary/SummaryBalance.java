package com.finerioconnect.sdk.core.domain.summary;

import java.math.BigDecimal;
import java.util.Date;

public class SummaryBalance {

  private Date date;
  private BigDecimal incomes;
  private BigDecimal expenses;

  public Date getDate() {
    return date;
  }

  public void setDate( Date date ) {
    this.date = date;
  }

  public BigDecimal getIncomes() {
    return incomes;
  }

  public void setIncomes( BigDecimal incomes ) {
    this.incomes = incomes;
  }

  public BigDecimal getExpenses() {
    return expenses;
  }

  public void setExpenses( BigDecimal expenses ) {
    this.expenses = expenses;
  }

}
