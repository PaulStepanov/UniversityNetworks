import data.entity.Message;
import files.MessageParser;
import dispatchers.MainDispatcher;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket socket = new ServerSocket(3200);
            MainDispatcher mainDispatcher = new MainDispatcher();
            mainDispatcher.dispatch(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
