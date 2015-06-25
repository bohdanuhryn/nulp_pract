package com.nulp.bohdanuhryn.snake;

import java.util.Vector;
import android.graphics.Point;

/**
 * Created by BohdanUhryn on 13.06.2015.
 */
public class Snake {

    public Vector<Bone> body;

    public int foodWeight;

    private SnakeState state;

    private int speedUpDelta;

    public Snake() {
    }

    public Snake(int x, int y, int length) {
        body = new Vector<Bone>();
        for(byte i = 0; i < length; ++i)
            body.add(new Bone(x, y + i));
        state = SnakeState.MOVE_ON;
        foodWeight = 0;
        speedUpDelta = 5;
    }

    public void Move(int moveX, int moveY, GameField gameField) {
        if(state == SnakeState.BITE_ITSELF || state == SnakeState.KICK_WALL)
            return;
        state = SnakeState.MOVE_ON;
        // Direction of snake head
        int dx = body.elementAt(0).x - body.elementAt(1).x;
        int dy = body.elementAt(0).y - body.elementAt(1).y;
        if(Math.abs(dx) > 1) dx = dx / Math.abs(dx) * -1;
        if(Math.abs(dy) > 1) dy = dy / Math.abs(dy) * -1;
        // Move direction
        Bone newHead;
        if((moveX == 0 && moveY == 0) || (moveX == dx || moveY == dy) || (moveX * -1 == dx || moveY * -1 == dy))
            newHead = new Bone(body.elementAt(0).x + dx, body.elementAt(0).y + dy);
        else
            newHead = new Bone(body.elementAt(0).x + moveX, body.elementAt(0).y + moveY);
        if(newHead.x < 0) newHead.x = gameField.width - 1;
        if(newHead.y < 0) newHead.y = gameField.height - 1;
        if(newHead.x >= gameField.width) newHead.x = 0;
        if(newHead.y >= gameField.height) newHead.y = 0;

        for(int i = 0; i < gameField.walls.size(); ++i)
            if(gameField.walls.elementAt(i).x == newHead.x &&
                    gameField.walls.elementAt(i).y == newHead.y)
                state = SnakeState.KICK_WALL;

        for(int i = 0; i < body.size(); ++i)
            if(body.elementAt(i).x == newHead.x && body.elementAt(i).y == newHead.y)
                state = SnakeState.BITE_ITSELF;

        if(gameField.food != null)
            if(gameField.food.x == newHead.x && gameField.food.y == newHead.y) {
                state = SnakeState.EAT_FOOD;
                foodWeight += gameField.food.weight;
            }

        if(state == SnakeState.MOVE_ON) {
            body.add(0, newHead);
            body.remove(body.lastElement());
        }
        if(state == SnakeState.EAT_FOOD)
            body.add(0, newHead);

        OnMove();
    }

    public SnakeState GetState() {
        return state;
    }

    public int GetSpeed() {
        if(500 - foodWeight > 300)
            return 500 - (foodWeight * speedUpDelta);
        else
            return 300;
    }

    public void SetSpeedUpDelta(int set) {
        speedUpDelta = set;
    }

    public void OnMove() {

    }

    public Snake Clone() {
        Snake snake = new Snake();
        snake.foodWeight = foodWeight;
        snake.speedUpDelta = speedUpDelta;
        snake.state = state;
        snake.body = new Vector<Bone>();
        for(int i = 0; i < body.size(); ++i)
            snake.body.add(new Bone(body.get(i).x, body.get(i).y));
        return snake;
    }
}
