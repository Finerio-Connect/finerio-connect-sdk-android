package com.finerioconnect.sdk.core.domain.analysis;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class AnalysisTransaction implements Parcelable {

  private String description;
  private BigDecimal average;
  private Integer quantity;
  private BigDecimal amount;

  public AnalysisTransaction() {

  }

  public String getDescription() {
    return description;
  }

  public void setDescription( String description ) {
    this.description = description;
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

  protected AnalysisTransaction( Parcel in ) {

    description = in.readString();
    average = new BigDecimal( in.readString() );

    if ( in.readByte() == 0 ) {
      quantity = null;
    } else {
      quantity = in.readInt();
    }

    amount = new BigDecimal( in.readString() );

  }

  @Override
  public void writeToParcel( Parcel dest, int flags ) {

    dest.writeString( description );
    dest.writeString( average.toString() );

    if ( quantity == null ) {
      dest.writeByte( (byte) 0 );
    } else {
      dest.writeByte( (byte) 1 );
      dest.writeInt( quantity );
    }

    dest.writeString( amount.toString() );

  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<AnalysisTransaction> CREATOR = new Creator<AnalysisTransaction>() {
    @Override
    public AnalysisTransaction createFromParcel( Parcel in ) {
      return new AnalysisTransaction( in );
    }

    @Override
    public AnalysisTransaction[] newArray( int size ) {
      return new AnalysisTransaction[ size ];
    }
  };

}
