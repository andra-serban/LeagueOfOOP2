package abilities;

import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public abstract class Abilities {

    abstract float getRaceModifier(Wizard player);

    abstract float getRaceModifier(Pyromancer player);

    abstract float getRaceModifier(Rogue player);

    abstract float getRaceModifier(Knight player);

    public abstract float getDamage(int level, Player player1, Player player2);

    /**
     * ?
     * @param level
     * @return overtimeDamage in functie de nivel
     */
    public float getOTD(final int level) {
        return 0f;
    };

    public abstract void setRaceModifier(float raceMofifier);

    /**
     *
     * @param player
     * @return raceModifier in functie de player-ul care se lupta
     */
    public  float getRaceModifier(final Player player) {
        if (player instanceof Wizard) {
            return getRaceModifier((Wizard) player);
        }
        if (player instanceof Pyromancer) {
            return getRaceModifier((Pyromancer) player);
        }
        if (player instanceof Rogue) {
            return getRaceModifier((Rogue) player);
        }
        if (player instanceof Knight) {
            return getRaceModifier((Knight) player);
        }
        return 0;
    }
}


