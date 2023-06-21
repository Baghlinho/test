package org.example.model.card;

public class NumberCard extends Card{
    private final int value;
    private final CardColor color;
    private final CardType type;
    protected final CardActions actions;

    public NumberCard(CardColor color, int number) {
        this(color, number, CardType.Number);
    }

    protected NumberCard(CardColor color, int value, CardType type) {
        this.color = color;
        this.value = value;
        this.type = type;
        this.actions = new CardActions();
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberCard card = (NumberCard) o;

        if (value != card.value) return false;
        if (color != card.color) return false;
        return type == card.type;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + color.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + value +
                ", color=" + color +
                ", type=" + type +
                '}';
    }

//    public abstract void doActions();
    public void doActions() {}
}
