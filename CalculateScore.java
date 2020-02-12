/* start of a program */

package com.michalsadecki;

import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class CalculateScore {

    /* begining of the main method */

    public static void main(String[] args) {

        /* the answer for a given problem: */

        String[] names = getStringArrayFromFile("names.txt");

        int answer;

        answer = calculateOverallScore(names);

        System.out.println("Solution of the task equals: " + answer);

        /* Solution of the task equals: 871198282 */

    } /* end of the main method */

    /* method to check if the given character is part of the passed array */
    private static boolean contains(char ch, char[] chArr) {

        for (char x : chArr) {
            if (x == ch)
                return true;

        }
        return false;
    }

    /* metod returning the array of numeric order values for each character in the word */
    private static int[] getNumVals(char[] chars) {

        /* character array with all 26 of latin alphabet letters for comparison purpose */
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        int[] nums = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {

            /* check if the characters are the part of the latin alphabet using the contains method */
            if (contains(chars[i], alphabet)) {

                /*
                assigning single character a numeric order value using the build-in method from the
                Character class (they take values 10-35 inclusive, so 9 was subtracted to receive
                1-26
                */
                nums[i] = Character.getNumericValue(chars[i]) - 9;

            }
            else {
                System.out.println("Ivalid input." +
                        "A program accepts only letters from the standard latin alphabet!");
                return null;

            }
        }
        return nums;
    }

    /* method calculating the sum of ints of the passed int array */
    private static int calcSum(int[] nums) {

        int sum = 0;
        if (nums != null) {

            for (int num : nums)
                sum += num;

        }
        return sum;
    }

    /* method creating an array of strings from the given file */
    private static String[] getStringArrayFromFile(String path) {

        String[] namesArr = new String[6000];

        try {
            /* reading from the file placed in the path passed as a method agument
             * inside a try-catch block in case of file not found issue */
            File listOfNames = new File(path);

            Scanner sc = new Scanner(listOfNames);

            while (sc.hasNext()) {

                String line = sc.nextLine();

                /* spliting a text read by the program into a separate items */
                namesArr = line.split("\",\"");

                /* deleting quotation marks inside first and last String in the array */
                String subStr1 = namesArr[0].substring(1);
                namesArr[0] = subStr1;
                String subStr2 = namesArr[namesArr.length - 1]
                        .substring(0, namesArr[namesArr.length - 1].length() - 1);
                namesArr[namesArr.length - 1] = subStr2;

            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        /* sorting items alphabetically using the build-in method from Arrays class */
        Arrays.sort(namesArr);

        return namesArr;
    }

    /* metod to calculate the overall score for entire array of Strings passed into it,
     * the score contains individual result from every item multiplied by its position
     * based of an alphabetical order in the array*/
    private static int calculateOverallScore(String[] strings) {

        int sumOverall = 0;

        for (int i = 0; i < strings.length; i++) {

            int sumPart = calcSum(getNumVals(strings[i]
                    .toLowerCase().toCharArray())) * (i + 1);

            sumOverall += sumPart;

            if (sumPart == 0)
                sumOverall = 0;
        }
        return sumOverall;
    }
}

/* end of a program */