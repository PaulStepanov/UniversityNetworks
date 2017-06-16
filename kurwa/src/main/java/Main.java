import game.GameEngine;
import game.entity.Player;
import game.map.objects.MapObject;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initializeMap();

        Player player = new Player();
        player.setName("Bob");
        gameEngine.addPlayer(player);

        MapObject mapObject = gameEngine.map.getMapObjList().get(2).get(2);
        System.out.println(mapObject);
    }
}
