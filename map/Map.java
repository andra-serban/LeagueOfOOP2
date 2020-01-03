package map;

import player.Player;

import java.util.List;

public final class Map {
    private static Map instance = null;
    private static Player[][] playersPositions;
    private final char[][] map;
    private final int length;
    private final int width;

    private Map() {
        map = new char[0][];
        length = 0;
        width = 0;
        playersPositions = new Player[0][];
    }

    private Map(final List<String> map, final int length, final int width,
                final Player[][] playersPositions) {
        this.map = new char[length][width];
        String[] array = map.toArray(new String[0]);
        for (int i = 0; i < array.length; ++i) {
            char[] typeList = array[i].toCharArray();
            System.arraycopy(typeList, 0, this.map[i], 0, typeList.length);
        }
        this.length = length;
        this.width = width;
        this.playersPositions = playersPositions;
    }

    public static Map getInstance(final List<String> map, final int length, final int width,
                                  final Player[][] playersPositions) {
        if (instance == null) {
            instance = new Map(map, length, width, playersPositions);
        }
        return instance;
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public char[][] getMap() {
        return map;
    }

    public static Player[][] getPlayersPositions() {
        return playersPositions;
    }

    public static void setPlayersPositions(Player[][] playersPositions) {
        Map.playersPositions = playersPositions;
    }
}
