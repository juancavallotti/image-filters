package com.juancavallotti.imgprocessing.processors.detection

import com.juancavallotti.imgprocessing.processors.ImageProcessor
import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.Point
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 9/25/16.
 */
class HorizontalLinesDetector implements ImageProcessor, ImageProcessingComponent {

    private static final Logger logger = LogManager.getLogger(HorizontalLinesDetector)

    List<List<Point>> linePoints = [];

    @Override
    Image process(BufferedImage source) {

        //we assume this has gone through convolution with edge detection filter and possibly threasholding.
        detectHorizontalLines(source)

        return source
    }

    private void detectHorizontalLines(BufferedImage source) {

        //go through the entire image
        logger.info('Attempting to detect horizontal lines...')

        for (int i = 0; i < source.width; i++) {
            for (int j = 0; j < source.height; j++) {

                if (isCandidateSpot(i, j, source)) {
                    logger.info("Found white spot at i: $i, j: $j, checking for straight line...")
                    Point endingPoint = walkLine(i, j, source);

                    if (endingPoint != null) {
                        Point start = new Point(i, j)
                        //we found our line
                        logger.info("Found line at $start and $endingPoint")
                        linePoints.add [start, endingPoint]
                    }
                }


            }
        }
    }

    boolean isCandidateSpot(int i, int j, BufferedImage source) {

        //we need to look for a white spot.
        int color = source.getRGB(i, j)

        if (!isColor(color, ImageProcessingComponent.CommonColors.WHITE)) {
            return false
        }

        //we may want to check for the surroundings

        return true;
    }

    Point walkLine(int i, int j, BufferedImage source) {
        return null;
    }

}
