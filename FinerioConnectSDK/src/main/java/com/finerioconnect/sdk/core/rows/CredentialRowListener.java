package com.finerioconnect.sdk.core.rows;

import com.finerioconnect.sdk.core.domain.ManualAccount;

interface CredentialRowListener {

  public void onCredentialClicked(CredentialRow credentialRow );
  public void onManualAccountClicked( ManualAccount manualAccount );
  public void onUpdateButtonClicked( CredentialRow credentialRow );
  public void onCredentialUpdated( CredentialRow credentialRow );
  public void onManualAccountUpdated( ManualAccount manualAccount );
  public void onDeleteButtonClicked( CredentialRow credentialRow );
  public void onDeleteButtonClicked( ManualAccount manualAccount );
  public void onCredentialDeleted(CredentialRow credentialRow );
  public void onManualAccountDeleted( ManualAccount manualAccount );

}
