package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 */
class BinaryImageProcessor implements ImageProcessor, ImageProcessingComponent {


    private static final Logger logger = LogManager.getLogger(BinaryImageProcessor)

    int threshold
    ColorComponent compareWithComponent


    BinaryImageProcessor() {
        logger.info("Initialize ${getClass().name}, with threshold: $threshold")
    }

    @Override
    Image process(BufferedImage source) {

        logger.info("Enter binary processing loop with threshold $threshold")

        for(int i = 0; i < source.getWidth() ; i++) {
            for (int j = 0 ; j < source.getHeight() ; j++) {
                if (mapToGray(source.getRGB(i, j)) < threshold) {
                    source.setRGB(i, j, ImageProcessingComponent.CommonColors.BLACK.colorValue)
                } else {
                    source.setRGB(i, j, ImageProcessingComponent.CommonColors.WHITE.colorValue)
                }
            }
        }

        return source
    }

    private int mapToGray(int color) {

        int component = (color & compareWithComponent.mask) >> compareWithComponent.position
        return component
    }

}
