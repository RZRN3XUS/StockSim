/*
Christopher Dycus
5/16/2019
Object class for Stock Sim
 */
import java.util.*;
public class Market {
    private static double dayPrices[] = new double[20];
    private static double bankBalance;
    private double portfolioValue = 0;
    public static boolean portBool= true;

    public static void setDayPrices(double dayPrices0, double dayPrices1, double dayPrices2, double dayPrices3, double dayPrices4, double dayPrices5, double dayPrices6, double dayPrices7, double dayPrices8, double dayPrices9, double dayPrices10, double dayPrices11, double dayPrices12, double dayPrices13, double dayPrices14, double dayPrices15, double dayPrices16, double dayPrices17, double dayPrices18, double dayPrices19) {
        dayPrices[0] = dayPrices0;
        dayPrices[1] = dayPrices1;
        dayPrices[2] = dayPrices2;
        dayPrices[3] = dayPrices3;
        dayPrices[4] = dayPrices4;
        dayPrices[5] = dayPrices5;
        dayPrices[6] = dayPrices6;
        dayPrices[7] = dayPrices7;
        dayPrices[8] = dayPrices8;
        dayPrices[9] = dayPrices9;
        dayPrices[10] = dayPrices10;
        dayPrices[11] = dayPrices11;
        dayPrices[12] = dayPrices12;
        dayPrices[13] = dayPrices13;
        dayPrices[14] = dayPrices14;
        dayPrices[15] = dayPrices15;
        dayPrices[16] = dayPrices16;
        dayPrices[17] = dayPrices17;
        dayPrices[18] = dayPrices18;
        dayPrices[19] = dayPrices19;
    }

    public static int setStocksOwned(int quantity, int stocksOwned) {
        return (int) (quantity * dayPrices[stocksOwned]*100);
    }

    public void setBankBalance(double bankBalance1) { //sets wanted balance
        bankBalance = bankBalance1;
    }

    public void getBankBalance() { //outputs current bank balance
        System.out.print("Current bank balance: $");
        System.out.printf("%.2f", bankBalance);
        System.out.println();
    }
    public static double getBankBalanceValue(){
        return bankBalance;
    }

    public void getDayPrices() { //scrambles day prices from -10% to +10%
        Random scrambler = new Random();
            for (int i = 0; i <= 19; i++) {
            double scrambleDouble = scrambler.nextDouble() * 20 - 10;
            dayPrices[i] = dayPrices[i] * (1 + (scrambleDouble * 0.01));
            System.out.print("Stock " + (i + 1) + ": ");
            System.out.printf("%.2f", dayPrices[i]);
            System.out.print(" and is ");
            if (scrambleDouble > 0) {
                System.out.println("up " + Math.round(scrambleDouble) + "%");
            } else if (scrambleDouble < 0) {
                System.out.println("down " + Math.round(Math.abs(scrambleDouble)) + "%");
            }
        }
    }

    public void buyStock(int stockNumber, int quantity) {
        if (stockNumber <= 20 && stockNumber > 0) {
            if (bankBalance > dayPrices[stockNumber - 1] * quantity) {
                bankBalance = bankBalance - (dayPrices[stockNumber - 1] * quantity);
            } else {
                System.err.println("Not enough balance");
                portBool = false;
            }
        } else{
            System.err.println("Invalid stock number");
            portBool = false;
        }
    }

    public void sellStock(int stockNumber, int quantity) {
        if (stockNumber <= 20 && stockNumber > 0) {
                bankBalance = bankBalance + (dayPrices[stockNumber - 1] * quantity);
            } else {
                System.err.println("Invalid stock number");
            }
        }
    public void stocksOwned(int quantity, int whichStock){
            System.out.println("You own " + quantity + " stocks of Stock " + (whichStock+1));
        }
    }



