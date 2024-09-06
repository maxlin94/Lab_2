package se.lernia.lindstrom.max.game;

import se.lernia.lindstrom.max.entities.Monster;
import se.lernia.lindstrom.max.entities.Position;
import se.lernia.lindstrom.max.items.Item;
import se.lernia.lindstrom.max.items.ItemList;

import java.util.ArrayList;
import java.util.Random;

public class Randomize {
    private static final Random random = new Random();

    public static void addRandomMonsters(Maze maze, int amount) {
        for (int i = 0; i < amount; i++) {
            Monster monster = createRandomMonster(maze);
            maze.addMonster(monster);
        }
    }

    public static void addRandomLoot(Maze maze, int amount) {
        for (int i = 0; i < amount; i++) {
            Item loot = createRandomLoot(maze);
            maze.addItem(loot);
        }
    }

    private static Item createRandomLoot(Maze maze) {
        ArrayList<Position> validPositions = getValidMazePos(maze);
        int randomIndex = random.nextInt(0, validPositions.size());
        return randomLoot(validPositions.get(randomIndex));
    }

    private static Monster createRandomMonster(Maze maze) {
        ArrayList<Position> validPositions = getValidMazePos(maze);
        int randomIndex = random.nextInt(0, validPositions.size());
        Item loot = randomLoot(validPositions.get(randomIndex));
        Position position = validPositions.get(randomIndex);
        return Monster.of(randomName(), randomHealth(), position, randomAttack(), loot, randomDefense());
    }

    private static String randomName() {
        String[] classicMonsters = {
                "Orc",
                "Goblin",
                "Troll",
                "Dragon",
                "Golem",
                "Giant",
                "Cyclops",
                "Griffin",
                "Minotaur",
                "Kobold",
                "Undead",
                "Lich",
                "Vampire",
                "Werewolf",
                "Wraith",
                "Basilisk",
                "Manticore",
                "Sphinx",
                "Chimera",
                "Hydra"
        };
        return classicMonsters[random.nextInt(0, classicMonsters.length)];
    }

    private static int randomHealth() {
        return random.nextInt(30, 150);
    }

    private static int randomAttack() {
        return random.nextInt(8, 20);
    }

    private static int randomDefense() {
        return random.nextInt(0, 10);
    }

    public static Item randomLoot(Position position) {
        ItemList[] types = ItemList.values();
        int randomIndex = random.nextInt(types.length);
        Item loot = types[randomIndex].getItem();
        loot.setPosition(position);
        return loot;
    }

    private static ArrayList<Position> getValidMazePos(Maze maze) {
        ArrayList<Position> positions = new ArrayList<>();
        int[][] arr = maze.getMaze();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    positions.add(Position.of(i, j));
                }
            }
        }
        return positions;
    }
}
