package com.tinrap.ChickenCrossGame;

import com.tinrap.framework.Image;
import com.tinrap.framework.Music;
import com.tinrap.framework.Sound;

public class Assets {
    
    public static Image menu, splash, background, chicken, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, chickenJump, chickenSit, chickenWalkLeft, chickenWalkRight;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    public static void load(ChickenCrossGame game) {
        // TODO Auto-generated method stub
        theme = game.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }
    
}
