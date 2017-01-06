package controller;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import model.*;
import view.*;

/**
 *
 */
public class TetrisController {
	static MainPanelView mainJFrame;
	private static boolean isStart;
	private static int sleepTime;
	private static boolean isMoveCols;
	private int initialX, initialY;

	public TetrisController() {
		setSleepTime(2000);// 初始化程序睡眠时间，用户看到的就是方块移动速度，值越大，速度越慢
	}

	public static void main(String[] args) {
		TetrisController tetris = new TetrisController();
		tetris.start();
	}

	public void start() {
		setIsStart(true);// for test,表示可以移动方块了，也就是游戏开始
		// setIsStart(false);// 表示初始状态时不可以移动方块了，直到按‘S’才启动
		MainPanelView mainPanelView = new MainPanelView(createTp());
		setMainJFrame(mainPanelView);// 画面板和其他部分

		// 进入玩游戏状态并监听键盘事件
		while (true) {
			if (this.isMoveCols()) {
				TetrisController.setMoveCols(false);
			} else {
				if (getIsStart()) {
					mainJFrame.getTp().setGameStatus(0);// 更改tablePanel中的状态，表示可以显示方块了而不是显示文字状态
					move();
				}
			}

			mainJFrame.repaint();// 不断flush画面

			// 重画一次之后就睡眠，user看到的就是方块移动的速度。因为 程序sleep时方块停止移动
			try {
				Thread.sleep(getSleepTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void move() {
		// 如果可以移动，则每次移动一个格子
		// 移动过程中会检查是否触底或者触及方块
		if (mainJFrame.getTp().getBox().getStatus() == 1) {
			canMove();
		} else {
			int random = (int) (1 + Math.random() * (3 - 0 + 1));
			switch (random) {
			case 0:
				this.updateBoxModel();
				break;
			case 1:
				this.updateBoxModel();
				break;
			case 2:
				this.updateBoxModel();
				break;
			case 3:
				this.updateBoxModel();
				break;
			default:
				this.updateBoxModel();
				break;
			}
		}
	}

	public void canMove() {
		// 先把下一个位置坐标给他，设置好
		// mainJFrame.getTp().getBox().setX(mainJFrame.getTp().getBox().getNextX());
		mainJFrame.getTp().getBox().setY(mainJFrame.getTp().getBox().getNextY());

		int[] tempX = mainJFrame.getTp().getBox().getX();
		int[] tempY = mainJFrame.getTp().getBox().getY();
		// 再更新下一个位置的下一个坐标
		mainJFrame.getTp().getBox().updateNextXY();

		// 判断是否触底以及 判断下方是否有积木
		int nextX;
		int nextY;
		// 如果这个方格被标记，则开始判断是否在当前积木的下一个位置中
		for (int m = 0; m < 4; m++) {
			nextX = mainJFrame.getTp().getBox().getNextX()[m];
			nextY = mainJFrame.getTp().getBox().getNextY()[m];

			if ((mainJFrame.getTp().getPositioinFlag()[nextX][nextY][0] == 1)) {
				mainJFrame.getTp().getBox().setStatus(0);
				for (int n = 0; n < 4; n++) {
					mainJFrame.getTp().updatePositioinFlag(tempX[n], tempY[n], 0, 1);// 对该格子标记要画颜色

					// 计算当前积木的颜色对应colorCode
					Color c = mainJFrame.getTp().getBox().getBaseColor();
					int colorCode = 0;
					if (c.getRGB() == Color.BLUE.getRGB()) {
						colorCode = 0;
					}
					if (c.getRGB() == Color.RED.getRGB()) {
						colorCode = 1;
					}
					if (c.getRGB() == Color.YELLOW.getRGB()) {
						colorCode = 2;
					}
					if (c.getRGB() == Color.GREEN.getRGB()) {
						colorCode = 3;
					}

					mainJFrame.getTp().updatePositioinFlag(tempX[n], tempY[n], 1, colorCode);// 要画的格子的颜色,0代表蓝色
				}
				break;
			}
		}
	}

	public TablePanel createTp() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		TablePanel tp = new TablePanel(200, 400, d.width, d.height, 2);

		tp.setBox(createRandomBoxModel());

		return tp;
	}

	// 新建一个boxmodel并初始化参数
	private BoxModel createRandomBoxModel() {

		int[] x = { 6, 7, 6, 7 };
		int[] y = { -1, -1, 0, 0 };
		BoxModel newBox = new EBox(x, y);
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 更新当前的boxmodel参数
	private void updateBoxModel() {
		int[] x1 = { 6, 7, 6, 7 };
		int[] y1 = { 0, 0, 1, 1 };
		mainJFrame.getTp().getBox().setStatus(1);
		mainJFrame.getTp().getBox().setXY(x1, y1);
		mainJFrame.getTp().getBox().updateNextXY();
	}

	public void setBoxInitialXY(int initialX, int initialY) {
		this.initialX = initialX;
		this.initialY = initialY;
	}

	public static MainPanelView getMainJFrame() {
		return mainJFrame;
	}

	public static void setMainJFrame(MainPanelView mainJFrame) {
		TetrisController.mainJFrame = mainJFrame;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public static int getSleepTime() {
		return sleepTime;
	}

	public static void setSleepTime(int sleepTime) {
		TetrisController.sleepTime = sleepTime;
	}

	public static boolean getIsStart() {
		return isStart;
	}

	public static void setIsStart(boolean flag) {
		isStart = flag;
	}

	public boolean isMoveCols() {
		return isMoveCols;
	}

	public static void setMoveCols(boolean isMoveCols) {
		TetrisController.isMoveCols = isMoveCols;
	}

}