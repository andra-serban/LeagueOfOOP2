package angels;

public final class AngelFactory {
    private static AngelFactory instance = null;

    private AngelFactory() { }

    public static AngelFactory getInstance() {
        if (instance == null) {
            instance = new AngelFactory();
        }
        return instance;
    }

    public Angel createAngel(final String type, final int id, final int x, final int y) {
        switch (type) {
            case "DamageAngel": return new DamageAngel(type, x, y);
            case "DarkAngel": return new DarkAngel(type, x, y);
            case "Dracula": return new Dracula(type, x, y);
            case "GoodBoy": return new GoodBoy(type, x, y);
            case "LevelUpAngel": return new LevelUpAngel(type, x, y);
            case "LifeGiver": return new LifeGiver(type, x, y);
            case "SmallAngel": return new SmallAngel(type, x, y);
            case "Spawner": return new Spawner(type, x, y);
            case "TheDoomer": return new TheDoomer(type, x, y);
            case "XPAngel": return new XPAngel(type, x, y);
            default : return null;
        }
    }
}
