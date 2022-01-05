package com.finerioconnect.sdk.core.views;

import android.view.View;

import java.io.File;
import java.net.URL;

public abstract class FinerioConnectView {

  private URL animationUrl;
  private File animationFile;
  private View emptyState;

  public URL getAnimationUrl() {
    return animationUrl;
  }

  public void setAnimationUrl( URL animationUrl ) {
    this.animationUrl = animationUrl;
  }

  public File getAnimationFile() {
    return animationFile;
  }

  public void setAnimationFile( File animationFile ) {
    this.animationFile = animationFile;
  }

  public View getEmptyState() {
    return emptyState;
  }

  public void setEmptyState( View emptyState ) {
    this.emptyState = emptyState;
  }

  public abstract void setLoading( Boolean loading );

}
