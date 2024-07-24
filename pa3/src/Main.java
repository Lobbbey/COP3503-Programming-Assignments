/*
 *          Summer 24
 *    COP 3503C Assignment 3
 * This program was written by: Joshua Tyler Bandy
 */

//Imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // Declarations
        Scanner stdin = new Scanner(System.in);
        int numComputers, numPairs, numToDestroy, item1, item2;
        long height = 0, currentConnectivity = 0;

        // Collect info from user
        numComputers = stdin.nextInt();
        numPairs = stdin.nextInt();
        numToDestroy = stdin.nextInt();
        
        dset mySet  = new dset(numComputers);// Create Set
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] edgeList = new ArrayList[numPairs];// Create edge list
        int[] destroyList = new int[numToDestroy];
        long[] results = new long[numToDestroy + 1];// Store connectivity
        boolean[] exists = new boolean[numPairs];
        Arrays.fill(exists, true);// Initialize exists array to true
        
        // Create edgeList
        for(int i = 0; i < numPairs; i++){
            edgeList[i] = new ArrayList<Integer>();
            item1 = (stdin.nextInt() - 1);// -1 to account for 0 based indexing
            item2 = (stdin.nextInt() - 1);// -1 to account for 0 based indexing
            edgeList[i].add(item1);
            edgeList[i].add(item2);
        }
        
        // Get sections to destroy 
        for(int i = 0; i < numToDestroy; i++){
            destroyList[i] = stdin.nextInt() - 1;// -1 to account for 0 based indexing
            exists[destroyList[i]] = false;
        }
        
        // Get Set information 
        for(int i = 0; i < numPairs; i++){
            if(exists[i]){
                item1 = edgeList[i].get(0);
                item2 = edgeList[i].get(1);
                mySet.union(item1, item2);
            }
        }

        //build connectivity backwards
        for(int j = numToDestroy - 1; j >= 0; j--){
            //reset variables
            height = 0;
            currentConnectivity = 0;
            
            for(int i = 0; i < numComputers; i++){
                if(mySet.find(i) == i){
                    height = mySet.getHeight(i);
                    currentConnectivity += height * height;
                }
            }
            
            results[j] = currentConnectivity;
            //Add what was deleted
            item1 = edgeList[destroyList[j]].get(0);
            item2 = edgeList[destroyList[j]].get(1);
            mySet.union(item1, item2);
        }

        // Reset Variable
        height = 0;
        currentConnectivity = 0;
        
        // Find intial connectivity
        for (int i = 0; i < numComputers; i++) {
            if (mySet.find(i) == i) {
                height = mySet.getHeight(i);
                currentConnectivity += height * height;
            }
        }
        System.out.println(currentConnectivity);// Print initial connectivity
        
        for(int i = 0; i < numToDestroy; i++){
            System.out.println(results[i]);
        }

        stdin.close();
    }
}

// Disjoint set from our class
class dset {
    // Declare Variables
    private pair[] parents;

    // numTrees initialize
    public dset(int n) {
        parents = new pair[n];
        for (int i = 0; i < n; i++) {
            parents[i] = new pair(i, 1);
        }
    }

    // Create the initial state of a disjoint set of elements, 0 to n-1
    // returns the root node of the tree storing id.
    public int find(int id) {
        // I am the root of the tree
        if (id == parents[id].getID()) {
            return id;
        }

        // Find my parent's root.
        int res = find(parents[id].getID());

        // if res it not my existing parent, make it parent
        if (res != parents[id].getID()) {
            // attach me directly to the root of my tree.
            parents[id].setID(res);
            parents[res].decHeight();// decrease height as id is leveled up
        }

        return res;
    }

    public int getHeight(int id){
        int root1 = find(id);
        return parents[root1].getHeight();
    }

    public boolean union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);

        // No union needed
        if (root1 == root2) {
            return false;
        }

        // Attach tree 2 to tree 1
        if (parents[root1].getHeight() > parents[root2].getHeight()) {
            parents[root2].setID(root1);
            parents[root1].incHeight(parents[root2].getHeight());
        } else if (parents[root2].getHeight() > parents[root1].getHeight()) {
            // Attach tree 1 to tree 2
            parents[root1].setID(root2);
            parents[root2].incHeight(parents[root1].getHeight());
        } else {// Same height case - just attach tree 2 to tree 1, adjust height
            parents[root2].setID(root1);
            parents[root1].incHeight(1);
        }

        // We successfully did a union
        return true;
    }
}

class pair {

	private int ID; 
	private int height;

	public pair(int myNum, int myHeight) {
		ID = myNum;
		height = myHeight;
	}

	public int getHeight() {
		return height;
	}

    public void setHeight(int newHeight){
        height = newHeight;
    }

	public int getID() {
		return ID;
	}

	public void incHeight(int inc) {
		height += inc;
	}

    public void decHeight() {
		height--;
	}

	public void setID(int newID) {
		ID = newID;
	}
}