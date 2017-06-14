package handlers;

import data.entity.ExecuteResult;
import data.entity.UserDI;
import exeptions.HandlersConflictExeption;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class HandlerExecutor {
    private ArrayList<Handler> handlers = new ArrayList<>();
    private UserDI userDI;

    public HandlerExecutor(UserDI userDI) {
        this.userDI = userDI;
        handlers.add(new UserHandler(userDI));
        handlers.add(new PassHandler(userDI));
        handlers.add(new ListHandler(userDI));
        handlers.add(new RetrHandler(userDI));
        handlers.add(new DeleHadler(userDI));
        handlers.add(new StatHandler(userDI));
        handlers.add(new TopHandler(userDI));
        handlers.add(new QuitHandler(userDI));
    }

    public ExecuteResult execute(String input) {
        ExecuteResult executeResult = null;
        AtomicBoolean isHandled = new AtomicBoolean(false);
        for (Handler handler : handlers) {
            if (handler.isThisHandlerSatisfy(input)) {
                //throw exeption if one already handle
                if (isHandled.get()) {
                    throw new HandlersConflictExeption();
                }
                isHandled.set(true);
                executeResult = handler.handle(input);

            }
        }
        return executeResult;
    }
}
