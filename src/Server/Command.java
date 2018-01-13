package Server;

import model.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public interface Command extends Serializable{
    public abstract void executeOn(IGame game);
}

