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

		int dungeonSize = 50;

		Gson gson = new Gson();

		RPC rpc = new RPC();
		DW dw = new DW();
		BSPCorridors bspCorridors = new BSPCorridors();

		String[][] bspAndrpc = rpc.generateDungeon(new BSPRooms(dungeonSize, 19).getRooms(), dungeonSize);
		String[][] bspAndbsp = bspCorridors.generateDungeon(new BSPRooms(dungeonSize, 19).getRectangles(), dungeonSize);
		String[][] bspAnddw = dw.generateDungeon(new BSPRooms(dungeonSize, 19).getRooms(), dungeonSize, 10);

		rpc = new RPC();
		dw = new DW();

		String[][] rrpAndrpc = rpc.generateDungeon(new RRP(dungeonSize, 10, 7, 15, 15).getRooms(), dungeonSize);
		String[][] rrpAnddw = dw.generateDungeon(new RRP(dungeonSize, 10, 7, 15, 15).getRooms(), dungeonSize, 10);

		Map<String, Object> data = new HashMap<>();

		// Used in client side as enum - can't have white spaces
		data.put( "BSPRoomsAndRPC", bspAndrpc);
		data.put( "BSPRoomsAndBSPCorridors", bspAndbsp);
		data.put( "BSPRoomsAndDW", bspAnddw);
		data.put( "RRPAndRPC", rrpAndrpc);
		data.put( "RRPAndDW", rrpAnddw);

		JSONObject obj = new JSONObject();

		obj.put("statusCode", new Integer(200));
		obj.put("body", gson.toJson(data));

		return obj;
	}

	private static void Print(String[][] mat){
		for (int i=0 ; i<mat.length ; i++){
			for (int j=0; j< mat[i].length; j++){
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
