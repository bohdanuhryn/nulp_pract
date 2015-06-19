package com.nulp.bohdanuhryn.snake;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BohdanUhryn on 19.06.2015.
 */
public class Settings {

    private static boolean isSoundFxOn;

    private static boolean isMusicOn;

    private static Context context;

    public static void Init(Context _context) {
        context = _context;
        ReadSettings();
    }

    public static boolean IsSoundFxOn() {
        return isSoundFxOn;
    }

    public static void SetSoundFxOn(boolean set) {
        isSoundFxOn = set;
    }

    public static boolean IsMusicOn() {
        return isMusicOn;
    }

    public static void SetMusicOn(boolean set) {
        isMusicOn = set;
    }

    public static void ReadSettings() {
        SharedPreferences settingsFile = context.getSharedPreferences(
                context.getString(R.string.settings_file), Context.MODE_PRIVATE);
        if(settingsFile == null) return;
        isSoundFxOn = settingsFile.getBoolean(context.getString(R.string.sound_fx_item), true);
        isMusicOn = settingsFile.getBoolean(context.getString(R.string.music_item), true);
    }

    public static void WriteSettings() {
        SharedPreferences settingsFile = context.getSharedPreferences(
                context.getString(R.string.settings_file), Context.MODE_PRIVATE);
        if(settingsFile == null) return;
        SharedPreferences.Editor editor = settingsFile.edit();
        editor.clear();
        editor.putBoolean(context.getString(R.string.sound_fx_item), isSoundFxOn);
        editor.putBoolean(context.getString(R.string.music_item), isMusicOn);
        editor.commit();
    }

}
