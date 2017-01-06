package model;

import java.awt.Color;

/**
 *
 */
public abstract class BoxModel {
	private   int DIMENSION = 25;// 一个方块大小，除了阴影
	private   int SHADEWIDTH = 4;// 阴影面积
	private int[] x;
	private int[] y;
	private int[] nextX;
	private int[] nextY;
	private int angle;
	private Color baseColor;
	private int status;
	private boolean isHitOthers;

	public BoxModel() {}

	public abstract void updateNextXY();

	// 一次设置两个坐标
	public void setXY(int[] x, int[] y) {
		this.setX(x);
		this.setY(y);
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

	public  int getDIMENSION() {
		return DIMENSION;
	}

	public  int getSHADEWIDTH() {
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

}