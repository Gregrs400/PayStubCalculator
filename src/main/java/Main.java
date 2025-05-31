import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);

        String payPeriodStartDate, payPeriodEndDate;
        double grossPay;

        // user input

            // pay period start date (mm/dd/yyyy)

        System.out.print("Enter pay period start date (mm/dd/yyyy): ");

        payPeriodStartDate = keyboard.nextLine();

        System.out.println(payPeriodStartDate);

            // pay period end date (mm/dd/yyyy)

        System.out.print("Enter pay period end date (mm/dd/yyyy): ");

        payPeriodEndDate = keyboard.nextLine();

        System.out.println(payPeriodEndDate);

            // gross pay

        System.out.print("Enter gross pay: ");

        grossPay = keyboard.nextDouble();

        System.out.printf("$%.2f", grossPay);

        // create csv of pay stub
    }
}
