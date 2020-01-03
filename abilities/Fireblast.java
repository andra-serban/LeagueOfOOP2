package abilities;

import constants.Constants;
import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Fireblast extends Abilities {
    private float wizardRaceModifier = Constants.FIREBLAST_WIZARD_MODIFIER;
    private float rogueRaceModifier = Constants.FIREBLAST_ROGUE_MODIFIER;
    private float knightRaceModifier = Constants.FIREBLAST_KNIGHT_MODIFIER;
    private float pyromancerRaceModifier = Constants.FIREBLAST_PYROMANCER_MODIFIER;
    private final float baseDamage = Constants.FIREBLAST_BASE_DAMAGE;
    private final float levelIncreser = Constants.FIREBLAST_LEVEL_INCREASER;

    public float getRaceModifier(final Wizard player) {
        return wizardRaceModifier;
    }

    public float getRaceModifier(final Pyromancer player) {
        return pyromancerRaceModifier;
    }

    public float getRaceModifier(final Rogue player) {
        return rogueRaceModifier;
    }

    public float getRaceModifier(final Knight player) {
        return knightRaceModifier;
    }

    public void setRaceModifier(final float raceMofifier) {
        wizardRaceModifier = raceMofifier + wizardRaceModifier;
        pyromancerRaceModifier = raceMofifier + pyromancerRaceModifier;
        knightRaceModifier = raceMofifier +  knightRaceModifier;
        rogueRaceModifier = raceMofifier + rogueRaceModifier;
    }

    public float getDamage(final int level, final Player player1, final Player player2) {
        return baseDamage + level * levelIncreser;
    }



}
