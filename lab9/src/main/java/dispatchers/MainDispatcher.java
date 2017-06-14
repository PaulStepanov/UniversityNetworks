package dispatchers;

import data.entity.User;
import data.entity.UserDI;
import handlers.HandlerExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class MainDispatcher implements Dispatcher {

    @Override
    public void dispatch(ServerSocket serverSocket) {
        try {
            //this code executes for each client
            Socket clientSocket = serverSocket.accept();
            UserDI userDI = new UserDI();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //reading all user input
            HandlerExecutor handlerExecutor = new HandlerExecutor(new UserDI());

            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String line = in.readLine();

                //checking if user closed conection
                if (line==null){
                    break;
                }

                StringBuilder userInput = new StringBuilder("");
                userInput.append(line);

                String executeResult = handlerExecutor.execute(userInput.toString());


                out.write(executeResult);
                out.write("\n");
                out.flush();
                System.out.println("writed " + executeResult);
            }


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
