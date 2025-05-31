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

        double[][] taxBracket_2023 = new double[][] {
                {0, 5250, 0, 0, 0},
                {5250, 16250, 0, 0.1, 5250},
                {16250, 49975, 1100, 0.12, 16250},
                {49975, 100625, 5147, 0.22, 49975},
                {100625, 187350, 16290, 0.24, 100625},
                {187350, 236500, 37104, 0.32, 187350},
                {236500, 583375, 52832, 0.35, 236500},
                {583375, -1, 174238.25, 0.37, 583375}
        };

        taxBrackets.put("2023", taxBracket_2023);

        double[][] taxBracket_2024 = new double[][] {
                {0, 6000, 0, 0, 0},
                {6000, 17600, 0, 0.1, 6000},
                {17600, 53150, 1160, 0.12, 17600},
                {53150, 106525, 5426, 0.22, 53150},
                {106525, 197950, 17168.50, 0.24, 106525},
                {197950, 249725, 39110.50, 0.32, 197950},
                {249725, 615350, 55678.50, 0.35, 249725},
                {615350, -1, 183647.25, 0.37, 615350}
        };

        taxBrackets.put("2024", taxBracket_2024);

        // create csv of pay stub
    }
}
