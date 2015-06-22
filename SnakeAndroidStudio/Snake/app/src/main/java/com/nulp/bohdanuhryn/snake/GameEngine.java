package com.nulp.bohdanuhryn.snake;

import java.util.Vector;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.app.Activity;
import android.os.Looper;
import android.widget.TextView;
import android.os.Handler;

import com.nulp.bohdanuhryn.snake.gui.MainMenuActivity;
import com.nulp.bohdanuhryn.snake.gui.SaveScoreActivity;

/**
 * Created by BohdanUhryn on 14.06.2015.
 */
public class GameEngine {

    private GameField gameField;

    private Snake snake;

    private int cellSize;

    private int moveX;

    private int moveY;

    private int gameSpeed;

    private int selectedLevelId;

    public GameEngine() {
        selectedLevelId = -1;
        TestField();
        moveX = 0;
        moveY = 0;
        gameSpeed = snake.GetSpeed();
    }

    public GameEngine(int _selectedLevelId) {
        selectedLevelId = _selectedLevelId;
        moveX = 0;
        moveY = 0;
        gameSpeed = snake.GetSpeed();
    }

    public void InitActivity(final Activity context) {
        final TextView score = (TextView) context.findViewById(R.id.score_text_view);
        score.post(new Runnable() {
            public void run() {
                String str = Integer.toString(snake.foodWeight).concat(" / ")
                        .concat(Integer.toString(ScoresManager.GetMaxScore()));
                score.setText(str);
            }
        });
    }

    public void Update() {
        gameField.Move(moveX, moveY);
        moveX = 0;
        moveY = 0;
    }

    public void UpdateUI(final Activity context) {
        switch (snake.GetState()) {
            case EAT_FOOD:
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.SNAKE_EAT_SOUND);
                final TextView score = (TextView) context.findViewById(R.id.score_text_view);
                score.post(new Runnable() {
                    public void run() {
                        String str = Integer.toString(snake.foodWeight).concat(" / ")
                                .concat(Integer.toString(ScoresManager.GetMaxScore()));
                        score.setText(str);
                    }
                });
                gameSpeed = snake.GetSpeed();
                break;
            case BITE_ITSELF:
            case KICK_WALL:
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.SNAKE_BITE_SOUND);
                Handler handler = new Handler(Looper.getMainLooper());
                if(ScoresManager.CheckScore(snake.foodWeight)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, SaveScoreActivity.class);
                            intent.putExtra("score", snake.foodWeight);
                            intent.putExtra(getResources().getString(R.string.selected_level_id), selectedLevelId);
                            context.startActivity(intent);
                        }
                    });
                }
                else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, MainMenuActivity.class);
                            intent.putExtra(context.getResources().getString(R.string.set_game_end_menu), true);
                            intent.putExtra(getResources().getString(R.string.selected_level_id), selectedLevelId);
                            context.startActivity(intent);
                        }
                    });
                }
                break;
        }
    }

    public void Draw(Canvas canvas) {
        Paint p = new Paint();

        p.setARGB(255, 190, 190, 100);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);

        p.setARGB(255, 120, 80, 80);
        for(int i = 0; i < gameField.walls.size(); ++i) {
            canvas.drawRect(
                    gameField.walls.elementAt(i).x * cellSize,
                    gameField.walls.elementAt(i).y * cellSize,
                    (gameField.walls.elementAt(i).x + 1) * cellSize,
                    (gameField.walls.elementAt(i).y + 1) * cellSize,
                    p);
        }

        p.setARGB(255, 255, 0, 0);
        if(gameField.food != null)
            canvas.drawCircle(
                    gameField.food.x * cellSize + cellSize / 2,
                    gameField.food.y * cellSize + cellSize / 2,
                    cellSize / 2,
                    p);

        p.setARGB(255, 0, 160, 0);
        for(int i = 0; i < snake.body.size(); ++i) {
            canvas.drawRect(
                    snake.body.elementAt(i).x * cellSize,
                    snake.body.elementAt(i).y * cellSize,
                    (snake.body.elementAt(i).x + 1) * cellSize,
                    (snake.body.elementAt(i).y + 1) * cellSize,
                    p);
        }
    }

    public void SetMoveDirection(int _moveX, int _moveY) {
        moveX = _moveX;
        moveY = _moveY;
    }

    public void UpdateCellSize(int _width, int _height) {
        cellSize = _width / gameField.width;
        if(_height / gameField.height < cellSize)
            cellSize = _height / gameField.height;
    }

    public int GetGameSpeed() {
        return gameSpeed;
    }

    private void TestField() {
        snake = new Snake(2, 2, 2);
        byte w = 10;
        byte h = 10;
        Vector<Wall> field = new Vector<Wall>();
        field.add(new Wall(0, 0));
        field.add(new Wall(0, 9));
        field.add(new Wall(9, 9));
        field.add(new Wall(9, 0));
        gameField = new GameField(w, h, field, snake);
    }
}