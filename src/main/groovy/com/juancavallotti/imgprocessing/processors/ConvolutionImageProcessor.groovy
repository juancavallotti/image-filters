package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.ConvolveOp
import java.awt.image.Kernel


/**
 * Created by juancavallotti on 2/21/15.
 */
class ConvolutionImageProcessor implements ImageProcessor, ImageProcessingComponent {

    float[] kernel
    int kernelWidth
    int kernelHeight


    private static final Logger logger = LogManager.getLogger(ConvolutionImageProcessor)

    @Override
    Image process(BufferedImage source) {

        logger.info('Applying convolution processor')
        logger.info("Convolution settings. Kernel: $kernel, w: $kernelWidth, h: $kernelHeight")

        Kernel ker = new Kernel(kernelWidth, kernelHeight, kernel)
        ConvolveOp op = new ConvolveOp(ker)
        return op.filter(source, null)
    }
}
