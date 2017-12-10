import java.util.Scanner;

/**
 * Created by oztiryakimeric on 6.12.2017.
 */
public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int numPlayers = scanner.nextInt();

        Game game = new Game(numPlayers);

        while(game.isFinished()){

        }
    }
}
