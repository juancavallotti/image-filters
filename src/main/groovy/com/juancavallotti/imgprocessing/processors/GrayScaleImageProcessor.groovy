package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.color.ColorSpace
import java.awt.image.BufferedImage
import java.awt.image.ColorConvertOp

/**
 * Created by juancavallotti on 2/21/15.
 */
class GrayScaleImageProcessor implements ImageProcessor, ImageProcessingComponent {

    private static Logger logger = LogManager.getLogger(GrayScaleImageProcessor)

    @Override
    Image process(BufferedImage source) {

        logger.info('Invoke convert color space to Grayscale...')
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null)
        return op.filter(source, null)

    }
}
