package game.entity;

import game.actions.Action;
import game.map.objects.MapObject;

/**
 * @alert !!!!!!!!Must be implemented equals and hash code!!!!!!!!!
 * */
public class Player implements MapObject{
    private PlayerState playerState;
    private String name;

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ActionResult engagePlayer(PlayerState playerState, Action action) {
        return null;
    }

    @Override
    public String getDescription() {
        return "Player:"+name;
    }
}
