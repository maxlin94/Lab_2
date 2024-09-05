package se.lernia.lindstrom.max.entities;

import se.lernia.lindstrom.max.items.Item;
import se.lernia.lindstrom.max.items.ItemType;

import java.util.ArrayList;
import java.util.Random;

public class Player implements Movable {
    private final String name;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private int health;
    private final int attack;
    private final int defence;
    private Item equippedWeapon;
    private Item equippedArmor;
    private Position position;


    public Player(String name, int health, Position position, int attack, int defence) {
        this.name = name;
        this.health = health;
        this.position = position;
        this.attack = attack;
        this.defence = defence;
    }

    @Override
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

    public int getDefence() {
        return defence;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public void addItem(Item item) {
        boolean itemAlreadyInInventory = getInventory().stream()
                .anyMatch(existingItem -> existingItem.getItemType() == item.getItemType());
        if (itemAlreadyInInventory && item.getItemSlot() != ItemType.ItemSlot.POTION) {
            return;
        }
        System.out.println("You have found a " + item.getName() + " on the ground.");
        inventory.add(item);
    }

    private void equipItem(Item item, ItemType.ItemSlot slot) {
        if (slot == ItemType.ItemSlot.BODY) {
            if (equippedArmor != item) {
                equippedArmor = item;
                return;
            }
            equippedArmor = null;
        }
        if (slot == ItemType.ItemSlot.HANDS) {
            if (equippedWeapon != item) {
                equippedWeapon = item;
                return;
            }
            equippedWeapon = null;
        }
    }

    public void useItem(Item item) {
        if (item.getItemSlot() == ItemType.ItemSlot.POTION) {
            addHealth(item.getStrength());
            inventory.remove(item);
        } else {
            equipItem(item, item.getItemSlot());
        }
    }

    public int attack() {
        Random random = new Random();
        int totalAttack = attack + (equippedWeapon != null ? equippedWeapon.getStrength() : 0);
        return random.nextInt((int) Math.floor(totalAttack / 1.5), (int) Math.floor(totalAttack * 2.5));
    }

    public void loseHealth(int amount) {
        health -= amount;
    }

    public void addHealth(int amount) {
        health += amount;
        System.out.println("You gained " + amount + " health");
    }

    public void printStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        if (equippedWeapon != null) {
            System.out.println("Attack: " + (attack + equippedWeapon.getStrength()) + " (" + attack + "+" + equippedWeapon.getStrength() + ")");
            System.out.println("Equipped weapon: " + equippedWeapon.getName());
        } else {
            System.out.println("Attack: " + attack);
        }
        if (equippedArmor != null) {
            System.out.println("Defence: " + (defence + equippedArmor.getStrength()) + " (" + defence + "+" + equippedArmor.getStrength() + ")");
        } else {
            System.out.println("Defence: " + defence);
        }
    }
}