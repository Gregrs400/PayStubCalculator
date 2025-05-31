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

            // pay period end date (mm/dd/yyyy)

        System.out.print("Enter pay period end date (mm/dd/yyyy): ");

        payPeriodEndDate = keyboard.nextLine();

            // gross pay

        System.out.print("Enter gross pay: ");

        grossPay = keyboard.nextDouble();

        // create csv of pay stub
    }
}
