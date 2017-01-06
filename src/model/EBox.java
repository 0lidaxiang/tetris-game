package model;

import java.awt.*;

/**
 *
 */
public class EBox extends BoxModel {
	public EBox(int[] x, int[] y) {
		setX(x);
		setY(y);
		//记得标记最后一行的下一行为1
		setAngle(0);
		setBaseColor(Color.BLUE);
//		setBriShaderColor(Color.BLUE.brighter());
//		setDarkShaderColor(Color.BLUE.darker());
	}

	

	@Override
	public void updateNextXY() {

		switch(getAngle()){
			case 0:
			updateNextXY0();break;
			case 90:
				updateNextXY0();break;
			case 180:
				updateNextXY0();break;
			case 270:
				updateNextXY0();break;
		}
	}

	public void updateNextXY0(){
		int[] tNextX = new int[4];
		int[] tNextY = new int[4];

		for(int i = 0 ;i < 4; i++){
			tNextX[i] = this.getX()[i];
			tNextY[i] = this.getY()[i] + 1;
		}
		this.setNextX(tNextX);
		this.setNextY(tNextY);
	}

}