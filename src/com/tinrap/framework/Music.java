/**
 * 
 */
package com.tinrap.framework;

/**
 * @author Parnit Sainion	
 * @description: Framework taken from kilobolt.com
 * @since 3 May 2014
 *
 */
public interface Music {
	public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();

    void seekBegin();
}
