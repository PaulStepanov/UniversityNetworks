package dispatchers;

import data.entity.User;
import handlers.HandlerExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainDispatcher implements Dispatcher{
    private User user = null;
    private HandlerExecutor handlerExecutor = new HandlerExecutor(user);

    @Override
    public void dispatch(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //reading all user input
            String line = in.readLine();
            StringBuilder userInput = new StringBuilder("");
            while(line!=null){
                userInput.append(line);
            }
            String executeResult = handlerExecutor.execute(userInput.toString());

            out.write(executeResult);
            out.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
