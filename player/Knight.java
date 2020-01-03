package player;
import abilities.Execute;
import abilities.Slam;
import angels.Angel;
import constants.Constants;
import map.Map;
import strategy.DefensiveKnight;
import strategy.OffensiveKnight;

public final class Knight extends Player {
    private final int playerSpecificHp = Constants.KNIGHT_SPECIFIC_HP;
    private final int playerSpecificLevelHp = Constants.KNIGHT_SPECIFIC_LEVEL_HP;
    private final float firstStrategyDefiner = Constants.KNIGHT_STRATEGY_FIRST_FACTOR;
    private final float secondStrategyDefiner = Constants.KNIGHT_STRATEGY_SECOND_FACTOR;
    public Knight(final int id, final int x, final int y) {
        super(id, x, y);
        this.setHp(playerSpecificHp);
        this.setType("Knight");
        this.setLandModifier(Constants.KNIGHT_LAND_MODIFIER);
        this.setFirstAbility(new Execute());
        this.setSecondAbility(new Slam());

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

    public void determineStrategy() {
        if ((firstStrategyDefiner * getMaxHp() < getHp() && getHp()
                < (secondStrategyDefiner * getMaxHp())))  {
            this.strategy(new OffensiveKnight());
        } else if (getHp() < (firstStrategyDefiner * getMaxHp())) {
            this.strategy(new DefensiveKnight());
        } else {
            this.strategy(null);
        }
    }

    public void fight(final Player player) {
        setDamage(0);
        float firstAbilityDamage;
        if (player.getHp() > player.getHpLimit(this)) {
            firstAbilityDamage = Math.round(Math.round((1 + landModifier())
                    * getFirstAbility().getDamage(this.getLevel(), this, player))
                    * (1 + getFirstAbility().getRaceModifier(player)));
        } else {
            firstAbilityDamage = player.getHp();
        }
        float secondAbilityDamage = Math.round(Math.round((1 + landModifier())
                * getSecondAbility().getDamage(this.getLevel(), this, player))
                * (1 + getSecondAbility().getRaceModifier(player)));
        player.setNrRoundsImobility(1);
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
        if (map[getY()][getX()] == 'L') {
            return getLandModifier();
        }
        return 0;
    }
}

