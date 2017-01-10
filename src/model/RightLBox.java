package model;

import java.awt.Color;

public class RightLBox extends BoxModel {
	public RightLBox(){
		int[] x={7, 7, 7, 6};
		int[] y={-1, 0, 1, 1};

		setX(x);
		setY(y);
		setStatus(1);
		setAngle(0);

		setBaseColor(Color.GREEN);
	}

	@Override
	public void update90NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];

		x[1] = x1;
		y[1] = y1;
		x[0] = x[1]+1;
		y[0] = y[1];
		x[2] = x[1]-1;
		y[2] = y[1];
		x[3] = x[1]-1;
		y[3] = y[1]-1;
		setX(x);
		setY(y);

		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
		setAngle(0);


	}

	@Override
	public void update180NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];

		x[1] = x1;
		y[1] = y1 - 1;
		x[0] = x[1];
		y[0] = y[1]+1;
		x[2] = x[1];
		y[2] = y[1]-1;
		x[3] = x[1]+1;
		y[3] = y[1]-1;
		setX(x);
		setY(y);

		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
		setAngle(0);

	}

	@Override
	public void update270NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];

		x[1] = x1;
		y[1] = y1-1;
		x[0] = x[1]-1;
		y[0] = y[1];
		x[2] = x[1]+1;
		y[2] = y[1];
		x[3] = x[1]+1;
		y[3] = y[1]+1;
		setX(x);
		setY(y);

		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
		setAngle(0);

	}

	@Override
	public void update360NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];

		x[1] = x1;
		y[1] = y1;
		x[0] = x[1];
		y[0] = y[1]-1;
		x[2] = x[1];
		y[2] = y[1]+1;
		x[3] = x[1]-1;
		y[3] = y[1]+1;
		setX(x);
		setY(y);

		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
		setAngle(0);

	}

}
