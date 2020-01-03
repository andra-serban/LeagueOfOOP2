package player;

public final class PlayerFactory {
    private static PlayerFactory instance = null;

    private PlayerFactory() { }

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player createPlayer(final String type, final int id, final int x, final int y) {
        switch (type) {
            case "P": return new Pyromancer(id, x, y);
            case "K": return new Knight(id, x, y);
            case "R": return new Rogue(id, x, y);
            case "W": return new Wizard(id, x, y);
            default : return null;
        }
    }
}
