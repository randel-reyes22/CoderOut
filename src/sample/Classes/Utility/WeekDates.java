package sample.Classes.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;

public class WeekDates {

    //sets the calendar to current date and time
    public static LinkedList<String> dates = new LinkedList<>();
    private static String monday;
    private static String sunday;

    public static String getMonday() {
        GetDates();
        return monday;
    }

    public static String getSunday() {
        return sunday;
    }


    private static void GetDates() {
        Calendar date = Calendar.getInstance();
        //sets the calendar with starting day of week
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        //printing of first and last day of th week
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        monday = dateformat.format(date.getTime());

        for (int i = 0; i < 6; i++) {
            date.add(Calendar.DATE, 1);
        }
        sunday = dateformat.format(date.getTime());
    }

    public void GetAllWeekDates(){
        Calendar date = Calendar.getInstance();
        //clear the values
        dates.clear();

        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        //printing of first and last day of th week
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {
            date.add(Calendar.DATE, 1);
            //add to the linklist
            dates.add(dateformat.format(date.getTime()));
        }

    }

    public static String DateNow(){
        //get the current date today
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

}
