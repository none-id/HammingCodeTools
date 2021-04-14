package tools;

import prepare.*;

import java.util.Scanner;

import static java.lang.Math.pow;

public class HammingCode2Info {
    private final String hammingCode;
    private String hammingCodeCorrect;
    private boolean parityModeOdd;
    private int k;

    public HammingCode2Info() {
        System.out.print("Please input your Hamming Code: ");

        Scanner tempScanner = new Scanner(System.in);

        hammingCode = tempScanner.next();
    }

    public void setParityMode() {
        System.out.print("Please input your parity mode(Odd as 1, Even as 2): ");

        Scanner tempScanner = new Scanner(System.in);
        int tempIn = tempScanner.nextInt();

        if (tempIn == 1) {
            parityModeOdd = true;
        } else {
            parityModeOdd = false;
        }
    }

    public void findK() {
        int k = 0;
        while (((int) pow(2, k)) < hammingCode.length()) {
            k++;
        }
        this.k = k;
    }

    public void outputOriInfo() {
        int finalOriInfo = 0;
        int j = 1, bits = hammingCodeCorrect.length() - k;

        for (int i = 0; i < hammingCodeCorrect.length(); i++) {
            if (j <= k) {
                if (i + 1 == (int) pow(2, j - 1)) {
                    j++;
                    continue;
                }
            }


            finalOriInfo +=
                    Integer.parseInt(String.valueOf(hammingCodeCorrect.charAt(i)))
                            * (int) pow(2, bits - 1);

            bits--;
        }

        System.out.println("The Original Info is: " + finalOriInfo);
    }

    public void convertHammingCode2Info() {
        PendingC CGroup = new PendingC();
        int codeLenth = hammingCode.length();
        int[] CPendingGroup = new int[10];

        for (int i = 1; i <= k; i++) {
            boolean eCGroup[] = CGroup.getCGroup(i);
            int startP = (int) pow(2, i - 1);
            int tempXOR = Integer.parseInt(String.valueOf(hammingCode.charAt(startP - 1)));

            for (int j = startP + 1; j <= codeLenth + 1; j++) {
                if (eCGroup[j]) {
                    tempXOR ^= Integer.parseInt(String.valueOf(hammingCode.charAt(j - 1)));
                }
            }

            if (!parityModeOdd) {
                CPendingGroup[i] = tempXOR;
            } else {
                CPendingGroup[i] = tempXOR == 1 ? 0 : 1;
            }

        }


        int correctionP = 0;
        for (int i = k; i >= 1; i--) {
            correctionP += CPendingGroup[i] * (int) pow(2, i - 1);
        }

        System.out.println();

        if (correctionP == 0) {
            System.out.println("No error!");
            hammingCodeCorrect = hammingCode;
        } else {
            StringBuilder hammingCodeCorrectGroup
                    = new StringBuilder(hammingCode);

            hammingCodeCorrectGroup.setCharAt(correctionP - 1,
                    hammingCode.charAt(correctionP - 1) == '1' ? '0' : '1');
            hammingCodeCorrect = hammingCodeCorrectGroup.toString();

            System.out.println("Error in bits No." + correctionP);
            System.out.println("The correct Hamming Code is:" + hammingCodeCorrect);
        }
    }
}
