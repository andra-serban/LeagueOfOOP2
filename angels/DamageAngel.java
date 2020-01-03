package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class DamageAngel extends Angel {


    public DamageAngel(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DAMAGE_ANGEL_KNIGHT_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DAMAGE_ANGEL_KNIGHT_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DAMAGE_ANGEL_PYROMANCER_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DAMAGE_ANGEL_PYROMANCER_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DAMAGE_ANGEL_ROGUE_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DAMAGE_ANGEL_ROGUE_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DAMAGE_ANGEL_WIZARD_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DAMAGE_ANGEL_WIZARD_MODIFIER);
        }
    }
}
