package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class SmallAngel extends Angel {

    public SmallAngel(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.SMALL_ANGEL_KNIGHT_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.SMALL_ANGEL_KNIGHT_MODIFIER);
            player.updateHP(Constants.SMALL_ANGEL_KNIGHT_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.SMALL_ANGEL_PYROMANCER_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.SMALL_ANGEL_PYROMANCER_MODIFIER);
            player.updateHP(Constants.SMALL_ANGEL_PYROMANCER_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.SMALL_ANGEL_ROGUE_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.SMALL_ANGEL_ROGUE_MODIFIER);
            player.updateHP(Constants.SMALL_ANGEL_ROGUE_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.SMALL_ANGEL_WIZARD_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.SMALL_ANGEL_WIZARD_MODIFIER);
            player.updateHP(Constants.SMALL_ANGEL_WIZARD_HP_MODIFIER);
        }
    }
}
