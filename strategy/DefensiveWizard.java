package strategy;

import constants.Constants;
import player.Player;

public final class DefensiveWizard implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP(Constants.WIZARD_DEFENSIVE_HP_MODIFIER * player.getHp());
        player.getFirstAbility().setRaceModifier(Constants.WIZARD_DEFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.WIZARD_DEFENSIVE_MODIFIER);
    }
}
