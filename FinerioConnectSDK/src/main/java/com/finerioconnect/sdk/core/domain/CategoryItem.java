package com.finerioconnect.sdk.core.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryItem extends FinerioConnectImage implements Parcelable {

  private Category category;

  public CategoryItem() {
    super();
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory( Category category ) {
    this.category = category;
  }

  protected CategoryItem( Parcel in ) {
    super( in );
    category = in.readParcelable( Category.class.getClassLoader() );
  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {
    super.writeToParcel( dest, flags );
    dest.writeParcelable( category, flags );
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<CategoryItem> CREATOR = new Creator<CategoryItem>() {

    @Override
    public CategoryItem createFromParcel( Parcel in ) {
      return new CategoryItem( in );
    }

    @Override
    public CategoryItem[] newArray( int size ) {
      return new CategoryItem[ size ];
    }
  };

}
