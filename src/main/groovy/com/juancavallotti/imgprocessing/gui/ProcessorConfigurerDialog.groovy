package com.juancavallotti.imgprocessing.gui

import com.juancavallotti.imgprocessing.processors.ImageProcessor
import groovy.swing.SwingBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import javax.swing.JFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by juancavallotti on 3/6/17.
 */
class ProcessorConfigurerDialog {

    private static final Logger logger = LogManager.getLogger(ProcessorConfigurerDialog)

    public static boolean displayProcessorConfiguration(ImageProcessor proc, JFrame parent) {

        //detect all configurable properties from image processor
        List props = detectConfigurationProperties(proc)

        if (props.isEmpty()) {
            logger.debug("Could not find any configurable properties in processor ${proc.class.name}")
            return true
        }

        SwingBuilder builder = new SwingBuilder()



    }

    private static List detectConfigurationProperties(ImageProcessor processorClass) {

        //get all fields
        logger.debug("Available properties: $processorClass.properties")

        return []
    }


}
