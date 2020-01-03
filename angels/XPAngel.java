package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class XPAngel extends Angel {

    public XPAngel(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.updateXP(Constants.XP_ANGEL_KNIGHT_MODIFIER);
        }
        }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.updateXP(Constants.XP_ANGEL_PYROMANCER_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.updateXP(Constants.XP_ANGEL_ROGUE_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.updateXP(Constants.XP_ANGEL_WIZARD_MODIFIER);
        }
    }
}
