//20155199 이수정 자바 프로젝트입니다(펭귄게임)

package 펭귄;

import javax.swing.*;



import java.awt.*;

// ImageIcon에 좌표의 위치를 부여하고자 ImageIcon 클래스를 상속함
public class PosImageIcon extends ImageIcon {
	int pX;				// ImageIcon의 X좌표
	int pY;				// ImageIcon의 y좌표
	int width;			// ImageIcon의 넓이
	int height;			// ImageIcon의 높이
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
		x2 = x;//원래 x
		y2 = y;//원래 y
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
