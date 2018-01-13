package Server;

import model.MultiplayerGame;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public interface Command extends Serializable{
    void executeOn(MultiplayerGame game);
    int getSenderId();
}

