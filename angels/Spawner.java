package angels;

import constants.Constants;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class Spawner extends Angel {

    public Spawner(final String type, final int y, final int x) {
        super(type, y, x, "helped");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (!player.isAlive()) {
            player.setAlive(true);
            player.setHp(Constants.SPAWNER_KNIGHT_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (!player.isAlive()) {
            player.setAlive(true);
            player.setHp(Constants.SPAWNER_PYROMANCER_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (!player.isAlive()) {
            player.setAlive(true);
            player.setHp(Constants.SPAWNER_ROGUE_MODIFIER);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (!player.isAlive()) {
            player.setAlive(true);
            player.setHp(Constants.SPAWNER_WIZARD_MODIFIER);
        }
    }
}
