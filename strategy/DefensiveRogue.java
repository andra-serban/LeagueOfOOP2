package strategy;

import constants.Constants;
import player.Player;

public final class DefensiveRogue implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP(Constants.ROGUE_DEFENSIVE_HP_MODIFIER * player.getHp());
        player.getFirstAbility().setRaceModifier(Constants.ROGUE_DEFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.ROGUE_DEFENSIVE_MODIFIER);
    }
}
