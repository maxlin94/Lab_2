package se.lernia.lindstrom.max.entities;

import se.lernia.lindstrom.max.items.Item;

public class Monster extends Player {
    private final Item loot;

    private Monster(String name, int health, Position position, int attack, Item loot, int defence) {
        super(name, health, position, attack, defence);
        this.loot = loot;
    }

    public static Monster of(String name, int health, Position position, int attack, Item loot, int defence) {
        return new Monster(name, health, position, attack, loot, defence);
    }

    public Item getLoot() {
        return loot;
    }
}