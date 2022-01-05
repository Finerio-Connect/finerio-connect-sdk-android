package com.finerioconnect.sdk.core.domain;

public class BankField implements FinerioConnectDomain {

  public enum BankFieldType {
    TEXT,
    PASSWORD
  }

  private String id;
  private String placeholder;
  private BankFieldType type;
  private Boolean required;
  private String value;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId( String id ) {
    this.id = id;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder( String placeholder ) {
    this.placeholder = placeholder;
  }

  public BankFieldType getType() {
    return type;
  }

  public void setType( BankFieldType type ) {
    this.type = type;
  }

  public Boolean getRequired() {
    return required;
  }

  public void setRequired( Boolean required ) {
    this.required = required;
  }

  public String getValue() {
    return value;
  }

  public void setValue( String value ) {
    this.value = value;
  }

}
