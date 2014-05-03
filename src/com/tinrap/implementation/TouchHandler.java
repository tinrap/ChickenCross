/**
 * 
 */
package com.tinrap.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import com.tinrap.framework.Input.TouchEvent;

/**
 * @author Parnit Sainion	
 * @description: Framework taken from kilobolt.com
 * @since 3 May 2014
 *
 */
public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
