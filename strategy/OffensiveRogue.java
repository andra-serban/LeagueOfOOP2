package strategy;

import constants.Constants;
import player.Player;

public final class OffensiveRogue implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP(Constants.ROGUE_OFFENSIVE_HP_MODIFIER * player.getHp());
        player.getFirstAbility().setRaceModifier(Constants.ROGUE_OFFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.ROGUE_OFFENSIVE_MODIFIER);
    }
}
