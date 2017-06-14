package dispatchers;

import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
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

                ExecuteResult executeResult = handlerExecutor.execute(userInput.toString());
                if(executeResult==null){
                    out.write("Wrong comand i'm not kidding, enter smth valid \n");
                    out.flush();
                    continue;
                }


                //writing +OK or -ERR message
                if (executeResult.getExecuteStatus().equals(ExecuteStatus.OK)){
                    out.write(ExecuteStatus.OK.getValue()+" ");
                } else {
                    out.write(ExecuteStatus.ERR.getValue()+" ");
                }

                out.write(executeResult.getResultMessage());
                out.write("\n");
                out.write("\n");
                out.flush();

                if (executeResult.isExit()){
                    break;
                }
            }


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
