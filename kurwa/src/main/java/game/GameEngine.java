package game;

import game.actions.Action;
import game.entity.ActionResult;
import game.entity.Player;
import game.entity.PlayerState;
import game.entity.UserPosition;
import game.map.GameMap;
import game.map.initializer.MapInitializer;
import game.map.objects.MapObject;

import java.util.ArrayList;

public class GameEngine {
    public GameMap map;
    private ArrayList<Player> players = new ArrayList<Player>();


    /**
     * Init map somehow
     */
    public void initializeMap() {
        this.map = MapInitializer.generateMap();
    }

    public Player addPlayer(Player player) {
        return MapInitializer.spawnPlayer(map, player.getName());
    }

    public PlayerState getPlayerState(Player player) {
        return null;
    }

    /**
     * @return Action result
     */
    public ActionResult makeAction(Player player, Action action) {
        switch (action) {
            case WALK_N: {
                UserPosition position = player.getPlayerState().getPosition();
                UserPosition newPosition = new UserPosition(position.getPosX() - 1, position.getPosX());
                MapObject mapObject = map.getObjectByCordinates(newPosition);

                ActionResult actionResult = mapObject.engagePlayer(player.getPlayerState(), action);
                

            }


        }
        return null;
    }


}
