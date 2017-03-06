package com.juancavallotti.imgprocessing.util

import javax.imageio.ImageIO
import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 */
class ImageUtils {

    public static BufferedImage loadFromFile(File imageFile) {
        return ImageIO.read(imageFile)
    }

}
