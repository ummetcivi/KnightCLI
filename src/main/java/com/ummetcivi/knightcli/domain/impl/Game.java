package com.ummetcivi.knightcli.domain.impl;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

class Game implements Serializable {

    private static final long serialVersionUID = 7748109623360944186L;

    private Character player;
    private Character opponent;

    void opponent(CharacterType characterType, int level) {
        opponent = Character.of(characterType, level);
    }

    void opponent(Mob mob) {
        opponent = Character.of(mob);
    }

    int getPlayerLevel() {
        return player.level();
    }

    void character(CharacterType characterType) {
        this.player = Character.of(characterType, 1);
    }

    FightStatus fight(boolean playersTurn, BiConsumer<String, Object[]> printFunction,
            BiFunction<Integer, Integer, Integer>
                    randomNumber) {
        if (playersTurn) {
            int damage = opponent.attackedBy(player, randomNumber);
            printFunction.accept("Opponent received %d damage\n", new Object[]{damage});
        } else {
            int damage = player.attackedBy(opponent, randomNumber);
            printFunction.accept("You've received %d damage\n", new Object[]{damage});
        }

        printWithOpponent(printFunction);

        if (player.hp() == 0) {
            player.decreaseExp();
            return FightStatus.LOSE;
        } else if (opponent.hp() == 0) {
            player.increaseExp(opponent);
            return FightStatus.WIN;
        }
        return FightStatus.CONTINUE;
    }

    void printPlayer(BiConsumer<String, Object[]> printFunction) {
        player.print(printFunction);
    }

    void printWithOpponent(BiConsumer<String, Object[]> printFunction) {
        player.printWithOpponent(printFunction, opponent);
    }
}
