
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
public class DetailsBank{
    private static String FILE_NAME = "bank.txt";
    private static void displayMenu(){
        System.out.println("Bank Management System");
        System.out.println("----------------------");
        System.out.println("1. Create an account");
        System.out.println("2. Display account details");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Exit");
        System.out.println("----------------------");
        System.out.print("Enter your choice: ");
    }
    private static void createAccount(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME,true));
            //FileWriter writer = new FileWriter(FILE_NAME);
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();
            System.out.print("Enter account holder name: ");
            String accountHolderName = sc.nextLine();
            System.out.print("Enter initial balance: ");
            double balance = sc.nextDouble();
            writer.println(accountHolderName+","+accountNumber+","+balance);
            System.out.println("Account successfully created");
            writer.close();
        }
        catch(IOException e){
            e.getMessage();
        }
    }
    private static void displayAccountDetails(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                System.out.println("Account Holder: " + accountData[0]);
                System.out.println("Account Number: " + accountData[1]);
                System.out.println("Balance: Rs" + accountData[2]);
                System.out.println("----------------------");

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error occurred while displaying account details.");
        }
    }
    private static void depositMoney(){

        try{
            BufferedReader r = new BufferedReader(new FileReader(FILE_NAME));
            //FileReader reader = new FileReader("bank.txt");
            StringBuilder fileContents = new StringBuilder();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();

            String l;
            boolean accountFound = false;
            while((l = r.readLine()) != null) {
                String[] accountData = l.split(",");
                if (accountData[1].equals(accountNumber)) {
                    double balance = Double.parseDouble(accountData[2]);
                    balance += amount;
                    accountData[2] = String.valueOf(balance);
                    accountFound = true;
                }
                fileContents.append(String.join(",", accountData)).append("\n");
            }

            if (accountFound) {
                try  {
                    //PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME,true));
                    //writer.print(fileContents);
                    BufferedWriter wr = Files.newBufferedWriter(Paths.get(FILE_NAME));
                    String s1 = fileContents.toString();
                    //System.out.println(s1);
                    wr.write(s1);
                    wr.flush();
                    System.out.println("Amount deposited successfully!");
                }
                catch (IOException e) {
                    System.out.println("Error occurred while depositing money.");
                }
            }
            else{
                System.out.println("Account not found!");
            }
            r.close();
        }
        catch(IOException e){
            System.out.println("Error occurred while depositing money.");
        }
    }
    private static void withdrawMoney(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            StringBuilder fileContents = new StringBuilder();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            String line;
            boolean accountFound = false;
            while((line = reader.readLine()) != null){
                String[] accountData = line.split(",");
                if(accountData[1].equals(accountNumber)){
                    double balance = Double.parseDouble(accountData[2]);
                    if(balance >= amount){
                        balance -= amount;
                        accountData[2] = String.valueOf(balance);
                        accountFound = true;
                    }
                    else{
                        System.out.println("Insufficient balance!");
                    }

                }
                fileContents.append(String.join(",",accountData)).append("\n");
            }
            //System.out.println(fileContents);
            if(accountFound){
                try{
                    //RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw");
                    //FileWriter fl = new FileWriter("bank.txt",true);
                    //raf.seek(0);
                    //PrintWriter fl = new PrintWriter((new FileWriter(FILE_NAME,true)));
                    BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME));
                    String s = fileContents.toString();
                    //System.out.println(s);
                    writer.write(s);
                    writer.flush();

                    //fl.write(s);
                    System.out.println("amount withdrawn successful!");
                }
                catch(IOException e){
                    System.out.println("error occured");
                }
            }
            else{
                System.out.println("account not found!");
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Error occurred while withdrawing money.");
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int choice;
        do{
            displayMenu();
            choice = s.nextInt();
            switch(choice){
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAccountDetails();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }while(choice != 5);
    }

}