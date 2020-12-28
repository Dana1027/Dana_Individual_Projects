// Dana Im
// CSE 142
// Assignment #6
// TA: Kendra Specht
// 5/21/2020
//
// This program produces the result of Kiersey personality test that
// people took previously. Users will type in data file name they want to read and it will
// calcuate the choices people made(which are constructed with As and Bs).
// It will sort out how many As and Bs they chose and it will calculate seperately
// in sections where it decides Extravert/Introvert, Sensing/Intuition, Thinking/
// Feeling, and Judging/Perceiving.
// A static value ARRAY_NUM is used because there are lots of fours in this program.
// There are two scanners in this program, one is for reading user input, and the other
// is to read the txt dat outside.
// The second scanner will read first and the second line and will
// iterate reading two lines inside the while loop.

import java.io.*;
import java.util.*;

public class Personality {
   public static final int ARRAY_NUM = 4;
   public static void main (String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      String inputFileName = introWithFileName(console);
      String outputFileName = outputFileName(console);
      Scanner input= new Scanner(new File (inputFileName));
      PrintStream output = new PrintStream(new File(outputFileName));
      while (input.hasNextLine()) {
         String firstLine = input.nextLine();
         String secondLine = input.nextLine().toLowerCase();
         char[] charArray = secondLine.toCharArray();
         int[] resultOfCountA = countAs(charArray);
         int[] resultOfCountB = countBs(charArray);
         int[] sumOfAAndB = sumOfLetters(resultOfCountA, resultOfCountB);
         int[] roundedPercentB = computePercent(resultOfCountB, sumOfAAndB);
         char[] personalityLetters = personalityArray(roundedPercentB);
         print(output, firstLine, roundedPercentB, personalityLetters);
      }
   }
   
   // This method prints out the introduction of the program.
   // It will explain what how this program processes things to
   // show the result of people's data.
   // Also it will prompt the users to type in the file name they want.
   // Paramater : Scanner console - the passes down Scanner will enable
   // users to type in the name of the file.
   // Return: String inputFileName - it will go to the main to
   // allow second scanner to read txt file.
   public static String introWithFileName(Scanner console) {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter.  It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      System.out.println();
      System.out.print("input file name? ");
      String inputFileName = console.next();
      return inputFileName;
   }
   
   // This method prompts users to type in the output file name they want.
   // the name will be used in forming file name after
   // the entire converting process is done.
   // Parameter: The passed down scanner will allow users to type in the output file name.
   // Return: outFileName - the name useres typed in.
   // The string value will be stored in the main and will be used when it creates result file
   // at the end of the entire process.
   public static String outputFileName(Scanner console) {
      System.out.print("output file name? ");
      String outFileName = console.next();
      System.out.println();
      return outFileName;
   }
   
   // This method counts character 'a's from the answers.
   // It uses % to iterate every other 7th question. Fore example, the array
   // will become a set of indexes of 012356.
   // In each iteration it will check whether there are 'a's, so it is basically
   // checking how many 'a's are in each indexes.
   // It will also add up 2th and 3th, 4th and 5th, 6th and 7th questions since they are
   // checking the same thing.
   // Parameters: charArray - with character values will be passed down which will
   // go into the for loop and will be checked.
   // Return: resultOfA - When the iteration is done it produces an array that has four indexes,
   // and will be returned to main, so that it can be use for later calculation.
   public static int[] countAs (char[] charArray) {
      int[] letterA = new int[7];
      for (int i = 0; i < charArray.length; i++) {
         if (charArray[i] == 'a') {
            letterA [i % 7] += 1;
         }
      }
      int[] resultOfCountA = new int[ARRAY_NUM];
      for (int i = 0; i < resultOfCountA.length; i++) {
         if (i == 0) {
            resultOfCountA[i] = letterA[i];
         } else {
            resultOfCountA[i] = letterA[2 * i-1] + letterA[2 * i];
         }
      }
      return resultOfCountA;
   }
   
   // This method counts character 'b's from the answers.
   // It uses % to iterrate every other 7th question. Fore example, the array
   // will become a set of indexes of 012356.
   // In each iteration it will check whether there are 'b's, so it is basically
   // checking how many 'b's are in each indexes.
   // It will also add up 2th and 3th, 4th and 5th, 6th and 7th questions since they are
   // checking the same thing.
   // Parameters: charArray - with character values will be passed down which will
   // go into the for loop and will be checked.
   // Return: resultOfB - When the iteration is done it produces an array that has four indexes,
   // and will be returned to main, so that it can be use for later calculation.
   public static int[] countBs (char[] charArray) {
      int[] letterB = new int[7];
      for (int i = 0; i < charArray.length; i++) {
         if (charArray[i] == 'b') {
            letterB [i % 7] += 1;
         }
      }
      
      int[] resultOfCountB = new int[ARRAY_NUM];
      for (int i = 0; i < resultOfCountB.length; i++) {
         if (i == 0) {
            resultOfCountB[i] = letterB[i];
         } else {
            resultOfCountB[i] = letterB[2 * i-1] + letterB[2 * i];
         }
      }
      return resultOfCountB;
   }
   
   // This method calculates how many letters each indexes have.
   // In some of the indexes there are '-'. So in order to sort this out,
   // I only have to know the sum of 'a's and 'b's.
   // Parameters: the arrays of counted 'a's and counted 'b's will be passed douwn.
   // These are used to sum each values of the the arrays.
   // Return: sumOfAAndB - after the summing process, it will produce an array.
   // This will be stored in main and will be used for calculating percetage of 'b'.
   public static int[] sumOfLetters (int[] resultOfCountA, int[] resultOfCountB) {
      int[] sumOfAAndB = new int[ARRAY_NUM];
      for (int i = 0; i < sumOfAAndB.length; i++) {
         sumOfAAndB[i] = resultOfCountA[i] + resultOfCountB[i];
      }
      return sumOfAAndB;
   }
   
   // This method is going to comapare how many 'b's were selected compared to entire question.
   // It will calculate the percent of 'b's and will round up it's values.
   // Parameters: the integer arrays of array of 'b' and sumation of 'a' and 'b' will be passed.
   // These values are going to be converted first into double so that the values can be rounded.
   // After the rounding process, the values will be restored as integers.
   // Return: roundedPercentB- the rounded integer values of the new array will be stored to main
   // and will be used personality type decision.
   public static int[] computePercent (int[] resultOfCountB, int[] sumOfAAndB) {
      double[] totalB = new double[ARRAY_NUM];
      double[] totalAAndB = new double[ARRAY_NUM];
      double[] percentOfB = new double[ARRAY_NUM];
      int[] roundedPercentB = new int[ARRAY_NUM];
      for (int i =0; i < ARRAY_NUM; i++) {
         totalB[i] = resultOfCountB[i];
         totalAAndB[i] = sumOfAAndB[i];
         percentOfB[i] = (totalB[i] * 100)/totalAAndB[i];
         roundedPercentB[i] = (int) Math.round(percentOfB[i]);
      }
      return roundedPercentB;
   }
   
   // This method make a decision which type you are based on the calculated percentage
   // of how much 'b's you selected.
   // Parameters: The array of roundedPercentB is passed down to check
   // the types of each indexes.
   // typeA - is for the types that matches with 'a' selections.
   // typeB - is for the types that matches with 'b' selections.
   // These are used in
   // Return: typeA or typeB or 'x' by decision.
   // This will be returned to method personalityArray.
   public static char personalityTypeDecision(int roundedPercentB, char typeA, char typeB) {
      if (roundedPercentB < 50) {
         return typeA;
      } else if (roundedPercentB > 50) {
         return typeB;
      } else {
         return 'X';
      }
   }
   
   // This method decides which type you have to consider by which index it is in.
   // Since types differ by each index it will check index by index to make varying decisions.
   // I made this to reduce the redundancy.
   // Parameters: roundedPercentB - to check each percent values of 'b'
   // Return: personalityLetters -After checking every indexes it will produce a new char array.
   public static char[] personalityArray(int[] roundedPercentB) {
      char[] personalityLetters = new char[ARRAY_NUM];
      
      personalityLetters[0] = personalityTypeDecision(roundedPercentB[0], 'E', 'I');
      personalityLetters[1] = personalityTypeDecision(roundedPercentB[1], 'S', 'N');
      personalityLetters[2] = personalityTypeDecision(roundedPercentB[2], 'T', 'F');
      personalityLetters[3] = personalityTypeDecision(roundedPercentB[3], 'J', 'P');
      
      return personalityLetters;
   }
   
   // This method prints out a string value and the arrys that's been produced.
   // It will print out the sting balue and percent value as their original types.
   // However, the personality letters will be restored as string values and printed out.
   // Parameters: PrintStream - passed down to print things out.
   // String firstLine - passed down to be printed out.
   // Integer array rounded percent - passed down to be printed out.
   // Character array of personality letters - passed down to be printed out.
   public static void print(PrintStream output, String firstLine,
      int[] roundedPercentB, char[] personalityLetters ) {
      output.print(firstLine + ": ");
      output.print(Arrays.toString(roundedPercentB) + " = ");
      for(int i = 0; i < ARRAY_NUM; i++){
         output.print(personalityLetters[i]);
      }
      output.println();
   }
}