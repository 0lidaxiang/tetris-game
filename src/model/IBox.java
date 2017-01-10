package model;

import java.awt.Color;

import controller.TetrisController;

public class IBox extends BoxModel {

	public IBox() {
		int[] x = { 6, 6, 6, 6 };
		int[] y = { -1, 0, 1, 2 };
		setX(x);
		setY(y);
		setBaseColor(Color.RED);
	}

	@Override
	public void update90NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];
		x[1] = x1;
		y[1] = y1;
		x[0] = x[1] + 1;
		y[0] = y[1];
		x[2] = x[1] - 1;
		y[2] = y[1];
		x[3] = x[1] - 2;
		y[3] = y[1];

		// 检查能否旋转，如果有一个方块的下一个坐标超过边界就不能
		boolean f = true;
		// 改过当前坐标后，重新定义下一个坐标
		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for (int i = 0; i < 4; i++) {
			tNextX[i] = x[i];
			tNextY[i] = y[i] + 1;
			
			//检查下一个位置是否会超过边界
			if ((tNextX[i] < 0) || (tNextX[i] > 12)) {
				f = false;
				break;
			}
			//检查下一个位置是否被标记
			if(TetrisController.mainJFrame.getTp().getPositioinFlag()[tNextX[i]][tNextY[i]][0] == 1){
				f = false;
				break;
			}
		}
		
		if (f) {
			setX(x);
			setY(y);
			this.setNextX(tNextX);
			this.setNextY(tNextY);
			setAngle(0);
		}
		else{
			//如果不能旋转90度，则正常下落。
			for (int i = 0; i < 4; i++) {
				tNextX[i] = this.getX()[i];
				tNextY[i] = this.getY()[i] + 1;
			}
			this.setNextX(tNextX);
			this.setNextY(tNextY);
		}
	}

	@Override
	public void update180NextXY(int x1, int y1) {
		int[] x = new int[4];
		int[] y = new int[4];
		x[1] = x1;
		y[1] = y1 - 1;
		x[0] = x[1];
		y[0] = y[1] + 1;
		x[2] = x[1];
		y[2] = y[1] - 1;
		x[3] = x[1];
		y[3] = y[1] - 2;
		setX(x);
		setY(y);

		// 改过当前坐标后，重新定义下一个坐标
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
		y[1] = y1;
		x[0] = x[1] + 1;
		y[0] = y[1];
		x[2] = x[1] - 1;
		y[2] = y[1];
		x[3] = x[1] - 2;
		y[3] = y[1];
		setX(x);
		setY(y);

		// 改过当前坐标后，重新定义下一个坐标
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
		y[1] = y1 - 1;
		x[0] = x[1];
		y[0] = y[1] + 1;
		x[2] = x[1];
		y[2] = y[1] - 1;
		x[3] = x[1];
		y[3] = y[1] - 2;
		setX(x);
		setY(y);

		// 改过当前坐标后，重新定义下一个坐标
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
