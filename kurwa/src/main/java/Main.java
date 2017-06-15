import game.GameEngine;
import game.map.objects.MapObject;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initializeMap();
        MapObject mapObject = gameEngine.map.getMapObjList().get(2).get(3);
        System.out.println(mapObject);
    }
}
