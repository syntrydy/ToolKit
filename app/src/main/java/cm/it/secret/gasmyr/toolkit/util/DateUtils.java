package cm.it.secret.gasmyr.toolkit.util;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gasmyr.mougang on 5/31/17.
 */

public class DateUtils {
    @NonNull
    public static SimpleDateFormat getDateFormatter() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @NonNull
    public static SimpleDateFormat getTimeFormatter() {
        return new SimpleDateFormat("hh:mm:ss");
    }

    @NonNull
    public static SimpleDateFormat getFullFormatter() {
        return new SimpleDateFormat("dd.MM.yyyy '-' hh:mm:ss");
    }

    @NonNull
    public static SimpleDateFormat getCodeFormatter() {
        return new SimpleDateFormat("ddMMyyyyhhmmss");
    }

    public static boolean isTimeForHelloDay(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 7 && timeOfDay <=8 ){
            return true;
        }
        return false;
    }
    public static boolean isTimeForHelloAfternoon(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 13 && timeOfDay <=14 ){
            return true;
        }
        return false;
    }
    public static boolean isTimeForHelloNight(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 20 && timeOfDay <=21 ){
            return true;
        }
        return false;
    }
    public static boolean isMorning(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 8 && timeOfDay <= 10){
            return true;
        }
        return false;
    }
    public static boolean isAfternoon(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 12 && timeOfDay < 16){
            return true;
        }
        return false;
    }
    public static boolean isEvening(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 16 && timeOfDay < 21){
            return true;
        }
        return false;
    }
    public static boolean isNight(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 21 && timeOfDay < 24){
            return true;
        }
        return false;
    }
    public static String getCopyright(){
        return " Par GASMYR MOUGANG @compute at "+DateUtils.getFullFormatter().format(new Date());
    }
    public static String getCopyrightCode(){
        return " Par GASMYR MOUGANG @code "+DateUtils.getCodeFormatter().format(new Date());
    }
    public static String getCode(){
        return DateUtils.getCodeFormatter().format(new Date());
    }
}
