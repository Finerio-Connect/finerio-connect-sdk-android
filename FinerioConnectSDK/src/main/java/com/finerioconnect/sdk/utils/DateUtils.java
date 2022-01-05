package com.finerioconnect.sdk.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
  private static final String YEAR_MONTH_DATE_FORMAT = "yyyy-MM";

  public static String formatDate( Date date ) {

    DateFormat format = new SimpleDateFormat( DATE_FORMAT, Locale.US );
    return format.format( date );

  }

  public static Date parseDate( String date ) {

    DateFormat format = new SimpleDateFormat( DATE_FORMAT, Locale.US );

    try {
      return format.parse( date );
    } catch ( ParseException e ) {
      return null;
    }

  }

  public static Date parseShortDate( String date ) {

    DateFormat format = new SimpleDateFormat( YEAR_MONTH_DATE_FORMAT, Locale.US );

    try {
      return format.parse( date );
    } catch ( ParseException e ) {
      return null;
    }

  }

  public static String getLocaleShortMonth( Date date ) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime( date );
    return new DateFormatSymbols().getShortMonths()[
        calendar.get( Calendar.MONTH ) ];

  }

  public static String getLocaleMonth( Date date ) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime( date );
    return new DateFormatSymbols().getMonths()[
        calendar.get( Calendar.MONTH ) ];

  }

  public static int getYear( Date date ) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime( date );
    return calendar.get( Calendar.YEAR );

  }

  public static Date dropTimeFromDate( Date date ) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime( date );
    calendar.set( Calendar.HOUR_OF_DAY, 0 );
    calendar.set( Calendar.MINUTE, 0 );
    calendar.set( Calendar.SECOND, 0 );
    calendar.set( Calendar.MILLISECOND, 0 );
    return calendar.getTime();

  }

}
