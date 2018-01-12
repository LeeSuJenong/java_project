//20155199 이수정 자바 프로젝트입니다(펭귄게임)

package 펭귄;
import javax.swing.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.*;
import javax.swing.Timer;



public class run {
	private final int SKIP =1;//버튼토글해줄것들
	private final int START = 2;
	private final int HOME = 4;
	private final int RANK = 8;
	private final int END = 16;
	private final int RHOME = 32;
	private final int HHOME = 64;
	private final int MENU = 128;
	private final int MHOME = 256;


	private AudioClip RankSound;//랭킹패널음악
	private AudioClip HappySound;//해피엔딩
	private AudioClip itemSound;//아이템먹으면 띵
	private AudioClip startSound;//시작음악
	private AudioClip eatSound;//생선먹은거
	private AudioClip StorySound;//스토리 음악
	private AudioClip SadSound;//sad 엔딩 음악
	private AudioClip CrashSound;//물개충돌음악
	private AudioClip WaterSound;//물에 빠지는거음악
	private AudioClip MainSound;//배경음악
	JFrame frame = new JFrame("펭귄게임");
	JPanel startPanel;//시작화면
	JPanel mainPanel;//메인 게임할화면
	JPanel endPanel;//종료시 화면
	JPanel happyEndPanel;//해피엔딩
	JPanel storyPanel;
	JPanel RankPanel;
	JPanel MenuPanel;

	Timer goAnime;
	Timer poolTimer;
	Timer Clock;//이건 그냥 시계
	Timer storyTimer;
	int x1,y1;//라벨 좌표
	int x =350,y=520;


	JLabel S = new JLabel(new ImageIcon("점수.PNG"));//점수 라벨
	JLabel E = new JLabel(new ImageIcon("생명.PNG"));// 생명라벨
	JLabel T = new JLabel(new ImageIcon("시간.PNG"));//시간라벨

	JButton end = new JButton(new ImageIcon("끝내기.PNG"));
	JButton MHome = new JButton(new ImageIcon("앞으로.PNG")); 
	JButton start = new JButton(new ImageIcon("스타트 버튼.PNG")); // 시작버튼
	JButton skip = new JButton(new ImageIcon("Skip.PNG"));
	JButton Home = new JButton(new ImageIcon("재시작버튼.PNG"));//end 꺼
	JButton home = new JButton(new ImageIcon("재시작버튼.PNG"));//happy 꺼
	JButton RHome = new JButton(new ImageIcon("재시작버튼.PNG"));//rank 꺼 
	JButton Rank = new JButton(new ImageIcon("랭킹.PNG"));
	JButton menuButton = new JButton(new ImageIcon("설명.PNG"));

	ArrayList<PosImageIcon> storyImage = new ArrayList<>();//스타트이미지 나오기 전에 보여주는 스토리 이미지
	
	final int WIDTH = 805,HEIGHT = 665;
	ImageIcon eat = new ImageIcon("생선먹음.png");//생선먹으면 플레이어 이미지가 이렇게 바뀜


	ImageIcon pool1Image = new ImageIcon("웅덩이.PNG");//리스트에 넣어줄꺼라 이미지만 해놓음
	ImageIcon pool2Image = new ImageIcon("물개.PNG");
	ImageIcon attack = new ImageIcon("빠짐2.png");//웅덩이에 충돌하면 이미지가 빠진 모습으로 바뀜
	ImageIcon attackL = new ImageIcon("왼쪽으로.PNG");//물개에 부딪치면 왼쪽으로 통통튐
	ImageIcon attackR = new ImageIcon("오른쪽으로.PNG");//물개에 무딪치면 오른쪽으로 통통 튐
	PosImageIcon startImage = new PosImageIcon("표지.PNG",0,0,800,635);//표지
	PosImageIcon fish = new PosImageIcon("물고기.PNG",(int)(Math.random()*200+240),950,20,10);//먹으면 에너지 올라감
	PosImageIcon item = new PosImageIcon("깃발.PNG",(int)(Math.random()*200+240),900,20,10);//먹으면 게임 성공조건중 하나
	PosImageIcon happy = new PosImageIcon("해피엔딩.PNG",0,0,800,635);
	PosImageIcon menu = new PosImageIcon("메뉴.PNG",0,0,800,635);

	PosImageIcon sad = new PosImageIcon("새드엔딩.PNG",0,0,810,635);
	//펭귄이 걷는거
	PosImageIcon player = new PosImageIcon("펭귄.gif",0,0,90,100);//이 이미지에서 바뀌면
	PosImageIcon origner = new PosImageIcon("펭귄.gif",0,0,90,100);//이 이미지로 다시 셋팅
	PosImageIcon itemOrigner = new PosImageIcon("깃발.png",(int)(Math.random()*200+240),900,20,10);
	PosImageIcon ground = new PosImageIcon("배경.PNG",0,0,800,635);
	ArrayList<PosImageIcon> ice = new ArrayList<PosImageIcon>();//배경얼음
	ArrayList<PosImageIcon> pool = new ArrayList<PosImageIcon>();//장애물
	ArrayList<JLabel>  Labellist = new ArrayList<JLabel>();//여기에 랭킹리스트를 집어넣고 보여준다 

	
	ArrayList<Rank> Ranking = new ArrayList<Rank>();//순위

	int[] iceX = new int[]{(int)(Math.random()*130+30),(int)(Math.random()*120),(int)(Math.random()*100+600),(int)(Math.random()*100+650)};//ice의 x좌표 두개
	int[] poolX = new int[]{(int)(Math.random()*60+210),(int)(Math.random()*70+270),(int)(Math.random()*20+340),(int)(Math.random()*80+380)};//pool의 x좌표 두개
	JLayeredPane panel = new JLayeredPane();

	int energy;
	int cnt;
	int count = 0;//깃발하고 충돌시 하나씩 증가

	int STEPS = 20;//객체가 움직이는 속도 
	int t =60;//이건 시간제한으로 1초에 1씩차감 다되면 게임끝

	int score=0;

	JTextField name = new JTextField(30);
	String playerName;
	public static void main(String [] args) {
		//playerName=JOptionPane.showInputDialog("이름을 입력해주세요 :");
		run a = new run();
		a.go();
	}
	// 버튼 투명하게하기
	public void cleanbutton(JButton a){
		a.setBorderPainted(false);
		a.setFocusPainted(false);
		a.setContentAreaFilled(false);}

	public void go(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		frame.setLocation(screen.width/6,screen.height/6);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new mainPanel();
		mainPanel.setBounds(0,0,WIDTH,HEIGHT);
		startPanel = new StartPanel();
		startPanel.setBounds(0, 0, WIDTH, HEIGHT);
		endPanel = new endPanel();
		endPanel.setBounds(0,0,WIDTH,HEIGHT);
		happyEndPanel = new happyEndPanel();
		happyEndPanel.setBounds(0,0, WIDTH, HEIGHT);
		storyPanel = new storyPanel();
		storyPanel.setBounds(0, 0, WIDTH, HEIGHT);
		RankPanel = new RankPanel();
		RankPanel.setBounds(0,0,WIDTH,HEIGHT);
		MenuPanel = new menuPanel();
		MenuPanel.setBounds(0,0,WIDTH,HEIGHT);



		storyImage.add(new PosImageIcon("기웃0.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("기웃1.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("기웃2.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("기웃3.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("기웃4.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("기웃5.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("표지.PNG",0,0,800,635));

		ice.add(new PosImageIcon ("얼음.png",iceX[0],300,30,10));
		ice.add(new PosImageIcon ("얼음2.png",iceX[1],700,30,10));
		ice.add(new PosImageIcon ("얼음.png",iceX[2],300,30,10));
		ice.add(new PosImageIcon ("얼음2.png",iceX[3],700,30,10));

		cleanbutton(start);
		cleanbutton(skip);
		cleanbutton(Home);
		cleanbutton(RHome);
		cleanbutton(end);
		cleanbutton(home);
		cleanbutton(Rank);
		cleanbutton(MHome);
		cleanbutton(menuButton);


		buttonToggler(SKIP);//초기는 스킵만먹는다

		happyEndPanel.add(Rank);
		happyEndPanel.add(home);
		mainPanel.add(end);
		MenuPanel.add(MHome);
		MHome.addActionListener(new HomeListener());

		mainPanel.add(S);
		mainPanel.add(T);
		mainPanel.add(E);
		end.addActionListener(new EndLisener());
		home.addActionListener(new HomeListener());
		Rank.addActionListener(new RankListener());
		RHome.addActionListener(new HomeListener());
		storyPanel.add(skip);
		skip.addActionListener(new SkipListener());

		panel.add(RankPanel,new Integer(0));
		panel.add(happyEndPanel,new Integer(1));
		panel.add(endPanel,  new Integer(2));
		panel.add(mainPanel,  new Integer(3));
		panel.add(startPanel,  new Integer(4));
		panel.add(MenuPanel, new Integer(5));
		panel.add(storyPanel, new Integer(6));

		startPanel.add(name);
		startPanel.add(menuButton);
		startPanel.add(start);
		RankPanel.add(RHome);
		menuButton.addActionListener(new menuListener());
		name.addActionListener(new Listener());
		start.addActionListener(new StartListener());
		frame.add(panel);
		frame.setSize(WIDTH,HEIGHT);
		frame.setResizable(false); 
		storyTimer = new Timer(1000,new storyTimerListener());

		poolTimer = new Timer(100,new poolListener());//웅덩이 시계
		Clock = new Timer(1000, new ClockListener());
		goAnime = new Timer(50,new TimerListener());
		endPanel.add(Home);
		Home.addActionListener(new HomeListener());
		frame.setVisible(true);

		mainPanel.addKeyListener(new DirectionListener());

		try {
			RankSound = JApplet.newAudioClip(new URL("file", "localhost","랭킹.WAV"));
			HappySound = JApplet.newAudioClip(new URL("file", "localhost","해피앤딩.WAV"));
			itemSound = JApplet.newAudioClip(new URL("file", "localhost","아이템 소리.wav"));
			startSound = JApplet.newAudioClip(new URL("file", "localhost","표지1.WAV"));
			eatSound = JApplet.newAudioClip(new URL("file", "localhost","먹음.WAV"));
			StorySound = JApplet.newAudioClip(new URL("file", "localhost","표지까지.WAV"));
			SadSound = JApplet.newAudioClip(new URL("file", "localhost","새드엔딩1.WAV"));
			CrashSound = JApplet.newAudioClip(new URL("file", "localhost","쾅.wav"));
			WaterSound = JApplet.newAudioClip(new URL("file", "localhost","빠지는 소리.WAV"));
			MainSound = JApplet.newAudioClip(new URL("file", "localhost","main.WAV"));
		}
		catch(Exception e1){
			System.out.println("배경음악 로딩 실패");
		}
		storyTimer.start();
		StorySound.play();
	}
	class RankPanel extends JPanel{
		public void paintComponent(Graphics g) {
			ground.draw(g);
			RHome.setBounds(610, 500, 180, 100);

		}
	}

	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == name){//꼭 엔터를 쳐야지만 이름이 등록된다!!!!!!!
				playerName = name.getText();
				name.setEditable(false);
			}
		}

	}

	class storyPanel extends JPanel{
		public void paintComponent(Graphics g){
			storyImage.get(0).draw(g);

			skip.setBounds(680, 5, 100, 60);
		}
	}

	class StartPanel extends JPanel{
		public void paintComponent(Graphics g){

			startImage.draw(g);
			g.drawString("이름 : ",590,495);

			name.setBounds(630, 480, 100, 25);

			menuButton.setBounds(600, 570, 180, 70);

			start.setBounds(610, 520, 180, 70);
			mainPanel.setFocusable(true);
			mainPanel.requestFocus();
		}
	}
	// 버튼의 활성 비활성화를 위한 루틴
	private void buttonToggler(int flags) {
		if((flags & SKIP) != 0)
			skip.setEnabled(true);
		else
			skip.setEnabled(false);
		if ((flags & START) != 0)
			start.setEnabled(true);
		else
			start.setEnabled(false);
		if ((flags & HOME) != 0)
			Home.setEnabled(true);
		else
			Home.setEnabled(false);
		if ((flags & RHOME) != 0)
			RHome.setEnabled(true);
		else
			RHome.setEnabled(false);
		if ((flags & HHOME) != 0)
			home.setEnabled(true);
		else
			home.setEnabled(false);
		if ((flags & END) != 0)
			end.setEnabled(true);
		else
			end.setEnabled(false);
		if ((flags & RANK) != 0)
			Rank.setEnabled(true);
		else
			Rank.setEnabled(false);

		if ((flags & MENU) != 0)
			menuButton.setEnabled(true);
		else
			menuButton.setEnabled(false);
		if ((flags & MHOME) != 0)
			MHome.setEnabled(true);
		else
			MHome.setEnabled(false);

	}

	class menuPanel extends JPanel{
		public void paintComponent(Graphics g){
			menu.draw(g);
			g.drawImage(fish.getImage(), 240,210,50,40,this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("신명조",Font.CENTER_BASELINE,20));
			g.drawString("주의! 깃발을 먹어도 시간이 다될때까지 ", 15, 530);
			g.drawString("에너지를 못지키면 실패", 70, 570);
			MHome.setBounds(640, 550, 100, 70);
		}
	}

	class endPanel extends JPanel{
		public void paintComponent(Graphics g){

			sad.draw(g);
			Home.setBounds(600,550,180,60);
			mainPanel.setFocusable(false);

		}
	}
	class happyEndPanel extends JPanel{
		public void paintComponent(Graphics g){

			happy.draw(g);

			Rank.setBounds(635, 540, 80, 60);
			home.setBounds(600,480,180,60);
			mainPanel.setFocusable(false);

		}
	}
	class mainPanel extends JPanel{
		int b=0;

		public void paintComponent(Graphics g){
			ground.draw(g);
			buttonToggler(END);
			g.setColor(Color.darkGray);
			end.setBounds(665, 15, 95, 50);

			g.setFont(new Font("신명조",Font.CENTER_BASELINE,25));
			S.setBounds(10, 30, 120, 30);
			g.drawString(""+score,130,55);
			E.setBounds(250, 30, 150, 40);
			g.drawString(""+energy,405,60);
			T.setBounds(500, 30, 110, 30);
			g.drawString(""+t,620,55);
			player.pX=x;
			player.pY=y;
			fish.draw(g);
			fish.itemMove();
			item.draw(g);
			item.itemMove();
			if(t<=20){
				if(fish.pY>1100){
					fish.pX = (int)(Math.random()*250+200);
					fish.pY = 380;
					fish.height = 10;
					fish.width = 20;
				}
				if(item.pY >1300){
					item.pX = (int)(Math.random()*250+200);
					item.pY = 300;
					item.height = 20;
					item.width = 20;
				}}
			if(t>20){
				if(fish.pY>1300){
					fish.pX = (int)(Math.random()*250+270);
					fish.pY = 380;
					fish.height = 10;
					fish.width = 20;
				}
				if(item.pY >1400){
					item.pX = (int)(Math.random()*250+270);
					item.pY = 300;
					item.height = 20;
					item.width = 20;
				}
			}

			if(fish.collide(new Point(item.getpX()+item.width/2,item.getpY()+item.height/2),40))
				fish.pY+=600;

			for(int i =0;i<pool.size();i++){

				pool.get(i).draw(g);
				pool.get(i).poolMove();
			}
			for(int i=0;i<4;i++){
				ice.get(i).draw(g);
				ice.get(i).iceMove();
				if(ice.get(0).pY >= 600){
					ice.get(0).pX = iceX[0];
					ice.get(0).pY = 250;
					ice.get(0).height = 10;
					ice.get(0).width = 30;
					ice.get(0).margin =0;
				}
				if(ice.get(1).pY >= 800){
					ice.get(1).pX = iceX[1];
					ice.get(1).pY = 250;
					ice.get(1).height = 10;
					ice.get(1).width = 30;
					ice.get(1).margin =0;
				}
				if(ice.get(2).pY >= 600){
					ice.get(2).pX = iceX[2];
					ice.get(2).pY = 250;
					ice.get(2).height = 10;
					ice.get(2).width = 30;
					ice.get(2).margin =0;
				}
				if(ice.get(3).pY >= 800){
					ice.get(3).pX = iceX[3];
					ice.get(3).pY = 250;
					ice.get(3).height = 10;
					ice.get(3).width = 30;
					ice.get(3).margin =0;
				}
			}


			g.drawImage(player.getImage(), x, y, this);//펭귄이 제일 위에
			if(energy <=0 || t<=0){
				finishGame();}


			for(int i=0;i<pool.size();i++){


				if(pool.get(i).collide(new Point(item.getpX()+item.width/2,item.getpY()+item.height/2),20))//장애물과 깃발이 곂칠경우 깃발이 안보이게
					item.pY +=1000;

				if(pool.get(i).collide(new Point(fish.getpX(),fish.getpY()),20))//장애물과 물고기가 곂칠경우 장애물이 안보이게
					fish.pY +=400;
				if(player.getImage() == origner.getImage()){
					if(pool.get(i).collide(new Point(player.getpX()+player.width/2,player.getpY()+player.height/2),80)){
						if(pool.get(i).getImage() == pool2Image.getImage() ){
							CrashSound.play();
							energy-=15;//물개한테 부딪치면 -15
							score -=300;//부딪치면 -300점
							goAnime.stop();
							poolTimer.stop();
							pool.get(i).pY += 1000;
							if(pool.get(i).pX+pool.get(i).width/2>player.pX)
								player.setImage(attackL.getImage());
							if(pool.get(i).pX+pool.get(i).width/2<player.pX)
								player.setImage(attackR.getImage());

						}
						if(pool.get(i).getImage() == pool1Image.getImage()){
							player.setImage(attack.getImage());
							WaterSound.play();
							energy-=10;//우물에 부딪치면 -10
							score -=200;//부딪치면 -200점
							poolTimer.stop();
							pool.get(i).pY += 1000;

						}
						mainPanel.setFocusable(false);
						mainPanel.requestFocus(false);//충돌시 플레이어 안움직임
						frame.repaint();
					}}
			}
			if(player.getImage() ==attackL.getImage()){
				b++;

				if(b%5==0){
					x-=10;

				}	

				if(b%30 == 0){
					mainPanel.setFocusable(true);//이미지가 원래로 돌아오면 다시 키 먹음
					mainPanel.requestFocus();	//이미지가 원래로 돌아오면 다시 키 먹음
					player.setImage(origner.getImage());
					frame.repaint();
					poolTimer.restart();
					goAnime.restart();
				}
			}
			if(player.getImage() ==attackR.getImage() ){
				b++;

				if(b%5==0){
					x+=10;

				}
				if(b%30==0){
					mainPanel.setFocusable(true);//이미지가 원래로 돌아오면 다시 키 먹음
					mainPanel.requestFocus();	//이미지가 원래로 돌아오면 다시 키 먹음

					player.setImage(origner.getImage());
					frame.repaint();
					goAnime.restart();
					poolTimer.restart();
				}

			}
			if(player.getImage() ==attack.getImage()){
				b++;
				if(b%30==0){
					poolTimer.restart();
					mainPanel.setFocusable(true);//이미지가 원래로 돌아오면 다시 키 먹음
					mainPanel.requestFocus();//이미지가 원래로 돌아오면 다시 키 먹음
					player.setImage(origner.getImage());
					frame.repaint();
					goAnime.restart();

				}
			}
			if(player.getImage() == origner.getImage()){
				mainPanel.setFocusable(true);
				mainPanel.requestFocus(true);
			}

			if(player.getImage() == origner.getImage()){
				if(fish.collide(new Point(player.getpX()+player.width/2,player.getpY()+player.height/2),50)){
					fish.pY+=600;
					eatSound.play();
					score +=500;
					if(energy >=100)
						energy =100;
					else
						energy += 5;
					player.setImage(eat.getImage());
					mainPanel.setFocusable(false);
					mainPanel.requestFocus(false);
					frame.repaint();
				}
			}
			if(player.getImage() ==eat.getImage()){
				b++;
				if(b%15==0){
					mainPanel.setFocusable(true);//이미지가 원래로 돌아오면 다시 키 먹음
					mainPanel.requestFocus();//이미지가 원래로 돌아오면 다시 키 먹음
					player.setImage(origner.getImage());
					frame.repaint();
				}
			}
			if(player.getImage() == origner.getImage()){
				if(item.collide(new Point(player.getpX()+player.width/2,player.getpY()+player.height/2),50)){
					itemSound.play();
					player.setImage(eat.getImage());
					mainPanel.setFocusable(false);
					mainPanel.requestFocus(false);
					frame.repaint();
					item.pY+=600;
					score +=1000;
					count++;
				}}
		}}

	class RankListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.setLayer(RankPanel, 11);
			buttonToggler(RHOME);//홈만작동
			HappySound.stop();
			RankSound.play();
		}
	}

	class SkipListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//buttonToggler(START);
			panel.setLayer(startPanel, 7);
			frame.repaint();
			StorySound.stop();
			startSound.play();

			buttonToggler(START+MENU);
		}
	}

	class menuListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			panel.setLayer(MenuPanel, 8);
			startSound.stop();
			StorySound.stop();
			startSound.play();

			buttonToggler(MHOME);
		}
	}

	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			startSound.stop();
			StorySound.stop();
			panel.setLayer(mainPanel, 9);// mainPanel 이 앞으로 나오게 
			mainPanel.setFocusable(true);// mainPanel 이 포커스될 수 있게 해줌
			mainPanel.requestFocus();// 포커스 맞춰주기 안하면 안됨 키먹게해준다
			Clock.start();
			poolTimer.start();
			goAnime.start();

			buttonToggler(END);

			frame.repaint();
			energy =100;
			t=60;

			MainSound.play();

		}}

	class EndLisener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			finishGame();
			MainSound.stop();
			SadSound.play();
			buttonToggler(HOME);
		}
	}
	class HomeListener implements ActionListener {
		public void actionPerformed(ActionEvent e){


			Labellist.clear();
			Ranking.clear();//랭킹패널이 잘나오게 하기위한 초기화이다
			RankPanel.removeAll();
			RankPanel.add(RHome);
			count =0;


			buttonToggler(START+MENU);
			SadSound.stop();
			HappySound.stop();
			RankSound.stop();
			startSound.play();
			panel.setLayer(startPanel,6);
			panel.setLayer(mainPanel,5);
			panel.setLayer(happyEndPanel, 4);
			panel.setLayer(endPanel,3);
			panel.setLayer(storyPanel, 2);
			panel.setLayer(RankPanel, 1);
			panel.setLayer(MenuPanel, 0);
			mainPanel.setFocusable(true);// mainPanel 이 포커스될 수 있게 해줌
			mainPanel.requestFocus();// 포커스 맞춰주기 안하면 안됨 
			goAnime.setDelay(50);
			poolTimer.setDelay(100);
			player.setImage(origner.getImage());
			name.setText(null);
			name.setEditable(true);

			frame.repaint();

			t=60;
			energy =100;
			x=350;y=520;

			frame.repaint();
		}}
	class DirectionListener implements KeyListener {
		public void keyPressed (KeyEvent e) {
			switch (e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				if (player.pX<660){
					x+=STEPS;
					player.pX=x;
				}

				break;
			case KeyEvent.VK_LEFT:
				if (player.pX >30){
					x-=STEPS;
					player.pX=x;
				}
				break;
			}
			mainPanel.repaint();

		}
		public void keyTyped (KeyEvent event) {}
		public void keyReleased (KeyEvent event) {}
	}


	public void finishGame(){
		int  totalScore;
		goAnime.stop();
		Clock.stop();
		poolTimer.stop();
		energy = 100;
		player.setImage(origner.getImage());
		player.pX = 350;
		player.pY = 520;
		pool.clear();
		MainSound.stop();


		String  Name;
		String  lineString;

		int rankCount=1;
		int cont=0;

		if(count>=1){
			if(t==0){
				HappySound.play();
				score += energy *10;
				buttonToggler(HHOME+RANK);
				panel.setLayer(happyEndPanel,10);

				File file = new File("RANK.txt"); 
				try{                                                       //txt파일 읽어오기
					Scanner scan = new Scanner(file);
					while(scan.hasNext()){//한줄씩 읽어서 받아들이는거
						lineString = scan.nextLine();
						Scanner scanFile = new Scanner(lineString);
						Name = scanFile.next();
						totalScore = Integer.parseInt(scanFile.next());//읽어드리기

						Ranking.add(new Rank(Name, totalScore));
					}
					scan.close();
				}catch(Exception ex){System.out.println("오류가 일어났습니다.");}


				Ranking.add(new Rank( playerName,score));

				Collections.sort(Ranking,Collections.reverseOrder());//현재꺼까지 등록한뒤에 배열정리
				for(Rank rank: Ranking){
					Labellist.add(new JLabel(rankCount+"   "+rank.name+" ("+rank.score+"점)"));//3위까지만 라벨에 등록
					if(rankCount==3) break;
					rankCount++;
				}
				for(JLabel label:Labellist){                                   //label 출력
					label.setForeground(Color.LIGHT_GRAY);
					label.setFont(new Font("신명조",Font.CENTER_BASELINE,40));
					label.setSize(600,100);
					if(cont==0){
						x1=200;
						y1=200;
					}
					if(cont==1){
						x1=290;
						y1=320;
					}
					if(cont==2){
						x1=360;
						y1=450;
					}
					label.setLocation(x1,y1);       //label 위치
					RankPanel.add(label); //RankPanel에 Label을 입력
					cont++;//cont를 하나씩 증가 시킴에 따라 x1,y1좌표가 바뀌어서 거기에 새로운 라벨을 등록
				}
				try {                                                        //txt파일에 입력
					FileWriter fileWriter = new FileWriter(file,true); 
					fileWriter.write(playerName+" "+Integer.toString(score)+"\n");
					fileWriter.flush();   //파일에 넣어준다.     
					fileWriter.close();   //파일을 닫아준다.
				} catch (Exception e2) {e2.printStackTrace();} 

			}

			else {
				panel.setLayer(endPanel,10);
				SadSound.play();
				buttonToggler(HOME);
			}
		}

		else{ 
			buttonToggler(HOME);
			SadSound.play();
			panel.setLayer(endPanel,10);

		}
		score = 0;
	}
	class TimerListener implements ActionListener{
		int b=0;
		public void actionPerformed(ActionEvent arg0) {
			if(score <=0)
				score =0;

			if(energy>=100)
				energy =100;

			if(t <=40){
				poolTimer.setDelay(75);
				goAnime.setDelay(45);
			}
			if(t<=20){
				poolTimer.setDelay(65);
				goAnime.setDelay(35);
			}
			if(t<=10){
				poolTimer.setDelay(55);
				goAnime.setDelay(25);
			}

			frame.repaint();
		}
	}
	class ClockListener implements ActionListener{
		int a;
		public void actionPerformed(ActionEvent e){
			score+=100;
			energy--;
			t--;


			if(score <=0)
				score =0;
			if(energy>=100)
				energy =100;

			frame.repaint();
			if(t <= 0)
				finishGame();
		}
	}
	class poolListener implements ActionListener{
		int a =0;//풀 리스너는 처음 100마다 작동 이때마다 하나씩 카운트해준다 즉 걸리는 초가 짧아질수록 생성속도는 빨라진다
		int a1,a2;//0~1까지의 랜덤수를 받아줄 것이다
		public void actionPerformed(ActionEvent e){

			if(a%20==0){//a를 20으로 나누었을때 나머지가 없으면 열심히 장애물을 생성
				while(true){//while를 쓰는 이유는 r과r1,r2,r3 가 안겹치게 할라고
					int r = (int)(Math.random()*4);
					int r1 = (int)(Math.random()*4);
					int r2 = (int)(Math.random()*4);
					int r3 = (int)(Math.random()*4);
					int ch = (int )(Math.random()*16);
					if(t<=20){
						if(r!=r1&&r!=r2&&r!=r3&&r1!=r2&&r1!=r3&&r2!=r3){
							if(ch%2==0){
								pool.add(new PosImageIcon("물개.PNG",poolX[r],380,40,20));
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[r2],300,40,5));
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[r3],340,50,5));
								break;
							}
							else{
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[r],300,40,5));
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[r1],350,40,20));
								break;
							}}
					}
					if(t>20){//이렇게해서 총 4개의 x좌표가 있는데 그것을 계속 번갈아가면서 나오게 한다 랜덤으로
						if(r!=r1||r!=r2||r!=r3||r1!=r2||r1!=r3||r2!=r3){
							if(r!=r1){
								a1 =r;
								a2 = r1;
							}
							else if(r!=r2){
								a1=r;
								a2 =r2;
							}
							else if(r!=r3){
								a1 = r;
								a2 = r3;
							}
							else if(r1!=r2){
								a1 =r1;
								a2 = r2;
							}
							else if(r1!=r3){
								a1 = r1;
								a2 = r3;
							}
							else if(r2!=r3){
								a1 = r2;
								a2 = r3;
							}
							if(ch%5==0){
								pool.add(new PosImageIcon("물개.PNG",poolX[a1],300,40,20));
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[a2],350,40,5));
								break;
							}
							else{
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[a1],300,50,5));
								pool.add(new PosImageIcon("웅덩이.PNG",poolX[a2],350,40,5));
								break;
							}


						}								
					}
				}}
			for(PosImageIcon p : pool){
				p.poolMove();
			}
			a++;

			mainPanel.repaint();

		}}
	class storyTimerListener implements ActionListener{
		int ti = 0;

		public void actionPerformed(ActionEvent e){

			if(ti<6){//이렇게해서 7번째모습은 보여주지않고 에러가 안생기게 할라고배열 0만 보여주니까 시간마다 다음 배열의 이미지를 배열 0의 이미지로 셋팅
				storyImage.get(0).setImage(storyImage.get(ti+1).getImage());
				ti++;
			}
			if(ti ==6){
				buttonToggler(START+MENU);
				panel.setLayer(startPanel, 7);
				storyTimer.stop();//이 역할이 끝나면 이 타이머는 종료

			}
			frame.repaint();
		}
	} 
} 