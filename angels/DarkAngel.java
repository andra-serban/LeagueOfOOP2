package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class DarkAngel extends Angel {

    public DarkAngel(final String type, final int y, final int x) {
        super(type, y, x, "hit");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.updateHP(Constants.DARK_ANGEL_KNIGHT_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.updateHP(Constants.DARK_ANGEL_PYROMANCER_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.updateHP(Constants.DARK_ANGEL_ROGUE_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.updateHP(Constants.DARK_ANGEL_WIZARD_MODIFIER);
        }
    }
}
