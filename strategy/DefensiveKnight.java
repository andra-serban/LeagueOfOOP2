package strategy;

import constants.Constants;
import player.Player;

public final class DefensiveKnight implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP((int) (Constants.KNIGHT_DEFENSIVE_HP_MODIFIER * player.getHp()));
        player.getFirstAbility().setRaceModifier(Constants.KNIGHT_DEFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.KNIGHT_DEFENSIVE_MODIFIER);
    }
}
