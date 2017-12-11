package model;

import java.util.Scanner;

/**
 * Created by oztiryakimeric on 6.12.2017.
 */
public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int numPlayers = 2;

        Game game = new Game(numPlayers);
        System.out.println(game);


    }
}
