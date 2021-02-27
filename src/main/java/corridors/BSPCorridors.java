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
 * Binary Space Partitioning (BSP) Corridors works using the tree structure used by BSP Rooms placing algorithm.
 * It sorts through the sub leaves, connecting each bottom leaf to its partner that shares a 
 * parent. When all sub-leafs are paired it connects one out of each pair to another pair,
 * until all sub-leafs containing rooms are connected.
 */
public class BSPCorridors {

	private List<Corridor> corridors = new ArrayList<>();
	private ArrayList<Room> rooms = new ArrayList<>();
	private ArrayList<Room> roomsTemp = new ArrayList<>();
	private Map<String, List<Point>> map = new HashMap<String, List<Point>>();
	private Rectangle rightLeaf = null;
	private Rectangle leftLeaf = null;
	
	public String[][] generateDungeon(ArrayList<Rectangle> rectangles, int mapSize) {
		Map<String, List<Point>> map = new HashMap<String, List<Point>>();
		connectRooms(rectangles.get(0));
		for(int i=0; i<roomsTemp.size()-1; i++) {
			map = CheckLocation.check(rooms.get(i), rooms.get(i + 1));
			corridors.add(GenerateCorridors.drawCorridor(map));
		}
		return GenerateMatrix.generate(rooms, corridors, mapSize);
	}
	
	private Rectangle connectRooms(Rectangle rectangle) {
		
		if(rectangle.getLeftChild() == null && rectangle.getRightChild() == null) {
			rooms.add(rectangle.getRoom());
			return rectangle;
		}
		if(rectangle.getRightChild() != null) {
			rightLeaf = connectRooms(rectangle.getRightChild());
		}
		if(rectangle.getLeftChild() != null) {
			leftLeaf = connectRooms(rectangle.getLeftChild());
		}
		if(rightLeaf != null && leftLeaf != null) {
			map = CheckLocation.check(leftLeaf.getRoom(), rightLeaf.getRoom());
			corridors.add(GenerateCorridors.drawCorridor(map));
			roomsTemp.add(leftLeaf.getRoom());
			return leftLeaf;
		}
		return leftLeaf;
	}

	public List<Corridor> getCorridors() {
		return corridors;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
