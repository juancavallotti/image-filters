package com.juancavallotti.imgprocessing.gui

import com.juancavallotti.imgprocessing.processors.ImageProcessor
import com.juancavallotti.imgprocessing.processors.ProcessorParameter
import groovy.swing.SwingBuilder
import org.apache.commons.lang3.ClassUtils
import org.apache.commons.lang3.reflect.FieldUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import javax.swing.JDialog
import javax.swing.JFrame
import java.awt.event.ActionEvent
import java.awt.BorderLayout as BL
import java.awt.event.ActionListener
import java.lang.reflect.Field

/**
 * Created by juancavallotti on 3/6/17.
 */
class ProcessorConfigurerDialog {

    private static final Logger logger = LogManager.getLogger(ProcessorConfigurerDialog)

    public static boolean configureProcessor(ImageProcessor proc, JFrame parent) {

        //detect all configurable properties from image processor
        List props = detectConfigurationProperties(proc)

        if (props.isEmpty()) {
            logger.debug("Could not find any configurable properties in processor ${proc.class.name}")
            return true
        }

        def guiSettings = [:]

        SwingBuilder builder = new SwingBuilder()

        JDialog dlg = null;

        dlg = builder.dialog(title: "Processor Configuration", preferredSize: [400, 30*props.size() + 30], owner: parent) {

            borderLayout()

            panel(constraints: BL.CENTER) {
                gridLayout(cols: 2, rows: props.size())

                for(def prop : props) {
                    label(text: prop.name)
                    prop.textField = textField(text: prop.value)
                }

            }
            panel(constraints: BL.SOUTH) {
                flowLayout()

                button(text: "OK", actionPerformed: {
                    guiSettings.action = 'OK'
                    dlg.setVisible(false)
                    dlg.dispose()

                    //read all the values
                    for(def prop: props) {

                        if (logger.isDebugEnabled())
                            logger.debug("New value for property \t $prop.fieldName $prop.value -> ${prop.textField.text}")

                        proc."$prop.fieldName" = prop.type.valueOf(prop.textField.text)
                    }

                })
                button(text: "Cancel", actionPerformed: {
                    guiSettings.action = 'CANCEL'
                    dlg.setVisible(false)
                    dlg.dispose()
                })
            }

        }

        dlg.pack()
        dlg.setLocationRelativeTo(null)
        dlg.setModal(true)
        dlg.setVisible(true)

        if (guiSettings.action == 'OK') {
            return true
        } else {
            return false;
        }

    }

    private static List detectConfigurationProperties(ImageProcessor processor) {

        //get all fields
        List ret = []

        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(processor.class, ProcessorParameter)

        fields.each {

            logger.debug("Looking up settings for property: ${it.name}")

            ProcessorParameter param = it.getAnnotation(ProcessorParameter)

            if (!param) {
                return
            }

            logger.debug("Found configuration for field ${it.name}: $param")

            Class type = it.type.isPrimitive() ? ClassUtils.primitiveToWrapper(it.type) : it.type

            ret.add([fieldName: it.name ,name: param.name(), min: param.minValue(), max: param.maxValue(), value: processor."${it.name}", type: type])
        }

        return ret
    }


}
