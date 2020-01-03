package player;
import abilities.Fireblast;
import abilities.Ignite;
import angels.Angel;
import constants.Constants;
import map.Map;
import strategy.DefensivePyromancer;
import strategy.OffensivePyromancer;

public final class Pyromancer extends Player {
    private final int playerSpecificHp = Constants.PYROMANCER_SPECIFIC_HP;
    private final int playerSpecificLevelHp = Constants.PYROMANCER_SPECIFIC_LEVEL_HP;
    private final float firstStrategyFactor = Constants.PYROMANCER_STRATEGY_FIRST_FACTOR;
    private final float secondStrategyFacor = Constants.PYROMANCER_STRATEGY_SECOND_FACTOR;
    public Pyromancer(final int id, final int x, final int y) {
        super(id, x, y);
        this.setHp(playerSpecificHp);
        this.setType("Pyromancer");
        this.setLandModifier(Constants.PYRO_LAND_MODIFIER);
        setFirstAbility(new Fireblast());
        setSecondAbility(new Ignite());
    }

    public void determineStrategy() {
        if ((firstStrategyFactor * getMaxHp() < getHp() && getHp()
                < (secondStrategyFacor * getMaxHp())))  {
            this.strategy(new OffensivePyromancer());
        } else if (getHp() < (firstStrategyFactor * getMaxHp())) {
            this.strategy(new DefensivePyromancer());
        } else {
            this.strategy(null);
        }
    }

    public void applyBonusFromAngel(final Angel angel) {
        angel.applyBonus(this);
    }

    @Override
    void resetHp() {
        setHp(playerSpecificHp + getLevel() * playerSpecificLevelHp);
    }

    public void isAtackedBy(final Player player) {
        player.fight(this);
    }

    public void fight(final Player player) {
        setDamage(0);
        float firstAbilityDamage = Math.round(Math.round((1 + landModifier())
                * getFirstAbility().getDamage(getLevel(), this, player))
                * (1 + getFirstAbility().getRaceModifier(player)));
        float secondAbilityDamage = Math.round(Math.round((1 + landModifier())
                * getSecondAbility().getDamage(this.getLevel(), this, player))
                * (1 + getSecondAbility().getRaceModifier(player)));
        player.setOvertimeDamage(Math.round((1 + getSecondAbility().getRaceModifier(player))
                * Math.round((1 + landModifier()) * getSecondAbility().getOTD(getLevel()))));
        player.setOvertimeDamageCount(2);
        updateDamage(firstAbilityDamage +  secondAbilityDamage);
        player.updateHP(-getDamage());
    }

    public float getMaxHp() {
        return playerSpecificHp + playerSpecificLevelHp * this.getLevel();
    }

    public float getDamageWithoutRace(final Player player) {
        return Math.round((1 + landModifier()) * getFirstAbility().getDamage(getLevel(),
                this, player)) + Math.round((1 + landModifier())
                * getSecondAbility().getDamage(getLevel(), this, player));
    }

    @Override
    public float landModifier() {
        char[][] map = Map.getInstance().getMap();
        if (map[getY()][getX()] == 'V') {
            return getLandModifier();
        }
        return 0;
    }

}
