package com.block.vtCoin.util;

import android.graphics.Color;


/**
 * @Description
 * @Author lijie
 * @Date 2017/7/13.
 */
public class ColorUtils {
    /**
     * Get the value of color with specified alpha.
     *
     * @param color
     * @param alpha between 0 to 255.
     * @return Return the color with specified alpha.
     */
    public static int getColorAtAlpha(int color, int alpha) {
        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("The alpha should be 0 - 255.");
        }
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
