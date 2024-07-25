/*
 * This Program Was Written By:
 * Joshua Tyler Bandy
 */

//Imports
import java.util.*;

public class Main{

    //Global Declarations
    public static int numStudents, totalProb = 0;

    public static void main(String[] args){
        //Declarations
        Scanner scanIn = new Scanner(System.in);

        numStudents = scanIn.nextInt();//Get number of students

        //Create arrays based on num of students
        int[] row1 = new int[numStudents];
        int[] row2 = new int[numStudents];

        //Collect input for each row
        for(int i = 0; i < numStudents; i++){
            row1[i] = scanIn.nextInt();
        }

        for(int i = 0; i < numStudents; i++){
            row2[i] = scanIn.nextInt();
        }

        //Create then initilize the completed rows
        int[] compR1 = new int[numStudents];
        int[] compR2 = new int[numStudents];

        compR1[0] = row1[0];
        compR2[0] = row2[0];
      
        for(int i = 1; i < numStudents; i++){
            compR1[i] = Math.max(compR1[i -1], compR2[i] + row1[i]);
            compR2[i] = Math.max(compR2[i -1], compR1[i] + row2[i]);                       
        }
        

        scanIn.close();
    }    
}
