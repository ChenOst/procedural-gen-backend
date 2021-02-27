package project;

import com.amazonaws.services.lambda.runtime.Context;

import corridors.BSPCorridors;
import corridors.DW;
import corridors.RPC;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import rooms.BSPRooms;
import rooms.RRP;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class GenerateDungeon{

	public static void main(String[] args) {

	}

	public JSONObject handleRequest(Object input, Context context) {

		int dungeonSize = 100;

		Gson gson = new Gson();

		RPC rpc = new RPC();
		DW dw = new DW();
		BSPCorridors bspCorridors = new BSPCorridors();

		String[][] bspAndrpc = rpc.generateDungeon(new BSPRooms(dungeonSize, 19).getRooms(), dungeonSize);
		String[][] bspAndbsp = bspCorridors.generateDungeon(new BSPRooms(dungeonSize, 19).getRectangles(), dungeonSize);
		String[][] bspAnddw = dw.generateDungeon(new BSPRooms(dungeonSize, 19).getRooms(), dungeonSize, 10);

		rpc = new RPC();
		dw = new DW();

		String[][] rrpAndrpc = rpc.generateDungeon(new RRP(100, 10, 10, 20, 15).getRooms(), dungeonSize);
		String[][] rrpAnddw = dw.generateDungeon(new RRP(100, 10, 10, 20, 15).getRooms(), dungeonSize, 10);

		Map<String, Object> data = new HashMap<>();
		data.put( "BSP Rooms and RPC", bspAndbsp);
		data.put( "BSP Rooms and BSP Corridors", bspAndbsp);
		data.put( "BSP Rooms and DW", bspAnddw);
		data.put( "RRP and RPC", rrpAndrpc);
		data.put( "RRP and DW", rrpAnddw);

		JSONObject obj = new JSONObject();

		obj.put("statusCode", new Integer(200));
		obj.put("body", gson.toJson(data));

		return obj;
	}
}
