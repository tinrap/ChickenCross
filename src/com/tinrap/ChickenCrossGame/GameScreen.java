package com.tinrap.ChickenCrossGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;

import com.tinrap.framework.Game;
import com.tinrap.framework.Graphics;
import com.tinrap.framework.Image;
import com.tinrap.framework.Input.TouchEvent;
import com.tinrap.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup

	private static Background bg1, bg2;
	private static Chicken chicken;
	public static EnemyOne enemy1, enemy2;

	private Image currentSprite, chickenImg;
	private Image enemyOneImg, enemyTwoImg, enemyThreeImg, enemyFourImg, enemyFiveImg;
	private Image walkLeft, walkRight;
			
	private Animation anim, enemyAnim, walkingAnim;

	private ArrayList tilearray = new ArrayList();

	int livesLeft = 1;
	Paint paint, paint2;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		chicken = new Chicken();
		enemy1 = new EnemyOne(340, 360);
		enemy2 = new EnemyOne(700, 360);

		chickenImg = Assets.chicken;
		
		enemyOneImg = Assets.heliboy;
		enemyTwoImg = Assets.heliboy2;
		enemyThreeImg = Assets.heliboy3;
		enemyFourImg = Assets.heliboy4;
		enemyFiveImg = Assets.heliboy5;
		
		walkLeft = Assets.chickenWalkLeft;
		walkRight = Assets.chickenWalkRight;

		anim = new Animation();
		anim.addFrame(chickenImg, 1250);
		/*anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
*/
		enemyAnim = new Animation();
		enemyAnim.addFrame(enemyOneImg, 100);
		enemyAnim.addFrame(enemyTwoImg, 100);
		enemyAnim.addFrame(enemyThreeImg, 100);
		enemyAnim.addFrame(enemyFourImg, 100);
		enemyAnim.addFrame(enemyFiveImg, 100);
		enemyAnim.addFrame(enemyFourImg, 100);
		enemyAnim.addFrame(enemyThreeImg, 100);
		enemyAnim.addFrame(enemyTwoImg, 100);

		
		walkingAnim = new Animation();
		walkingAnim.addFrame(walkLeft, 120);
		walkingAnim.addFrame(chickenImg, 120);
		walkingAnim.addFrame(walkRight, 120);
		

		currentSprite = anim.getImage();
		
		loadMap();

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = new Scanner(ChickenCrossGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void update(float deltaTime) {
		List touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 0, 285, 65, 65)) {
					chicken.jump();
					currentSprite = anim.getImage();
					chicken.setDucked(false);
				}

				else if (inBounds(event, 0, 350, 65, 65)) {

					if (chicken.isDucked() == false && chicken.isJumped() == false
							&& chicken.isReadyToFire()) {
						chicken.shoot();
					}
				}

				else if (inBounds(event, 0, 415, 65, 65)
						&& chicken.isJumped() == false) {
					currentSprite = Assets.chickenSit;
					chicken.setDucked(true);
					chicken.setSpeedX(0);

				}

				if (event.x > 400) {
					// Move right.
					chicken.moveRight();
					chicken.setMovingRight(true);

				}

			}

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 415, 65, 65)) {
					currentSprite = anim.getImage();
					chicken.setDucked(false);

				}

				if (inBounds(event, 0, 0, 35, 35)) {
					pause();

				}

				if (event.x > 400) {
					// Move right.
					chicken.stopRight();
				}
			}

		}

		// 2. Check miscellaneous events like death:

		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, robot.update();
		chicken.update();
		if (chicken.isJumped()) {
			currentSprite = Assets.chickenJump;
		} else if (chicken.isMovingLeft() || chicken.isMovingRight()) {
			currentSprite = walkingAnim.getImage();
		}else if (chicken.isJumped() == false && chicken.isDucked() == false) {
			currentSprite = anim.getImage();
		}

		ArrayList projectiles = chicken.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}
		
		updateTiles();
		enemy1.update();
		enemy2.update();
		bg1.update();
		bg2.update();
		animate();

		if (chicken.getCenterY() > 500) {
			state = GameState.GameOver;
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		ArrayList<Projectile> projectiles = chicken.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
		}
		// First draw the game elements.

		g.drawImage(currentSprite, chicken.getCenterX() - 61,
				chicken.getCenterY() - 70);
		g.drawImage(enemyAnim.getImage(), enemy1.getCenterX() - 48,
				enemy1.getCenterY() - 48);
		g.drawImage(enemyAnim.getImage(), enemy2.getCenterX() - 48,
				enemy2.getCenterY() - 48);
		
		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		anim.update(10);
		enemyAnim.update(50);
		walkingAnim.update(15);
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		bg1 = null;
		bg2 = null;
		chicken = null;
		enemy1 = null;
		enemy2 = null;
		currentSprite = null;
		chickenImg = null;
		enemyOneImg = null;
		enemyTwoImg = null;
		enemyThreeImg = null;
		enemyFourImg = null;
		enemyFiveImg = null;
		anim = null;
		enemyAnim = null;
		walkingAnim = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
		g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
		g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
		g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Chicken getChicken() {
		// TODO Auto-generated method stub
		return chicken;
	}

}
