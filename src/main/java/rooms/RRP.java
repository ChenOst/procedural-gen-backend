package rooms;

import java.util.ArrayList;
import java.util.Random;

import components.Rectangle;
import components.Room;

/**
 * Random Room Placement (RRP) is algorithm that starts by randomly generating 
 * a room of a random width and length, the size controlled by maximum and minimum allowed lengths.
 * Next the algorithm picks a random point and check for floor tiles on the map, if a floor tile is found,
 * the algorithm will attempt to generate another point to place the room on the map.
 * This processes is repeated until either the desired number of rooms have been placed
 * or the maximum number of attempts (tolerance value) is reached.
 */
public class RRP {

	private ArrayList<Room> rooms = new ArrayList<Room>();

	public RRP(int mapSize, int roomsNum, int minLen,int maxLen, int attempsNum) {

		int attemps = 0;

		while(rooms.size()<roomsNum && attemps<attempsNum ) {
			attemps++;

			// Generate random width and height
			Random randon = new Random();
			int width  = minLen + randon.nextInt(maxLen - minLen + 1);
			int height  = minLen + randon.nextInt(maxLen - minLen + 1);

			// Pick a random point with enough place to create the room
			int top  = randon.nextInt(mapSize - height);
			int left  = randon.nextInt(mapSize - width);

			// Check if not cross pacing another room
			if(ifNotOverLap(top, left, width, height)) {
				rooms.add(new Room(top, left, height, width));
			}
		}
		
	}
	private boolean ifNotOverLap(int top, int left, int width, int height){
		for (Room room : rooms) { ;
			if(room.getTop() <= top && top <= room.getTop() + room.getHeight()) {
				if(room.getLeft() <= left + width && left + width <= room.getLeft() + room.getWidth()) {
					return false;
				}
				else if(room.getLeft() <= left && left <= room.getLeft() + room.getWidth()) {
					return false;
				}
			}
			else if(top <= room.getTop() && room.getTop() <= top + height) {
				if(room.getLeft() <= left && left <= room.getLeft() + room.getWidth()) {
					return false;
				}
				else if(left <= room.getLeft() && room.getLeft() <= left + width){
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
