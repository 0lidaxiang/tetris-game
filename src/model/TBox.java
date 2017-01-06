package model;

import java.awt.Color;

public class TBox extends BoxModel {

	public TBox() {
		int[] x = { 6, 5, 6, 7 };
		int[] y = { -1, 0, 0, 0 };
		setX(x);
		setY(y);
		setBaseColor(Color.PINK);
	}

	@Override
	public void update90NextXY(int x1, int y1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update180NextXY(int x1, int y1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update270NextXY(int x1, int y1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update360NextXY(int x1, int y1) {
		// TODO Auto-generated method stub

	}

}
