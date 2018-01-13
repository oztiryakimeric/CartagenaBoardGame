package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class Server {
    public static final int PORT = 1414;

    private int playerCount;

    private ServerSocket serverSocket;
    private List<Connection> connectionList;

    public Server(int playerCount) throws IOException{
        this.playerCount = playerCount;
        this.connectionList = new ArrayList<>(playerCount);
        this.serverSocket = new ServerSocket(PORT);

        System.out.println("Server started to listening...");
        listenConnections();
        listenCommands();
    }

    private void listenConnections() throws IOException{
        while(true){
            Socket socket = serverSocket.accept();
            connectionList.add(new Connection(socket));
            System.out.println("There is a new connection.");

            if(connectionList.size() == playerCount)
                break;
        }
    }

    private void listenCommands(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    for(Connection connection: connectionList){
                        try {
                            Command command = (Command) connection.getObjectInputStream().readObject();
                            broadcast(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void broadcast(Command command) throws IOException {
        for(Connection connection: connectionList){
            connection.getObjectOutputStream().writeObject(command);
        }
    }

    public void send(int playerId, Command command) throws IOException{
        connectionList.get(playerId).getObjectOutputStream().writeObject(command);
    }

}
