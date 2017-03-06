package com.juancavallotti.imgprocessing.processors.effects

import com.juancavallotti.imgprocessing.processors.DisplaySettings
import com.juancavallotti.imgprocessing.processors.LookupImageProcessor
import com.juancavallotti.imgprocessing.processors.ProcessorParameter
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * Created by juancavallotti on 2/22/15.
 */

@DisplaySettings(displayText = "Lookup experiment...")
class LookupExperiment extends LookupImageProcessor {

    private static final Logger logger = LogManager.getLogger(LookupExperiment)

    @ProcessorParameter(name = "Red Threshold", maxValue = "255", minValue = "0")
    int redThreshold = 90

    @ProcessorParameter(name = "Green Threshold", maxValue = "255", minValue = "0")
    int greenThreshold = 0

    @ProcessorParameter(name = "Blue Threshold", maxValue = "255", minValue = "0")
    int blueThreshold = 10


    LookupExperiment() {

        logger.info('Configuring the lookup experiment')

        short[] rlevels = new short[256]


        for(int i = 0 ; i < rlevels.length ; i++) {
            if (i > redThreshold) {
                rlevels[i] = 0xFF
            }
        }

        short[] glevels = new short[256]


        for(int i = 0 ; i < glevels.length ; i++) {
            if (i > greenThreshold) {
                glevels[i] = 0x00
            }
        }

        short[] blevels = new short[256]


        for(int i = 0 ; i < blevels.length ; i++) {
            if (i > blueThreshold) {
                blevels[i] = 0x20
            }
        }

        short[][] levels = [rlevels, glevels, blevels]

        this.lookups = levels

    }

}
