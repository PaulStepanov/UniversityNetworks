package game.map.objects;

import game.actions.Action;
import game.entity.ActionResult;
import game.entity.Player;
import game.entity.PlayerState;

public interface MapObject {
    public ActionResult engagePlayer(PlayerState playerState, Action action);

    public String getDescription();
}
