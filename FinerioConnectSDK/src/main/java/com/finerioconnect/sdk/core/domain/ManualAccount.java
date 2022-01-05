package com.finerioconnect.sdk.core.domain;

import java.math.BigDecimal;

public class ManualAccount implements FinerioConnectDomain {

  public enum ManualAccountType {
    CASH,
    DEBIT_CARD,
    DEBT,
    CREDIT_CARD,
    INVESTMENT,
    ASSURANCE,
    POSSESSION,
    MORTGAGE,
    PERSONAL_CREDIT
  }

  private String id;
  private String name;
  private ManualAccountType type;
  private BigDecimal amount;
  private Boolean cashDefault;
  private Boolean atmDefault;

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

  public ManualAccountType getType() {
    return type;
  }

  public void setType( ManualAccountType type ) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public Boolean getCashDefault() {
    return cashDefault;
  }

  public void setCashDefault( Boolean cashDefault ) {
    this.cashDefault = cashDefault;
  }

  public Boolean getAtmDefault() {
    return atmDefault;
  }

  public void setAtmDefault( Boolean atmDefault ) {
    this.atmDefault = atmDefault;
  }

}
