package player;
import abilities.Deflect;
import abilities.Drain;
import angels.Angel;
import constants.Constants;
import map.Map;
import strategy.DefensiveWizard;
import strategy.OffensiveWizard;

public final class Wizard extends Player {
    private final int playerSpecificHp = Constants.WIZARD_SPECIFIC_HP;
    private final int playerSpecificLevelHp = Constants.WIZARD_SPECIFIC_LEVEL_HP;
    private final float firstStrategyFactor  = Constants.WIZARD_STRATEGY_FIRST_FACTOR;
    private final float secondStrategyFactor = Constants.WIZARD_STRATEGY_SECOND_FACTOR;

    public Wizard(final int id, final int x, final int y) {
        super(id, x, y);
        this.setHp(playerSpecificHp);
        this.setType("Wizard");
        this.setLandModifier(Constants.WIZARD_LAND_MODIFIER);
        setFirstAbility(new Drain());
        setSecondAbility(new Deflect());
    }

    public void determineStrategy() {
        if ((firstStrategyFactor * getMaxHp() < getHp() && getHp()
                < (secondStrategyFactor * getMaxHp())))  {
            this.strategy(new OffensiveWizard());

        } else if (getHp() < (firstStrategyFactor * getMaxHp())) {
            this.strategy(new DefensiveWizard());
        } else {
            this.strategy(null);
        }
    }
    @Override
    void resetHp() {
        setHp(playerSpecificHp + getLevel() * playerSpecificLevelHp);
    }

    public void isAtackedBy(final Player player) {
        player.fight(this);
    }

    public void applyBonusFromAngel(final Angel angel) {
        angel.applyBonus(this);
    }

    public void fight(final Player player) {
        setDamage(0);
        float firstAbilityDamage = Math.round(
                (1 + getFirstAbility().getRaceModifier(player)) * (1 + landModifier())
                * getFirstAbility().getDamage(this.getLevel(), this, player)
                * Math.min(Constants.DRAIN_PERCENT * player.getMaxHp(), player.getHp()));

        float secondAbilityDamage = Math.round(
                (1 + getSecondAbility().getRaceModifier(player)) * (1 + landModifier())
                * getSecondAbility().getDamage(this.getLevel(), this, player)
                * player.getDamageWithoutRace(player));
        updateDamage(firstAbilityDamage +  secondAbilityDamage);
        player.updateHP(-getDamage());
    }

    public float getMaxHp() {
        return playerSpecificHp + playerSpecificLevelHp * this.getLevel();
    }

    public float getDamageWithoutRace(final Player player) {
        return (1 + landModifier()) * (getFirstAbility().getDamage(getLevel(), this, player)
                + getSecondAbility().getDamage(getLevel(), this, player));
    }

    @Override
    float landModifier() {
        char[][] map = Map.getInstance().getMap();
        if (map[getY()][getX()] == 'D') {
            return getLandModifier();
        }
        return 0;
    }
}
