import dispatchers.MainDispatcher;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(3200);
            MainDispatcher mainDispatcher = new MainDispatcher();
            mainDispatcher.dispatch(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
