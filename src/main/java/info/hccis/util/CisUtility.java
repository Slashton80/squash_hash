package info.hccis.util;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * Has some useful methods to be used in our programs.
 *
 * @author bjmaclean
 * @since Oct 19, 2021
 */
public class CisUtility {

    private static Scanner input = new Scanner(System.in);

    /**
     * Validate name based on A6 requirements
     *
     * @param name String to be checked
     * @return If valid or not
     * @since 20230525
     * @author BJM
     */
    public static boolean validateName(String name) {
        boolean valid = true;
        name = name.trim();

        int indexOfSpace = name.indexOf(" ");

        if (indexOfSpace < 0) {
            valid = false;
        } else {

            //There should be exactly one space
            int indexOfLastSpace = name.lastIndexOf(" ");

            if (indexOfSpace != indexOfLastSpace) {
                valid = false;
            } else {
                //first name should be > 1 character.
                //if indexOfSpace == 1 then first name is too short.
                if (indexOfSpace < 2) {
                    valid = false;
                } else {
                    //last name should be > 1 character.
                    //Joe B --> length == 5 location of space == 3.  
                    //Length of last name == length - 1 - indexOfSpace (5-1-3 == 1)
                    int lengthOfLastName = name.length() - 1 - indexOfSpace;
                    if (lengthOfLastName < 2) {
                        valid = false;
                    }
                }
            }

        }
        return valid;
    }

    /**
     * Format name to set first letter of first and last name to uppercase.
     *
     * @since 20230524
     * @author BJM
     */
    public static String formatName(String name) {

        String temp = "";
        int indexOfSpace = name.indexOf(" ");
        
        String firstLetter = name.substring(0, 1);
        firstLetter = firstLetter.toUpperCase();

        temp = firstLetter + name.substring(1,indexOfSpace+1);

        firstLetter = name.substring(indexOfSpace+1, (indexOfSpace + 2));
        firstLetter = firstLetter.toUpperCase();

        //Bob Jones
        //012345678
        temp += firstLetter;
        temp += name.substring(indexOfSpace + 2);

        return temp;
    }

    /**
     * Show welcome design
     *
     * @since 20230523
     * @author BJM
     */
    public static void displayDesign() {
        String output = "*";
        String spaces = "";
        String lastLine = "***";
        System.out.println(output);
        for (int counter = 0; counter < 10; counter++) {
            spaces += " ";
            lastLine += "*";
            System.out.println(output + spaces + output);
        }
        System.out.println(lastLine);
    }

    /**
     * Return the default currency String value of the double passed in as a
     * parameter.
     *
     * @param inputDouble double to be formatted
     * @return String in default currency format
     *
     * @since 20211020
     * @author BJM
     */
    public static String toCurrency(double inputDouble) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(inputDouble);
    }

    /**
     * Return the number formatted with the given number of decimal places
     *
     * @since 20230111
     * @author CIS1232A
     * @param theNumber
     * @param numberOfDecimalPlaces
     * @return The number formatted
     */
    public static String formatDouble(double theNumber, int numberOfDecimalPlaces) {
        //https://stackoverflow.com/questions/5195837/format-float-to-n-decimal-places
        String theNumberFormatted = String.format("%." + numberOfDecimalPlaces + "f", theNumber);
        return theNumberFormatted;
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return String entered by the user
     * @since 20211020
     * @author BJM
     */
    public static String getInputString(String prompt) {

        System.out.println(prompt + " -->");
        String output = input.nextLine();
        return output;
    }

    /**
     * Get input from the user using the console that is less than the max
     * length passed in.
     *
     * @param prompt Prompt for the user
     * @param minLength Minimum length allowed for the input
     * @param maxLength Maximum length allowed for the input
     * @return String entered by the user
     * @since 20211020
     * @author BJM
     */
    public static String getInputString(String prompt, int minLength, int maxLength) {

        String output;
        int length;
        do {
            System.out.println(prompt + " -->");
            output = input.nextLine();
            length = output.length();
        } while (length < minLength || length > maxLength);
        return output;
    }

    /**
     * Get input from the user using the console for date and default to today
     * if user presses enter.
     *
     * @param prompt Prompt for the user
     * @return String entered by the user or today's date
     * @since 20211020
     * @author BJM
     */
    public static String getInputDateWithTodayDefault(String prompt) {

        System.out.println(prompt + " (leave blank for today's date) " + "-->");
        String output = input.nextLine();

        if (output == null || (output.isEmpty())) {
            output = getTodayString("yyyy-MM-dd");
        }
        return output;
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 20211020
     * @author BJM
     */
    public static double getInputDouble(String prompt) {

        String inputString = getInputString(prompt);
        double output = Double.parseDouble(inputString);
        return output;
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 20211020
     * @author BJM
     */
    public static int getInputInt(String prompt) {

        String inputString = getInputString(prompt);
        int output = Integer.parseInt(inputString);
        return output;
    }

    /**
     * Get input from the user using the console in the range provided
     *
     * @param prompt Prompt for the user
     * @param min
     * @param max
     * @return The double entered by the user
     * @since 20211020
     * @author BJM
     */
    public static int getInputInt(String prompt, int min, int max) {

        boolean valid = false;
        int output = 0;
        while (!valid) {
            String inputString = getInputString(prompt);
            output = Integer.parseInt(inputString);
            if (output >= min && output <= max) {
                valid = true;
            } else {
                System.out.println("Invalid, number must be between " + min + " and " + max + " please try again.");
            }
        }
        return output;
    }

    /**
     * Get input boolean from the user using the console
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @since 20211108
     * @author BJM
     */
    public static boolean getInputBoolean(String prompt) {

        String inputString = getInputString(prompt + " (y/n)");
        if (inputString.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Get input boolean from the user using the console
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @since 20211108
     * @author BJM
     */
    public static boolean getInputBoolean(String prompt, String affirmative, String negative) {

        String inputString = getInputString(prompt + " (" + affirmative + "/" + negative + ")");
        if (inputString.equalsIgnoreCase(affirmative)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Provide today's date in the specified format
     *
     * @param format Date format desired
     * @return Today's date in specified format
     * @since 20211021
     * @author BJM
     */
    public static String getTodayString(String format) {
        //https://www.javatpoint.com/java-get-current-date

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    /**
     * Get a random number between min and max
     *
     * @since 20211109
     * @author BJM
     */
    public static int getRandom(int min, int max) {
        Random rand = new Random();
        int theRandomNumber = rand.nextInt((max - min) + 1) + min;
        return theRandomNumber;
    }

}
