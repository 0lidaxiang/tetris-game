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

	public TablePanel(int width, int height, int rowsLength, int colsLength) {
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

		if (gameStatus == 0) {
			box = getBox();
			// 测试，先画一个初始化方块
//			System.out.println("Tablepanel.getGameStatus : " + getGameStatus());

			box.drawBox(g);
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
}
