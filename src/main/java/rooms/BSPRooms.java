package rooms;
import java.util.ArrayList;
import java.util.Random;

import components.Rectangle;
import components.Room;

/**
 * Binary Space Partitioning (BSP) Room Placement works by
 * dividing the map into a series of rectangles, and then placing a room in each one on the
 * map. The rectangle can be split either vertically or horizontally, and the minimum size
 * of the split is determined based on the minimum size of the rooms.
 * @author https://stackoverflow.com/questions/4997642/simple-example-of-bsp-dungeon-generation
 */
public class BSPRooms {

	private static Random rnd = new Random(); 
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Room> rooms = new ArrayList<Room>();

	public BSPRooms(int mapSize, int rectanglesNum){

		Rectangle root = new Rectangle(0, 0, mapSize, mapSize);
		rectangles.add(root);

		// 19 Rectangles will give us 10 leaf areas
		//					*
		// 				  /   \
		//				 *     *
		//			   /  \    / \
		//			  *    *  *   *
		//			 /\   /\  /\  /\
		//			*  4 5 6  7 8 9 *
		//		   /\  				/\
		//		  0  1  		   2  3
		while(rectangles.size() < rectanglesNum) { 
			// choose a random element
			int splitIdx = rnd.nextInt(rectangles.size()); 
			Rectangle toSplit = rectangles.get(splitIdx); 
			//attempt to split
			if(toSplit.split()) { 
				rectangles.add(toSplit.getLeftChild());
				rectangles.add(toSplit.getRightChild());
			} 
		}
		
		//generate dungeons
		root.generateDungeon();
		
		for(Rectangle r: rectangles) {
			if(r.getRoom() != null) {
				rooms.add(r.getRoom());
			}
		}
	}
	
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}

}
