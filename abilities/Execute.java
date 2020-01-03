package abilities;

import constants.Constants;
import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Execute extends Abilities {
    private float wizardRaceModifier = Constants.EXECUTE_WIZARD_MODIFIER;
    private float rogueRaceModifier = Constants.EXECUTE_ROGUE_MODIFIER;
    private float knightRaceModifier = Constants.EXECUTE_KNIGHT_MODIFIER;
    private float pyromancerRaceModifier = Constants.EXECUTE_PYROMANCER_MODIFIER;
    private final float baseDamage = Constants.EXECUTE_BASE_DAMAGE;
    private final float levelIncreser = Constants.EXECUTE_LEVEL_INCREASER;

    public float getRaceModifier(final Wizard player) {
        return wizardRaceModifier;
    }

    public float getRaceModifier(final Rogue player) {
        return rogueRaceModifier;
    }

    public float getRaceModifier(final Knight player) {
        return 0f;
    }

    public float getRaceModifier(final Pyromancer player) {
        return pyromancerRaceModifier;
    }

    public void setRaceModifier(final float raceMofifier) {
        wizardRaceModifier = raceMofifier + wizardRaceModifier;
        pyromancerRaceModifier = raceMofifier + pyromancerRaceModifier;
        knightRaceModifier = 0;
        rogueRaceModifier = raceMofifier + rogueRaceModifier;
    }

    public float getDamage(final int level, final Player player1, final Player player2) {
        return baseDamage + level * levelIncreser;
    }


}
