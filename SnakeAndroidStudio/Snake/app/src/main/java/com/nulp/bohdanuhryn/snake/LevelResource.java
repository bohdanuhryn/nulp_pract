package com.nulp.bohdanuhryn.snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by BohdanUhryn on 23.06.2015.
 */
public class LevelResource {

    public int width;

    public int height;

    public Vector<Wall> walls;

    public Snake snake;

    private static HashMap<Integer, LevelResource> levels;

    public static void InitLevels() {
        levels = new HashMap<Integer, LevelResource>();
        int n = 0;
        LevelResource level;

        level = new LevelResource();
        level.height = 19;
        level.width = 15;
        level.snake = new Snake(8, 9, 2);
        level.walls = new Vector<Wall>();
        levels.put(n++, level);

        level = new LevelResource();
        level.height = 19;
        level.width = 15;
        level.snake = new Snake(8, 9, 2);
        level.walls = new Vector<Wall>();
        for(int i = 0; i < level.width; ++i) {
            level.walls.add(new Wall(i, 0));
            level.walls.add(new Wall(i, level.height - 1));
        }
        for(int i = 1; i < level.height - 1; ++i) {
            level.walls.add(new Wall(0, i));
            level.walls.add(new Wall(level.width - 1, i));
        }
        levels.put(n++, level);

        level = new LevelResource();
        level.height = 19;
        level.width = 15;
        level.snake = new Snake(8, 9, 2);
        level.walls = new Vector<Wall>();
        for(int i = 2; i < level.width - 2; ++i) {
            level.walls.add(new Wall(i, 5));
            level.walls.add(new Wall(i, 13));
        }
        levels.put(n, level);
    }

    public LevelResource Clone() {
        LevelResource level = new LevelResource();
        level.height = height;
        level.width = width;
        level.snake = snake.Clone();
        level.walls = new Vector<Wall>();
        for(int i = 0; i < walls.size(); ++i)
            level.walls.add(new Wall(walls.get(i).x, walls.get(i).y));
        return level;
    }

    public static LevelResource Get(int id) {
        if(levels == null)
            InitLevels();
        else if(levels.isEmpty())
            InitLevels();
        return levels.get(id).Clone();
    }

    public static ArrayList<Integer> GetIdArray() {
        if(levels == null)
            InitLevels();
        else if(levels.isEmpty())
            InitLevels();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for(Map.Entry<Integer, LevelResource> entry : levels.entrySet()) {
            array.add(entry.getKey());
        }
        return array;
    }
}
