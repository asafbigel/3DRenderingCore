package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {


    @Test
    void getNy() {
    }

    @Test
    void getNx() {
    }

    @Test
    /**
     * this test is for writeToImage class, creating an image of background color of
     * yellow, and a grid colored red.
     */
    void writeToImage() {
        ImageWriter iw = new ImageWriter("first try", 800, 500);
        for (int j = 0; j < 800; j++) {
            for (int i = 0; i < 500; i++) {
                if (i % 50 == 0 || j % 50 == 0)
                    iw.writePixel(j, i, new Color(255, 0, 0));
                else
                    iw.writePixel(j, i, new Color(255, 255, 0));
            }
        }
        iw.writeToImage();
    }

    @Test
    void writePixel() {
    }
}