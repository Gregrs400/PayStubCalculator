import java.util.HashMap;
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

        // add tax brackets for each year in 2D Arrays

        HashMap<String, double[][]> taxBrackets = new HashMap<>();

        double[][] taxBracket_2022 = new double[][] {
                {0, 4350, 0, 0, 0},
                {4350, 14625, 0, 0.1, 4350},
                {14625, 46125, 1027.50, 0.12, 14625},
                {46125, 93425, 4807.50, 0.22, 46125},
                {93425, 174400, 15213.50, 0.24, 93425},
                {174400, 220300, 34647.50, 0.32, 174400},
                {220300, 544250, 49335.50, 0.35, 220300},
                {544250, -1, 162718, 0.37, 544250}
        };

        taxBrackets.put("2022", taxBracket_2022);

        // create csv of pay stub
    }
}
