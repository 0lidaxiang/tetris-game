package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public abstract class BoxModel {
	private final int DIMENSION = 25;
	private final int SHADEWIDTH = 4;
	private int x;
	private int y;
	private Color baseColor;
	private Color briShaderColor;
	private Color darkShaderColor;

	public BoxModel() {

	}
	
	public abstract void drawTetrisBox();

	public abstract void drawBox(Graphics g);

	public abstract void setXY(int i, int j);// 一次设置两个坐标

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	public Color getBriShaderColor() {
		return briShaderColor;
	}

	public void setBriShaderColor(Color briShaderColor) {
		this.briShaderColor = briShaderColor;
	}

	public Color getDarkShaderColor() {
		return darkShaderColor;
	}

	public void setDarkShaderColor(Color darkShaderColor) {
		this.darkShaderColor = darkShaderColor;
	}

	public int getDIMENSION() {
		return DIMENSION;
	}

	public int getSHADEWIDTH() {
		return SHADEWIDTH;
	}

}