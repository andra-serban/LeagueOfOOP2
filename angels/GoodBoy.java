package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class GoodBoy extends Angel {

    public GoodBoy(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.GOODE_BOY_KNIGHT_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.GOODE_BOY_KNIGHT_MODIFIER);
            player.updateHP(Constants.GOODE_BOY_KNIGHT_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.GOODE_BOY_PYROMANCER_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.GOODE_BOY_PYROMANCER_MODIFIER);
            player.updateHP(Constants.GOODE_BOY_PYROMANCER_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.GOODE_BOY_ROGUE_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.GOODE_BOY_ROGUE_MODIFIER);
            player.updateHP(Constants.GOODE_BOY_ROGUE_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.GOODE_BOY_WIZARD_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.GOODE_BOY_WIZARD_MODIFIER);
            player.updateHP(Constants.GOODE_BOY_WIZARD_HP_MODIFIER);
        }
    }
}
