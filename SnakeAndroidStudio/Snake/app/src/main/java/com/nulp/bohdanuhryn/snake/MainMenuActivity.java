package com.nulp.bohdanuhryn.snake;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.main_menu_header);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click);

        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
        Button button;

        button = new Button(this);
        button.setText(R.string.game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LevelActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);

        button = new Button(this);
        button.setText(R.string.scores_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScoresActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);

        button = new Button(this);
        button.setText(R.string.sound_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SoundActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);
        // About button
        button = new Button(this);
        button.setText(R.string.about_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutActivity.class);
                mp.start();
                startActivity(intent);
            }
        });
        content.addView(button);
    }
}
