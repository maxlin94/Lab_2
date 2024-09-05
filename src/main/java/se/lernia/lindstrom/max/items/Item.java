package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Player;
import se.lernia.lindstrom.max.entities.Position;

public class Item {
    private final Position position;
    private final ItemType itemType;

    private Item(Position position, ItemType itemType) {
        this.position = position;
        this.itemType = itemType;
    }

    public static Item of(Position position, ItemType itemType) {
        return new Item(position, itemType);
    }

    public int getStrength() {
        return itemType.getStrength();
    }

    public String getName() {
        return itemType.getName();
    }

    public Position getPosition() {
        return position;
    }

    public ItemType.ItemSlot getItemSlot() {
        return itemType.getItemSlot();
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getDescription(Player player) {
        String info = this.getName();

        switch (this.getItemSlot()) {
            case HANDS:
                info += " Attack: " + this.getStrength();
                info += (this == player.getEquippedWeapon()) ? " (Equipped)" : "";
                break;
            case BODY:
                info += " Defence: " + this.getStrength();
                info += (this == player.getEquippedArmor()) ? " (Equipped)" : "";
                break;
            case POTION:
                info += " Health: " + this.getStrength();
                break;
            default:
                info += " (Unknown Slot)";
        }
        return info;
    }
}