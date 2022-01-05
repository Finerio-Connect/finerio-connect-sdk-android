package com.finerioconnect.sdk.core.domain;

import java.math.BigDecimal;

public class AccountBalance {

  private BigDecimal debit;
  private BigDecimal shortTermCredit;
  private BigDecimal investments;
  private BigDecimal longTermCredit;

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit( BigDecimal debit ) {
    this.debit = debit;
  }

  public BigDecimal getShortTermCredit() {
    return shortTermCredit;
  }

  public void setShortTermCredit( BigDecimal shortTermCredit ) {
    this.shortTermCredit = shortTermCredit;
  }

  public BigDecimal getInvestments() {
    return investments;
  }

  public void setInvestments( BigDecimal investments ) {
    this.investments = investments;
  }

  public BigDecimal getLongTermCredit() {
    return longTermCredit;
  }

  public void setLongTermCredit( BigDecimal longTermCredit ) {
    this.longTermCredit = longTermCredit;
  }

}
