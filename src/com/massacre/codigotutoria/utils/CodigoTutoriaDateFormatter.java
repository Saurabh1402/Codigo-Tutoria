package com.massacre.codigotutoria.utils;

import org.apache.log4j.lf5.util.DateFormatManager;

import java.text.*;
import java.util.Date;

/**
 * Created by saurabh on 28/7/17.
 */
public class CodigoTutoriaDateFormatter extends DateFormat {
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return null;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return null;
    }

    @Override
    public Date parse(String source) throws ParseException {
        return new SimpleDateFormat("YYYY-MM-DD").parse(source);
    }

    public String formatYYYYMMDD(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);

    }
}
