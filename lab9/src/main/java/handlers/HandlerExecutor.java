package handlers;

import data.entity.User;
import data.entity.UserDI;
import exeptions.HandlersConflictExeption;

import java.util.ArrayList;

public class HandlerExecutor {
    private ArrayList<Handler> handlers = new ArrayList<>();
    private UserDI userDI;

    public HandlerExecutor(UserDI userDI) {
        this.userDI = userDI;
        handlers.add(new UserHandler(userDI));
        handlers.add(new PassHandler(userDI));
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
