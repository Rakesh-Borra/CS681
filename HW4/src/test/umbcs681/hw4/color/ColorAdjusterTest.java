package umbcs681.hw4.color;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import umbcs681.hw4.color.Color;
import umbcs681.hw4.color.Image;
import umbcs681.hw4.color.Images;

class ColorAdjusterTest {
	
	private String colorToString(Color color) {

	    return color.getRedScale() + "," + color.getGreenScale() + "," + color.getBlueScale();
	}
	
	private int getAverage(Color c) {
	    return (c.getRedScale() + c.getGreenScale() + c.getBlueScale()) / 3;
	}


	@Test
	void GrayScaleAdjusterTest() {
		Image img = new Image(2, 2);

		img.setColor(0, 0, new Color(100, 150, 200)); 
        img.setColor(0, 1, new Color(50, 75, 100));   
        img.setColor(1, 0, new Color(25, 50, 75));   
        img.setColor(1, 1, new Color(200, 220, 240));

        Image grayScaleImg = Images.transform(img,
				c->new Color(getAverage(c),getAverage(c),getAverage(c)));
        assertEquals("150,150,150", colorToString(grayScaleImg.getColor(0, 0)));
        assertEquals("75,75,75", colorToString(grayScaleImg.getColor(0, 1)));
        assertEquals("50,50,50", colorToString(grayScaleImg.getColor(1, 0)));
        assertEquals("220,220,220", colorToString(grayScaleImg.getColor(1, 1)));
	}


	@Test
	void BinarizationAdjusterTest() {
		Image img = new Image(2, 2);

		img.setColor(0, 0, new Color(100, 150, 200));
        img.setColor(0, 1, new Color(50, 75, 100));  
        img.setColor(1, 0, new Color(25, 50, 75));  
        img.setColor(1, 1, new Color(200, 220, 240));

        Image binarizedImg = Images.transform(img, 
        		c->getAverage(c)<=125?new Color(0, 0, 0):new Color(255, 255, 255));
        assertEquals("255,255,255", colorToString(binarizedImg.getColor(0, 0)));
        assertEquals("0,0,0", colorToString(binarizedImg.getColor(0, 1)));
        assertEquals("0,0,0",colorToString( binarizedImg.getColor(1, 0)));
        assertEquals("255,255,255", colorToString(binarizedImg.getColor(1, 1)));
		
	}


}
