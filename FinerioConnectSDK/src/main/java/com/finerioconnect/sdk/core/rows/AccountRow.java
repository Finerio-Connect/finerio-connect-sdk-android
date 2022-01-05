package com.finerioconnect.sdk.core.rows;

import com.finerioconnect.sdk.core.domain.Account;
import com.finerioconnect.sdk.core.domain.FinerioConnectImage;

public class AccountRow extends FinerioConnectImage {

  private Account account;
  private AccountRowListener listener;

  public Account getAccount() {
    return account;
  }

  public void setAccount( Account account ) {
    this.account = account;
  }

  public AccountRowListener getListener() {
    return listener;
  }

  public void setListener( AccountRowListener listener ) {
    this.listener = listener;
  }

}
