package com.tinrap.ChickenCrossGame;

import android.graphics.Rect;
import java.util.ArrayList;

public class Chicken {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	
	private int centerX = 100;
	private int centerY = 363;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private int speedX = 0;
	private int speedY = 0;
	
	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	

	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect left = new Rect(0, 0, 0, 0);
	public static Rect right = new Rect(0, 0, 0, 0);	
	public static Rect yellowRed = new Rect(0, 0, 0, 0);
	
	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void update() {
		// Moves Character or Scrolls Background accordingly.

		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200){
			bg1.setSpeedX(-MOVESPEED/5);
			bg2.setSpeedX(-MOVESPEED/5);
		}

		// Updates Y Position
		centerY += speedY;
		
		// Handles Jumping
		speedY += 1;

		if (speedY > 3){
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		
		rect.set(centerX - 30, centerY - 45	, centerX + 65, centerY - 5);
		rect2.set(rect.left+20, rect.top + 40, rect.left+88, rect.top + 116);

		left.set(rect.left, rect.top, rect.left + 26, rect.top+ 70);
		right.set(rect.left + 68, rect.top, rect.left + 94, rect.top+70);
		
		yellowRed.set(centerX - 110, centerY - 110, centerX +70, centerY + 80);
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}
