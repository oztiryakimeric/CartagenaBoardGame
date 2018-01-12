import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class Server {
    private static final int PORT = 1414;

    private int playerCount;

    private ServerSocket serverSocket;
    private List<ClientConnection> connectionList;

    public static void main(String[] args) throws IOException{
        new Server(3);
    }

    public Server(int playerCount) throws IOException{
        this.playerCount = playerCount;
        this.connectionList = new ArrayList<>(playerCount);
        this.serverSocket = new ServerSocket(PORT);

        System.out.println("Server started to listening...");
        listen();
    }

    private void listen() throws IOException{
        while(true){
            Socket socket = serverSocket.accept();
            connectionList.add(new ClientConnection(socket));
            System.out.println("There is a new connection.");

            if(connectionList.size() == playerCount)
                break;
        }
    }

    public void broadcast(Object obj) throws IOException {
        for(ClientConnection connection: connectionList){
            connection.getObjectOutputStream().writeObject(obj);
        }
    }

    private static class ClientConnection{
        private Socket socket;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;

        public ClientConnection(Socket socket) throws IOException{
            this.socket = socket;
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        public Socket getSocket() {
            return socket;
        }

        public ObjectOutputStream getObjectOutputStream() {
            return objectOutputStream;
        }

        public ObjectInputStream getObjectInputStream() {
            return objectInputStream;
        }
    }

}
