package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Player;
import se.lernia.lindstrom.max.entities.Position;

public class Potion extends Item {
    public Potion(Position position, ItemSlot itemSlot, String name, int health) {
        super(position, itemSlot, name, health);
    }

    @Override
    public String getDescription(Player player) {
        return getName() + " Health: " + getStrength();
    }
}
