package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String option = null;

        do{
            System.out.println("Please input operation (encode/decode/exit):");
            option = scanner.nextLine();

            switch (option) {
                case "encode":
                    System.out.println("Input string:");

                    String text = scanner.nextLine();

                    encoder(text);
                    break;
                case "decode":
                    System.out.println("Input encoded string:");

                    String code = scanner.nextLine();

                    decoder(code);
                    break;
                case "exit":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("There is no '" + option + "' operation");
                    System.out.println();
            }
        } while (!option.equals("exit"));
    }

    public static void encoder(String text){
        System.out.println("Encoded string:");
        String bin = "";
        String add = "";

        // Char to Binery
        for (int i = 0; i < text.length(); i++) {
            bin += String.format("%7s", Integer.toBinaryString(text.charAt(i))).replace(' ', '0');
        }

        // Replace each binomial with its encoding
        for (int j = 0; j < bin.length(); j++) {
            char now = bin.charAt(j);
            if (now == '0') {
                add += "00 0";
            } else {
                add += "0 0";
            }

            for (int k = j + 1; k < bin.length(); k++) {
                if (bin.charAt(k) == now && j < bin.length() - 1) {
                    add += "0";
                    j++;
                } else {
                    j = k - 1;
                    add += " ";
                    break;
                }
            }
        }

        // Print
        System.out.print(add);
        System.out.println("\n");
    }

    public static void decoder(String code){
        String[] values = code.split(" ");

        String identifier = "";

        String number = "";

        String result = "";

        int count = 0;

        boolean isValid = true;

        // To find error
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == ' ' || code.charAt(i) == '0') {

            } else {
                isValid = false;
            }
        }

        // to split in different strings
        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
                identifier += values[i] + " ";
            } else {
                number += values[i] + " ";
            }
        }

        // Remove end space
        identifier = identifier.substring(0, identifier.length() - 1);
        number = number.substring(0, number.length() - 1);

        // make it array for easier resolution
        String[] identifierArray = identifier.split(" ");
        String[] numberArray = number.split(" ");

        if (identifierArray.length == numberArray.length && isValid) {
            for (int i = 0; i < identifierArray.length; i++) {
                for (int j = 0; j < numberArray[i].length(); j++) {
                    count++;

                    if (identifierArray[i].equals("00")) {
                        result += "0";
                    } else if(identifierArray[i].equals("0")) {
                        result += "1";
                    } else {
                        isValid = false;
                    }

                    if (count > 0 && count % 7 == 0){
                        result += " ";
                    }
                }
            }

            // Print if is valid format
            if (count % 7 == 0 && isValid) {
                String[] multiResult = result.split(" ");
                System.out.println("Decoded string:");

                for (int i = 0; i < multiResult.length; i++) {
                    System.out.print((char)Integer.parseInt(multiResult[i], 2));
                }
            } else {
                System.out.print("Encoded string is not valid.");
            }

        } else {
            System.out.print("Encoded string is not valid.");
        }

        System.out.println("\n");
    }
}

