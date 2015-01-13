/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.utils;

import kylevedder.com.github.main.MainApp;
import org.newdawn.slick.Image;

/**
 *
 * @author Kyle
 */
public class Utils
{

    /**
     *
     * If val greater than high, wraps val to # above high to low (inclusive)
     * <p>
     * If val lower than low, wraps val to # below low to high (inclusive)
     * </p>
     *
     * @param val
     * @param low
     * @param high
     * @return
     */
    public static int wrapInt(int val, int low, int high)
    {
        int newVal = val % high;
        return (newVal < 0) ? high + newVal : newVal;
    }

    /**
     *
     * If val greater than high, wraps val to # above high to low (inclusive)
     * <p>
     * If val lower than low, wraps val to # below low to high (inclusive)
     * </p>
     *
     * @param val
     * @param low
     * @param high
     * @return
     */
    public static float wrapFloat(float val, float low, float high)
    {
        float newVal = val % high;
        return (newVal < 0f) ? high + newVal : newVal;
    }
    
    /**
     *
     * If val greater than high, sets val to high (inclusive)
     * <p>
     * If val lower than low, sets val to low (inclusive)
     * </p>
     *
     * @param val
     * @param low
     * @param high
     * @return
     */
    public static int clampInt(int val, int low, int high)
    {
        if(val > high)return high;
        if(val < low)return low;
        return val;
    }
    /**
     * Useful utility to check if an item needs to be rendered.
     * @param rect rectangle of the item
     * @param image image of the item
     * @param scale scale of the item
     * @param renderOffsetX renderer offset of the item in the X
     * @param renderOffsetY renderer offset of the item in the Y
     * @return 
     */
    public static boolean isVisible(CenteredRectangle rect, Image image, float scale, float renderOffsetX, float renderOffsetY)
    {
        return //within screen X
                rect.getCornerX() - renderOffsetX + image.getWidth() * scale > 0 && rect.getCornerX() - renderOffsetX < MainApp.SCREEN_WIDTH
                && //within screen Y
                rect.getCornerY() - renderOffsetY + image.getHeight() * scale > 0 && rect.getCornerY() - renderOffsetY < MainApp.SCREEN_WIDTH;
    }
}
