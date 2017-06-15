package game.map.objects;

import game.actions.Action;
import game.entity.ActionResult;
import game.entity.PlayerState;

public class Wall implements MapObject {
    @Override
    public ActionResult engagePlayer(PlayerState playerState, Action action) {
        ActionResult actionResult = new ActionResult();
        actionResult.setSucceed(false);
        actionResult.setPlayerState(playerState);

        switch (action){
            case WALK:{
                actionResult.setMessageToPrint("Вы ударились лбом об стену, не лучшая идея");
            }
            case ATACK:{
                actionResult.setMessageToPrint("По голове себе лучше ударь, а не по стене");
            }
            default:{
                actionResult.setMessageToPrint("Тщетно");
            }
        }

        return actionResult;
    }
}
