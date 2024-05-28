package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneratoreMappa {
    static final int MAP_SIZE = 50;
    static final double NOISE_SCALE = 0.1;
    static final double TREE_THRESHOLD = 0.6;
    static final double WATER_THRESHOLD = 0.4;
    static final double SAND_THRESHOLD = 0.5;

    static final int DIRT = 0;
    static final int COBBLESTONE = 1;
    static final int WATER = 2;
    static final int TREE = 3;
    static final int SAND = 4;

    static Random random = new Random();

    public static void s() {
        double[][] noiseMap = generateNoiseMap(MAP_SIZE, MAP_SIZE);

        // Genera mappa
        int[][] map = generateMap(noiseMap);

        String fileName = "mondo02.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int y = 0; y < MAP_SIZE; y++) {
                for (int x = 0; x < MAP_SIZE; x++) {
                    writer.write(Integer.toString(map[y][x]));
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Mappa generated successfully and saved as " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Generazione mappa perlin noise
    static double[][] generateNoiseMap(int width, int height) {
        double[][] noiseMap = new double[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                noiseMap[x][y] = random.nextDouble();
            }
        }

        return noiseMap;
    }

    // Generazione in base alla mappa di perlin noise
    static int[][] generateMap(double[][] noiseMap) {
        int[][] map = new int[MAP_SIZE][MAP_SIZE];

        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                double noiseValue = noiseMap[x][y];

                if (noiseValue < WATER_THRESHOLD) {
                    map[y][x] = WATER;
                } else if (noiseValue < SAND_THRESHOLD) {
                    map[y][x] = SAND;
                } else {
                    map[y][x] = DIRT;
                }

                if (map[y][x] == DIRT && noiseValue > TREE_THRESHOLD && random.nextDouble() < 0.2) {
                    map[y][x] = TREE;
                }
            }
        }

        return map;
    }
}
