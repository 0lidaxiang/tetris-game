package model;

import java.awt.Color;

/**
 *
 */
public class EBox extends BoxModel {
	public EBox() {
		int[] x = { 6, 7, 6, 7 };
		int[] y = { -1, -1, 0, 0 };
		setX(x);
		setY(y);
		setBaseColor(Color.BLUE);
	}

	@Override
	public void update90NextXY(int x1, int y1) {
		update0NextXY();
	}

	@Override
	public void update180NextXY(int x1, int y1) {
			update0NextXY();
	}

	@Override
	public void update270NextXY(int x1, int y1) {
			update0NextXY();
	}

	@Override
	public void update360NextXY(int x1, int y1) {
			update0NextXY();
	}

}