package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class LifeGiver extends Angel {

    public LifeGiver(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.updateHP(Constants.LIFE_GIVER_KNIGHT_MODIFIER);
            if (player.getHp() > player.getMaxHp()) {
                player.setHp(player.getMaxHp());
            }
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.updateHP(Constants.LIFE_GIVER_PYROMANCER_MODIFIER);
            if (player.getHp() > player.getMaxHp()) {
                player.setHp(player.getMaxHp());
            }
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.updateHP(Constants.LIFE_GIVER_ROGUE_MODIFIER);
            if (player.getHp() > player.getMaxHp()) {
                player.setHp(player.getMaxHp());
            }
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.updateHP(Constants.LIFE_GIVER_WIZARD_MODIFIER);
            if (player.getHp() > player.getMaxHp()) {
                player.setHp(player.getMaxHp());
            }
        }
    }
}
