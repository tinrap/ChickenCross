/**
 * 
 */
package com.tinrap.framework;

import com.tinrap.framework.Graphics.ImageFormat;

/**
 * @author Parnit Sainion	
 * @description: Framework taken from kilobolt.com
 * @since 3 May 2014
 *
 */
public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();

}
