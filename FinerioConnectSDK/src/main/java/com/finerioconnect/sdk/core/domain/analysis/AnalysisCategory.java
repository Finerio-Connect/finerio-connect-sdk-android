package com.finerioconnect.sdk.core.domain.analysis;

import android.os.Parcel;
import android.os.Parcelable;

import com.finerioconnect.sdk.core.domain.CategoryItem;

import java.math.BigDecimal;
import java.util.List;

public class AnalysisCategory implements Parcelable {

  private CategoryItem categoryItem;
  private BigDecimal amount;
  private List<AnalysisSubCategory> subCategories;
  private AnalysisCategoryListener listener;

  public AnalysisCategory() {

  }

  public CategoryItem getCategoryItem() {
    return categoryItem;
  }

  public void setCategoryItem( CategoryItem categoryItem ) {
    this.categoryItem = categoryItem;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public List<AnalysisSubCategory> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories( List<AnalysisSubCategory> subCategories ) {
    this.subCategories = subCategories;
  }

  public AnalysisCategoryListener getListener() {
    return listener;
  }

  public void setListener( AnalysisCategoryListener listener ) {
    this.listener = listener;
  }

  protected AnalysisCategory( Parcel in ) {

    categoryItem = in.readParcelable( CategoryItem.class.getClassLoader() );
    amount = new BigDecimal( in.readString() );
    subCategories = in.createTypedArrayList( AnalysisSubCategory.CREATOR );

  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {

    dest.writeParcelable( categoryItem, flags );
    dest.writeString( amount.toString() );
    dest.writeTypedList( subCategories );

  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<AnalysisCategory> CREATOR = new Creator<AnalysisCategory>() {
    @Override
    public AnalysisCategory createFromParcel( Parcel in ) {
      return new AnalysisCategory( in );
    }

    @Override
    public AnalysisCategory[] newArray( int size ) {
      return new AnalysisCategory[ size ];
    }
  };

}
