package com.nulp.bohdanuhryn.snake.gui;

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

import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ResourceManager;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.about_header);

        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ResourceManager.PlayBackgroundMusic(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ResourceManager.PlayBackgroundMusic(false);
    }
}
