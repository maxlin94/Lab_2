package se.lernia.lindstrom.max.game;

import se.lernia.lindstrom.max.entities.Monster;
import se.lernia.lindstrom.max.entities.Position;
import se.lernia.lindstrom.max.items.Item;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class GameUI {

    static void displayAvailableMoves(ArrayList<Position> moves, Maze maze) {
        for (int i = 0; i < moves.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + maze.getMoveDirection(Game.player.getPosition(), moves.get(i)));
        }
        System.out.println("[" + (moves.size() + 1) + "] View inventory");
        System.out.println("[" + (moves.size() + 2) + "] View map");
    }

    static void printBattleMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Attack");
        System.out.println("[2] Run away");
        System.out.println("[3] View inventory");
    }

    static boolean processBattleSelection(int selection, Monster monster) {
        switch (selection) {
            case 1:
                executeAttack(Game.player, monster, Game.player.attack());
                if (monster.getHealth() > 0) {
                    executeAttack(monster, monster, monster.attack());
                }
                break;
            case 2:
                if (runAway()) {
                    System.out.println("You successfully ran away!");
                    return false;
                } else {
                    System.out.println("You failed to run away!");
                    executeAttack(monster, monster, monster.attack());
                }
                break;
            case 3:
                printInventory();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return true;
    }

    private static void executeAttack(Object attacker, Monster monster, int amount) {
        if (attacker instanceof Monster) {
            int totalDamage = Math.max(0, amount - Game.player.getDefence());
            System.out.println("The monster attacks you and deals " +
                    totalDamage + " damage!");
            Game.player.loseHealth(totalDamage);
            System.out.println("You have " + Game.player.getHealth() + " HP left.");
        } else {
            System.out.println("You attack the monster and deal " + amount + " damage!");
            monster.loseHealth(amount);
            System.out.println("The " + monster.getName() + " has " + monster.getHealth() + " HP left.");
        }
    }

    private static boolean runAway() {
        return Math.random() > 0.7;
    }

    static int getMenuSelection(ArrayList<Position> moves) {
        int selection;
        while (true) {
            try {
                selection = Game.scanner.nextInt();
                if (isValidSelection(selection, moves.size())) {
                    return selection;
                }
                System.out.println("Invalid input");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                Game.scanner.nextLine();
            }
        }
    }

    private static boolean isValidSelection(int selection, int numberOfMoves) {
        return selection > 0 && selection <= numberOfMoves + 2;
    }

    static void printInventory() {
        Game.player.printStats();
        ArrayList<Item> inventory = Game.player.getInventory();
        displayInventory(inventory);

        int selection = getInventorySelection(inventory.size());
        if (selection != inventory.size()) {
            handleInventorySelection(selection, inventory);
            printInventory();
        }
    }

    private static void displayInventory(ArrayList<Item> inventory) {
        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("[%d] " + item.getDescription(Game.player) + "%n", i + 1);
        }
        System.out.println("[" + (inventory.size() + 1) + "] Close inventory");
    }

    private static int getInventorySelection(int inventorySize) {
        int selection;
        while (true) {
            try {
                selection = Game.scanner.nextInt();
                if (isValidInventorySelection(selection, inventorySize)) {
                    return selection - 1;
                }
                System.out.println("Invalid input");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                Game.scanner.nextLine();
            }
        }
    }

    private static boolean isValidInventorySelection(int selection, int inventorySize) {
        return selection > 0 && selection <= inventorySize + 1;
    }

    private static void handleInventorySelection(int selection, ArrayList<Item> inventory) {
        if (selection >= 0 && selection < inventory.size()) {
            Game.player.useItem(inventory.get(selection));
        }
    }
}
