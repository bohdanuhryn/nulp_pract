package com.nulp.bohdanuhryn.snake.gui;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.Settings;
import com.nulp.bohdanuhryn.snake.ResourceManager;

public class LevelSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fullscreen on
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set layout
        setContentView(R.layout.activity_menu);
        // set header
        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.level_select_header);
        // content fill
        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
        Button button;
        LinearLayout buttonLayout;
        // Restart button
        buttonLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.menu_button, null);
        button = (Button) buttonLayout.findViewById(R.id.menu_button);
        button.setText(R.string.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LevelActivity.class);// TODO:!!!
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
                startActivity(intent);
            }
            });
        content.addView(buttonLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ResourceManager.ResumeBackgroundMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ResourceManager.PauseBackgroundMusic();
    }
}
