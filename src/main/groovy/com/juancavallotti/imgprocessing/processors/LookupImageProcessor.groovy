package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.LookupOp
import java.awt.image.LookupTable
import java.awt.image.ShortLookupTable

/**
 * Created by juancavallotti on 2/21/15.
 */
class LookupImageProcessor implements ImageProcessor, ImageProcessingComponent {

    private static final Logger logger = LogManager.getLogger(LookupImageProcessor)


    def lookups

    @Override
    Image process(BufferedImage source) {

        logger.info('Invoke lookup image processor...')

        if (logger.isDebugEnabled()) {
            logger.debug("Lookups are: $lookups")
        }

        LookupTable table = new ShortLookupTable(0, lookups)
        LookupOp op = new LookupOp(table, null)

        return op.filter(source, null)
    }
}
