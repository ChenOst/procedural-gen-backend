package corridors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import components.Corridor;
import components.Point;
import components.Rectangle;
import components.Room;
import project.CheckLocation;
import project.GenerateMatrix;

/**
 * Drunkard's Walk (DW) selects vertex between the rooms
 * and starts drawing a line at the first in the direction of the second.
 * Corridor is being made in �steps�. Each step adds a corridor of a random length
 * that goes in the direction of the end point. The algorithm will continue to take
 * steps until it either runs out of maximum allowed steps, or reaches the end point.
 */
public class DW {

	private List<Corridor> corridors = new ArrayList<>();
	private ArrayList<Room> rooms = new ArrayList<>();
	private int steps;

	public String[][] generateDungeon(ArrayList<Room> rooms, int mapSize, int steps) {

		Map<String, List<Point>> map = new HashMap<String, List<Point>>();
		this.rooms = rooms;
		this.steps = steps;

		for(int i=0 ; i<rooms.size() ; i++) {
			if(i == rooms.size()-1) {	
				map = CheckLocation.check(rooms.get(rooms.size()-1), rooms.get(0));
			}
			else {
				map = CheckLocation.check(rooms.get(i), rooms.get(i+1));
			}
			drawCorridor(map);
		}
		return GenerateMatrix.generate(rooms, corridors, mapSize);
	}

	/**
	 * This function generate corridors depending on the information received about the location of the rooms.
	 * There are four different drawing options: Vertical, Horizontal, LowLeft and HighLeft.
	 */
	private void drawCorridor(Map<String, List<Point>> map) {
		String mapValueString = map.keySet().toString();
		mapValueString = mapValueString.substring(1, mapValueString.length()-1);

		if(!map.get(mapValueString).isEmpty()) {
			switch(mapValueString) {
				case "Vertical":
					vertical(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
					break;
				case "Horizontal":
					horizontal(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
					break;
				case "LowLeft":
					lowLeft(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
					break;
				case "HighLeft":
					highLeft(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
					break;
				default:
					System.err.print("Invalid value entered\n");
					break;
			}
		}
	}

	private void vertical(Point left, Point right){

		Random randon = new Random();
		int nextIndex  = left.getY() + randon.nextInt(right.getY() - left.getY() + 1);
		int startIndex = left.getY();

		boolean flag = true;
		for(int i=steps; i>0 && flag; i--) {
			Corridor newCorridor = new Corridor();
			List<Point> newPoints = new ArrayList<>();

			for(int j = startIndex; j<=nextIndex; j++) {
				if(j == right.getY()) {
					flag = false;
					break;
				}
				else {
					newPoints.add(new Point(left.getX(), j));
				}
			}

			startIndex =  nextIndex;
			nextIndex = nextIndex + randon.nextInt(right.getY() - nextIndex + 1);
			newCorridor.setPoints(newPoints);
			corridors.add(newCorridor);
		}
	}

	private void horizontal(Point low, Point high){
		Random randon = new Random();
		int nextIndex  = high.getX() + randon.nextInt(low.getX() - high.getX() + 1);
		int startIndex = high.getX();

		boolean flag = true;
		for(int i=steps; i>0 && flag; i--) {
			Corridor newCorridor = new Corridor();
			List<Point> newPoints = new ArrayList<>();

			for(int j = startIndex; j<=nextIndex; j++) {
				if(j == low.getX()) {
					flag = false;
					break;
				}
				else {
					newPoints.add(new Point(j, low.getY()));
				}
			}

			startIndex =  nextIndex;
			nextIndex = nextIndex + randon.nextInt(low.getX() - nextIndex + 1);
			newCorridor.setPoints(newPoints);
			corridors.add(newCorridor);
		}
	}

	private void lowLeft(Point lowLeft, Point highRight){
		Random randon = new Random();
		Point startPoint = new Point(lowLeft);
		int index=-1;
		boolean flag = true;
		boolean goUp = true;

		for(int i=steps; i>0 && flag; i--) {
			Corridor newCorridor = new Corridor();
			List<Point> newPoints = new ArrayList<>();
			if(goUp) {
				Point upPoint  = new Point(highRight.getX() 
						+ randon.nextInt(startPoint.getX() - highRight.getX() + 1), startPoint.getY());
				for(int k = startPoint.getX(); k >= highRight.getX(); k--) {
					if(k == highRight.getX()) {
						vertical(new Point(k, startPoint.getY()), highRight);
						flag = false;
						break;
					}
					else {
						newPoints.add(new Point(k,startPoint.getY()));
						index = k;
					}
				}
				startPoint = new Point(index, startPoint.getY());
				goUp = false;
			}
			else {
				Point leftPoint = new Point(startPoint.getX(), startPoint.getY() 
						+ randon.nextInt(highRight.getY() - startPoint.getY() + 1));
				for(int k = startPoint.getY(); k <= leftPoint.getY(); k++) {
					if(k == highRight.getY()) {
						horizontal(highRight, new Point(startPoint.getX(), k));
						flag = false;
						break;
					}
					else {
						newPoints.add(new Point(startPoint.getX(),k));
						index = k;
					}
					startPoint = new Point(startPoint.getX(), index);
					goUp = true;
				}
			}
				newCorridor.setPoints(newPoints);
				corridors.add(newCorridor);
			
		}
	}

	private void highLeft(Point highLeft, Point lowRight){

		Random randon = new Random();
		Point startPoint = new Point(highLeft);
		int index=-1;
		boolean flag = true;
		boolean goDown = true;

		for(int i=steps; i>0 && flag; i--) {
			Corridor newCorridor = new Corridor();
			List<Point> newPoints = new ArrayList<>();
			if(goDown) {
				Point downPoint  = new Point(startPoint.getX() 
						+ randon.nextInt(lowRight.getX() - startPoint.getX() + 1), startPoint.getY());
				for(int k = startPoint.getX(); k <= downPoint.getX(); k++) {
					if(k == lowRight.getX()) {
						vertical(new Point(k,startPoint.getY()), lowRight);
						flag = false;
						break;
					}
					else {
						newPoints.add(new Point(k,startPoint.getY()));
						index = k;
					}
				}
				startPoint = new Point(index, startPoint.getY());
				goDown = false;
			}
			else {
				Point leftPoint  = new Point(startPoint.getX(), startPoint.getY() 
						+ randon.nextInt(lowRight.getY() - startPoint.getY() + 1));
				for(int k = startPoint.getY(); k <= leftPoint.getY(); k++) {
					if(k == lowRight.getY()) {
						horizontal(lowRight, new Point(startPoint.getX(), k));
						flag = false;
						break;
					}
					else {
						newPoints.add(new Point(startPoint.getX(),k));
						index = k;
					}
					startPoint = new Point(startPoint.getX(), index);
					goDown = true;
				}
			}
			newCorridor.setPoints(newPoints);
			corridors.add(newCorridor);

		}
	}

	public List<Corridor> getCorridors() {
		return corridors;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
