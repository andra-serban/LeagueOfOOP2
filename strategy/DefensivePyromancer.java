package strategy;

import constants.Constants;
import player.Player;

public final class DefensivePyromancer implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP((int) (Constants.PYROMANCER_DEFENSIVE_HP_MODIFIER * player.getHp()));
        player.getFirstAbility().setRaceModifier(Constants.PYROMANCER_DEFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.PYROMANCER_DEFENSIVE_MODIFIER);
    }
}
