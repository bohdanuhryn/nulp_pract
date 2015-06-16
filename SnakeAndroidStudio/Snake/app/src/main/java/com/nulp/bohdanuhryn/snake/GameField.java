package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import java.lang.Math;
import java.util.Random;
import android.graphics.Point;

/**
 * Created by BohdanUhryn on 13.06.2015.
 */
public class GameField {

    public int width;
    public int height;
    public Vector<Wall> walls;

    private Snake snake;

    public Food food;

    public GameField(
            int _width,
            int _height,
            Vector<Wall> _walls,
            Snake _snake) {
        width = _width;
        height = _height;
        walls = _walls;
        snake = _snake;
        food = null;
        GenerateFood();
    }

    public void GenerateFood() {
        Vector<Point> emptyFieldCells = new Vector<Point>();

        int[][] fieldCells = new int[height][width];

        for(int i = 0; i < walls.size(); ++i)
            fieldCells[walls.elementAt(i).y][walls.elementAt(i).x] = 1;
        for(int i = 0; i < snake.body.size(); ++i)
            fieldCells[snake.body.elementAt(i).y][snake.body.elementAt(i).x] = 1;

        for(int i = 0; i < height; ++i)
            for(int j = 0; j < width; ++j) {
                if(fieldCells[i][j] == 0)
                    emptyFieldCells.add(new Point(j, i));
            }

        Random r = new Random();
        int emptyCellNumber = r.nextInt(emptyFieldCells.size());
        food = new Food(
                emptyFieldCells.elementAt(emptyCellNumber).x,
                emptyFieldCells.elementAt(emptyCellNumber).y,
                1);
    }

    public void Move(int moveX, int moveY) {
        snake.Move(moveX, moveY, this);
        if(snake.GetState() == SnakeState.EAT_FOOD)
            GenerateFood();
    }
}