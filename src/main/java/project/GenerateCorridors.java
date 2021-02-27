package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import components.Corridor;
import components.Point;

/**
 * This class generate corridors depending on the information received about the location of the rooms.
 * There are four different drawing options: Vertical, Horizontal, LowLeft and HighLeft.
 * This class used by BSP Corridors and RPC.
 */
public class GenerateCorridors {
	
	public static Corridor drawCorridor(Map<String, List<Point>> map) {
		String mapValueString = map.keySet().toString();
		mapValueString = mapValueString.substring(1, mapValueString.length()-1);
		if(!map.get(mapValueString).isEmpty()) {
			switch(mapValueString) {
			case "Vertical":
				return vertical(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
			case "Horizontal":
				return horizontal(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
			case "LowLeft":
				return lowLeft(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
			case "HighLeft":
				return highLeft(map.get(mapValueString).get(0), map.get(mapValueString).get(1));
			default:
				System.err.print("Invalid value entered");
				break;
			}
		}
		return new Corridor();
	}
	
	private static Corridor vertical(Point left, Point right){
		Corridor newCorridor = new Corridor();
		List<Point> newPoints = new ArrayList<>();
		newPoints.add(left);
		newPoints.add(right);
		for(int i = left.getY()+1 ; i<right.getY(); i++) {
			newPoints.add(new Point(left.getX(),i));
		}
		newCorridor.setPoints(newPoints);
		return newCorridor;
	}

	private static Corridor horizontal(Point low, Point high){
		Corridor newCorridor = new Corridor();
		List<Point> newPoints = new ArrayList<>();
		newPoints.add(low);
		newPoints.add(high);
		for(int i = high.getX()+1 ; i<low.getX(); i++) {
			newPoints.add(new Point(i,high.getY()));
		}
		newCorridor.setPoints(newPoints);
		return newCorridor;
	}

	private static Corridor lowLeft(Point lowLeft, Point highRight){
		Corridor newCorridor = new Corridor();
		List<Point> newPoints = new ArrayList<>();
		newPoints.add(lowLeft);
		newPoints.add(highRight);
		for(int i=highRight.getX(); i<lowLeft.getX();i++) {
			newPoints.add(new Point(i, lowLeft.getY()));
		}
		for(int i=lowLeft.getY()+1; i<highRight.getY(); i++) {
			newPoints.add(new Point(highRight.getX(),i));
		}
		newCorridor.setPoints(newPoints);
		return newCorridor;
	}

	private static Corridor highLeft(Point highLeft, Point lowRight){
		Corridor newCorridor = new Corridor();
		List<Point> newPoints = new ArrayList<>();
		newPoints.add(highLeft);
		newPoints.add(lowRight);
		for(int i= highLeft.getY(); i<=lowRight.getY(); i++) {
			newPoints.add(new Point(highLeft.getX(), i));
		}
		for(int i= highLeft.getX(); i<=lowRight.getX(); i++) {
			newPoints.add(new Point(i, lowRight.getY()));
		}
		newCorridor.setPoints(newPoints);
		return newCorridor;
	}

}
