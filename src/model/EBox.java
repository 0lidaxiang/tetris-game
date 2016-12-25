package model;

import java.awt.*;

/**
 *
 */
public class EBox extends BoxModel {
	public EBox(int x, int y, Color baseColor, Color briShaderColor, Color darkShaderColor) {
		setX(x);
		setY(y);
		setBaseColor(baseColor);
		setBriShaderColor(briShaderColor);
		setDarkShaderColor(darkShaderColor);
	}

	@Override
	public void drawBox(Graphics g) {

		Color norColor = getBaseColor();
		Color briColor = getBriShaderColor();
		Color darkColor = getDarkShaderColor();
		int x = getX();
		int y = getY();

		g.setColor(norColor);
		g.fillRect(x, y, getDIMENSION(), getDIMENSION());

		g.setColor(darkColor);
		g.fillRect(x + getDIMENSION() - getSHADEWIDTH(), y, getSHADEWIDTH(), getDIMENSION());// right
																								// shade
		g.fillRect(x, y + getDIMENSION() - getSHADEWIDTH(), getDIMENSION(), getSHADEWIDTH());// bottom
																								// shade

		g.setColor(briColor);
		for (int i = 0; i < getSHADEWIDTH(); i++) {
			g.drawLine(x, y + i, x + getDIMENSION() - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + getDIMENSION() - i - 1);
		}
	}

	// 一次设置两个坐标
	@Override
	public void setXY(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	@Override
	public void drawTetrisBox() {
		// TODO Auto-generated method stub
		
	}

}