package com.finerioconnect.sdk.core.rows;

import com.finerioconnect.sdk.core.domain.FinerioConnectImage;
import com.finerioconnect.sdk.core.domain.Transaction;

public class TransactionRow extends FinerioConnectImage {

  private Transaction transaction;
  private Boolean canBeDeleted;
  private TransactionRowListener listener;

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction( Transaction transaction ) {
    this.transaction = transaction;
  }

  public Boolean getCanBeDeleted() {
    return canBeDeleted;
  }

  public void setCanBeDeleted( Boolean canBeDeleted ) {
    this.canBeDeleted = canBeDeleted;
  }

  public TransactionRowListener getListener() {
    return listener;
  }

  public void setListener( TransactionRowListener listener ) {
    this.listener = listener;
  }

}
