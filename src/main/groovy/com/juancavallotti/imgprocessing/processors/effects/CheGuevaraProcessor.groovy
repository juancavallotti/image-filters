package com.juancavallotti.imgprocessing.processors.effects

import com.juancavallotti.imgprocessing.processors.CompositeImageProcessor
import com.juancavallotti.imgprocessing.processors.ConvolutionImageProcessor
import com.juancavallotti.imgprocessing.processors.DisplaySettings
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * Created by juancavallotti on 2/21/15.
 */

@DisplaySettings(displayText = 'El Che Guevara')
class CheGuevaraProcessor extends CompositeImageProcessor {

    private static final Logger logger = LogManager.getLogger(CheGuevaraProcessor)


    CheGuevaraProcessor() {
        logger.info("Initialising el che guevara processor")
        logger.info('Adding the binary processor...')
        //this.processors.add(new BinaryImageProcessor(threshold: 90, compareWithComponent: ColorComponent.RED))
        this.processors.add(new LookupExperiment())

        //add a convolve processor with a blur kernel

        float ninth = 1.0f / 9.0f
        float[] kernel = new float[9]
        for (int i = 0; i < kernel.length; i++) {
            kernel[i] = ninth
        }

        this.processors.add(new ConvolutionImageProcessor(kernel: kernel, kernelWidth: 3, kernelHeight: 3))
    }

}
