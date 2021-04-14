/*
* Author: None_id
* Date: 2021-04-14
*/

import tools.HammingCode2Info;
import tools.Info2HammingCode;

import java.util.Scanner;

public class HammingCodeTools {
    public static void main(String[] args) {
        System.out.printf("------------Menu------------\n"
                + "1. Convert binary number to Hamming Code\n"
                + "2. Check Hamming Code and Convert to binary number\n"
                + "----------------------------\n"
                + "Your choice: ");

        Scanner tempScanner = new Scanner(System.in);
        int choice = tempScanner.nextInt();

        System.out.println();

        if (choice == 1) {
            info2HammingCode();
        } else {
            hammingCode2Info();
        }
    }

    public static void info2HammingCode() {
        Info2HammingCode fin = new Info2HammingCode();
        fin.setParityMode();
        fin.findK();
        fin.createHammingCode();
    }

    public static void hammingCode2Info() {
        HammingCode2Info fin = new HammingCode2Info();
        fin.setParityMode();
        fin.findK();
        fin.convertHammingCode2Info();
        fin.outputOriInfo();
    }
}
