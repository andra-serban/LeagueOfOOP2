package main;

import fileio.FileSystem;
import input.GameInput;
import input.GameInputLoader;
import map.Map;
import observer.GreatMagician;
import player.Player;

import java.io.IOException;
import java.util.List;

public final class Main {
    private Main() {

    }
    public static void main(final String[] args) throws IOException {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        List<Player> players = gameInput.getPlayerInfo();
        GreatMagician magician = GreatMagician.getInstance();
        char[][] directions = gameInput.getDirections();
        Player[][]  positions = Map.getPlayersPositions();
        FileSystem fs = new FileSystem(args[0], args[1]);
        /*
            Pentru fiecare runda, mut toti jucatorii care nu sunt imobili
            in functie de coordonatele din positions.

         */
        for (int i = 0; i < gameInput.getRoundsNr(); i++) {
            Game.displayRoundNr(fs, i);
            Game.updateOvertimeDamage(players);
            Game.updatePlayerCoordinates(players, magician, directions, positions, i);
            for (Player player : players) {
                if (player.isAlive() && player.getY() >= 0 && player.getX() >= 0) {
                    if (positions[player.getY()][player.getX()] == player) {
                        for (Player player1 : players) {
                            if (player != player1 && player1.getX() == player.getX()
                                    && player1.getY() == player.getY()) {
                                break;
                            }
                        }
                    }
                    Game.testForFight(fs, positions, player, magician);

                }
            }

            Game.angelsAction(fs, i, gameInput, players, magician);
            Map.setPlayersPositions(positions);
        }
        Game.displayResults(players, fs);
        fs.close();
    }

}



