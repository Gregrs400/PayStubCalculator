import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);

        String payPeriodStartDate, payPeriodEndDate, payPeriodStartYear, payPeriodEndYear, payPeriodYear;
        double grossPay;

        // user input

            // pay period start date (mm/dd/yyyy)

        do {

            System.out.print("Enter pay period start date (mm/dd/yyyy): ");

            payPeriodStartDate = keyboard.nextLine();

            payPeriodStartYear = payPeriodStartDate.split("/")[2];

            // pay period end date (mm/dd/yyyy)

            System.out.print("Enter pay period end date (mm/dd/yyyy): ");

            payPeriodEndDate = keyboard.nextLine();

            payPeriodEndYear = payPeriodEndDate.split("/")[2];

            if (!payPeriodStartYear.equals(payPeriodEndYear))
                System.out.println("Error. Pay period start and end years must match.");

        }while(!payPeriodStartYear.equals(payPeriodEndYear));

        payPeriodYear = payPeriodStartYear;

            // gross pay

        System.out.print("Enter gross pay: ");

        grossPay = keyboard.nextDouble();

        // add tax brackets for each year in 2D Arrays

        HashMap<String, double[][]> taxBrackets = getTaxBrackets();

        HashMap<String, Double> familyLeaveRates = new HashMap<>();
        familyLeaveRates.put("2022", 0.0014);
        familyLeaveRates.put("2023", 0.0006);
        familyLeaveRates.put("2024",0.0009);
        familyLeaveRates.put("2025", 0.0033);

        double adjustedAnnualWage = calcAdjustedAnnualWage(grossPay, 24);

        double tentativeWithholdingAmount = calcTentativeWithholdingAmount(taxBrackets.get(payPeriodYear), adjustedAnnualWage,
                24);

        System.out.printf("Tentative Withholding Amount: $%.2f\n",tentativeWithholdingAmount);

        generatePayStubCSV(grossPay, tentativeWithholdingAmount, familyLeaveRates.get(payPeriodYear));
        // create csv of pay stub
    }

    private static HashMap<String, double[][]> getTaxBrackets() {

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

        double[][] taxBracket_2025 = new double[][] {
                {0, 6400, 0, 0, 0},
                {6400, 18325, 0, 0.1, 6400},
                {18325, 54875, 1192.50, 0.12, 18325},
                {54875, 109750, 5578.50, 0.22, 54875},
                {109750, 203700, 17651, 0.24, 109750},
                {203700, 256925, 40199, 0.32, 203700},
                {256925, 632750, 57231, 0.35, 256925},
                {632750, -1, 188769.75, 0.37, 632750}
        };

        taxBrackets.put("2025", taxBracket_2025);
        return taxBrackets;

    }

    public static double calcAdjustedAnnualWage(double grossPay, int numOfPayPeriodsPerYear)
    {

        if ((grossPay*numOfPayPeriodsPerYear)-8600 < 0)
            return 0;
        else
            return (grossPay * numOfPayPeriodsPerYear)-8600;

    }

    public static double calcTentativeWithholdingAmount(double[][] taxBracket, double adjustedAnnualWage,
                                                        int numOfPayPeriodsPerYear)
    {

        int columnNum = 0;

        if (adjustedAnnualWage > taxBracket[7][0])
        {

            columnNum = 7;

        }
        else
        {

            while (adjustedAnnualWage >= taxBracket[columnNum][0])
            {

                if (adjustedAnnualWage < taxBracket[columnNum][1])
                    break;
                else
                    columnNum++;

            }

        }

        double columnA = taxBracket[columnNum][0];
        double columnC = taxBracket[columnNum][2];
        double columnDPercentage = taxBracket[columnNum][3];

        return (((adjustedAnnualWage-columnA)*columnDPercentage)+columnC)/numOfPayPeriodsPerYear;

    }

    public static HashMap<String,Double> generatePayStub(double grossPay, double tentativeWithholdingAmount, double familyLeaveRate)
    {

        HashMap<String,Double> payStub = new HashMap<>();
        payStub.put("Federal Tax", tentativeWithholdingAmount);

        System.out.printf("Gross Pay: $%.2f\n", grossPay);

        double fica = (double) Math.round((grossPay*0.062)*100)/100;
        payStub.put("FICA", fica);
        double medicare = (double) Math.round((grossPay*0.0145)*100)/100;
        payStub.put("Medicare", medicare);
        double sui = (double) Math.round((grossPay*0.00425)*100)/100;
        payStub.put("SUI", sui);
        double familyLeaveInsurance = (double) Math.round((grossPay*familyLeaveRate)*100)/100;
        payStub.put("Family Leave Insurance", familyLeaveInsurance);
        double paStateTax = (double) Math.round((grossPay*0.0307)*100)/100;
        payStub.put("PA State Tax", paStateTax);

        double totalDeductions = tentativeWithholdingAmount + fica + medicare + sui + familyLeaveInsurance + paStateTax;

        double netPay = grossPay - totalDeductions;
        payStub.put("Net Pay", netPay);

        System.out.printf("Federal Tax: $%.2f\n", tentativeWithholdingAmount);
        System.out.printf("FICA: $%.2f\n", fica);
        System.out.printf("Medicare: $%.2f\n", medicare);
        System.out.printf("SUI: $%.2f\n", sui);
        System.out.printf("Family Leave Insurance: $%.2f\n", familyLeaveInsurance);
        System.out.printf("PA State Tax: $%.2f\n", paStateTax);
        System.out.printf("Net Pay: $%.2f\n", netPay);

        return payStub;

    }

    public static void generatePayStubCSV(double grossPay, double tentativeWithholdingAmount, double familyLeaveRate)
    {

        HashMap<String,Double> payStub = generatePayStub(grossPay, tentativeWithholdingAmount, familyLeaveRate);

        File payStubCSV = createOrAccessFile("PayStub.csv");

        String fileContents = "";

        fileContents = String.format(fileContents + "Gross Pay" + ",$%.2f\n", grossPay);
        fileContents = String.format(fileContents + "Federal Tax" + ",$%.2f\n", payStub.get("Federal Tax"));
        fileContents = fileContents + "FICA" + "," + payStub.get("FICA") + "\n";
        fileContents = fileContents + "Medicare" + "," + payStub.get("Medicare") + "\n";
        fileContents = fileContents + "SUI" + "," + payStub.get("SUI") + "\n";
        fileContents = fileContents + "Family Leave Insurance" + "," + payStub.get("Family Leave Insurance") + "\n";
        fileContents = fileContents + "PA State Tax" + "," + payStub.get("PA State Tax") + "\n";
        fileContents = fileContents + "Net Pay" + "," + payStub.get("Net Pay");

        writeToFile(payStubCSV, fileContents);

    }

    public static File createOrAccessFile(String filePathAndName) {

        try {

            File file = new File(filePathAndName);

            if (file.createNewFile()) {

                System.out.println("file created");

            } else {

                System.out.println("file already created");

            }

            return file;

        } catch (IOException e) {

            System.out.println("IOException occurred");
            e.printStackTrace();

            return null;

        }

    }

    public static void writeToFile(File file, String text)
    {

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(text);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}
