package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import model.BoxModel;
import model.EBox;
import view.MainPanelView;
import view.TablePanel;

/**
 *
 */
public class TetrisController {
	private MainPanelView mainJFrame;
	private static boolean isMove;
	private static int sleepTime;
	private int initialX, initialY;

	public TetrisController() {
		setSleepTime(2500);// 初始化程序睡眠时间，用户看到的就是方块移动速度，值越大，速度越慢
		setBoxInitialXY(150, 25);// 方块产生时的初始化位置
	}

	public static void main(String[] args) {
		TetrisController tetris = new TetrisController();
		tetris.start();
	}

	public void start() {
		setIsMove(false);// 表示不能移动方块，保持初始化坐标
		setMainJFrame(new MainPanelView(createTp()));// 画面板和其他部分
		int i = 0;
		// 进入玩游戏状态并监听键盘事件
		while (true) {

			if (getIsMove()) {

				// 更改tablePanel中的状态，表示可以显示方块了而不是显示文字状态
				mainJFrame.getTp().setGameStatus(0);

				// 每次移动一个格子
				if (i < 23) {
					System.out.print("方块标志位置的坐标 : " + mainJFrame.getTp().getBox().getX());
					System.out.println(" , " + mainJFrame.getTp().getBox().getY());
					// 下一个位置的坐标
					mainJFrame.getTp().getBox().setXY(getInitialX(), getInitialY() * i);

					i = i + 1;
				}
			}

			mainJFrame.repaint();

			// 重画一次之后就睡眠，user看到的就是方块移动的速度
			// 程序sleep时方块停止移动
			try {
				Thread.sleep(getSleepTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public TablePanel createTp() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		TablePanel tp = new TablePanel(200, 400, d.width, d.height);

		tp.setBox(createRandomBoxModel());

		return tp;
	}

	// 新建一个boxmodel并初始化参数
	private BoxModel createRandomBoxModel() {
		Color baseColor = Color.BLUE;
		Color briShaderColor = Color.BLUE.brighter();
		Color darkShaderColor = Color.BLUE.darker();
		BoxModel newBox = new EBox(0, 0, baseColor, briShaderColor, darkShaderColor);

		return newBox;
	}

	public void setBoxInitialXY(int initialX, int initialY) {
		this.initialX = initialX;
		this.initialY = initialY;
	}

	public MainPanelView getMainJFrame() {
		return mainJFrame;
	}

	public void setMainJFrame(MainPanelView mainJFrame) {
		this.mainJFrame = mainJFrame;
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

	public static boolean getIsMove() {
		return isMove;
	}

	public static void setIsMove(boolean flag) {
		isMove = flag;
	}
}