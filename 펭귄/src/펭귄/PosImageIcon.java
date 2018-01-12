//20155199 �̼��� �ڹ� ������Ʈ�Դϴ�(��ϰ���)

package ���;

import javax.swing.*;



import java.awt.*;

// ImageIcon�� ��ǥ�� ��ġ�� �ο��ϰ��� ImageIcon Ŭ������ �����
public class PosImageIcon extends ImageIcon {
	int pX;				// ImageIcon�� X��ǥ
	int pY;				// ImageIcon�� y��ǥ
	int width;			// ImageIcon�� ����
	int height;			// ImageIcon�� ����
	int margin;
	int xSizeUp;
	int ySizeUp;
	int yDirection;
	int x2,y2;

	public PosImageIcon(String img, int x, int y, int width, int height) {
		super(img);
		yDirection = 2;
		xSizeUp = 2;
		ySizeUp = 1;
		
		pX = x;
		pY =y;
		x2 = x;//���� x
		y2 = y;//���� y
		this.width = width;
		this.height = height;
	
	}
	
	public void iceMove(){
		if(pX<200){
			pX	= pX-4;
		}
		if(pX>450){
			pX	= pX+4;
		}
		pY+=yDirection;

		width +=1;
		height += 1;
		
		
	}
	
	public void itemMove(){
		if(pX>360){
			pX	= pX+2;
		}
		if(pX>=340&&pX<=360)
			pX = pX+0;
		if(pX<340&&pX>=250)
			pX = pX-2;
		if(pX<250)
			pX = pX-3;
		pY+=yDirection;

		width +=1;
		height += 1;
		
	}

	public void poolMove(){

		if(x2>360){
			pX	= pX+2;
		}
		if(x2>=340&&x2<=360)
			pX = pX+1;
		if(x2<340&&x2>=270)
			pX = pX-1;
		if(x2<270)
			pX = pX-3;
		pY+=yDirection;

		width +=xSizeUp;
		height += ySizeUp;
	}	
	public int getpX() {
		return pX;
	}
	public int getpY() {
		return pY;
	}
		
	public boolean collide (Point p2,int margin) {
		Point p = new Point(this.pX, this.pY);
		if (p2.distance(p.getX()+this.width/2-10, p.getY()+this.height/2-10)<=margin) return true;
		return false;
	}
	public int getWidth() {
		return width;
	}

	
	public int getHeight() {
		return height;
	}

	

	public int getMargin() {
		return margin;
	}

	
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), pX, pY, width, height, null);
	}
}
