package com.juancavallotti.imgprocessing.util

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent.CommonColors

import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 *
 * Common behavior to all components that process an image.
 *
 */
trait ImageProcessingComponent {

    static enum CommonColors {
        WHITE(0xFFFFFF),
        BLACK(0x000000)

        final int colorValue
        CommonColors(int color) {
            colorValue = color
        }
    }


    public BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB)

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics()
        bGr.drawImage(img, 0, 0, null)
        bGr.dispose()

        // Return the buffered image
        return bimage
    }

    public boolean isColor(int color, CommonColors target) {
        return (color & target.colorValue) == color
    }
}