package handlers;

import data.entity.User;

public interface Handler {
    public boolean isThisHandlerSatisfy(String string);
    public String handle(String input);
}
