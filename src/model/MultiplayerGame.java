package model;

import Server.*;
import gui.game.GameController;

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
    private int playerId;
    private GameController controller;

    public MultiplayerGame(IGame game) throws Exception {
        this.game = game;
        connect();
        listenCreatorCommands();
        new Thread(new CommandListener()).start();
    }

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
        controlCreation();
    }

    private void connect() throws IOException {
        Socket socket = new Socket(IP, Server.PORT);
        connection = new Connection(socket);
    }

    private void listenCreatorCommands() throws IOException, ClassNotFoundException {
        Command command = (Command) connection.getObjectInputStream().readObject();
        command.executeOn(MultiplayerGame.this);
        controlCreation();
        System.out.println("Creator commands received. This is player " + playerId);
        System.out.println("************************************************************************************************");
    }

    private void controlCreation(){
        if(getCurrentPlayer() != null && controller != null)
            if(game.getCurrentPlayer().getId() != playerId)
                controller.disable();
            else
                controller.enable();
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

    public void setPlayerId(int id){
        this.playerId = id;
    }

    @Override
    public void playForward(Pirate pirate, Symbol symbol) {
        playForwardWithBroadcast(pirate, symbol, true);
    }

    public void playForwardWithBroadcast(Pirate pirate, Symbol symbol, boolean broadcast){
        game.playForward(pirate, symbol);

        if(broadcast){
            broadcast(new PlayForwardCommand(playerId, pirate, symbol));
            System.out.println("Player " + playerId + " sending a play forward message to server.");
        }
        controller.update();
    }

    @Override
    public void playBackward(Pirate pirate) {
        playBackwardWithBroadcast(pirate, true);
        System.out.println("Send play backward command to server.");
    }

    public void playBackwardWithBroadcast(Pirate pirate, boolean broadcast){
        game.playBackward(pirate);

        if(broadcast){
            broadcast(new PlayBackwardCommand(playerId, pirate));
            System.out.println("Player " + playerId + " sending a play backward message to server.");
        }
        controller.update();
    }

    @Override
    public void switchToNextPlayer() {
        switchToNextPlayerWithBroadcast(true);
        System.out.println("Send switch next player command to server.");
    }

    public void switchToNextPlayerWithBroadcast(boolean broadcast){
        game.switchToNextPlayer();

        if(game.getCurrentPlayer().getId() != playerId)
            controller.disable();
        else
            controller.enable();

        if(broadcast){
            broadcast(new SwitchToNextPlayerCommand(playerId));
            System.out.println("Player " + playerId + " sending a switch to next player message to server.");
        }
        controller.update();
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

    private void broadcast(Command command){
        try {
            connection.getObjectOutputStream().writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class CommandListener implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    Command command = (Command) connection.getObjectInputStream().readObject();

                    if(command.getSenderId() != playerId){
                        command.executeOn(MultiplayerGame.this);
                    }
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














