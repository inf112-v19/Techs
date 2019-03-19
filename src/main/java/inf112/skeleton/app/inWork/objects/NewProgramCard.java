package inf112.skeleton.app.inWork.objects;

import inf112.skeleton.app.CardType;
import inf112.skeleton.app.IProgramCard;

public class NewProgramCard implements IProgramCard {

    CardType cardType;
    int priority;
    boolean locked;

    public NewProgramCard(CardType cardType, int priority) {
        this.cardType = cardType;
        this.priority = priority;
        this.locked = false;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public CardType getCardType() {
        return this.cardType;
    }

    @Override
    public String toString() {
        return priority + " " + cardType.toString();
    }

    public void setLocked() {
        locked = true;
    }

    public void setUnlocked() {
        locked = false;
    }

}
