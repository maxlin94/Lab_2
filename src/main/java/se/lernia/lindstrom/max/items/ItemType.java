package se.lernia.lindstrom.max.items;

public enum ItemType {
    BAT("Bat", 14, ItemSlot.HANDS),
    KNIFE("Knife", 19, ItemSlot.HANDS),
    SWORD("Sword", 25, ItemSlot.HANDS),
    GUN("Gun", 32, ItemSlot.HANDS),
    SHIRT("Shirt", 5, ItemSlot.BODY),
    WORN_ARMOR("Worn armor", 10, ItemSlot.BODY),
    PLATE_ARMOR("Plate armor", 15, ItemSlot.BODY),
    SMALL_HEALTH_POTION("Small health potion", 15, ItemSlot.POTION),
    HEALTH_POTION("Health potion", 30, ItemSlot.POTION),
    LARGE_HEALTH_POTION("Large health potion", 60, ItemSlot.POTION),
    ;

    public enum ItemSlot {
        HANDS,
        BODY,
        POTION
    }

    private final String name;
    private final int strength;
    private final ItemSlot itemSlot;

    ItemType(String name, int strength, ItemSlot itemSlot) {
        this.name = name;
        this.strength = strength;
        this.itemSlot = itemSlot;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public ItemSlot getItemSlot() {
        return itemSlot;
    }
}