package se.lernia.lindstrom.max.items;

import se.lernia.lindstrom.max.entities.Position;

public enum ItemList {
    SHIRT(new Armor(Position.of(0, 0), ItemSlot.BODY, "Shirt", 5)),
    WORN_ARMOR(new Armor(Position.of(0, 0), ItemSlot.BODY, "Worn armor", 10)),
    PLATE_ARMOR(new Armor(Position.of(0, 0), ItemSlot.BODY, "Plate armor", 15)),
    BAT(new Weapon(Position.of(0, 0), ItemSlot.HANDS, "Bat", 14)),
    KNIFE(new Weapon(Position.of(0, 0),  ItemSlot.HANDS, "Knife", 19)),
    SWORD(new Weapon(Position.of(0, 0),  ItemSlot.HANDS, "Sword", 25)),
    GUN(new Weapon(Position.of(0, 0),  ItemSlot.HANDS, "Gun", 32)),
    SMALL_HEALTH_POTION(new Potion(Position.of(0, 0), ItemSlot.POTION, "Small health potion", 15)),
    HEALTH_POTION(new Potion(Position.of(0, 0), ItemSlot.POTION, "Health potion", 30)),
    LARGE_HEALTH_POTION(new Potion(Position.of(0, 0), ItemSlot.POTION, "Large health potion", 50));

    private final Item item;

    ItemList(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}