/**
 * 
 */
package com.tinrap.framework;

/**
 * @author Parnit
 *
 */
public interface Game {

	public Audio getAudio();

	public Input getInput();

	public FileIO getFileIO();

	public Graphics getGraphics();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getInitScreen();
}
