package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent

import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 */
interface ImageProcessor {

    /**
     * Generic abstraction of an image processor.
     * @param source
     * @return
     */
    Image process(BufferedImage source)
}