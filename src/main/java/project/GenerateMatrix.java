package project;

import components.Corridor;
import components.Point;
import components.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the matrix of each algorithm.
 * It take the rooms and corridors created and set the info into 2D array.
 */
public class GenerateMatrix {

    // Creates the matrix
    public static String[][] generate(ArrayList<Room> rooms, List<Corridor> corridors, int mapSize){
        String[][] lines = new String[mapSize][];
        for( int i = 0; i < mapSize; i++ ) {
            lines[ i ] = new String[mapSize];
            for( int j = 0; j < mapSize; j++ )
                lines[i][j] = ".";
        }
        //int dungeonCount = -1;
        for( Room r : rooms ) {
            //dungeonCount++;
            for( int i = 0; i < r.getHeight(); i++ ) {
                for( int j = 0; j < r.getWidth(); j++ )
                    //lines[ r.getTop() + i ][ r.getLeft()+ j ] = String.valueOf(dungeonCount);
                    lines[ r.getTop() + i ][ r.getLeft()+ j ] = "#";
            }
        }
        for(Corridor c: corridors) {
            for(Point point : c.getPoints()) {
                lines[point.getX() ][ point.getY()] = "#";
            }
        }
        return lines;
    }

    // Prints the matrix
    public static void print(String title, String[][] arr) {
        System.out.println(title);
        for( int i = 0; i < arr.length; i++ ) {
            for( int j = 0; j < arr[0].length; j++ ) {
                System.out.print(arr[ i ][ j ] );
            }
            System.out.println();
        }
        System.out.println();
    }
}
