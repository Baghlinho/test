package org.example.effects;

import org.example.game.Game;

public abstract class EffectDecorator implements CardEffect{
    private final CardEffect effect;
    private final int times;

    protected EffectDecorator(CardEffect effect, int times) {
        this.effect = effect;
        this.times = times;
    }

    @Override
    public void executeEffect(Game game) {
        effect.executeEffect(game);
    }

    protected int getTimes() {
        return times;
    }
}
