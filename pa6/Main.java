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

        

        scanIn.close();
    }    
}
