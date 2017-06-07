package handlers;

import data.entity.User;
import exeptions.HandlersConflictExeption;

import java.util.ArrayList;

public class HandlerExecutor {
    private ArrayList<Handler> handlers = new ArrayList<>();
    private User user;

    public HandlerExecutor(User user) {
        this.user = user;
        handlers.add(new UserHandler(user));
    }

    public String execute(String input){
        final StringBuilder result = new StringBuilder();
        handlers.forEach(handler -> {
            if (handler.isThisHandlerSatisfy(input)){
                //throw exeption if one already handle
                if(result.length()!=0){
                    throw new HandlersConflictExeption();
                }
                result.append(handler.handle(input));
            }
        });
        return result.toString();
    }
}
