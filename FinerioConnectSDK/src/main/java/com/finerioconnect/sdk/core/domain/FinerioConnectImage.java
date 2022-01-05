package com.finerioconnect.sdk.core.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class FinerioConnectImage implements Parcelable {

  private String imageUrl;
  private String imageFile;
  private Integer imageResource;

  public FinerioConnectImage() {

  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl( String imageUrl ) {
    this.imageUrl = imageUrl;
  }

  public String getImageFile() {
    return imageFile;
  }

  public void setImageFile( String imageFile ) {
    this.imageFile = imageFile;
  }

  public Integer getImageResource() {
    return imageResource;
  }

  public void setImageResource( Integer imageResource ) {
    this.imageResource = imageResource;
  }

  protected FinerioConnectImage( Parcel in ) {
    imageUrl = in.readString();
    imageFile = in.readString();
    if ( in.readByte() == 0 ) {
      imageResource = null;
    } else {
      imageResource = in.readInt();
    }
  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {
    dest.writeString( imageUrl );
    dest.writeString( imageFile );
    if ( imageResource == null ) {
      dest.writeByte( (byte) 0 );
    } else {
      dest.writeByte( (byte) 1 );
      dest.writeInt( imageResource );
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<FinerioConnectImage> CREATOR = new Creator<FinerioConnectImage>() {
    @Override
    public FinerioConnectImage createFromParcel( Parcel in ) {
      return new FinerioConnectImage( in );
    }

    @Override
    public FinerioConnectImage[] newArray( int size ) {
      return new FinerioConnectImage[ size ];
    }
  };

}
