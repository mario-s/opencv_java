package org.opencv.face.image;

import java.io.File;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author spindizzy
 */
public class ImageFaceDetectorTest {
    
    private ImageFaceDetector classUnderTest;
    
    private File targetFile;
    
    @Before
    public void setUp() {
        classUnderTest = new ImageFaceDetector();
        
        targetFile = new File(getClass().getResource(".").getFile(), "out.png");
        if(targetFile.exists()){
            targetFile.delete();
        }
    }
    
    private File createFile(String name){
        return new File(getClass().getResource(name).getFile());
    }

    @Test
    public void testContainsFace() {
        assertFalse(classUnderTest.containsFace(null));
        assertFalse(classUnderTest.containsFace(new File(getClass().getName())));
        
        assertFalse("expected no human face for horses", classUnderTest.containsFace(createFile("horses.jpg")));
        assertFalse("expected no human face for koala", classUnderTest.containsFace(createFile("koala.jpg")));
        assertFalse("expected no human face for back", classUnderTest.containsFace(createFile("back.jpg")));
        
        assertTrue("expected human face", classUnderTest.containsFace(createFile("face.jpg")));
    }
    
    @Test
    public void testMarkKoalaFace() {
        File sourceFile = createFile("koala.jpg");
        classUnderTest.saveMarkedFaces(sourceFile, targetFile);
        assertFalse(targetFile.exists());
    }
    
    @Test
    public void testMarkHumanFace() {
        File sourceFile = createFile("face.jpg");
        classUnderTest.saveMarkedFaces(sourceFile, targetFile);
        assertTrue(targetFile.exists());
    }
}