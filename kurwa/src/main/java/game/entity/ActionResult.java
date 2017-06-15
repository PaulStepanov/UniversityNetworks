package game.entity;

public class ActionResult {
    private PlayerState playerState;
    //if smth changes in player state or message was printed
    private boolean isSucceed;
    private String messageToPrint;

    public ActionResult(PlayerState playerState, boolean isSucceed, String messageToPrint) {
        this.playerState = playerState;
        this.isSucceed = isSucceed;
        this.messageToPrint = messageToPrint;
    }

    public ActionResult(){}

    public PlayerState getPlayerState() {
        return playerState;
    }

    public ActionResult setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
        return this;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public ActionResult setSucceed(boolean succeed) {
        isSucceed = succeed;
        return this;
    }

    public String getMessageToPrint() {
        return messageToPrint;
    }

    public ActionResult setMessageToPrint(String messageToPrint) {
        this.messageToPrint = messageToPrint;
        return this;
    }
}
