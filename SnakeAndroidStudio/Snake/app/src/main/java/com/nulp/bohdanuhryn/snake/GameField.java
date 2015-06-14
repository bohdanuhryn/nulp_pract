package com.nulp.bohdanuhryn.snake;

/**
 * Created by BohdanUhryn on 13.06.2015.
 */
public class GameField {

    public byte width;
    public byte height;
    //public byte[,] field;

    public GameField(
            byte _width,
            byte _height/*,
            byte[,] _field*/) {
        width = _width;
        height = _height;
        //field = _field;
    }
}