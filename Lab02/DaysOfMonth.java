import java.util.Scanner;

public class DaysOfMonth {
    public static final String[] numberic = {
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12"
    };
    public static final String[] three_letters = {
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    };
    public static final String[] abbreviation = {
        "Jan.",
        "Feb.",
        "Mar.",
        "Apr.",
        "May",
        "June",
        "July",
        "Aug.",
        "Sept.",
        "Oct.",
        "Nov.",
        "Dec."
    };
    public static final String[] full_name = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    };
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String month;
        int num_month = 0;
        int year;

        do {
            System.out.print("Enter month: ");
            month = scanner.nextLine();
            for(int i = 0; i < 12; i++) {
                if(month.equals(numberic[i]) || month.equals(three_letters[i]) || month.equals(abbreviation[i]) || month.equals(full_name[i])) {
                    num_month = i + 1;
                    break;
                }
            }
        } while (num_month == 0);

        do {
            System.out.print("Enter year: ");
            year = scanner.nextInt();
        } while (year < 0);

        switch (num_month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("31 days.");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("30 days.");
                break;
            case 2:
                if(year%4 == 0){
                    if((year%100 == 0) && (year%400 != 0)) {
                        System.out.println("28 days.");
                    } else {
                        System.out.println("29 days.");
                    }
                } else {
                    System.out.println("28 days.");
                }
                break;
        }

        scanner.close();
    }
}