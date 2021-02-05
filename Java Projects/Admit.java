package CSE142;

import java.util.*;
    // Dana Im
    // CSE 142
    // Assignment #3
    // TA: Kendra Specht
public class Admit {
    // This is a caculator which makes users to type in either SAT or ACT scores
    // and it will calculate those scores into 0~100 scale and add the scores with GPA
    // It will also tell which student is better or the same.
    public static void main (String[] args) {
       Scanner console = new Scanner(System.in);
       programExplanation();
       double applicant1 = getScores(console, 1);
       double applicant2 = getScores(console, 2);
       System.out.println("First applicant overall score = " + round1(applicant1));
       System.out.println("Second applicant overall score =" + round1(applicant2));
       report(applicant1, applicant2);
    }
    
    //This method is going to introduce students what this calculator is going to do.
    public static void programExplanation() {
       System.out.println("This program compares two applicants to");
       System.out.println("determine which one seems like the stronger");
       System.out.println("applicant. For each candidate I will need");
       System.out.println("either SAT or ACT scores plus a weighted GPA.");
       System.out.println();
    }
    
    // This method is going to compare the scores of two applicants and report
    // final decision whether one is better than the other or same.
    public static void report(double applicant1, double applicant2) {
       if (applicant1 > applicant2) {
          System.out.println("The first applicant seems to be better");
       } else if (applicant1 < applicant2) {
          System.out.println("The second applicant seems to be better");
       } else if (applicant1 == applicant2) {
          System.out.println("The two applicants seem to be equal");
       }
    }
    
    // This method is going to decide which test calculator should be used and return its value
    // After decidin the score, it will be adding returned GPA score and add with the test scores.
    //Then it will return to main.
    public static double getScores(Scanner console, int applicantNum) {
       System.out.println("information for applicant #" + applicantNum + ":");
       System.out.print("do you have 1) SAT scores or 2) ACT scores? ");
       int whichTest = console.nextInt();
       double testScore = 0.0;
       if (whichTest == 1) {
          testScore = getSAT(console);
          
       } else if(whichTest == 2) {
          testScore = getACT(console);
       }
       double gpaScore = getGPA(console);
       return testScore + gpaScore;
    }
    
    // This method is going to collect GPA scores and calculate into 0~100 scale.
    // It is going to return it's value to gpa score in getscores.
    public static double getGPA(Scanner console) {
       System.out.print("overall GPA? ");
       double GPA = console.nextDouble();
       System.out.print("max GPA? ");
       double maxGpa = console.nextDouble();
       System.out.print("Transcript Multiplier? ");
       double Mult = console.nextDouble();
       double gpaScore = transMulti(GPA, maxGpa, Mult);
       System.out.println("GPA score = " + round1(gpaScore));
       System.out.println();
       return gpaScore;
    }
    
    //This method prints out prompts so that users can type in their SAT scores.
    //It makes users to type math, reading, and writing scores and
    //calcuates the score into 0~100 scale score.
    public static double getSAT(Scanner console) {
       System.out.print("SAT math? ");
       double SATMath = console.nextDouble();
       System.out.print("SAT critical reading? ");
       double SATRead = console.nextDouble();
       System.out.print("SAT writing? ");
       double SATWrit = console.nextDouble();
       double score = SATScore(SATMath, SATRead, SATWrit);
       System.out.println("exam score = " + round1(score));
       return score;
       
    }
    
    //This method prints out prompts so that users can type in their ACT scores.
    //It makes users to type English, math, reading, and science scores and
    //calcuates the score into 0~100 scale score.
    public static double getACT(Scanner console) {
       System.out.print("ACT English? ");
       double ACTEng = console.nextDouble();
       System.out.print("ACT math? ");
       double ACTMath = console.nextDouble();
       System.out.print("ACT reading? ");
       double ACTRead = console.nextDouble();
       System.out.print("ACT science? ");
       double ACTSci = console.nextDouble();
       double score = ACTScore(ACTEng, ACTMath, ACTRead, ACTSci);
       System.out.println("exam score = " + round1(score));
       return score;
    }
    
    // This formula returns to getSAT and transfers ACT scores into 0~100 scale.
    public static double SATScore(double x, double y, double z) {
       return (2.0 * x + y + z) / 32;
    }
    
    // This formula returns to getACT and transfers ACT scores into 0~100 scale.
    public static double ACTScore(double x, double y, double z, double h) {
       return (x + 2.0 * y + z + h) / 1.8;
    }
    
    // This formula returns to getGPA and calculate GPA numbers to 0~100 scale.
    public static double transMulti(double actGpa, double maxGpa, double Mult) {
       return actGpa/maxGpa * 100 * Mult;
    }
    
    // This formula returns to final numbers and rounds numbers up to nearest 10th decimal point.
    public static double round1(double n) {
       return Math.round(n * 10.0) / 10.0;
    }

       
 }
