package com.finerioconnect.sdk.core.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Category implements FinerioConnectDomain, Parcelable {

  private String id;
  private String parentId;
  private String name;
  private String color;
  private String textColor;
  private List<Category> subCategories;

  public Category() {

  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId( String id ) {
    this.id = id;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId( String parentId ) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor( String color ) {
    this.color = color;
  }

  public String getTextColor() {
    return textColor;
  }

  public void setTextColor( String textColor ) {
    this.textColor = textColor;
  }

  public List<Category> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories( List<Category> subCategories ) {
    this.subCategories = subCategories;
  }

  protected Category( Parcel in ) {

    id = in.readString();
    name = in.readString();
    color = in.readString();
    textColor = in.readString();
    subCategories = in.createTypedArrayList( Category.CREATOR );

  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {

    dest.writeString( id );
    dest.writeString( name );
    dest.writeString( color );
    dest.writeString( textColor );
    dest.writeTypedList( subCategories );

  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Category> CREATOR = new Creator<Category>() {
    @Override
    public Category createFromParcel( Parcel in ) {
      return new Category( in );
    }

    @Override
    public Category[] newArray( int size ) {
      return new Category[ size ];
    }
  };

}
