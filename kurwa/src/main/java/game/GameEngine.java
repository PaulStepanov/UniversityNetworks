package game;

import game.actions.Action;
import game.entity.ActionResult;
import game.entity.Player;
import game.entity.PlayerState;
import game.map.GameMap;
import game.map.initializer.MapInitializer;

import java.util.ArrayList;

public class GameEngine {
    public GameMap map;
    private ArrayList<Player> players = new ArrayList<Player>();


    /**
     * Init map somehow
     * */
    public void initializeMap(){
        this.map = MapInitializer.generateMap();
    }

    public void addPlayer(Player player){

    }

    public PlayerState getPlayerState(Player player){
        return null;
    }

    /**
     * @return Action result
     * */
    public ActionResult makeAction(Player player, Action action){
        return null;
    }


}
