package hust.soict.hedspi.aims.utils;

public class DateUtils {
    public static int compare(MyDate date1, MyDate date2) {
        if(date1.getYear() < date2.getYear()) {
            return -1;
        }
        if (date1.getYear() > date2.getYear()) {
            return 1;
        }
        if(date1.getMonth() < date2.getMonth()) {
            return -1;
        }
        if (date1.getMonth() > date2.getMonth()) {
            return 1;
        }
        if(date1.getDay() < date2.getDay()) {
            return -1;
        }
        if (date1.getDay() > date2.getDay()) {
            return 1;
        }
        return 0;
    }

    public static void sort(MyDate... date) {
        for (int i = 0; i < date.length; i++) {
            for (int j = i; j < date.length; j++) {
                if(compare(date[i], date[j]) > 0){
                    swap(date[i], date[j]);
                }
            }
        }
    }

    private static void swap(MyDate date1, MyDate date2) {
        int day = date1.getDay();
        int month = date1.getMonth();
        int year = date1.getYear();
        date1.setDate(date2.getDay(), date2.getMonth(), date2.getYear());
        date2.setDate(day, month, year);
    }
}
