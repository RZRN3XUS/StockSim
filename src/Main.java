/*
Christopher Dycus
5/14/2019
Stock Simulator
    Runs through day cycles where stocks are randomized
    Market Class will have options for each day
 */
import java.util.*;
public class Main {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
        // making randoms for new stock prices
        Market prices = new Market();
        Random random0 = new Random();
        int days;
        boolean inputCheck = true;
        int stocksOwned[] = new int[20];
            for (int i = 0; i < 19; i++) {
                stocksOwned[i] = 0;
        }
        boolean day = true;
        double bankBalance = 1.0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Stock Market Simulator");
        System.out.println("What is your starting bank balance? ($xx.xx 1-999999)");
        while (inputCheck) { //checks for normal starting value
            bankBalance = sc.nextDouble();
            if (bankBalance > 1000000 || bankBalance <= 0)
               System.err.println("Invalid Input");
            else
            inputCheck = false;
        }
        prices.setBankBalance(bankBalance);
        System.out.println("What is the amount of business days you want to simulate?");
        days = sc.nextInt();
        prices.setDayPrices(Math.abs(random0.nextInt(100) * 0.01), Math.abs(random0.nextInt(400) * 0.01), Math.abs(random0.nextInt(500) * 0.01), Math.abs(random0.nextInt(1000) * 0.01), Math.abs(random0.nextInt(20000) * 0.01), Math.abs(random0.nextInt(20000) * 0.01), Math.abs(random0.nextInt(50000) * 0.01), Math.abs(random0.nextInt(600000) * 0.01), Math.abs(random0.nextInt(100000) * 0.01), Math.abs(random0.nextInt(200000) * 0.01), Math.abs(random0.nextInt(100000)) * 0.01, Math.abs(random0.nextInt(500000) * 0.01), Math.abs(random0.nextInt(50000) * 0.01), Math.abs(random0.nextInt(6969) * 0.01), Math.abs(random0.nextInt(999999) * 0.01), Math.abs(random0.nextInt(666666) * 0.01), Math.abs(random0.nextInt(50000) * 0.01), Math.abs(random0.nextInt(250000) * 0.01), Math.abs(random0.nextInt(900000) * 0.01), Math.abs(random0.nextInt(1000) * 0.01));
        for (int dayCount = 0; dayCount < days; dayCount++) { // counts days until number of days requested is met (master loop for day cycle)
            System.out.println("Day " + (dayCount + 1));
            prices.getDayPrices();
            day = true;


            String action = "buy";
            while (day) {
                int placeholder = 0;
                double portfolioValue = 0;
                for (int i = 0; i<=19; i++){
                    placeholder = (placeholder + prices.setStocksOwned(stocksOwned[i], i));
                }
                System.out.println();
                prices.getBankBalance();
                if (placeholder < 0)
                    placeholder = 0;
                System.out.print("Portfolio Value: $");
                portfolioValue = placeholder*0.01;
                System.out.printf("%.2f", portfolioValue);
                System.out.println();
                System.out.println("What action would you like to do? (Buy [b], Sell [s], Check balance [c], Check stocks owned [p], Check day prices [d], End Day [e])");
                action = sc.next();
                if (action.equalsIgnoreCase("b")) { //buying stock
                    System.out.println("What stock would you like to buy? (Stock #)");
                    int stockNumber = sc.nextInt();
                    System.out.println("How many of Stock " + stockNumber + " would you like to buy?");
                    int quantity = sc.nextInt();
                    if (quantity <0)
                        System.err.println("Invalid Input");
                    else
                    prices.buyStock(stockNumber, quantity);
                    if (prices.portBool)
                    stocksOwned[stockNumber-1] = quantity;
                    prices.portBool = true;
                } else if (action.equalsIgnoreCase("s")) { //selling stock
                    System.out.println("What stock would you like to sell?");
                    int stockNumber = sc.nextInt();
                    if (stocksOwned[stockNumber - 1] > 0) {
                        System.out.println("How much of this stock would you like to sell?");
                        int quantity = sc.nextInt();
                        if (quantity < 0)
                            System.err.println("Invalid Input");
                        else {
                            if (quantity <= stocksOwned[stockNumber - 1]) {
                                prices.sellStock(stockNumber, stocksOwned[stockNumber - 1]);
                                stocksOwned[stockNumber-1] = stocksOwned[stockNumber-1] - quantity;
                            } else
                                System.err.println("You don't own that much of this stock.");
                        }
                    }
                            else
                        System.err.println("You don't own enough of this stock.");
                    }
                else if (action.equalsIgnoreCase("e")) { //end day
                    day = false;}
                else if (action.equalsIgnoreCase("d")) { //get day prices
                    prices.getDayPrices();
                } else if(action.equalsIgnoreCase("c")){ //check bank balance
                    prices.getBankBalance();
                } else if(action.equalsIgnoreCase("p")){ //check stocks owned
                    for (int i = 0; i<=19; i++)
                    prices.stocksOwned(stocksOwned[i], i);
                }
                else {
                    System.err.println("Invalid action");
                }
                }
            }
        double finalBalanceProfitPercent = prices.getBankBalanceValue()/bankBalance;
        double finalBalanceProfit = prices.getBankBalanceValue() - bankBalance;
            System.out.println("Your final balance was $" + prices.getBankBalanceValue());
            System.out.println("Your profit margin was " + Math.round(finalBalanceProfitPercent*100));
            System.out.println("Your total profit was $" + finalBalanceProfit);
            int placeholder = 0;
        for (int i = 0; i<=19; i++){
            placeholder = (placeholder + prices.setStocksOwned(stocksOwned[i], i));
        }
        double portfolioValue = placeholder*.01;
            System.out.println("Your portfolio was valued at $" +portfolioValue);
            for (int i = 0; i <=19; i++)
            prices.stocksOwned(stocksOwned[i], i);
        }
    }

