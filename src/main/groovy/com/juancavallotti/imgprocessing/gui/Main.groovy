package com.juancavallotti.imgprocessing.gui

import com.juancavallotti.imgprocessing.processors.GrayScaleImageProcessor
import com.juancavallotti.imgprocessing.processors.ImageProcessor
import com.juancavallotti.imgprocessing.processors.effects.CheGuevaraProcessor
import com.juancavallotti.imgprocessing.processors.effects.DetectHorizonProcessor
import com.juancavallotti.imgprocessing.processors.effects.GrayscaleProcessor
import com.juancavallotti.imgprocessing.processors.effects.LookupExperiment
import groovy.swing.SwingBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


class Main {

    private static Logger logger = LogManager.getLogger(Main)

    public static void main(String[] args) {

        logger.info("Starting instance of the User interface...")

        UserInterface ui = new UserInterface(buildProcessors())

        ui.show()
    }


    private static List<ImageProcessor> buildProcessors() {
        def ret = []

        //load el che guevara
        ret.add(new CheGuevaraProcessor())

        //load one for making it grayscale.
        ret.add(new GrayscaleProcessor())

        //load the lookup experiment
        ret.add new LookupExperiment()

        //load the horizon processor
        ret.add new DetectHorizonProcessor()

        logger.info("Instantiated processors are: $ret")
        return ret
    }
}