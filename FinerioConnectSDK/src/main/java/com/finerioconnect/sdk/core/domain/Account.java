package com.finerioconnect.sdk.core.domain;

import java.math.BigDecimal;

public class Account implements FinerioConnectDomain {

  private String id;
  private String name;
  private String type;
  private BigDecimal amount;

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

  public String getType() {
    return type;
  }

  public void setType( String type ) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

}
