/*          COP 3503C Assignment 4
 * This program is written by: Joshua Tyler Bandy
 */

//Imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

 public class Main{
    static Scanner stdin;
    public static int row, col;

    public static void main(String[] args){
        
        // Declarations
        stdin = new Scanner(System.in);
        position start = null, cur;
        LinkedList<position> queue = new LinkedList<>();
        HashMap<Character, List<position>> teleMap = new HashMap<>();
        int[] dX = {1, -1, 0, 0};// Directions for X-Axis
        int[] dY = {0, 0, -1, 1};// Directoins for Y-Axis
        boolean answer = false;

        // Read info from user
        row = stdin.nextInt();
        col = stdin.nextInt();

        // Set up maze/visited arrays
        char[][] maze = new char[row][col];
        boolean[][] visited = new boolean[row][col];
        
        for(int i = 0; i < row; i++){
            maze[i] = stdin.next().toCharArray();//Read in input and fill 2d maze array 
            
            Arrays.fill(visited[i], false);// Initalize visited Array

            // Find starting location and add any letters to teleportation map
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == '*') { // Case for saving starting location and any relevant information
                    start = new position(i, j, 0);
                    queue.offer(start);// Add starting point to queue
                    visited[start.row][start.col] = true;// Store starting position
                }
                else if(Character.isLetter(maze[i][j])){
                    char temp = maze[i][j];
                    Character.toUpperCase(temp);
                    teleMap.computeIfAbsent(temp, k-> new ArrayList<>()).add(new position(i, j, 0));
                }
            }
        }

        // begin BFS
        while(queue.size() > 0){
            // Get next location
            cur = queue.poll();

            if(maze[cur.row][cur.col] == '$'){
                // found answer, print moves, then exit
               System.out.println(cur.moves);
               answer = true;
               break;
            }

            // Check the four directions
            for(int i = 0; i < 4; i++){
                // update  
                int newRow = cur.row + dX[i];
                int newCol = cur.col + dY[i];

                //Check if the new values are on the grid
                if(inbounds(newRow, newCol)){
                    // check if the space is not visited or '! '
                    if(maze[newRow][newCol] != '!' && visited[newRow][newCol] == false){
                        queue.offer(new position(newRow, newCol, cur.moves + 1));//Store the locations and increaase the moves amount
                    }
                    visited[newRow][newCol] = true;// Mark 2d matrix as visited
                }
            }

            // Handling teleports
            if (Character.isLetter(maze[cur.row][cur.col])) {
                char temp = maze[cur.row][cur.col];
                if (teleMap.containsKey(temp)) {
                    // For loop to go through each of the teleport chars.
                    for (position p : teleMap.get(temp)) {
                        // If the teleport hasn't been visited add it to queue
                        if (!visited[p.row][p.col]) {
                            visited[p.row][p.col] = true;
                            queue.add(new position(p.row, p.col, cur.moves + 1));
                        }
                    }
                }
                teleMap.remove(temp);// Remove the value from the tele map to not readd it.
            }
        }

        if(!answer){// no answer was found
            System.out.println("Stuck, we need help!");
        }
    }

    // Class to hold values
    static class position {
        int row, col, moves;

        position(int newRow, int newCol, int newMoves) {
            this.row = newRow;
            this.col = newCol;
            this.moves = newMoves;
        }
    }

    public static boolean inbounds(int x, int y){
        // Ensure we don't go out of bounds, the tile isn't illegal, and we haven't visited before
        return x >= 0 && x < row && y >= 0 && y < col;
    }

 }