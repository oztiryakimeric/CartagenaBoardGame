package Server;

import model.Board;
import model.IGame;

public class SetBoardCommand implements Command{
    private Board board;

    public SetBoardCommand(Board board) {
        this.board = board;
    }

    @Override
    public void executeOn(IGame game) {
        game.setBoard(board);
    }
}
