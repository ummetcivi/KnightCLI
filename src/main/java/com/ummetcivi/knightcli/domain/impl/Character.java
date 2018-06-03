package com.ummetcivi.knightcli.domain.impl;

import com.ummetcivi.knightcli.constant.Constants;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

class Character implements Serializable {

    private static final long serialVersionUID = -7528122046512627390L;
    private final CharacterType characterType;
    private final Mob mob;
    private int attackPower;
    private int defence;
    private int hp;
    private int level = 1;
    private int exp = 0;

    private Character(Mob mob) {
        this.mob = mob;
        this.characterType = null;
        this.attackPower = mob.attackPower;
        this.defence = mob.defence;
        this.hp = mob.hp;
    }

    private Character(CharacterType characterType, int level) {
        this.characterType = characterType;
        this.attackPower = characterType.attackPowerFor(level);
        this.defence = characterType.defenceFor(level);
        this.hp = characterType.hpFor(level);
        this.level = level;
        this.mob = null;
    }

    static Character of(Mob mob) {
        return new Character(mob);
    }

    static Character of(CharacterType characterType, int level) {
        return new Character(characterType, level);
    }

    int attackedBy(Character opponent, BiFunction<Integer, Integer, Integer> randomGenerator) {
        int chance = randomGenerator.apply(0, opponent.attackPower) - opponent.attackPower / 2;
        int damage = opponent.attackPower + chance - this.defence;

        if (damage < Constants.MINIMUM_DAMAGE) {
            damage = Constants.MINIMUM_DAMAGE;
        }

        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }

        return damage;
    }

    void decreaseExp() {
        int expToLose = experienceToLose();
        if (level == 1) {
            if (exp - expToLose > 0) {
                exp -= expToLose;
            } else {
                exp = 0;
            }
        } else {
            if (exp - expToLose >= 0) {
                exp -= expToLose;
            } else {
                level--;
                exp = exp - expToLose + maxExperience();

            }
        }
        reCalculateStats();

    }

    void increaseExp(Character opponent) {
        int expToAdd = opponent.experienceToLose();
        int maxExp = maxExperience();

        if (exp + expToAdd > maxExp) {
            level++;
            exp = exp + expToAdd - maxExp;
        } else {
            exp += expToAdd;
        }

        reCalculateStats();
    }

    private void reCalculateStats() {
        if (characterType == null) {
            return;
        }
        this.attackPower = characterType.attackPowerFor(this.level);
        this.hp = characterType.hpFor(this.level);
        this.defence = characterType.defenceFor(this.level);
    }

    private int maxExperience() {
        if (mob != null) {
            return mob.exp;
        }
        return level * 500;
    }

    private int experienceToLose() {
        if (mob != null) {
            return mob.exp;
        }

        return maxExperience() / 4;
    }

    int hp() {
        return hp;
    }

    int level() {
        return level;
    }

    private int maxHp() {
        return characterType != null ? characterType.hpFor(level) : mob.hp;
    }

    void print(BiConsumer<String, Object[]> printFunction) {
        String leftAlignFormat = "| %-20s | %-20s | %n";

        printFunction.accept("+---------------------You---------------------+%n", null);

        printFunction.accept(leftAlignFormat, new Object[]{
                "Character: " + characterType,
                statString("Level", level)
        });

        printFunction.accept(leftAlignFormat, new Object[]{
                rateString("HP", hp, characterType.hpFor(level)),
                statString("Attack P", attackPower)
        });

        printFunction.accept(leftAlignFormat, new Object[]{
                statString("Defense", defence),
                rateString("Exp", exp, maxExperience())
        });

        printFunction.accept("+----------------------+----------------------+%n", new Object[]{});
    }

    void printWithOpponent(BiConsumer<String, Object[]> printFunction, Character opponent) {
        String leftAlignFormat = "| %-20s | %-20s | + | %-20s | %-20s |%n";

        printFunction
                .accept("+---------------------You---------------------+ | +-------------------Opponent------------------+%n",
                        new Object[]{});

        printFunction.accept(leftAlignFormat, new Object[]{
                "Character: " + characterType,
                statString("Level", level),
                "Character: " + (opponent.characterType != null ? opponent.characterType : opponent.mob),
                "Level: " + (opponent.characterType == null ? "MOB" : opponent.level)
        });

        printFunction.accept(leftAlignFormat, new Object[]{
                rateString("HP", hp, characterType.hpFor(level)),
                statString("Attack P", attackPower),
                rateString("HP", opponent.hp, opponent.maxHp()),
                statString("Attack P", opponent.attackPower)
        });

        printFunction.accept(leftAlignFormat, new Object[]{
                statString("Defense", defence),
                rateString("Exp", exp, maxExperience()),
                statString("Defense", opponent.defence),
                rateString("Exp", opponent.exp, opponent.maxExperience())
        });

        printFunction
                .accept("+----------------------+----------------------+ | +----------------------+----------------------+ %n",
                        new Object[]{});
    }

    private String rateString(String text, int actual, int maximum) {
        return text + ": " + actual + "/" + maximum;
    }

    private String statString(String text, int actual) {
        return text + ": " + actual;
    }
}
