package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.BoxModel;

/**
 *
 */
public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int gameStatus = 1;
	private JFrame j;
	private BoxModel box;
	private int rowsLength;
	private int colsLength;
	private int[][][] positioinFlag;// the front two parameters are postion.
	// the third parameter is 参数列表。参数列表0表示是否该点被标记画出实心(0不画，1画)，
	// 参数列表1表示该点的颜色（蓝色0，红色1，黄色2，绿色3,pink4）。

	public TablePanel(int width, int height, int rowsLength, int colsLength, int flagNum) {
		initialPositionFlag(new int[13][27][2]);
		setRowsLength(rowsLength);
		setColsLength(colsLength);

		j = new JFrame("tablePanel");
		j.setSize(width, height);
		j.add(this);
		j.setVisible(false);
	}

	// 横纵格之间都间隔25像素
	@Override
	public void paint(Graphics g) {
		int length = 0;

		box = getBox();
		// 画固定在table中的底层方块
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 23; j++) {
				if (this.getPositioinFlag()[i][j][0] == 1) {
					drawPostionInTable(g, this.getPositioinFlag()[i][j][1], i * 25, j * 25);
				}
			}
		}

		if (gameStatus == 0) {
			// 测试，先画一个初始化方块
			this.drawTetrisBox(g);
		} else if (gameStatus == 1) {
			// show some words "Please press the key '0' to start this game."

		} else if (gameStatus == 2) {
			// show some words "GAME PAUSEING"
		}

		g.setColor(Color.gray);
		if (rowsLength > colsLength) {
			length = rowsLength;
		} else {
			length = colsLength;
		}
		// 绘制rows向线
		for (int i = 0; i < length / 25; i++) {
			g.drawLine(0, i * 25, length, i * 25);
		}
		// 绘制cols向线
		for (int i = 0; i < length / 25; i++) {
			g.drawLine(0 + i * 25, 0, i * 25, length);
		}
	}

	public void drawPostionInTable(Graphics g, int colorCode, int x, int y) {
		Color baseColor;
		Color darkShaderColor;
		Color briShaderColor;
		int dimension = box.getDIMENSION();
		int shadeWidth = box.getSHADEWIDTH();

		switch (colorCode) {
		case 0:
			baseColor = Color.BLUE;
			break;
		case 1:
			baseColor = Color.RED;
			break;
		case 2:
			baseColor = Color.YELLOW;
			break;
		case 3:
			baseColor = Color.GREEN;
			break;
		case 4:
			baseColor = Color.PINK;
			break;
		default:
			baseColor = Color.BLUE;
			break;
		}
		darkShaderColor = baseColor.darker();
		briShaderColor = baseColor.brighter();

		g.setColor(baseColor);
		g.fillRect(x, y, dimension, dimension);

		g.setColor(darkShaderColor);
		g.fillRect(x + dimension - shadeWidth, y, shadeWidth, dimension);// right
																			// shade
		g.fillRect(x, y + dimension - shadeWidth, dimension, shadeWidth);// bottom
																			// shade

		g.setColor(briShaderColor);
		for (int i = 0; i < shadeWidth; i++) {
			g.drawLine(x, y + i, x + dimension - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + dimension - i - 1);
		}
	}

	public void drawTetrisBox(Graphics g) {
		int[] x = box.getX();
		int[] y = box.getY();
		Color tempColor = box.getBaseColor();

		System.out.println("4方块位置的坐标 : " + " || x[0] " + x[0] + " , y[0] " + y[0] + " || x[1] " + x[1] + ", y[1]" + y[1]
				+ " || x[2] " + x[2] + ", y[2]" + y[2] + " || x[3] " + x[3] + ", y[3]" + y[3]);

		drawBox(g, x[0], y[0], tempColor);
		drawBox(g, x[1], y[1], tempColor);
		drawBox(g, x[2], y[2], tempColor);
		drawBox(g, x[3], y[3], tempColor);
	}

	public void drawBox(Graphics g, int x, int y, Color tempColor) {
		int dimension = box.getDIMENSION();
		int shadeWidth = box.getSHADEWIDTH();

		g.setColor(tempColor);
		g.fillRect(x * dimension, y * dimension, dimension, dimension);

		g.setColor(tempColor.darker());
		g.fillRect(x * dimension + dimension - shadeWidth, y * dimension, shadeWidth, dimension);// right
																									// shade
		g.fillRect(x * dimension, y * dimension + dimension - shadeWidth, dimension, shadeWidth);// bottom
																									// shade

		g.setColor(tempColor.brighter());
		for (int i = 0; i < shadeWidth; i++) {
			g.drawLine(x * dimension, y * dimension + i, x * dimension + dimension - i - 1, y * dimension + i);
			g.drawLine(x * dimension + i, y * dimension, x * dimension + i, y * dimension + dimension - i - 1);
		}
	}

	public void initialPositionFlag(int[][][] newArray) {
		this.setPositioinFlag(newArray);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 27; j++) {
				// 把最后一行以及看不见的3行都标记为1
				if (j > 22) {
					this.getPositioinFlag()[i][j][0] = 1;
					this.getPositioinFlag()[i][j][1] = 0;
				} else {
					this.getPositioinFlag()[i][j][0] = 0;
					this.getPositioinFlag()[i][j][1] = 0;
				}

			}
		}
	}

	public int getRowsLength() {
		return rowsLength;
	}

	public void setRowsLength(int rowsLength) {
		this.rowsLength = rowsLength;
	}

	public int getColsLength() {
		return colsLength;
	}

	public void setColsLength(int colsLength) {
		this.colsLength = colsLength;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int input) {
		gameStatus = input;
	}

	public void setBox(BoxModel newBox) {
		box = newBox;
	}

	public BoxModel getBox() {
		return box;
	}

	public TablePanel getTp() {
		// TODO Auto-generated method stub
		return null;
	}

	public TablePanel setTp() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[][][] getPositioinFlag() {
		return positioinFlag;
	}

	public void setPositioinFlag(int[][][] positioinFlag) {
		this.positioinFlag = positioinFlag;
	}

	public void updatePositioinFlag(int positionX, int positionY, int flagZ, int flag) {
		this.positioinFlag[positionX][positionY][flagZ] = flag;
	}
}
