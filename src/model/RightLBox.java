package model;

import java.awt.Color;

public class RightLBox extends BoxModel {
	public RightLBox(){
		int[] x={7, 7, 7, 6};
		int[] y={-1, 0, 1, 1};

		setX(x);
		setY(y);
		setBaseColor(Color.GREEN);
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
