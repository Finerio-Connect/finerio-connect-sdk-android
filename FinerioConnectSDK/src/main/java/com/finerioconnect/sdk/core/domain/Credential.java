package com.finerioconnect.sdk.core.domain;

import java.util.Date;
import java.util.List;

public class Credential implements FinerioConnectDomain {

  public enum CredentialStatus {
    ACTIVE,
    INVALID,
    PENDING,
    TOKEN
  }

  private String id;
  private Bank bank;
  private List<BankField> fields;
  private Date lastUpdated;
  private CredentialStatus status;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId( String id ) {
    this.id = id;
  }

  public Bank getBank() {
    return bank;
  }

  public void setBank( Bank bank ) {
    this.bank = bank;
  }

  public List<BankField> getFields() {
    return fields;
  }

  public void setFields( List<BankField> fields ) {
    this.fields = fields;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated( Date lastUpdated ) {
    this.lastUpdated = lastUpdated;
  }

  public CredentialStatus getStatus() {
    return status;
  }

  public void setStatus( CredentialStatus status ) {
    this.status = status;
  }

}
