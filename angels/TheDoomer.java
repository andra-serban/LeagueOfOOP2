package angels;

import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public final class TheDoomer extends Angel {

    public TheDoomer(final String type, final int y, final int x) {
        super(type, y, x, "hit");
    }

    @Override
    public void applyBonus(final Knight player) {
        if (player.isAlive()) {
            player.setAlive(false);
        }
    }

    @Override
    public void applyBonus(final Pyromancer player) {
        if (player.isAlive()) {
            player.setAlive(false);
        }
    }

    @Override
    public void applyBonus(final Rogue player) {
        if (player.isAlive()) {
            player.setAlive(false);
        }
    }

    @Override
    public void applyBonus(final Wizard player) {
        if (player.isAlive()) {
            player.setAlive(false);
        }
    }
}
