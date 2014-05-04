package com.tinrap.ChickenCrossGame;

import android.graphics.Rect;

public class Enemy {
	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private Background bg = GameScreen.getBg1();
	private Chicken robot = GameScreen.getChicken();
	
	public int health= 5;

	public Rect r = new Rect(0,0,0,0);

	private int movementSpeed;
	
	public void update(){

		follow();
		centerX +=speedX;
		

		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		r.set(centerX - 25, centerY-25, centerX+ 25, centerY + 35);
		
		if (Rect.intersects(r, Chicken.yellowRed)){
			checkCollision();
		}
	}
	
public void follow() {
		
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}
	
	private void checkCollision() {
		if (Rect.intersects(r, Chicken.rect) || Rect.intersects(r, Chicken.rect2) || Rect.intersects(r, Chicken.left) || Rect.intersects(r, Chicken.right)){
				System.out.println("collision");			
			}
		}
	
	public void die(){
		
	}
	
	public void attack(){
		
	}
	
	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

}
