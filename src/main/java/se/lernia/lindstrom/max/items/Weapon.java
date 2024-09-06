package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Position;

public class Weapon extends Item {
    public Weapon(Position position, ItemSlot itemSlot, String name, int attack) {
        super(position, itemSlot, name, attack);
    }
}
