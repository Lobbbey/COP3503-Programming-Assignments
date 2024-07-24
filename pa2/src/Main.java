/*COP3503C Assignment 2
This Program is written by: Joshua Tyler Bandy*/

//Imports
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Variables to be used throughtout the program
    static Scanner in;
    static int row, col, numOfWords, startOfWord;

    // Function to check if we are in bounds
    static boolean isSafe(char letterMatrix[][], int x, int y, char wordToFind, char sol[][]) {
        if (x >= 0 && x < row && y >= 0 && y < col && letterMatrix[x][y] == wordToFind && sol[x][y] == ' ') {
            return true;
        }
        return false;
    }

    // Function to print the solution matrix
    static void printSolution(char sol[][]) {
        for (int x = 0; x < row; x++) {
            System.out.print("[");
            for (int y = 0; y < col; y++) {
                //Change the printing format depending on its placement in the matrix
                if (y == col - 1) {
                    System.out.print(" " + sol[x][y]);
                } else if (y == 0) {
                    System.out.print(sol[x][y] + ",");

                } else {
                    System.out.print(" " + sol[x][y] + ",");
                }
            }
            System.out.println("]");
        }
    }

    // Wrapper function for findWordSolver
    static boolean findWord(char letterMatrix[][], char[] wordToFind) {
        char sol[][] = new char[row][col];

        //Fill the solution array with Spaces to indicate unused
        for (int i = 0; i < row; i++) {
            Arrays.fill(sol[i], ' ');
        }

        // Find a starting point to check for the word
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (letterMatrix[x][y] == wordToFind[startOfWord]) {
                    sol[x][y] = letterMatrix[x][y];
                    startOfWord++;
                    // Call the solver and return a value based on its result
                    if (findWordSolver(letterMatrix, wordToFind, sol, x, y)) {
                        printSolution(sol);
                        return true;
                    }
                }
                //a word wasn't found so reset
                startOfWord = 0;
                sol[x][y] = ' ';
            }
        }
        return false;// Word could not be found
    }

    // Recursive Function to Find the word in the Matrix
    static boolean findWordSolver(char letterMatrix[][], char wordToFind[], char sol[][], int x, int y) {
        // Base case: A letter was found on the matrix that is in the wordToFind
        if (startOfWord == wordToFind.length ) {
            if(sol[x][y] == wordToFind[startOfWord - 1])
                return true;
        }
        else {
            // Recursion Calls to check for a word
            if (isSafe(letterMatrix, x, y - 1, wordToFind[startOfWord], sol)) {// Left
                sol[x][y - 1] = letterMatrix[x][y - 1];// Set answer in sol matrix
                startOfWord++;
                //recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x, y - 1)){
                    return true;
                }
                else{
                    sol[x][y - 1] = ' ';
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x, y + 1, wordToFind[startOfWord], sol)) {// Right
                sol[x][y + 1] = letterMatrix[x][y + 1];//Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x, y + 1)){
                    return true;
                }
                else{
                    sol[x][y + 1] = ' ';// Reset the sol matrix
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x - 1, y, wordToFind[startOfWord], sol)) {// Up
                sol[x - 1][y] = letterMatrix[x - 1][y];// Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x - 1, y)){
                    return true;
                }
                else{
                    sol[x - 1][y] = ' ';// Reset the sol matrix
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x + 1, y, wordToFind[startOfWord], sol)) {// Down
                sol[x + 1][y] = letterMatrix[x + 1][y];//Store the correct answer
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x + 1, y)){
                    return true;
                }
                else{
                    sol[x + 1][y] = ' ';// Reset the sol matrix
                    return false;
                }
            }
            else if (isSafe(letterMatrix, x + 1, y - 1, wordToFind[startOfWord], sol)) {// Bottom Left
                sol[x + 1][y - 1] = letterMatrix[x + 1][y - 1];// Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x + 1, y - 1)){
                    return true;
                }
                else{
                    sol[x + 1][y - 1] = ' ';// Reset the sol matrix
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x + 1, y + 1, wordToFind[startOfWord], sol)) {// Bottom Right
                sol[x + 1][y + 1] = letterMatrix[x + 1][y + 1];// Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x + 1, y + 1)){
                    return true;
                }
                else{
                    sol[x + 1][y + 1] = ' ';// Reset the sol matrix
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x - 1, y - 1, wordToFind[startOfWord], sol)) {// Upper Left
                sol[x - 1][y - 1] = letterMatrix[x - 1][y - 1];// Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x - 1, y - 1)){
                    return true;
                }
                else{
                    sol[x - 1][y - 1] = ' ';// Reset the sol matrix
                    return false;
                }
            } 
            else if (isSafe(letterMatrix, x - 1, y + 1, wordToFind[startOfWord], sol)) {// Upper Right
                sol[x - 1][y + 1] = letterMatrix[x - 1][y + 1];// Set answer in sol matrix
                startOfWord++;
                // recursion call to test the next area
                if(findWordSolver(letterMatrix, wordToFind, sol, x - 1, y + 1)){
                    return true;
                }
                else{
                    sol[x - 1][y + 1] = ' ';//Reset the sol matrix
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        // Declarations
        in = new Scanner(System.in);
        char[] letterArray;

        // Read info from user
        row = in.nextInt();
        col = in.nextInt();
        numOfWords = in.nextInt();

        // Create Matrix/Array based on input
        char[][] letterMatrix = new char[row][col];
        String[] wordArray = new String[numOfWords];

        // Fill the letterMatrix with letters from user
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                letterMatrix[i][j] = in.next().charAt(0);
            }
        }

        // Fill wordArray with user input
        for (int i = 0; i < numOfWords; i++) {
            wordArray[i] = in.next();
        }

        // Loop to begin to find words
        for (int i = 0; i < numOfWords; i++) {
            startOfWord = 0;
            letterArray = wordArray[i].toCharArray();
            System.out.println("Looking for " + wordArray[i]);

            // Call our main find function, if its false print error msg
            if (!findWord(letterMatrix, letterArray)) {
                System.out.println(wordArray[i] + " not found!");
            }
            System.out.println("");
        }
    }
}
