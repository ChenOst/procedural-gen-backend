package corridors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.Corridor;
import components.Point;
import components.Rectangle;
import components.Room;
import project.CheckLocation;
import project.GenerateCorridors;
import project.GenerateMatrix;

/**
 * Random Point Connect (RPC) is a brute force algorithm that works by first iterating
 * through the list of rooms created by the room generating algorithm. It connects each
 * item in the list to the one next to it, so the first room, at index 0, is connected to the
 * room at index 1 and the last room (index is the number_of_rooms - 1) is connected the
 * first room.
 */
public class RPC {

	private List<Corridor> corridors = new ArrayList<>();
	private ArrayList<Room> rooms = new ArrayList<>();

	public String[][] generateDungeon(ArrayList<Room> rooms, int mapSize) {

		Map<String, List<Point>> map = new HashMap<String, List<Point>>();
		this.rooms = rooms;

		for(int i=0 ; i<rooms.size() && rooms.size()>1; i++) {
			if(i == rooms.size()-1) {	
				map = CheckLocation.check(rooms.get(rooms.size()-1), rooms.get(0));
			}
			else {
				map = CheckLocation.check(rooms.get(i), rooms.get(i+1));
			}
			corridors.add(GenerateCorridors.drawCorridor(map));
		}
		return GenerateMatrix.generate(rooms, corridors, mapSize);
	}
	public List<Corridor> getCorridors() {
		return corridors;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}

