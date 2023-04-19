package day3workshop;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
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

        while (!input.equals("quit")) {
            input = cons.readLine("How can we help you today?: ");

            if (input.startsWith("login")) {
                Scanner scan = new Scanner(input.substring(6));

                while (scan.hasNext()) {
                    loginuser = scan.next();
                    File loginInfo = new File(dirPath + File.separator + loginuser);
                    if (loginInfo.exists()) {
                        System.out.println("User " + loginuser + " already exists");
                    } else {
                        loginInfo.createNewFile();
                        System.out.println("this works");
                    }

                }
            }

                // to check if the loginuser file exists
                // if it doesnt exist create a new one

                if (input.equals("users")) {
                    File directoryPath = new File(dirPath);

                    String[] directoryListing = pathFinder.list();
                    System.out.println("List of files in directory in folder" + pathFinder + ": ");
                    for (String directories : directoryListing) {
                        System.out.println(directories);
                    }

                    // use filewriter to write in login user info

                }

                if (input.startsWith("add")) {
                    input = input.replace(",", " ");

                    FileWriter fw = new FileWriter(dirPath + File.separator + loginuser);
                    PrintWriter pw = new PrintWriter(fw);

                    String currentScan = "";
                    Scanner cartscanner = new Scanner(input.substring(4));

                    int i = 0;

                    System.out.println("sfgjsighsfofsdoif");

                    while (cartscanner.hasNext()) {
                        i += 1;
                        currentScan = cartscanner.next();
                        stuffInCart.add(currentScan);
                        pw.write("\n" + i + ". " + currentScan);
                    }

                    pw.flush();
                    pw.close();
                    fw.close();

                    System.out.println(input.substring(4) + " has been added to your cart.");
                }
            }
        }

    }

