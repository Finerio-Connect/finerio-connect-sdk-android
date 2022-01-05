package com.finerioconnect.sdk.core.rows;

import android.app.Activity;

import com.finerioconnect.sdk.core.domain.Credential;
import com.finerioconnect.sdk.core.domain.FinerioConnectImage;
import com.finerioconnect.sdk.core.domain.ManualAccount;

import java.util.List;

public class CredentialRow extends FinerioConnectImage {

  private Credential credential;
  private ManualAccount manualAccount;
  private Boolean showSynchronizeButton;
  private String usernameTitle;
  private String usernameContent;
  private List<AccountRow> accounts;
  private Activity preview;
  private CredentialRowListener listener;

  public Credential getCredential() {
    return credential;
  }

  public void setCredential( Credential credential ) {
    this.credential = credential;
  }

  public ManualAccount getManualAccount() {
    return manualAccount;
  }

  public void setManualAccount( ManualAccount manualAccount ) {
    this.manualAccount = manualAccount;
  }

  public Boolean getShowSynchronizeButton() {
    return showSynchronizeButton;
  }

  public void setShowSynchronizeButton( Boolean showSynchronizeButton ) {
    this.showSynchronizeButton = showSynchronizeButton;
  }

  public String getUsernameTitle() {
    return usernameTitle;
  }

  public void setUsernameTitle( String usernameTitle ) {
    this.usernameTitle = usernameTitle;
  }

  public String getUsernameContent() {
    return usernameContent;
  }

  public void setUsernameContent( String usernameContent ) {
    this.usernameContent = usernameContent;
  }

  public List<AccountRow> getAccounts() {
    return accounts;
  }

  public void setAccounts( List<AccountRow> accounts ) {
    this.accounts = accounts;
  }

  public Activity getPreview() {
    return preview;
  }

  public void setPreview( Activity preview ) {
    this.preview = preview;
  }

  public CredentialRowListener getListener() {
    return listener;
  }

  public void setListener( CredentialRowListener listener ) {
    this.listener = listener;
  }

}
