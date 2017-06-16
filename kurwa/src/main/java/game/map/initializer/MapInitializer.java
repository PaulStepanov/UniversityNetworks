package game.map.initializer;

import game.entity.Player;
import game.entity.PlayerState;
import game.entity.UserPosition;
import game.map.GameMap;
import game.map.objects.Emty;
import game.map.objects.MapObject;
import game.map.objects.Wall;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MapInitializer {
    public static final int CUBE_DEMENSION = 6;
    public static final UserPosition spawnPosition = new UserPosition(2,2);

    public static GameMap generateMap() {
        GameMap gameMap = generateEmtyMapWithWalls();

        gameMap.addWithCordinatesObject(3,2,new Wall());

        return gameMap;
    }


    //creates and spawn player
    public static Player spawnPlayer(GameMap map,String name){
        Player player = new Player();
        player.setName(name);
        PlayerState playerState = new PlayerState();
        playerState.setDodgeChance((double) 30);
        playerState.setAlive(true);
        playerState.setMoney((double) 0);
        playerState.setLevel(1);
        playerState.setExperience((double) 0);
        playerState.setPower(10);

        playerState.setPosition(spawnPosition);

        player.setPlayerState(playerState);
        map.addWithCordinatesObject(spawnPosition,player);

        return player;
    }

    private static GameMap generateEmtyMapWithWalls() {
        //fill with empty
        ArrayList<ArrayList<MapObject>> mapObjList = new ArrayList<>();
        IntStream.range(0, CUBE_DEMENSION - 1).forEach(i -> {
            ArrayList<MapObject> row = new ArrayList<>();
            IntStream.range(0, CUBE_DEMENSION - 1).forEach(ri -> {
                row.add(new Emty());
            });
            mapObjList.add(row);
        });

        //adding walls on perimeter
        ArrayList<MapObject> topRow = new ArrayList<>();
        IntStream.range(0, CUBE_DEMENSION - 1).forEach(i -> {
            topRow.add(i, new Wall());
        });
        mapObjList.add(0, topRow);
        //add bottom row
        mapObjList.add(CUBE_DEMENSION - 1, topRow);

        mapObjList.forEach(mapObjects -> {
            mapObjects.add(0, new Wall());
            mapObjects.add(CUBE_DEMENSION - 1, new Wall());
        });

        return new GameMap(mapObjList);
    }
}
