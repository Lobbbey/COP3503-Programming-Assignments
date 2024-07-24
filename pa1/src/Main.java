/*COP 3503C Assignment 1
 * This program is written by: Joshua Tyler Bandy*/

//Imports
import java.util.Scanner;
import java.util.HashSet;

class arcadeInfo{
    private int higher, lower, tickets;

    public int getHigher(){
        return higher;
    }
    
    public void setHigher(int newHigher){
        this.higher = newHigher;
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int newLower) {
        this.lower = newLower;
    }

    public int getTickets(){
        return tickets;
    }

    public void setTickets(int newTickets){
        this.tickets = newTickets;
    }

}

public class Main {
    static Scanner in;
    public static arcadeInfo arcade;

    public static void getCandidatePair(int A[], int target){//Function for sorted Array
        int len = A.length;
        int sum, i = 0, j = len -1;

        while(i < len && j > 0){//Check the lowest part of the array and the highest
            sum = A[i] + A[j];
            if(sum == target && A[i] != A[j]){
                if(A[i] < A[j]){
                    arcade.setLower(A[i]);
                    arcade.setHigher(A[j]);
                }
                else{
                    arcade.setLower(A[j]);
                    arcade.setHigher(A[i]);
                }
                return;
            }
            else if(A[i] < A[j]){
                i++;
            }
            else if(A[j] < A[i]){
                j--;
            }
            else if(A[i] == A[j]){
                i = 0;
                j--;
            }
        }
        arcade.setLower(0);
        arcade.setHigher(0);
    }

    public static void main(String[] args) throws Exception {
        //Declarations
        in = new Scanner(System.in);
        arcade = new arcadeInfo();
        int sorted, numOfTestCases, arrayLen, cost, tickets;
        int[] costArray;
        HashSet<Integer>  costHashSet = new HashSet<Integer>();

        numOfTestCases = in.nextInt();

        for(int i = 0; i < numOfTestCases; i++){//Loop to get info for test cases
            arcade.setLower(0);
            arcade.setHigher(0);
            sorted = in.nextInt();
            arrayLen = in.nextInt();
            costArray = new int[arrayLen];
            
            switch (sorted) {
                case 0://Unsorted Array
                    for (int j = 0; j < arrayLen; j++) {
                        cost = in.nextInt();
                        costHashSet.add(cost);
                    }

                    tickets = in.nextInt();
                    arcade.setTickets(tickets);

                    for (Integer k : costHashSet) {// Check the hash set for a match

                        if (costHashSet.contains((arcade.getTickets() - k)) && ((arcade.getTickets() - k) != k)) {
                            arcade.setLower(k);
                            arcade.setHigher((arcade.getTickets() - k));
                            costHashSet.clear();
                            break;
                        }
                    }

                    break;

                case 1://Sorted array
                    for(int j = 0; j < arrayLen; j++){
                        cost = in.nextInt();
                        costArray[j] = cost;
                    }

                    tickets = in.nextInt();
                    arcade.setTickets(tickets);

                    getCandidatePair(costArray, tickets);
                    break;
            
                default:
                    break;
            }
            
            if(arcade.getLower() == 0 && arcade.getHigher() == 0){//No matching pair was found
                System.out.println("Test case#" + (i + 1) + ": No way you can spend exactly " + arcade.getTickets() + " points.");
            }
            else{
                System.out.println("Test case#" + (i + 1) + ": Spend " + arcade.getTickets() + " points by playing the games with " + arcade.getLower() + " points and " + arcade.getHigher() + " points.");
            }
        }
    }
}
