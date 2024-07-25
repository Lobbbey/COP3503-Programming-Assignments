/*
 * This Program Was Written By:
 * Joshua Tyler Bandy
 */

//Imports
import java.util.*;

public class Main{

    public static void main(String[] args){
        //Declarations
        Scanner scanIn = new Scanner(System.in);
        int numStudents, totalProb = 0;

        numStudents = scanIn.nextInt();//Get number of students

        //Create arrays based on num of students
        int[] row1 = new int[numStudents];
        int[] row2 = new int[numStudents];

        //Collect input for each row in row1
        for(int i = 0; i < numStudents; i++){
            row1[i] = scanIn.nextInt();
        }

        //Collect input for each row in row2
        for(int i = 0; i < numStudents; i++){
            row2[i] = scanIn.nextInt();
        }

        //Create then initilize the completed rows
        int[][] compRows = new int[2][numStudents];
        compRows[0][0] = row1[0];
        compRows[1][0] = row2[0]; 
     
        //Find the max number of problems
        for(int i = 1; i < numStudents; i++){
            compRows[0][i] = Integer.max(compRows[0][i-1], compRows[1][i - 1] + row1[i]);         
            compRows[1][i] = Integer.max(compRows[1][i-1], compRows[0][i - 1] + row2[i]);         
        }

        //Last max check then print result
        totalProb = Integer.max(compRows[0][numStudents - 1], compRows[1][numStudents - 1]);
        System.out.println(totalProb);
        scanIn.close();
    }    
}
