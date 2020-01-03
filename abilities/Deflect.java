package abilities;

import constants.Constants;
import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Deflect extends Abilities {
    private float wizardRaceModifier = Constants.DEFLECT_WIZARD_MODIFIER;
    private float rogueRaceModifier = Constants.DEFLECT_ROGUE_MODIFIER;
    private float knightRaceModifier = Constants.DEFLECT_KNIGHT_MODIFIER;
    private float pyromancerRaceModifier = Constants.DEFLECT_PYROMANCER_MODIFIER;
    private final float procent = Constants.DEFLECT_PROCENT;
    private final float procentIncreser = Constants.DEFLECT_PROCENT_INCREASER;

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

    public float getOTD(final int level) {
        return 0f;
    }


    public void setRaceModifier(final float raceMofifier) {
        pyromancerRaceModifier = raceMofifier + pyromancerRaceModifier;
        knightRaceModifier = raceMofifier +  knightRaceModifier;
        rogueRaceModifier = raceMofifier + rogueRaceModifier;
    }

    public float getDamage(final int level, final Player player1, final Player player2) {
        return Math.min(procent + procentIncreser * level, Constants.DEFLECT_LIMIT);
    }


}
