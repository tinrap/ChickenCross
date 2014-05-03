/**
 * 
 */
package com.tinrap.implementation;

import android.media.SoundPool;

import com.tinrap.framework.Sound;

/**
 * @author Parnit Sainion	
 * @description: Framework taken from kilobolt.com
 * @since 3 May 2014
 *
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
