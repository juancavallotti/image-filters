package com.juancavallotti.imgprocessing.processors

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import javax.swing.ImageIcon
import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 */
class ScaleImageProcessor {

    private static Logger logger = LogManager.getLogger(ScaleImageProcessor)

    int maxWidth
    int maxHeight

    Image scale(BufferedImage orig) {

        logger.info('Scale Image processor')

        double origw = orig.width
        double origh = orig.height
        double relation = 1.0

        logger.info("Current dimensions, w: $origw, h: $origh")


        if (origw > origh) {
            relation = maxWidth / origw
        } else {
            relation = maxHeight / origh
        }

        logger.info("Scale proportion: $relation")

        //do not scale
        if (relation >= 1.0) {
            logger.info('Image does not need to be scaled...')
            return orig
        }

        return orig.getScaledInstance( (int) origw * relation, (int) origh * relation, BufferedImage.SCALE_SMOOTH)
    }

}
