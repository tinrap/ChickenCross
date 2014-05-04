package com.tinrap.ChickenCrossGame;


import com.tinrap.framework.Image;
import android.graphics.Rect;

public class Tile {

    private int tileX, tileY, speedX;
    public int type;
    public Image tileImage;
    private Chicken robot = GameScreen.getChicken();
    private Rect r;

    private Background bg = GameScreen.getBg1();

    public Tile(int x, int y, int typeInt) {
        tileX = x * 40;
        tileY = y * 40;

        type = typeInt;

        if (type == 5) {
            tileImage = Assets.tiledirt;
        } else if (type == 8) {
            tileImage = Assets.tilegrassTop;
        } else if (type == 4) {
            tileImage = Assets.tilegrassLeft;

        } else if (type == 6) {
            tileImage = Assets.tilegrassRight;

        } else if (type == 2) {
            tileImage = Assets.tilegrassBot;
        }else{
            type = 0;
        }
        
        r = new Rect();

    }

    public void update() {
        speedX = bg.getSpeedX() * 5;
        tileX += speedX;
        r.set(tileX, tileY, tileX + 40, tileY + 40);
        
        if (Rect.intersects(r, Chicken.yellowRed) && type != 0) {
			checkVerticalCollision(Chicken.rect, Chicken.rect2);
			checkSideCollision(Chicken.left, Chicken.right);
		}
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }
    
    public void checkVerticalCollision(Rect rtop, Rect rbot) {
        if (Rect.intersects(rtop, r)) {
        }

        if (Rect.intersects(rbot, r) && type == 8) {
            robot.setJumped(false);
            robot.setSpeedY(0);
            robot.setCenterY(tileY - 70);
        }
    }

    public void checkSideCollision(Rect rleft, Rect rright) {
        if (type != 5 && type != 2 && type != 0){
            if (Rect.intersects(rleft, r)) {
                robot.setCenterX(tileX + 80);
                robot.setSpeedX(0);    
            }
            
            if (Rect.intersects(rright, r)) {
                robot.setCenterX(tileX - 62); 
                robot.setSpeedX(0);
            }
        }
    }

}