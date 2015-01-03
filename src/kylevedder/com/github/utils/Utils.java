/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.utils;

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
     * @param val
     * @param low
     * @param high
     * @return 
     */
    public static int wrap(int val, int low, int high)
    {
        int newVal = val % high;
        return (newVal < 0)? high + newVal : newVal;
    }
}
