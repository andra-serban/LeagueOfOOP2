package strategy;

import constants.Constants;
import player.Player;

public final class OffensiveKnight implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP((int) (Constants.KNIGHT_OFFENSIVE_HP_MODIFIER * player.getHp()));
        player.getFirstAbility().setRaceModifier(Constants.KNIGHT_OFFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.KNIGHT_OFFENSIVE_MODIFIER);
    }
}
