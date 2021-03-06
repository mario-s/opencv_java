package org.javacv.glue;

import org.bytedeco.javacv.Frame;

/**
 * Defines contract to show an image. It can be a a gui that does the actual painting.
 */
public interface ImageShowable {

    void setSize(int width, int height);

    void showImage(Frame image);
}
