package org.opencv.img;

import java.io.File;
import java.util.Collection;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import static org.opencv.img.ImageIO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class loads images with different EL creates a HDR image and saves it as a LDR image.
 */
public class MertensProcessor {
    
    private static final Logger LOG = LoggerFactory.getLogger(MertensProcessor.class);

    public boolean create(Collection<String> pics, File out) {
        boolean val = false;

        if (!pics.isEmpty()) {
            
            List<Mat> images = loadImages(pics);
            
            LOG.debug("number of images to merge: {}", images.size());
            
            Mat merge = new Merge().merge(images);
            Mat result = multipy(merge);
            

            write(result, out);
            
            LOG.debug("merged images into: {}", out);

            val = true;
        }

        return val;
    }

    private Mat multipy(Mat fusion) {
        Mat result = new Mat();
        Scalar scalar = new Scalar(255, 255, 255);
        Core.multiply(fusion, scalar, result);
        return result;
    }

}
