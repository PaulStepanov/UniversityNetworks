package handlers;

import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
import data.entity.User;

public abstract class Handler {
    abstract public ExecuteResult handle(String input);

    private String pattern = null;

    public Handler(String pattern) {
        this.pattern = pattern;
    }

    public boolean isThisHandlerSatisfy(String string){
        if (string.matches(pattern)){
            return  true;
        }
        return false;
    }

}
