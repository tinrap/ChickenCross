package com.tinrap.ChickenCrossGame;

import android.graphics.Rect;

public class Projectile {

	private int x,y, speedX;
	private boolean visible;
	private Rect r; 
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
		
		r = new Rect(0,0,0,0);
	}
	
	public void update(){
		x += speedX;
		
		r.set(x, y, x + 10, y + 5);
		if (x > 810) {			
		   visible = false;
		   r = null;
		}
		
		if(x <801){
			checkCollision();
		}
	}
	
	private void checkCollision() {
        if(Rect.intersects(r, GameScreen.enemy1.r)){
            visible = false;
        
            if (GameScreen.enemy1.health > 0) {
                GameScreen.enemy1.health -= 1;
            }
            if (GameScreen.enemy1.health == 0) {
                GameScreen.enemy1.setCenterX(-100);
                //GameScreen.score += 5;

            }

        }
        
        if (Rect.intersects(r, GameScreen.enemy2.r)){
            visible = false;

            if (GameScreen.enemy2.health > 0) {
                GameScreen.enemy2.health -= 1;
            }
            if (GameScreen.enemy2.health == 0) {
                GameScreen.enemy2.setCenterX(-100);
                //GameScreen.score += 5;

            }

        }
    }
}
