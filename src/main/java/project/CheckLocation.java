package project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.Point;
import components.Rectangle;
import components.Room;

public class CheckLocation {

	public static Map<String, List<Point>> check(Room thisRectangle, Room otherRectangle) {
		Map<String, List<Point>> outputMap = new HashMap<String, List<Point>>();
		List<Point> outputList = new ArrayList<>();

		// Check Vertical (top + height) 
		// returns "Vertical" ( left point, right point)
		for(int i=thisRectangle.getTop() ; i<thisRectangle.getTop() + thisRectangle.getHeight() ; i++) {
			for(int j=otherRectangle.getTop() ; j<otherRectangle.getTop() + otherRectangle.getHeight() ; j++) {
				if(i==j) {

					// thisRectangle is in the left of otherRectangle
					if(thisRectangle.getLeft() + thisRectangle.getWidth() <= otherRectangle.getLeft()) {
						outputList.add(new Point(i, thisRectangle.getLeft() + thisRectangle.getWidth()));
						outputList.add(new Point(j, otherRectangle.getLeft()));
					}
					// thisRectangle is in the right of otherRectangle
					else if(otherRectangle.getLeft() + otherRectangle.getWidth() <= thisRectangle.getLeft()) {
						outputList.add(new Point(j, otherRectangle.getLeft() + otherRectangle.getWidth()));
						outputList.add(new Point(i, thisRectangle.getLeft()));
					}
					outputMap.put("Vertical", outputList);
					return outputMap;
				}
			}
		}

		// Check Horizontal (left + width) 
		// returns "Horizontal" ( lower point, upper point)
		for(int i=thisRectangle.getLeft() ; i<thisRectangle.getLeft() + thisRectangle.getWidth() ; i++) {
			for(int j=otherRectangle.getLeft() ; j<otherRectangle.getLeft() + otherRectangle.getWidth() ; j++) {
				if(i==j) {

					// thisRectangle is lower from otherRectangle
					if(thisRectangle.getTop() >= otherRectangle.getTop() + otherRectangle.getHeight()) {
						outputList.add(new Point(thisRectangle.getTop(), i));
						outputList.add(new Point(otherRectangle.getTop() + otherRectangle.getHeight(), j));
					}
					// thisRectangle is upper from otherRectangle
					else if(otherRectangle.getTop() >= thisRectangle.getTop() + thisRectangle.getHeight()) {
						outputList.add(new Point(otherRectangle.getTop(), j));
						outputList.add(new Point(thisRectangle.getTop() + thisRectangle.getHeight(), i));	
					}
					outputMap.put("Horizontal", outputList);
					return outputMap;
				}
			}
		}

		// Not Vertical or Horizontal
		// Two options: high left, low right OR low left, high right

		// thisRectangle is lower from otherRectangle
		if(thisRectangle.getTop() >= otherRectangle.getTop() + otherRectangle.getHeight()) {
			// thisRectangle is in the left of otherRectangle
			if(thisRectangle.getLeft() + thisRectangle.getWidth() <= otherRectangle.getLeft()) {
				outputList.add(new Point(thisRectangle.getTop(), thisRectangle.getLeft() + thisRectangle.getWidth()-1));
				outputList.add(new Point(otherRectangle.getTop() + otherRectangle.getHeight() -1, otherRectangle.getLeft()));
				// 1 - this, 2 - other
				//		[2]
				//	[1]
				outputMap.put("LowLeft", outputList);
				return outputMap;
			}
			// thisRectangle is in the right of otherRectangle
			else if(otherRectangle.getLeft() + otherRectangle.getWidth() <= thisRectangle.getLeft()) {
				outputList.add(new Point(otherRectangle.getTop() + otherRectangle.getHeight()-1, otherRectangle.getLeft() + otherRectangle.getWidth()-1));
				outputList.add(new Point(thisRectangle.getTop(), thisRectangle.getLeft()));
				// 1 - this, 2 - other
				//	[2]
				//		[1]
				outputMap.put("HighLeft", outputList);
				return outputMap;
			}

		}
		// thisRectangle is upper from otherRectangle
		else if(otherRectangle.getTop() >= thisRectangle.getTop() + thisRectangle.getHeight()) {
			// thisRectangle is in the left of otherRectangle
			if(thisRectangle.getLeft() + thisRectangle.getWidth() <= otherRectangle.getLeft()) {
				outputList.add(new Point(thisRectangle.getTop() + thisRectangle.getHeight()-1, thisRectangle.getLeft() + thisRectangle.getWidth()-1));
				outputList.add(new Point(otherRectangle.getTop(), otherRectangle.getLeft()));
				// 1 - this, 2 - other
				//	[1]
				//		[2]
				outputMap.put("HighLeft", outputList);
				return outputMap;
			}
			// thisRectangle is in the right of otherRectangle
			else if(otherRectangle.getLeft() + otherRectangle.getWidth() <= thisRectangle.getLeft()) {
				outputList.add(new Point(otherRectangle.getTop(), otherRectangle.getLeft() + otherRectangle.getWidth()-1));
				outputList.add(new Point(thisRectangle.getTop() + thisRectangle.getHeight()-1, thisRectangle.getLeft()));
				// 1 - this, 2 - other
				//		[1]
				//	[2]
				outputMap.put("LowLeft", outputList);
				return outputMap;
			}
		}
		return outputMap;
	}

}
