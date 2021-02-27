import corridors.BSPCorridors;
import corridors.DW;
import corridors.RPC;
import org.junit.Assert;
import org.junit.Test;
import project.GenerateMatrix;
import rooms.BSPRooms;
import rooms.RRP;

import static org.junit.Assert.assertEquals;

public class TestAlgorithms {

    @Test()
    public void bspRoomsAndRPC_RoomsTest() {
        int dungeonSize = 25;
        RPC rpc = new RPC();
        BSPRooms bsp = new BSPRooms(dungeonSize, 5);

        String[][] bspAndrpc = rpc.generateDungeon(bsp.getRooms(), dungeonSize);
        GenerateMatrix.print("Testing BSP Rooms and RPC - Rooms", bspAndrpc);
        Assert.assertEquals(3, rpc.getRooms().size());
    }

    @Test()
    public void bspRoomsAndRPC_CorridorsTest() {
        int dungeonSize = 25;
        RPC rpc = new RPC();
        BSPRooms bsp = new BSPRooms(dungeonSize, 5);

        String[][] bspAndrpc = rpc.generateDungeon(bsp.getRooms(), dungeonSize);
        GenerateMatrix.print("Testing BSP Rooms and RPC - Corridors", bspAndrpc);
        Assert.assertEquals(3, rpc.getCorridors().size());
    }

    @Test()
    public void bspRoomsAndBspCorridors_RoomsTest() {
        int dungeonSize = 25;
        BSPCorridors bspCorridors = new BSPCorridors();
        BSPRooms bsp = new BSPRooms(dungeonSize, 3);

        String[][] bspAndbsp = bspCorridors.generateDungeon(bsp.getRectangles(), dungeonSize);
        GenerateMatrix.print("Testing BSP Rooms and BSP Corridors - Rooms", bspAndbsp);
        Assert.assertEquals(2, bspCorridors.getRooms().size());
    }

    @Test()
    public void bspRoomsAndBspCorridors_CorridorsTest() {
        int dungeonSize = 25;
        BSPCorridors bspCorridors = new BSPCorridors();
        BSPRooms bsp = new BSPRooms(dungeonSize, 3);

        String[][] bspAndbsp = bspCorridors.generateDungeon(bsp.getRectangles(), dungeonSize);
        GenerateMatrix.print("Testing BSP Rooms and BSP Corridors - Corridors", bspAndbsp);
        Assert.assertEquals(1, bspCorridors.getCorridors().size());
    }

    @Test()
    public void bspRoomsAndDW_RoomsTest() {
        int dungeonSize = 25;
        DW dw = new DW();
        BSPRooms bsp = new BSPRooms(dungeonSize, 5);

        String[][] bspAnddw = dw.generateDungeon(bsp.getRooms(), dungeonSize, 10);
        GenerateMatrix.print("Testing BSP Rooms and DW - Rooms", bspAnddw);
        Assert.assertEquals(3, dw.getRooms().size());
    }

    @Test()
    public void bspRoomsAndDW_CorridorsTest() {
        int dungeonSize = 25;
        DW dw = new DW();
        BSPRooms bsp = new BSPRooms(dungeonSize, 5);

        String[][] bspAnddw = dw.generateDungeon(bsp.getRooms(), dungeonSize, 10);
        GenerateMatrix.print("Testing BSP Rooms and DW - Corridors", bspAnddw);
        Assert.assertNotNull(dw.getCorridors());
    }

    @Test()
    public void rrpRoomsAndRPC_RoomsTest() {
        int dungeonSize = 25;
        int minMaxLen = 24;
        RPC rpc = new RPC();
        RRP rrp = new RRP(dungeonSize,1,minMaxLen, minMaxLen,1);

        String[][] rrpAndrpc = rpc.generateDungeon(rrp.getRooms(), dungeonSize);
        GenerateMatrix.print("Testing RRP and RPC - Rooms", rrpAndrpc);
        Assert.assertEquals(1, rpc.getRooms().size());
    }

    @Test()
    public void rrpRoomsAndRPC_CorridorsTest() {
        int dungeonSize = 25;
        int minMaxLen = 24;
        RPC rpc = new RPC();
        RRP rrp = new RRP(dungeonSize,1,minMaxLen, minMaxLen,1);

        String[][] rrpAndrpc = rpc.generateDungeon(rrp.getRooms(), dungeonSize);
        GenerateMatrix.print("Testing RRP and RPC - Corridors", rrpAndrpc);
        Assert.assertEquals(0, rpc.getCorridors().size());
    }

    @Test()
    public void rrpRoomsAndDW_RoomsTest() {
        int dungeonSize = 25;
        int minMaxLen = 24;
        DW dw = new DW();
        RRP rrp = new RRP(dungeonSize,1,minMaxLen, minMaxLen,1);

        String[][] bspAnddw = dw.generateDungeon(rrp.getRooms(), dungeonSize, 10);
        GenerateMatrix.print("Testing RRP and DW - Rooms", bspAnddw);
        Assert.assertEquals(1, dw.getRooms().size());
    }

    @Test()
    public void rrpRoomsAndDW_CorridorsTest() {
        int dungeonSize = 25;
        int minMaxLen = 24;
        DW dw = new DW();
        RRP rrp = new RRP(dungeonSize,1,minMaxLen, minMaxLen,1);

        String[][] bspAnddw = dw.generateDungeon(rrp.getRooms(), dungeonSize, 10);
        GenerateMatrix.print("Testing RRP and DW - Corridors", bspAnddw);
        Assert.assertEquals(0, dw.getCorridors().size());
    }
}
