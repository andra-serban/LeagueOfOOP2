package angels;

import fileio.FileSystem;
import observer.Observer;
import observer.Subject;
import player.Knight;
import player.Player;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;
import visitor.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Angel implements Subject, Visitor {
    private int x;
    private int y;
    private String bonusType;
    private String type;
    private List<Observer> observers;
    private boolean changed = false;

    public Angel(final String type, final int y, final int x, final String bonusType) {
        this.observers = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.type = type;
        this.bonusType = bonusType;
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

    @Override
    public final void visit(final Player player) {
        player.applyBonusFromAngel(this);
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final String getBonusType() {
        return bonusType;
    }

    public final String getType() {
        return type;
    }

    public final void setChanged(final boolean changed) {
        this.changed = changed;
    }

    public abstract void applyBonus(Knight player);
    public abstract void applyBonus(Pyromancer player);
    public abstract void applyBonus(Rogue player);
    public abstract void applyBonus(Wizard player);
}
