package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Player;
import se.lernia.lindstrom.max.entities.Position;

public class Armor extends Item {
    public Armor(Position position, ItemSlot itemSlot, String name, int defense) {
        super(position, itemSlot, name, defense);
    }

    @Override
    public String getDescription(Player player) {
        return getName() + " Defense: " + getStrength() + (player.getEquippedItem(getItemSlot()) == this ? " (Equipped)" : "");
    }
}
