package com.juancavallotti.imgprocessing.processors.effects

import com.juancavallotti.imgprocessing.processors.BinaryImageProcessor
import com.juancavallotti.imgprocessing.processors.ColorComponent
import com.juancavallotti.imgprocessing.processors.CompositeImageProcessor
import com.juancavallotti.imgprocessing.processors.ConvolutionImageProcessor
import com.juancavallotti.imgprocessing.processors.DisplaySettings
import com.juancavallotti.imgprocessing.processors.detection.HorizontalLinesDetector
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 9/25/16.
 */
@DisplaySettings(displayText = "Detect Horizon")
class DetectHorizonProcessor extends CompositeImageProcessor {
    private static final Logger logger = LogManager.getLogger(DetectHorizonProcessor)


    DetectHorizonProcessor() {

        float[] kernel = [1.0f, 1.0f , 1.0f,
                          0.0f ,  0.0f , 0.0f,
                          -1.0f ,  -1.0f , -1.0f
        ]

        processors.add new ConvolutionImageProcessor(kernel: kernel, kernelWidth: 3, kernelHeight: 3)

        processors.add new BinaryImageProcessor(threshold: 128, compareWithComponent: ColorComponent.BLUE)

        processors.add(new HorizontalLinesDetector())
    }

    @Override
    Image process(BufferedImage source) {
        BufferedImage result = toBufferedImage(super.process(source))

        return result
    }
}
