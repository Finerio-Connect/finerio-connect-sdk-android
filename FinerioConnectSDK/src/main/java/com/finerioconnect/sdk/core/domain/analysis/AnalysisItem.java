package com.finerioconnect.sdk.core.domain.analysis;

import java.util.Date;
import java.util.List;

public class AnalysisItem {

  private Date date;
  private List<AnalysisCategory> categories;

  public Date getDate() {
    return date;
  }

  public void setDate( Date date ) {
    this.date = date;
  }

  public List<AnalysisCategory> getCategories() {
    return categories;
  }

  public void setCategories( List<AnalysisCategory> categories ) {
    this.categories = categories;
  }

}
