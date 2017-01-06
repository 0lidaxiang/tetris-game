package model;

import java.awt.Color;

/**
 *
 */
public abstract class BoxModel {
	private int DIMENSION = 25;// 一个方块大小，除了阴影
	private int SHADEWIDTH = 4;// 阴影面积
	private int[] x;
	private int[] y;
	private int[] nextX;
	private int[] nextY;
	private int angle;
	private int currentAngle;// 保存当前的角度
	private Color baseColor;// 在box里面控制旋转的角度
	private int status;
	private boolean isHitOthers;

	public BoxModel() {
	}

	public abstract void update90NextXY(int x1, int y1);

	public abstract void update180NextXY(int x1, int y1);

	public abstract void update270NextXY(int x1, int y1);

	public abstract void update360NextXY(int x1, int y1);

	// 一次设置两个坐标
	public void setXY(int[] x, int[] y) {
		this.setX(x);
		this.setY(y);
	}

	public void updateNextXY() {
		int x1 = getX()[1];
		int y1 = getY()[1];

		switch (getAngle()) {
		case 0:
			update0NextXY();
			break;
		case 90:
			update90NextXY(x1, y1);
			break;
		case 180:
			update180NextXY(x1, y1);
			break;
		case 270:
			update270NextXY(x1, y1);
			break;
		case 360:
			update360NextXY(x1, y1);
			break;
		}
	}

	public void update0NextXY() {
		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
	}

	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public Color getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	public int getDIMENSION() {
		return DIMENSION;
	}

	public int getSHADEWIDTH() {
		return SHADEWIDTH;
	}

	public void setStatus(int status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	public boolean isHitOthers() {
		return isHitOthers;
	}

	public void setHitOthers(boolean isHitOthers) {
		this.isHitOthers = isHitOthers;
	}

	public int[] getNextX() {
		return nextX;
	}

	public void setNextX(int[] nextX) {
		this.nextX = nextX;
	}

	public int[] getNextY() {
		return nextY;
	}

	public void setNextY(int[] nextY) {
		this.nextY = nextY;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(int currentAngle) {
		this.currentAngle = currentAngle;
	}

}