// Dana Im
// CSE 142
// Assignment #5
// TA: Kendra Specht
//
// This program will run a guessing game.
// It will start with calling introduction method that explains what the 
// program is doing. Next scanner console and Random r are sotred as variables to
// enable players to type their guesses and computer to select random number to
// initialize the guessing game.
// Then guessing game method with total guesses stored and promptNexGame with character
// value stored will be called to initialize the game. 
// When the user finishes first round, it will notice you with 
// how much guesses you made and will prompt whether you want to play more or not.
// At this point you will decide whether you want to continue or not.
// In the main method, the guessing game will be looping the game continuously 
// when players type letter 'y'.
// It doesn't matter whether you are typing lower case or upper case,
// it will always be converted into lowercase letters so to check whether first character is 
// same with the character 'y'. This is also same with 'n' as well. 
// Players will type any words that have 'n' as first character to stop playin the game.
// When user types in any letter containing 'n', the loop will end and 
// the overallscore output will be displayed.
// The class constants GUESS_NUM_MAX restricts range of the numbers that 
// are supposed to be guess and NUM_GUESSES_MAX restricts total number of guesses 
// tht players can make.
// Inside the while loop in main, it is constantly checking which game was done
// with minimum number of guesses and keeps updating. It's also updating how many
// games were played each time players repeat playing.

import java.util.*;

public class Guess {
    public static final int GUESS_NUM_MAX = 100;
    public static final int NUM_GUESSES_MAX = 99999;
    public static void main (String[] arg) {
        programExplanation();
        Scanner console = new Scanner(System.in);
        Random r = new Random();
        int totalGuesses = guessingGame(console, r);
        char initialLetter = promptNexGame(console);
        int numGames = 1;
        int min = totalGuesses; 
        int numGameGuesses = totalGuesses;
        while (initialLetter == 'y') {
            numGameGuesses = guessingGame(console, r);
            if (numGameGuesses < min){
                min = numGameGuesses;
            }
            totalGuesses = totalGuesses + numGameGuesses;
            numGames++;
            initialLetter = promptNexGame(console);
        } 
        if (initialLetter == 'n') {        
            scoreResults(totalGuesses, numGames, min);
        }
    }
        
    // This is the program explanation that informs the user what kind of number range
    // the game is going to use and that the users should guess numbers within this range. 
    // In case of changing the range of numbers, I added 'GUESS_NUM_MAX' so that when its value
    // is changed, it can be changed inside the program Explanation as well.  
    public static void programExplanation() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and");
        System.out.println(GUESS_NUM_MAX + " and will allow you to guess until");
        System.out.println("you get it.  For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower");
        System.out.println("than your guess.");
        System.out.println();
    }

    // This is the actual game part where users type in there numbers. 
    // The console will provide whether you have to type in lower numbers or 
    // higher numbers to get the answers.
    // This is done by while loop, which continues until players provide an answer.
    // It will also return it's 'numGameGuesses' to the  main 
    // so that it can add number of guesses made within each game at the end 
    // to increase the total number of guesses whenever users repeat playing the game. 
    // Paramater 
    // Scanner console: passed down so to enable players to type in guesses of numbers. 
    // Random r : selects random numbers for the anwer within the GUESS_NUM_MAX range.
    public static int guessingGame(Scanner console, Random r) {
        int answer = r.nextInt(GUESS_NUM_MAX) + 1;
        System.out.println("I'm thinking of a number between 1 and " + GUESS_NUM_MAX + "...");
        int numGameGuesses = 0;
        int guess = 0;
        while (guess != answer && numGameGuesses <= NUM_GUESSES_MAX) {
            numGameGuesses++;
            System.out.print("Your guess? ");
            guess = console.nextInt();
            if (guess < answer) {
                System.out.println("It's higher.");
            } else if (guess > answer) {
                System.out.println("It's lower.");
            } 
        }          
        if (numGameGuesses == 1) {
            System.out.println("You got it right in " + numGameGuesses + " guess"); 
        } else {
            System.out.println("You got it right in " + numGameGuesses + " guesses");
        }
        return numGameGuesses;
    }

    // This method prompts users whether to continue the game or not. 
    // The console here will make users to type in characters that includes y or n 
    // Regardless of whether these are lower case or uppercase, it will chage all the 
    // initial characters into lower case and will return its value to main so that
    // it can be stored in promptNexGame in the main.
    // Paramater
    // Scanner console: passed down so to enable players to type in guesses of numbers. 
    public static char promptNexGame(Scanner console) {
        System.out.print("Do you want to play again? ");
        String response = console.next();
        System.out.println();
        response = response.toLowerCase();
        char initialLetter = response.charAt(0);
        return initialLetter;
    }
    
    // This method produce the overall outcome of the games you played.
    // When you decide to stop playing game it will show you the overall scores including
    // total number of games that you did,
    // total guesses that you made which are sum of guesses that you made in each game,
    // and total guesses divided by number of games.
    // It will also show you the round which you made the least guesses of all games. 
    // The variable I created in the begining of the method is to make the integer value of
    // numGames to become a double so that when it divides allguesses 
    // the the outcome value can become a double.
    // Parameter allGuesses, numGames, and min are the value of 
    // allGuesses : to print out total number of guesses that's been added throughout the entire games
    // numGames: print out to total number of games played 
    // min: game that has minimum nerber of gueses
    // Also the double value, guessesGameMean is rounded up to one decimal point
    // by the returned round1 method.
    public static void scoreResults(int allGuesses, int numGames, int min) {
        double numGamesDouble = numGames;
        double guessesGameMean = allGuesses/numGamesDouble;
        System.out.println("Overall results:");
        System.out.println("    total games   = " + numGames);
        System.out.println("    total guesses = " + allGuesses);
        System.out.println("    guesses/game  = " + round1(guessesGameMean));
        System.out.println("    best game     = " + min); 
    }

    // This method is to round double values up to one decimal point.
    // The parameter guessesGameMean is passed down to be calculated and rounded to
    // one decimal point.
    // It will return to whichever value it is used and round the number up to 
    // one decimal point.
    public static double round1(double guessesGameMean) {
        return Math.round(guessesGameMean * 10.0) / 10.0;
    }
}


    
