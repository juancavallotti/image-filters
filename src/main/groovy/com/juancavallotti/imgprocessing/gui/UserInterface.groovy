package com.juancavallotti.imgprocessing.gui

import com.juancavallotti.imgprocessing.processors.ImageProcessor
import com.juancavallotti.imgprocessing.processors.ScaleImageProcessor
import com.juancavallotti.imgprocessing.processors.DisplaySettings
import com.juancavallotti.imgprocessing.util.ImageProcessingComponent
import com.juancavallotti.imgprocessing.util.ImageUtils
import groovy.swing.SwingBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JFrame as JF
import javax.swing.JLabel
import java.awt.BorderLayout as BL
import java.awt.Color
import java.awt.FlowLayout as FL
import javax.swing.JFileChooser as JFC
import java.awt.Image
import java.awt.event.ActionListener
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/20/15.
 */
class UserInterface {

    private JFrame frame
    private File currentWorkingDirectory

    private final Logger logger = LogManager.getLogger(UserInterface)


    JLabel imageContainer
    ImageIcon picture
    Image original

    private ScaleImageProcessor scaleProcessor = new ScaleImageProcessor(maxWidth: 1024, maxHeight: 768)

    public UserInterface(List<ImageProcessor> processors) {

        new SwingBuilder().edt {
            this.frame = frame(
                    title: 'Image Filters',
                    extendedState: JF.MAXIMIZED_BOTH,
                    visible: false,
                    defaultCloseOperation: JF.EXIT_ON_CLOSE
            ) {

                menuBar() {
                    menu(text: 'File') {
                        menuItem(text: 'Exit', actionPerformed: { exitApp() } )
                    }
                }

                borderLayout()

                scrollPane(constraints: BL.CENTER) {
                    def defaultImage = imageIcon(resource: '/placeholder.png')
                    this.picture = defaultImage
                    this.original = defaultImage.image
                    this.imageContainer = label(icon: defaultImage)
                }

                //top panel
                panel(constraints: BL.NORTH) {
                    flowLayout(alignment: FL.LEFT)
                    button(text: 'Select Image...', actionPerformed: { pickImage() })

                    button(text: 'Reopen last file', actionPerformed: {reopenLastFile()})

                }

                //bottom panel:
                panel(constraints: BL.SOUTH, background: Color.WHITE) {
                    flowLayout(alignment: FL.LEFT)

                    for(ImageProcessor processor : processors) {

                        ActionListener listener = new ExecuteClosureListener(param: processor, action: { proc -> executeProcessor(proc) })
                        button(text: readProcessorName(processor)).addActionListener(listener)
                    }
                }

            }

        }
    }

    public void show() {
        logger.info("Presenting main window...")
        frame.setVisible(true)
    }


    public void pickImage() {

        new SwingBuilder().edt {
            JFC chooser = fileChooser(dialogTitle: 'Select Image...' , dialogType: JFC.OPEN_DIALOG)

            if (currentWorkingDirectory) {
                chooser.currentDirectory = currentWorkingDirectory
            }

            int state = chooser.showOpenDialog(frame)


            if (state == JFC.APPROVE_OPTION) {
                this.currentWorkingDirectory = chooser.currentDirectory
                logger.info("Set current directory to $currentWorkingDirectory")
                displayFile(chooser.selectedFile)
            }

        }

    }

    public void exitApp() {
        logger.info("Performing exit tasks...")
        this.frame.dispose()
        logger.info("Disposed main window...")
    }


    private String readProcessorName(ImageProcessor processor) {

        logger.info("Reading name for: ${processor.getClass().name}")

        DisplaySettings sets = processor.class.getAnnotation(DisplaySettings)

        if (!sets) {
            return "Unknown effect..."
        }

        return sets.displayText()
    }

    private void executeProcessor(ImageProcessor processor) {

        if (!ProcessorConfigurerDialog.configureProcessor(processor, this.frame)) {
            //user hit cancel or dialog has invalid configuration.
            return;
        }


        logger.info("Will invoke processor: ${processor.getClass().name}")

        ImageProcessingComponent comp = processor;

        BufferedImage img =  comp.toBufferedImage(this.original)

        Image result = processor.process(img)

        updatePicture(result)
    }


    private void updatePicture(Image image) {

        SwingBuilder sb = new SwingBuilder()

        this.picture = sb.imageIcon(image: image)
        this.imageContainer.icon = this.picture

        logger.info('Updated current image in display')

    }

    private void displayFile(File f) {
        updatePicture(scaleProcessor.scale(ImageUtils.loadFromFile(f)))
        logger.info("Created image from file: $f")
        this.original = this.picture.image
        ProgramSessionState.instance.lastOpened = f
    }


    private void reopenLastFile() {
        if (ProgramSessionState.instance.lastOpened) {
            displayFile(ProgramSessionState.instance.lastOpened)
        }
    }

}
