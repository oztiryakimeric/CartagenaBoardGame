package model;

import Server.Connection;
import Server.Server;
import Server.*;

import java.io.IOException;
import java.net.Socket;
import java.util.List;


/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class MultiplayerGame implements IGame {
    public static final String IP = "127.0.0.1";

    private IGame game;
    private Connection connection;


    public MultiplayerGame(IGame game) throws Exception {
        this.game = game;
        connect();
        listenCreatorCommands();
        new Thread(new CommandListener()).start();
    }

    private void connect() throws IOException {
        Socket socket = new Socket(IP, Server.PORT);
        connection = new Connection(socket);
        System.out.println("Connected!");
    }

    private void listenCreatorCommands() throws IOException, ClassNotFoundException {
        for(int i=0; i<3; i++){
            Command command = (Command) connection.getObjectInputStream().readObject();
            command.executeOn(MultiplayerGame.this);
            System.out.println("Command received and executed");
        }
    }

    @Override
    public void setBoard(Board board) {
        game.setBoard(board);
    }

    @Override
    public void setPlayerList(List<Player> playerList) {
        game.setPlayerList(playerList);
    }

    @Override
    public void setDeck(Deck deck) {
        game.setDeck(deck);
    }

    @Override
    public void playForward(Pirate pirate, Symbol symbol) {
        game.playForward(pirate, symbol);
        System.out.println("Send play forward command to server.");
    }

    @Override
    public void playBackward(Pirate pirate) {
        game.playBackward(pirate);
        System.out.println("Send play backward command to server.");
    }

    @Override
    public void switchToNextPlayer() {
        game.switchToNextPlayer();
        System.out.println("Send switch next player command to server.");
    }

    @Override
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    @Override
    public boolean isFinished() {
        return game.isFinished();
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }

    @Override
    public List<Player> getPlayerList() {
        return game.getPlayerList();
    }

    private class CommandListener implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    Command command = (Command) connection.getObjectInputStream().readObject();
                    command.executeOn(MultiplayerGame.this);
                    System.out.println("Command received and executed");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("IO EXCEPTION");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
                throw new RuntimeException("CLASS NOT FOUND");
            }
        }
    }
}














