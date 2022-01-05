package com.finerioconnect.sdk.core.domain.analysis;

import android.os.Parcel;
import android.os.Parcelable;

import com.finerioconnect.sdk.core.domain.CategoryItem;

import java.math.BigDecimal;
import java.util.List;

public class AnalysisSubCategory implements Parcelable {

  private CategoryItem categoryItem;
  private BigDecimal average;
  private Integer quantity;
  private BigDecimal amount;
  private List<AnalysisTransaction> transactions;

  public AnalysisSubCategory() {

  }

  public CategoryItem getCategoryItem() {
    return categoryItem;
  }

  public void setCategoryItem( CategoryItem categoryItem ) {
    this.categoryItem = categoryItem;
  }

  public BigDecimal getAverage() {
    return average;
  }

  public void setAverage( BigDecimal average ) {
    this.average = average;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity( Integer quantity ) {
    this.quantity = quantity;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount( BigDecimal amount ) {
    this.amount = amount;
  }

  public List<AnalysisTransaction> getTransactions() {
    return transactions;
  }

  public void setTransactions( List<AnalysisTransaction> transactions ) {
    this.transactions = transactions;
  }

  protected AnalysisSubCategory( Parcel in ) {

    categoryItem = in.readParcelable( CategoryItem.class.getClassLoader() );
    average = new BigDecimal( in.readString() );

    if ( in.readByte() == 0 ) {
      quantity = null;
    } else {
      quantity = in.readInt();
    }

    amount = new BigDecimal( in.readString() );
    transactions = in.createTypedArrayList( AnalysisTransaction.CREATOR );
  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {

    dest.writeParcelable( categoryItem, flags );
    dest.writeString( average.toString() );

    if ( quantity == null ) {
      dest.writeByte( (byte) 0 );
    } else {
      dest.writeByte( (byte) 1 );
      dest.writeInt( quantity );
    }

    dest.writeString( amount.toString() );
    dest.writeTypedList( transactions );
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<AnalysisSubCategory> CREATOR = new Creator<AnalysisSubCategory>() {
    @Override
    public AnalysisSubCategory createFromParcel( Parcel in ) {
      return new AnalysisSubCategory( in );
    }

    @Override
    public AnalysisSubCategory[] newArray( int size ) {
      return new AnalysisSubCategory[ size ];
    }
  };

}
