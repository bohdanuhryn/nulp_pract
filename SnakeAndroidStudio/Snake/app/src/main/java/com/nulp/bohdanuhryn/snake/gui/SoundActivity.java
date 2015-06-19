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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ResourceManager;
import com.nulp.bohdanuhryn.snake.Settings;

public class SoundActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Settings.ReadSettings();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        TextView header = (TextView)findViewById(R.id.menu_header);
        header.setText(R.string.sound_header);
        // content fill
        LinearLayout content = (LinearLayout)findViewById(R.id.menu_content);
        TextView text;
        LinearLayout buttonLayout;
        // Sound fx button
        buttonLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.toggle_button, null);
        final ToggleButton soundFxButton = (ToggleButton) buttonLayout.findViewById(R.id.toggle_button);
        soundFxButton.setChecked(Settings.IsSoundFxOn());
        soundFxButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.SetSoundFxOn(isChecked);
                Settings.WriteSettings();
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
            }
        });
        text = (TextView) buttonLayout.findViewById(R.id.toggle_button_text);
        text.setText(R.string.sound_fx_button);
        content.addView(buttonLayout);
        // Music button
        buttonLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.toggle_button, null);
        final ToggleButton musicButton = (ToggleButton) buttonLayout.findViewById(R.id.toggle_button);
        musicButton.setChecked(Settings.IsMusicOn());
        musicButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.SetMusicOn(isChecked);
                Settings.WriteSettings();
                ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
                if(isChecked) {
                    ResourceManager.PlayBackgroundMusic(true);
                }
                else {
                    ResourceManager.PlayBackgroundMusic(false);
                }
            }
        });
        text = (TextView) buttonLayout.findViewById(R.id.toggle_button_text);
        text.setText(R.string.music_button);
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