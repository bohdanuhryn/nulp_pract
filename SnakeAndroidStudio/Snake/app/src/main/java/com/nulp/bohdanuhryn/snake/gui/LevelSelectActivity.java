package com.nulp.bohdanuhryn.snake.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import com.nulp.bohdanuhryn.snake.LevelResource;
import com.nulp.bohdanuhryn.snake.LevelThumbView;
import com.nulp.bohdanuhryn.snake.R;
import com.nulp.bohdanuhryn.snake.ResourceManager;

import java.util.ArrayList;

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

        LinearLayout frame;
        LevelThumbView level_thumb;
        TextView level_header;
        ArrayList<Integer> levelsList = LevelResource.GetIdArray();
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < levelsList.size(); ++i) {
            frame = (LinearLayout)inflater.inflate(R.layout.levels_list_item, null);
            level_thumb = (LevelThumbView) frame.findViewById(R.id.level_thumb);
            level_header = (TextView) frame.findViewById(R.id.level_header);
            level_thumb.setLevelId(levelsList.get(i));
            level_header.setText(getResources().getString(R.string.level_header_prefix) + Integer.toString(i + 1));
            frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), LevelActivity.class);
                    LevelThumbView thumb = (LevelThumbView)v.findViewById(R.id.level_thumb);
                    intent.putExtra(
                            getResources().getString(R.string.selected_level_id),
                            thumb.getLevelId());
                    ResourceManager.PlaySoundFX(ResourceManager.ResourceEnum.BUTTON_CLICK_SOUND);
                    startActivity(intent);
                }
            });
            content.addView(frame);
        }
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
