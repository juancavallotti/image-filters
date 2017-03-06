package com.juancavallotti.imgprocessing.processors

import com.juancavallotti.imgprocessing.util.ImageProcessingComponent

import java.awt.Image
import java.awt.image.BufferedImage

/**
 * Created by juancavallotti on 2/21/15.
 */
class CompositeImageProcessor implements ImageProcessor, ImageProcessingComponent {

    ArrayList<ImageProcessor> processors = []

    @Override
    Image process(BufferedImage source) {

        if (processors == null) {
            return source;
        }

        Image result = source

        for(ImageProcessor proc : processors) {

            result = proc.process(source)
            source = toBufferedImage(result)

        }

        return result
    }


}
