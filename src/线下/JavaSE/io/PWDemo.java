package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PWDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("pw.txt");
        pw.println("Welcome to the PWR demo!");
        pw.println("Press enter to continue...");
        pw.close();
    }
}
