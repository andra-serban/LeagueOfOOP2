package player;
import abilities.Backstab;
import abilities.Paralysis;
import angels.Angel;
import constants.Constants;
import map.Map;
import strategy.DefensiveRogue;
import strategy.OffensiveRogue;

public final class Rogue extends Player {
    private final int playerSpecificHp = Constants.ROGUE_SPECIFIC_HP;
    private final int playerSpecificLevelHp = Constants.ROGUE_SPECIFIC_LEVEL_HP;
    public boolean attackedThisRound = false;
    private final float firstStrategyFactor = Constants.ROGUE_STRATEGY_FIRST_FACTOR;
    private final float secondStrategyFactor = Constants.ROGUE_STRATEGY_SECOND_FACTOR;

    private int backstabCount  = 0;
    public Rogue(final int id, final int x, final int y) {
        super(id, x, y);
        this.setHp(playerSpecificHp);
        this.setType("Rogue");
        this.setLandModifier(Constants.ROGUE_LAND_MODIFIER);
        setFirstAbility(new Backstab());
        setSecondAbility(new Paralysis());
    }

    public void determineStrategy() {
        if ((firstStrategyFactor * getMaxHp() < getHp() && getHp()
                < (secondStrategyFactor * getMaxHp())))  {
            this.strategy(new OffensiveRogue());

        } else if (getHp() < (firstStrategyFactor * getMaxHp())) {
            this.strategy(new DefensiveRogue());
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

        float firstAbilityDamage;
        setDamage(0);
        attackedThisRound = true;
        if (backstabCount % Constants.BACKSTAB_ROUND_COUNT == 0
                && Map.getInstance().getMap()[getY()][getX()] == 'W') {
            firstAbilityDamage =  Math.round((1 + landModifier())
                    * Math.round((1 + getFirstAbility().getRaceModifier(player))
                    * Math.round(1.5f * getFirstAbility().getDamage(this.getLevel(),
                    this, player))));
            backstabCount++;
            player.setNrRoundsImobility(Constants.MAX_NR_ROUND_IMOBILITY_PARALYSIS);
        } else if (backstabCount % Constants.BACKSTAB_ROUND_COUNT == 0
                && Map.getInstance().getMap()[getY()][getX()] != 'W') {
            firstAbilityDamage = Math.round((1 + landModifier())
                    * Math.round((1 + getFirstAbility().getRaceModifier(player))
                    * getFirstAbility().getDamage(this.getLevel(),
                    this, player)));
            backstabCount++;
            player.setNrRoundsImobility(Constants.NR_ROUND_IMOBILITY_PARALYSIS);
        } else {
            firstAbilityDamage = Math.round((1 + landModifier())
                    * Math.round((1 + getFirstAbility().getRaceModifier(player))
                    * getFirstAbility().getDamage(this.getLevel(),
                    this, player)));
            backstabCount++;
            player.setNrRoundsImobility(Constants.NR_ROUND_IMOBILITY_PARALYSIS);
        }
        float secondAbilityDamage = Math.round((1 + landModifier())
                * Math.round((1 + getSecondAbility().getRaceModifier(player))
                * getSecondAbility().getDamage(this.getLevel(), this, player)));
        if (landModifier() != 0) {
            player.setOvertimeDamage(secondAbilityDamage);
            player.setOvertimeDamageCount(Constants.MAX_NR_ROUND_IMOBILITY_PARALYSIS);
        } else {
            player.setOvertimeDamage(secondAbilityDamage);
            player.setOvertimeDamageCount(Constants.NR_ROUND_IMOBILITY_PARALYSIS);
        }
        updateDamage(firstAbilityDamage +  secondAbilityDamage);
        player.updateHP(-getDamage());

    }

    public float getMaxHp() {
        return playerSpecificHp + playerSpecificLevelHp * this.getLevel();
    }

    public float getDamageWithoutRace(final Player player) {
        if (backstabCount % Constants.BACKSTAB_ROUND_COUNT == 1
                && Map.getInstance().getMap()[getY()][getX()] == 'W'
                && attackedThisRound) {
            return (1 + landModifier()) * (1.5f * getFirstAbility().getDamage(getLevel(),
                    this, player) + getSecondAbility().getDamage(getLevel(),
                    this, player) + getOvertimeDamage());
        }
        if (backstabCount % Constants.BACKSTAB_ROUND_COUNT == 0
                && Map.getInstance().getMap()[getY()][getX()] == 'W') {
            return (1 + landModifier()) * (1.5f * getFirstAbility().getDamage(getLevel(),
                    this, player) + getSecondAbility().getDamage(getLevel(),
                    this, player) + getOvertimeDamage());
        }
        return (1 + landModifier()) * (getFirstAbility().getDamage(getLevel(),
                this, player) + getSecondAbility().getDamage(getLevel(),
                this, player) + getOvertimeDamage());
    }

    @Override
    float landModifier() {
        char[][] map = Map.getInstance().getMap();
        if (map[getY()][getX()] == 'W') {
            return  getLandModifier();
        }
        return 0;
    }

    public void setAttackedThisRound(boolean attackedThisRound) {
        this.attackedThisRound = attackedThisRound;
    }
}
