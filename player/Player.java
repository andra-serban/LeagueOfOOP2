package player;

import abilities.Abilities;
import angels.Angel;
import constants.Constants;
import fileio.FileSystem;
import observer.Observer;
import observer.Subject;
import strategy.Strategy;
import visitor.Visitable;
import visitor.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Player implements Subject, Visitable {
    private boolean alive;
    private int xp = 0;
    private float hp;
    private int level = 0;
    private int xpLevelUp;
    private int id;
    private int x;
    private int y;
    private String type;
    private float landModifier;
    private int nrRoundsImobility = 0;
    private float damage;
    private float overtimeDamage = 0;
    private int overtimeDamageCount = 0;
    private Abilities firstAbility;
    private Abilities secondAbility;
    private List<Observer> observers;
    private boolean changed = false;
    public Player(final int id, final int y, final int x) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.alive = true;
        this.observers = new ArrayList<>();
    }

    @Override
    public final void addObserver(final observer.Observer obj) {
            if (!observers.contains(obj)) {
                observers.add(obj);
            }
    }

    @Override
    public final void deleteObserver(final Observer obj) {
            observers.remove((obj));
    }

    @Override
    public final void notifyObservers(final FileSystem fs, final String message)
            throws IOException {
        List<Observer> observersLocal;
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        for (Observer obj : observersLocal) {
            obj.update(fs, message);
        }
    }

    public final void strategy(final Strategy strategy) {
        if (strategy != null) {
            strategy.applyStrategy(this);
        }
    }

    public final void setChanged(final boolean changed) {
        this.changed = changed;
    }

    public void isAtackedBy(final Player player) {
    }
    public void fight(final Player player) {
    }
    public void applyBonusFromAngel(final Angel angel) {
    }
    /**
     *
     * @param defeted
     */
    public void increaseXp(final Player defeted) {
        this.xp = this.xp + Math.max(0, Constants.DEFAULT_XP - (this.level - defeted.level) * 40);
    }
    public void determineStrategy() {
    }
    /**
     * modificarea xp-ului dupa avansarea in nivel.
     */
    public void setXpLevelUp() {
        this.xpLevelUp = 250 + level * 50;
    }


    /**
     *
     */
    void resetHp() {
    }

    /**
     * scada numarul de runda in care jucatorul nu se poate misca.
     */
    public void updateNrRoundsImobility() {
        nrRoundsImobility--;
    }

    /**
     *  se muta o celula mai sus.
     */
    public void moveUp() {
        this.y -= 1;
    }

    /**
     * se muta o celula mai jos.
     */
    public void moveDown() {
        this.y += 1;
    }

    /**
     * se muta o celula la stanga.
     */
    public void moveLeft() {
        this.x -= 1;
    }

    /**
     * se muta  o celula la dreapta.
     */
    public void moveRight() {
        this.x += 1;
    }

    /**
     *
     * @return landModifier pe celula in care se afla jucatorul.
     */
    float landModifier() {
        return 0;
    }

    /**
     *  realizeaza cresterea in nivel a jucatorului.
     */
    public void updateLevel() {
        while (xp >= xpLevelUp) {
            level++;
            resetHp();
            setXpLevelUp();
        }
    }


    /**
     *
     * @return player id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return player xp.
     */
    public int getXp() {
        return xp;
    }

    /**
     *
     * @return player hp.
     */
    public float getHp() {
        return hp;
    }

    /**
     *
     * @return player level.
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return xp-ul necesar pentru a trece la nivelul urmator.
     */
    public int getXpLevelUp() {
        return xpLevelUp;
    }

    /**
     *
     * @return coordonata jucatorui pe axa ox.
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return coordonata jucatorului pe oy.
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return tipul de jucator.
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     *
     * @return
     */
    public int getNrRoundsImobility() {
        return nrRoundsImobility;
    }

    /**
     *
     * @param hpToAdd
     */
    public void updateHP(final float hpToAdd) {
        this.hp += hpToAdd;
        Math.round(hp);
    }

    final public void updateXP(final float xpToAdd) {
        this.xp += xpToAdd;
    }
    /**
     *
     * @param player
     * @return
     */
    float getDamageWithoutRace(final Player player) {
        return 0;
    }

    /**
     *
     * @return hp maxim.
     */

    protected float getMaxHp() {
        return  0;
    }

    /**
     *
     * @return landModifier.
     */
    public float getLandModifier() {
        return landModifier;
    }

    /**
     *
     * @return damage.
     */
    public float getDamage() {
        return damage;
    }

    /**
     *
     * @param alive
     */
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    /**
     *
     * @param xp
     */
    public void setXp(final int xp) {
        this.xp = xp;
    }

    /**
     *
     * @param hp
     */
    public void setHp(final float hp) {
        this.hp = Math.round(hp);
    }

    /**
     *
     * @param level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     *
     * @param xpLevelUp
     */
    public void setXpLevelUp(final int xpLevelUp) {
        this.xpLevelUp = xpLevelUp;
    }

    /**
     *
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @param newLandModifier
     */
    public void setLandModifier(final float newLandModifier) {
        this.landModifier = newLandModifier;
    }

    /**
     *
     * @param nrRoundsImobility
     */
    public void setNrRoundsImobility(final int nrRoundsImobility) {
        this.nrRoundsImobility = nrRoundsImobility;
    }

    /**
     *
     * @param damage
     */
    public void setDamage(final float damage) {
        this.damage = damage;
    }

    /**
     *
     * @param damageToAdd
     */
    public void updateDamage(final float damageToAdd) {
        this.damage += damageToAdd;
    }

    /**
     *
     * @param hpToAdd
     */
    public void updateHp(final float hpToAdd) {
        this.hp += hpToAdd;
        Math.round(this.hp);
    }

    /**
     *
     * @return overtime damage.
     */
    public float getOvertimeDamage() {
        return overtimeDamage;
    }

    /**
     *
     * @return overtime damage rounds.
     */
    public int getOvertimeDamageCount() {
        return overtimeDamageCount;
    }

    /**
     *
     * @return firstAbility.
     */
    public Abilities getFirstAbility() {
        return firstAbility;
    }

    /**
     *
     * @return secondAbility.
     */
    public Abilities getSecondAbility() {
        return secondAbility;
    }

    /**
     *
     * @param overtimeDamage
     */
    public void setOvertimeDamage(final float overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    /**
     *
     * @param overtimeDamageCount
     */
    public void setOvertimeDamageCount(final int overtimeDamageCount) {
        this.overtimeDamageCount = overtimeDamageCount;
    }

    public final void updateOverTimeDamageCount() {
        overtimeDamageCount--;
    }
    /**
     *
     * @param firstAbility
     */
    public final void setFirstAbility(final Abilities firstAbility) {
        this.firstAbility = firstAbility;
    }

    /**
     *
     * @param secondAbility
     */
    public final void setSecondAbility(final Abilities secondAbility) {
        this.secondAbility = secondAbility;
    }


    /**
     *
     * @param player
     * @return
     */
    public final float getHpLimit(final Player player) {
        if (Constants.HP_LIMIT_PERCENT * getMaxHp() + (Constants.LEVEL_INCREASER
                * player.getLevel()) * getMaxHp() <= Constants.HP_LIMIT * getMaxHp()) {
            return Constants.HP_LIMIT_PERCENT * getMaxHp() + (Constants.LEVEL_INCREASER
                    * player.getLevel()) * getMaxHp();
        } else {
            return Constants.HP_LIMIT;
        }
    }


    @Override
    public final void accept(final Visitor v) {
        v.visit(this);
    }
}
