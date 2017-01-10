package test;

import junit.framework.TestCase;
import model.EBox;
import model.IBox;
import model.LeftLBox;
import model.RightZBox;

public class ModelTest extends TestCase {
	private EBox eBox = new EBox();
	private IBox iBox = new IBox();
	private LeftLBox leftLBox = new LeftLBox();
	private RightZBox rightZBox = new RightZBox();

	public void update90NextXYTest() {
		eBox.update90NextXY(1, 1);
		assertEquals(eBox.getNextX().length, 4);
	}

	public void update180NextXYTest() {
		leftLBox.update360NextXY(3, 4);
		assertEquals(leftLBox.getX()[1], 3);
		assertEquals(leftLBox.getY()[1], 4);
	}

	public void update270NextXYTest() {
		iBox.update270NextXY(2, 2);
		assertEquals(iBox.getX()[1], 2);
		assertEquals(iBox.getY()[1], 2);
	}

	public void update360NextXYTest() {
		rightZBox.update360NextXY(6, 7);
		assertEquals(rightZBox.getX()[1], 6);
		assertEquals(rightZBox.getY()[1], 7);
	}

}
