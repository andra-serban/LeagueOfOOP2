package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Dracula extends Angel {

    public Dracula(final String type, final int y, final int x) {
        super(type, y, x, "hit");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DRACULA_KNIGHT_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DRACULA_KNIGHT_MODIFIER);
            player.updateHP(Constants.DRACULA_KNIGHT_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DRACULA_PYROMANCER_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DRACULA_PYROMANCER_MODIFIER);
            player.updateHP(Constants.DRACULA_PYROMANCER_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DRACULA_ROGUE_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DRACULA_ROGUE_MODIFIER);
            player.updateHP(Constants.DRACULA_ROGUE_HP_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.getFirstAbility().setRaceModifier(Constants.DRACULA_WIZARD_MODIFIER);
            player.getSecondAbility().setRaceModifier(Constants.DRACULA_WIZARD_MODIFIER);
            player.updateHP(Constants.DRACULA_WIZARD_HP_MODIFIER);
        }
    }
}
