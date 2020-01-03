package main;

import angels.Angel;
import fileio.FileSystem;
import input.GameInput;
import observer.GreatMagician;
import player.Player;
import player.Rogue;

import java.io.IOException;
import java.util.List;

public final class Game {
    private Game() {

    }

    static void displayRoundNr(final FileSystem fs,final int i) throws IOException {
        fs.writeWord("~~ Round ");
        fs.writeInt(i + 1);
        fs.writeWord(" ~~");
        fs.writeNewLine();
    }

    /*
     Modificare pozitiilor pentru player, in functie de input
     */
    static void movePlayer(final char cell, final Player player) {
        switch (cell) {
            case 'U':
                player.moveUp();
                break;
            case 'D':
                player.moveDown();
                break;
            case 'L':
                player.moveLeft();
                break;
            case 'R':
                player.moveRight();
                break;
            default:
                break;
        }
    }

    /*
        Afisarea rezultatelor finale
     */
    static void displayResults(final List<Player> players, final FileSystem fs)
            throws IOException {
        fs.writeWord("~~ Results ~~");
        fs.writeNewLine();
        for (Player player : players) {
            if (!player.isAlive()) {
                fs.writeCharacter(player.getType().charAt(0));
                fs.writeCharacter(' ');
                fs.writeWord("dead");
                fs.writeNewLine();
            } else {
                fs.writeCharacter(player.getType().charAt(0));
                fs.writeCharacter(' ');
                fs.writeInt(player.getLevel());
                fs.writeCharacter(' ');
                fs.writeInt(player.getXp());
                fs.writeCharacter(' ');
                fs.writeInt((int) player.getHp());
                fs.writeCharacter(' ');
                fs.writeInt(player.getY());
                fs.writeCharacter(' ');
                fs.writeInt(player.getX());
                fs.writeNewLine();
            }
        }
    }

    static void updatePlayerCoordinates(final List<Player> players, final GreatMagician magician,
                                        final char[][] directions, final Player[][] positions,
                                        final int i) {
        for (Player player : players) {
            player.addObserver(magician);
            player.setXpLevelUp();
            if (player.isAlive()) {
                if (player.getNrRoundsImobility() <= 0) {
                    if (player.getY() >= 0 && player.getX() >= 0
                            && positions[player.getY()][player.getX()] == player) {
                        positions[player.getY()][player.getX()] = null;
                    }
                    player.determineStrategy();
                    Game.movePlayer(directions[i][player.getId()], player);
                    if (player.getY() >= 0 && player.getX() >= 0
                            && positions[player.getY()][player.getX()] == null) {
                        positions[player.getY()][player.getX()] = player;
                    } else if (player.getY() >= 0 && player.getX() >= 0
                            && positions[player.getY()][player.getX()] != null) {
                        positions[player.getY()][player.getX()] = player;
                    }
                } else {
                    player.updateNrRoundsImobility();
                }
            }

        }
    }

    /*
      Adaug la damage-ul ce va actiona asupra jucatorilor si
      damage-ul overtime
   */
    static void updateOvertimeDamage(final List<Player> players) {
        for (Player value : players) {
            if (value.getOvertimeDamageCount() > 0) {
                value.updateHP(-value.getOvertimeDamage());
                value.updateOverTimeDamageCount();
                if (value.getHp() <= 0) {
                    value.setAlive(false);
                }
            }
        }
    }

    /*
        Functie in care verific daca jucatorii se pot lupta
     */
    static void testForFight(final FileSystem fs, final Player[][] position, final Player player,
                             final GreatMagician magician) throws IOException {
        Player enemy = position[player.getY()][player.getX()];

        if (enemy != null && enemy != player  &&  enemy.isAlive()) {
            player.isAtackedBy(enemy);
            enemy.isAtackedBy(player);
            if (player.getId() < enemy.getId()) {
                updatePlayerInfo(fs, position, player, enemy, magician);
                updatePlayerInfo(fs, position, enemy, player, magician);
            } else {
                updatePlayerInfo(fs, position, enemy, player, magician);
                updatePlayerInfo(fs, position, player, enemy, magician);
            }
        } else if (player instanceof Rogue) {
            ((Rogue) player).setAttackedThisRound(false);
        }
    }

    /*
        Functia verifica daca jucatorii au murit dupa lupta
     */
    private static void updatePlayerInfo(final FileSystem fs, final Player[][] position,
                                         final Player player, final Player enemy,
                                         GreatMagician magician) throws IOException {

        if (enemy.getHp() <= 0) {
            enemy.setAlive(false);
            String message = "";
            message = "Player " + enemy.getType() + ' ' + enemy.getId() + " was killed by "
                    + player.getType() + ' ' + player.getId();
            player.addObserver(magician);
            magician.setSubject(player);
            player.setChanged(true);
            player.notifyObservers(fs, message);
            if (position[enemy.getY()][enemy.getX()] == enemy) {
                position[enemy.getY()][enemy.getX()] = null;
            }
            /*
                Verific daca cei 2 jucatori nu s-au omorat reciproc
             */
            if (player.getHp() > 0 &&  player.isAlive()) {
                player.increaseXp(enemy);
                player.setXpLevelUp();
                int levelBefore = player.getLevel();
                playerUpdateLevel(fs, player, levelBefore);
            }
        }
    }

    /*
    Fiecare inger este spawnat pe harta si aplica bunusul asupra jucatorilor
     */
    static void angelsAction(final FileSystem fs, final  int roundNr, final  GameInput gameInput,
                             final  List<Player> players, final GreatMagician magician)
            throws IOException {
        List<Angel> angelList = gameInput.getAngels().get(roundNr);
        String message = "";
        if (angelList != null) {
            for (Angel angel : angelList) {
                angel.addObserver(magician);
                magician.setSubject(angel);
                message = "angels.Angel " + angel.getType() + " was spawned at "
                        + angel.getY() +  ' ' + angel.getX();
                angel.setChanged(true);
                angel.notifyObservers(fs, message);
                for (Player player : players) {
                    if (player.getX() == angel.getX()  &&  player.getY() == angel.getY()) {
                        int levelBefore = player.getLevel();
                        if (angel.getType().equals("Spawner")) {
                            if (!player.isAlive()) {
                                playerWasBroughtToLive(fs, angel, player);

                            }
                        } else if (angel.getType().equals("TheDoomer")) {
                            if (player.isAlive()) {
                                playerWasKIlledByAngel(fs, magician, angel, player);
                            }
                        } else {
                            if (player.isAlive()) {
                                angelGaveBonus(fs, magician, angel, player);
                            }
                        }
                        float hpBefore  = player.getHp();
                        player.accept(angel);
                        if (player.getHp() < 0 && hpBefore > 0) {
                            playerKilldByAngel(fs, player);
                        }
                        if (angel.getType().equals("XPAngel")
                                || angel.getType().equals("LevelUpAngel") && player.isAlive()) {
                            playerUpdateLevel(fs, player, levelBefore);
                        }
                    }
                }
            }
        }
        fs.writeNewLine();
    }

    private static void angelGaveBonus(final FileSystem fs, final GreatMagician magician,
                                       final Angel angel, final Player player) throws IOException {
        String message;
        message = angel.getType() + " " + angel.getBonusType() + ' '
                + player.getType() + ' ' + player.getId();
        magician.update(fs, message);
    }

    private static void playerWasKIlledByAngel(final FileSystem fs, final GreatMagician magician,
                                               final Angel angel, final Player player)
            throws IOException {
        String message;
        angelGaveBonus(fs, magician, angel, player);
        message = "Player " + player.getType() + ' ' + player.getId()
                + " was killed by an angel";
        magician.update(fs, message);
    }

    private static void playerWasBroughtToLive(final FileSystem fs, final Angel angel,
                                               final Player player) throws IOException {
        String message;
        message = angel.getType() + " " + angel.getBonusType() + ' '
                + player.getType() + ' ' + player.getId();
        angel.setChanged(true);
        angel.notifyObservers(fs, message);
        message = "Player " + player.getType() + ' ' + player.getId()
                + " was brought to life by an angel";
        player.setChanged(true);
        player.notifyObservers(fs, message);
    }

    private static void playerUpdateLevel(final FileSystem fs, final Player player,
                                          final int levelBefore) throws IOException {
        String message;
        player.updateLevel();
        for (int i = levelBefore + 1; i <= player.getLevel(); i++) {
            message = player.getType() + ' ' + player.getId()
                    + " reached level " + i;
            player.setChanged(true);
            player.notifyObservers(fs, message);
        }
    }

    private static void playerKilldByAngel(final FileSystem fs, final Player player)
            throws IOException {
        String message;
        message = "Player " + player.getType() + ' ' + player.getId()
                + " was killed by an angel";
        player.setChanged(true);
        player.notifyObservers(fs, message);
        player.setAlive(false);
    }
}
