package input;

import angels.Angel;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public final class GameInput {
    private final List<String> groundType;
    private final int roundsNr;
    private final List<Player> playerInfo;
    private char[][] directions;
    private Player[][] positions;
    private ArrayList<ArrayList<Angel>> angels;

    public GameInput() {
        this.groundType = null;
        this.playerInfo = null;
        this.roundsNr = 0;
        this.directions = null;
        this.positions = null;
    }

    public GameInput(final List<String> groundType,
                     final List<Player> playerInfo, final Player[][] positions,
                     final int r, final char[][] directions,
                     final ArrayList<ArrayList<Angel>> angels) {

        this.groundType = groundType;
        this.playerInfo = playerInfo;
        this.roundsNr = r;
        this.directions = directions;
        this.positions = positions;
        this.angels = angels;
    }

    public int getRoundsNr() {
        return roundsNr;
    }

    public List<Player> getPlayerInfo() {
        return playerInfo;
    }


    public Player[][] getPositions() {
        return positions;
    }

    public char[][] getDirections() {
        return directions;
    }

    public ArrayList<ArrayList<Angel>> getAngels() {
        return angels;
    }
}
