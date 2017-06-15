package game.map.objects;

import game.actions.Action;
import game.entity.ActionResult;
import game.entity.PlayerState;

public class Emty implements MapObject {
    @Override
    public ActionResult engagePlayer(PlayerState playerState, Action action) {


        return null;
    }
}
