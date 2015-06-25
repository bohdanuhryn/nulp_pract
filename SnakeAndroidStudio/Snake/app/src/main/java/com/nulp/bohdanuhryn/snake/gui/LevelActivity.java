package com.nulp.bohdanuhryn.snake.gui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nulp.bohdanuhryn.snake.GameEngine;
import com.nulp.bohdanuhryn.snake.GameView;
import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ResourceManager;
import com.nulp.bohdanuhryn.snake.ScoresManager;


public class LevelActivity extends Activity {

    private GameEngine gameEngine;

    private GameView gameView;

    private int selectedLevelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        gameEngine = (GameEngine)getLastNonConfigurationInstance();
        Intent intent = getIntent();
        selectedLevelId = intent.getIntExtra(getResources().getString(R.string.selected_level_id), -1);
        if(gameEngine == null) {
            gameEngine = new GameEngine(selectedLevelId);
        }
        gameEngine.InitActivity(this);
        gameView = new GameView(this, gameEngine);
        LinearLayout l = (LinearLayout) findViewById(R.id.level_linear_layout);
        l.addView(gameView);
        ScoresManager.Init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.Resume();
        ResourceManager.PlayBackgroundMusic(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.Suspend();
        ResourceManager.PlayBackgroundMusic(false);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        final GameEngine data = gameEngine;
        return data;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void pauseOnClick(View v) {
        gameView.Suspend();
        Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
        intent.putExtra(getResources().getString(R.string.set_pause_menu), true);
        intent.putExtra(getResources().getString(R.string.selected_level_id), selectedLevelId);
        ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
        startActivity(intent);
    }
}
