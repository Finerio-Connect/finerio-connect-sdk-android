package com.finerioconnect.sdk.core.rows;

import com.finerioconnect.sdk.core.domain.Budget;
import com.finerioconnect.sdk.core.domain.FinerioConnectImage;

public class BudgetRow extends FinerioConnectImage {

  private Budget budget;
  private BudgetRowListener listener;

  public Budget getBudget() {
    return budget;
  }

  public void setBudget( Budget budget ) {
    this.budget = budget;
  }

  public BudgetRowListener getListener() {
    return listener;
  }

  public void setListener( BudgetRowListener listener ) {
    this.listener = listener;
  }

}
