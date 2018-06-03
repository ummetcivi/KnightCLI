package com.ummetcivi.knightcli.domain.impl;

public enum CharacterType {
    WARRIOR(50, 80, 400),
    ROGUE(60, 70, 370),
    MAGE(70, 50, 340);

    final int attackPower;
    final int defence;
    final int hp;

    CharacterType(int attackPower, int defence, int hp) {
        this.attackPower = attackPower;
        this.defence = defence;
        this.hp = hp;
    }

    int defenceFor(int level) {
        return calculateIncreaseOfStat(defence, level);
    }

    int attackPowerFor(int level) {
        return calculateIncreaseOfStat(attackPower, level);
    }

    int hpFor(int level) {
        return calculateIncreaseOfStat(hp, level);
    }

    int calculateIncreaseOfStat(int stat, int level) {
        return stat + (level - 1) * 25;
    }
}
