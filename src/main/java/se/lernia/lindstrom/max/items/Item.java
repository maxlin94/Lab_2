package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Player;
import se.lernia.lindstrom.max.entities.Position;

public abstract class Item {
    private Position position;
    private final ItemSlot itemSlot;
    private final String name;
    private final int strength;

    Item(Position position, ItemSlot itemSlot, String name, int strength) {
        this.position = position;
        this.itemSlot = itemSlot;
        this.name = name;
        this.strength = strength;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition) {
        position = newPosition;
    }


    public ItemSlot getItemSlot() {
        return itemSlot;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public String getDescription(Player player) {
        return getName() + " Attack: " + getStrength() + (player.getEquippedItem(itemSlot) == this ? " (Equipped)" : "");
    }
}