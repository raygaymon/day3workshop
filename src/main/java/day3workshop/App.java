package day3workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        // first argument is the directory to access or create
        String dirPath = args[0];

        // file class and object is to check if they exits and if no to make a new one

        File pathFinder = new File(dirPath);
        if (pathFinder.exists()) {
            System.out.println("Pathway " + dirPath + " present LFG");
        } else {
            pathFinder.mkdir();
        }

        // list of carts because everyone gets their own cart depending on user
        List<String> stuffInCart = new ArrayList<String>();

        Console cons = System.console();

        String input = "";

        System.out.println("Welcome to Shopee Lazada");

        String loginuser = "";

        String loginTime = cons.readLine("Please enter login information in this format:\n" + "login <username>\n");

        if (loginTime.startsWith("login")) {
            Scanner scan = new Scanner(loginTime.substring(6));

            while (scan.hasNext()) {
                loginuser = scan.next();
                File loginInfo = new File(dirPath + File.separator + loginuser);
                if (loginInfo.exists()) {
                    System.out.println(
                            "User " + loginuser + " login successful. Please spend lots of money with us!");
                } else {
                    String createAcc = cons.readLine(
                            "Seems like you're not a user. Would you like to create an account? It's free. \nYes/No\n");
                    if (createAcc.toLowerCase().equals("yes")) {
                        loginInfo.createNewFile();
                        System.out.println("New User created. Enjoy Shopee Lazada!");
                    } else {
                        System.out.println("Awh okay then :(");
                    }

                }

            }
        }

        while (!input.equals("quit")) {
            input = cons.readLine("How can we help you today?: ");

            // to check if the loginuser file exists
            // if it doesnt exist create a new one

            if (input.equals("users")) {
                File directoryPath = new File(dirPath);

                String[] directoryListing = pathFinder.list();
                System.out.println("List of files in directory in folder " + pathFinder + ": ");
                for (String directories : directoryListing) {
                    System.out.println(directories);
                }
            }

            else if (input.startsWith("add")) {
                input = input.replace(",", " ");

                FileWriter fw = new FileWriter(dirPath + File.separator + loginuser, true);
                PrintWriter pw = new PrintWriter(fw);

                String currentScan = "";
                Scanner cartscanner = new Scanner(input.substring(4));

                while (cartscanner.hasNext()) {
                    currentScan = cartscanner.next();
                    stuffInCart.add(currentScan);
                    pw.write(currentScan);
                }

                pw.flush();
                pw.close();
                fw.close();

                System.out.println(input.substring(4) + " has been added to your cart.");
            }

            else if (input.equals("list")) {
                // need file calss and bufferedreader to read cart items from file
                File readCart = new File(dirPath + File.separator + loginuser);
                BufferedReader br = new BufferedReader(new FileReader(readCart));

                String readCartInput = "";

                stuffInCart = new ArrayList<String>();

                // use while loop to read through items in file

                System.out.println("Your cart contains: \n");

                while ((readCartInput = br.readLine()) != null) {
                    System.out.println(readCartInput);
                    stuffInCart.add(readCartInput);
                }
                // exit loop and close reader

                br.close();

                System.out.println("\nCheckout within the next 5 minutes for 10% off!");
            }

            else if (input.startsWith("delete")) {
                String[] stringValue = input.split(" ");
                // stringVal [1] can act as delete also, stringVal [0] would be "delete"
                // delte from index stringVal [1] onwards
                // can also start from input.substring(7) since items to delete start from index
                // 7 onwards

                int thingToDelete = Integer.parseInt(stringValue[1]);
                if (thingToDelete < stuffInCart.size()) {
                    stuffInCart.remove(thingToDelete - 1);
                    FileWriter fw2 = new FileWriter(dirPath + File.separator + loginuser, false);
                    BufferedWriter bw2 = new BufferedWriter(fw2);

                    int listStuff = 0;
                    while (listStuff < stuffInCart.size()) {
                        bw2.write(stuffInCart.get(listStuff));
                        bw2.newLine();
                        listStuff++;
                    }

                    bw2.flush();
                    bw2.close();
                    fw2.close();

                    System.out.println(input.substring(7) + " has been removed");

                } else {
                    System.out.println("There's not that many items in your cart boy delete what");
                }
            }

            else {
                System.out.println("WTF you tryna do sis");
            }
        }
        if (input.equals("quit")) {
            System.out.println("See you again! Have you bought stuff?");
        }
    }
}
