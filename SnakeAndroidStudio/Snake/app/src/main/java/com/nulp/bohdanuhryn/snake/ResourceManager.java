package com.nulp.bohdanuhryn.snake;

import java.util.Map;
import java.util.HashMap;
import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by BohdanUhryn on 19.06.2015.
 */
public class ResourceManager {

    public enum ResourceEnum {
        BUTTON_CLICK_SOUND,
        SNAKE_EAT_SOUND,
        SNAKE_BITE_SOUND,
        BACKGROUND_MUSIC
    }

    private static boolean isInitialized = false;

    private static Context context;

    private static boolean isBackgroundMusicPlay;

    private static Map<ResourceEnum, MediaPlayer> players;

    public static void Init(Context _context) {
        if(isInitialized)
            return;
        context = _context;
        players = new HashMap<ResourceEnum, MediaPlayer>();
        players.put(ResourceEnum.BUTTON_CLICK_SOUND, MediaPlayer.create(context, R.raw.button_click));
        players.put(ResourceEnum.SNAKE_EAT_SOUND, MediaPlayer.create(context, R.raw.snake_eat));
        players.put(ResourceEnum.SNAKE_BITE_SOUND, MediaPlayer.create(context, R.raw.snake_kick));
        MediaPlayer background = MediaPlayer.create(context, R.raw.background_music);
        background.setLooping(true);
        players.put(ResourceEnum.BACKGROUND_MUSIC, background);
        isBackgroundMusicPlay = false;
        isInitialized = true;
    }

    public static void PlaySoundFX(ResourceEnum res) {
        if(Settings.IsSoundFxOn())
            players.get(res).start();
    }

    public static void PlayBackgroundMusic(boolean play) {
        if(play && isBackgroundMusicPlay)
            return;
        if(play) {
            players.get(ResourceEnum.BACKGROUND_MUSIC).start();
            isBackgroundMusicPlay = true;
        }
        else {
            players.get(ResourceEnum.BACKGROUND_MUSIC).stop();
            isBackgroundMusicPlay = false;
        }
    }

    public static void PauseBackgroundMusic() {
        players.get(ResourceEnum.BACKGROUND_MUSIC).pause();
    }

    public static void ResumeBackgroundMusic() {
        players.get(ResourceEnum.BACKGROUND_MUSIC).start();
    }

}