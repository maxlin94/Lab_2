package se.lernia.lindstrom.max.game;

import se.lernia.lindstrom.max.entities.Monster;
import se.lernia.lindstrom.max.entities.Player;
import se.lernia.lindstrom.max.entities.Position;
import se.lernia.lindstrom.max.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static final Scanner scanner = new Scanner(System.in);
    static Player player;
    private static final int[][] map = new int[6][6];
    private static final Maze maze = new Maze();


    public static void main(String[] args) {
        init();
        gameLoop();
    }

    private static void gameLoop() {
        while (player.getHealth() > 0) {
            ArrayList<Position> moves = maze.getValidMoves(player.getPosition());
            GameUI.displayAvailableMoves(moves, maze);
            int selection = GameUI.getMenuSelection(moves);
            handleMenuSelection(selection, moves);
            maze.updateMaze();
        }
    }

    private static void init() {
        player = createPlayer();
        Randomize.addRandomMonsters(maze, 4);
        Randomize.addRandomLoot(maze, 7);
    }

    private static Player createPlayer() {
        System.out.println("What's your name?");
        String name = scanner.nextLine();
        return new Player(name, 100, Position.of(0, 0), 8, 10);
    }

    private static void handleMenuSelection(int selection, ArrayList<Position> moves) {
        if (selection == moves.size() + 1) {
            GameUI.printInventory();
        } else if (selection == moves.size() + 2) {
            printMap();
        } else {
            Position oldPos = player.getPosition();
            Position newPos = moves.get(selection - 1);
            Monster monster = maze.checkForMonster(newPos);
            if (monster != null) {
                initializeBattle(monster);
            }
            if (maze.checkForMonster(newPos) == null) {
                player.move(newPos);
            }
            updateMap(oldPos, newPos);
            checkForItem();
        }
    }

    private static void updateMap(Position oldPos, Position currentPos) {
        map[oldPos.x()][oldPos.y()] = 1;
        map[currentPos.x()][currentPos.y()] = 2;
    }

    private static void printMap() {
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void checkForItem() {
        ArrayList<Item> items = maze.getItems();
        for (Item item : items) {
            if (item.getPosition().equals(player.getPosition())) {
                player.addItem(item);
                maze.removeItem(item);
            }
        }
    }

    private static void initializeBattle(Monster monster) {
        System.out.println("You have encountered a monster");
        monster.printStats();

        while (monster.getHealth() > 0 && player.getHealth() > 0) {
            GameUI.printBattleMenu();
            try {
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (!GameUI.processBattleSelection(selection, monster)) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
            checkBattleResult(monster);
        }
    }

    private static void checkBattleResult(Monster monster) {
        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated by the monster...");
        } else if (monster.getHealth() <= 0) {
            System.out.println("You have defeated the monster!");
            player.addItem(monster.getLoot());
            maze.removeMonster(monster);
        }
    }
}