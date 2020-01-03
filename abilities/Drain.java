package abilities;

import constants.Constants;
import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Drain extends Abilities {
    private float wizardRaceModifier = Constants.DRAIN_WIZARD_MODIFIER;
    private float rogueRaceModifier = Constants.DRAIN_ROGUE_MODIFIER;
    private float knightRaceModifier = Constants.DRAIN_KNIGHT_MODIFIER;
    private float pyromancerRaceModifier = Constants.DRAIN_PYROMANCER_MODIFIER;
    private final float procent = Constants.DRAIN_PROCENT;
    private final float procentIncreaser = Constants.DRAIN_PROCENT_INCREASER;

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
        return (procent + level * procentIncreaser);
    }


}
