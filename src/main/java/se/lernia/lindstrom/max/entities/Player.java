package se.lernia.lindstrom.max.entities;

import se.lernia.lindstrom.max.items.Item;
import se.lernia.lindstrom.max.items.ItemSlot;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class Player implements Movable {
    private final String name;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final Map<ItemSlot, Item> equippedItems;
    private int health;
    private final int attack;
    private final int defense;
    private Position position;

    public Player(String name, int health, Position position, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.position = position;
        this.attack = attack;
        this.defense = defense;
        equippedItems = new EnumMap<>(ItemSlot.class);
    }

    public void move(Position newPosition) {
        position = newPosition;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return Math.max(0, health);
    }

    public int getDefense() {
        return defense;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item getEquippedItem(ItemSlot slot) {
        return equippedItems.get(slot);
    }

    public void addItem(Item item, boolean droppedByMonster) {
        boolean itemAlreadyInInventory = getInventory().stream()
                .anyMatch(existingItem -> existingItem.equals(item));
        if (itemAlreadyInInventory && item.getItemSlot() != ItemSlot.POTION) {
            return;
        }
        if (droppedByMonster) {
            System.out.println("The monster dropped a " + item.getName());
        } else {
            System.out.println("You have found a " + item.getName() + " on the ground.");
        }
        inventory.add(item);
    }

    private void equipItem(Item item) {
        ItemSlot slot = item.getItemSlot();
        if (item.equals(equippedItems.get(slot))) {
            equippedItems.remove(slot);
        } else {
            equippedItems.put(item.getItemSlot(), item);
        }
    }

    public void useItem(Item item) {
        if (item.getItemSlot() == ItemSlot.POTION) {
            addHealth(item.getStrength());
            inventory.remove(item);
        } else {
            equipItem(item);
        }
    }

    public int attack() {
        Random random = new Random();
        Item equippedWeapon = equippedItems.get(ItemSlot.HANDS);
        int totalAttack = attack + (equippedWeapon != null ? equippedWeapon.getStrength() : 0);
        return random.nextInt((int) Math.floor(totalAttack / 1.5), (int) Math.floor(totalAttack * 2.5));
    }

    public void loseHealth(int amount) {
        health -= amount;
    }

    private void addHealth(int amount) {
        health += amount;
        System.out.println("You gained " + amount + " health");
    }

    public void printStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        Item equippedWeapon = equippedItems.get(ItemSlot.HANDS);
        Item equippedArmor = equippedItems.get(ItemSlot.BODY);
        if (equippedWeapon != null) {
            System.out.println("Attack: " + (attack + equippedWeapon.getStrength()) + " (" + attack + "+" + equippedWeapon.getStrength() + ")");
        } else {
            System.out.println("Attack: " + attack);
        }
        if (equippedArmor != null) {
            System.out.println("Defense: " + (defense + equippedArmor.getStrength()) + " (" + defense + "+" + equippedArmor.getStrength() + ")");
        } else {
            System.out.println("Defense: " + defense);
        }
    }
}