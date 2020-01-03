package strategy;

import constants.Constants;
import player.Player;

public final class OffensiveWizard implements Strategy {
    @Override
    public void applyStrategy(final Player player) {
        player.updateHP(Constants.WIZARD_OFFENSIVE_HP_MODIFIER * player.getHp());
        player.getFirstAbility().setRaceModifier(Constants.WIZARD_OFFENSIVE_MODIFIER);
        player.getSecondAbility().setRaceModifier(Constants.WIZARD_OFFENSIVE_MODIFIER);
    }
}
