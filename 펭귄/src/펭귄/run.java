//20155199 �̼��� �ڹ� ������Ʈ�Դϴ�(��ϰ���)

package ���;
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
	private final int SKIP =1;//��ư������ٰ͵�
	private final int START = 2;
	private final int HOME = 4;
	private final int RANK = 8;
	private final int END = 16;
	private final int RHOME = 32;
	private final int HHOME = 64;
	private final int MENU = 128;
	private final int MHOME = 256;


	private AudioClip RankSound;//��ŷ�г�����
	private AudioClip HappySound;//���ǿ���
	private AudioClip itemSound;//�����۸����� ��
	private AudioClip startSound;//��������
	private AudioClip eatSound;//����������
	private AudioClip StorySound;//���丮 ����
	private AudioClip SadSound;//sad ���� ����
	private AudioClip CrashSound;//�����浹����
	private AudioClip WaterSound;//���� �����°�����
	private AudioClip MainSound;//�������
	JFrame frame = new JFrame("��ϰ���");
	JPanel startPanel;//����ȭ��
	JPanel mainPanel;//���� ������ȭ��
	JPanel endPanel;//����� ȭ��
	JPanel happyEndPanel;//���ǿ���
	JPanel storyPanel;
	JPanel RankPanel;
	JPanel MenuPanel;

	Timer goAnime;
	Timer poolTimer;
	Timer Clock;//�̰� �׳� �ð�
	Timer storyTimer;
	int x1,y1;//�� ��ǥ
	int x =350,y=520;


	JLabel S = new JLabel(new ImageIcon("����.PNG"));//���� ��
	JLabel E = new JLabel(new ImageIcon("����.PNG"));// �����
	JLabel T = new JLabel(new ImageIcon("�ð�.PNG"));//�ð���

	JButton end = new JButton(new ImageIcon("������.PNG"));
	JButton MHome = new JButton(new ImageIcon("������.PNG")); 
	JButton start = new JButton(new ImageIcon("��ŸƮ ��ư.PNG")); // ���۹�ư
	JButton skip = new JButton(new ImageIcon("Skip.PNG"));
	JButton Home = new JButton(new ImageIcon("����۹�ư.PNG"));//end ��
	JButton home = new JButton(new ImageIcon("����۹�ư.PNG"));//happy ��
	JButton RHome = new JButton(new ImageIcon("����۹�ư.PNG"));//rank �� 
	JButton Rank = new JButton(new ImageIcon("��ŷ.PNG"));
	JButton menuButton = new JButton(new ImageIcon("����.PNG"));

	ArrayList<PosImageIcon> storyImage = new ArrayList<>();//��ŸƮ�̹��� ������ ���� �����ִ� ���丮 �̹���
	
	final int WIDTH = 805,HEIGHT = 665;
	ImageIcon eat = new ImageIcon("��������.png");//���������� �÷��̾� �̹����� �̷��� �ٲ�


	ImageIcon pool1Image = new ImageIcon("������.PNG");//����Ʈ�� �־��ٲ��� �̹����� �س���
	ImageIcon pool2Image = new ImageIcon("����.PNG");
	ImageIcon attack = new ImageIcon("����2.png");//�����̿� �浹�ϸ� �̹����� ���� ������� �ٲ�
	ImageIcon attackL = new ImageIcon("��������.PNG");//������ �ε�ġ�� �������� ����Ʀ
	ImageIcon attackR = new ImageIcon("����������.PNG");//������ ����ġ�� ���������� ���� Ʀ
	PosImageIcon startImage = new PosImageIcon("ǥ��.PNG",0,0,800,635);//ǥ��
	PosImageIcon fish = new PosImageIcon("�����.PNG",(int)(Math.random()*200+240),950,20,10);//������ ������ �ö�
	PosImageIcon item = new PosImageIcon("���.PNG",(int)(Math.random()*200+240),900,20,10);//������ ���� ���������� �ϳ�
	PosImageIcon happy = new PosImageIcon("���ǿ���.PNG",0,0,800,635);
	PosImageIcon menu = new PosImageIcon("�޴�.PNG",0,0,800,635);

	PosImageIcon sad = new PosImageIcon("���忣��.PNG",0,0,810,635);
	//����� �ȴ°�
	PosImageIcon player = new PosImageIcon("���.gif",0,0,90,100);//�� �̹������� �ٲ��
	PosImageIcon origner = new PosImageIcon("���.gif",0,0,90,100);//�� �̹����� �ٽ� ����
	PosImageIcon itemOrigner = new PosImageIcon("���.png",(int)(Math.random()*200+240),900,20,10);
	PosImageIcon ground = new PosImageIcon("���.PNG",0,0,800,635);
	ArrayList<PosImageIcon> ice = new ArrayList<PosImageIcon>();//������
	ArrayList<PosImageIcon> pool = new ArrayList<PosImageIcon>();//��ֹ�
	ArrayList<JLabel>  Labellist = new ArrayList<JLabel>();//���⿡ ��ŷ����Ʈ�� ����ְ� �����ش� 

	
	ArrayList<Rank> Ranking = new ArrayList<Rank>();//����

	int[] iceX = new int[]{(int)(Math.random()*130+30),(int)(Math.random()*120),(int)(Math.random()*100+600),(int)(Math.random()*100+650)};//ice�� x��ǥ �ΰ�
	int[] poolX = new int[]{(int)(Math.random()*60+210),(int)(Math.random()*70+270),(int)(Math.random()*20+340),(int)(Math.random()*80+380)};//pool�� x��ǥ �ΰ�
	JLayeredPane panel = new JLayeredPane();

	int energy;
	int cnt;
	int count = 0;//����ϰ� �浹�� �ϳ��� ����

	int STEPS = 20;//��ü�� �����̴� �ӵ� 
	int t =60;//�̰� �ð��������� 1�ʿ� 1������ �ٵǸ� ���ӳ�

	int score=0;

	JTextField name = new JTextField(30);
	String playerName;
	public static void main(String [] args) {
		//playerName=JOptionPane.showInputDialog("�̸��� �Է����ּ��� :");
		run a = new run();
		a.go();
	}
	// ��ư �����ϰ��ϱ�
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



		storyImage.add(new PosImageIcon("���0.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("���1.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("���2.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("���3.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("���4.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("���5.PNG",0,0,800,635));
		storyImage.add(new PosImageIcon("ǥ��.PNG",0,0,800,635));

		ice.add(new PosImageIcon ("����.png",iceX[0],300,30,10));
		ice.add(new PosImageIcon ("����2.png",iceX[1],700,30,10));
		ice.add(new PosImageIcon ("����.png",iceX[2],300,30,10));
		ice.add(new PosImageIcon ("����2.png",iceX[3],700,30,10));

		cleanbutton(start);
		cleanbutton(skip);
		cleanbutton(Home);
		cleanbutton(RHome);
		cleanbutton(end);
		cleanbutton(home);
		cleanbutton(Rank);
		cleanbutton(MHome);
		cleanbutton(menuButton);


		buttonToggler(SKIP);//�ʱ�� ��ŵ���Դ´�

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

		poolTimer = new Timer(100,new poolListener());//������ �ð�
		Clock = new Timer(1000, new ClockListener());
		goAnime = new Timer(50,new TimerListener());
		endPanel.add(Home);
		Home.addActionListener(new HomeListener());
		frame.setVisible(true);

		mainPanel.addKeyListener(new DirectionListener());

		try {
			RankSound = JApplet.newAudioClip(new URL("file", "localhost","��ŷ.WAV"));
			HappySound = JApplet.newAudioClip(new URL("file", "localhost","���Ǿص�.WAV"));
			itemSound = JApplet.newAudioClip(new URL("file", "localhost","������ �Ҹ�.wav"));
			startSound = JApplet.newAudioClip(new URL("file", "localhost","ǥ��1.WAV"));
			eatSound = JApplet.newAudioClip(new URL("file", "localhost","����.WAV"));
			StorySound = JApplet.newAudioClip(new URL("file", "localhost","ǥ������.WAV"));
			SadSound = JApplet.newAudioClip(new URL("file", "localhost","���忣��1.WAV"));
			CrashSound = JApplet.newAudioClip(new URL("file", "localhost","��.wav"));
			WaterSound = JApplet.newAudioClip(new URL("file", "localhost","������ �Ҹ�.WAV"));
			MainSound = JApplet.newAudioClip(new URL("file", "localhost","main.WAV"));
		}
		catch(Exception e1){
			System.out.println("������� �ε� ����");
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
			if(e.getSource() == name){//�� ���͸� �ľ����� �̸��� ��ϵȴ�!!!!!!!
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
			g.drawString("�̸� : ",590,495);

			name.setBounds(630, 480, 100, 25);

			menuButton.setBounds(600, 570, 180, 70);

			start.setBounds(610, 520, 180, 70);
			mainPanel.setFocusable(true);
			mainPanel.requestFocus();
		}
	}
	// ��ư�� Ȱ�� ��Ȱ��ȭ�� ���� ��ƾ
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
			g.setFont(new Font("�Ÿ���",Font.CENTER_BASELINE,20));
			g.drawString("����! ����� �Ծ �ð��� �ٵɶ����� ", 15, 530);
			g.drawString("�������� ����Ű�� ����", 70, 570);
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

			g.setFont(new Font("�Ÿ���",Font.CENTER_BASELINE,25));
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


			g.drawImage(player.getImage(), x, y, this);//����� ���� ����
			if(energy <=0 || t<=0){
				finishGame();}


			for(int i=0;i<pool.size();i++){


				if(pool.get(i).collide(new Point(item.getpX()+item.width/2,item.getpY()+item.height/2),20))//��ֹ��� ����� ��ĥ��� ����� �Ⱥ��̰�
					item.pY +=1000;

				if(pool.get(i).collide(new Point(fish.getpX(),fish.getpY()),20))//��ֹ��� ����Ⱑ ��ĥ��� ��ֹ��� �Ⱥ��̰�
					fish.pY +=400;
				if(player.getImage() == origner.getImage()){
					if(pool.get(i).collide(new Point(player.getpX()+player.width/2,player.getpY()+player.height/2),80)){
						if(pool.get(i).getImage() == pool2Image.getImage() ){
							CrashSound.play();
							energy-=15;//�������� �ε�ġ�� -15
							score -=300;//�ε�ġ�� -300��
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
							energy-=10;//�칰�� �ε�ġ�� -10
							score -=200;//�ε�ġ�� -200��
							poolTimer.stop();
							pool.get(i).pY += 1000;

						}
						mainPanel.setFocusable(false);
						mainPanel.requestFocus(false);//�浹�� �÷��̾� �ȿ�����
						frame.repaint();
					}}
			}
			if(player.getImage() ==attackL.getImage()){
				b++;

				if(b%5==0){
					x-=10;

				}	

				if(b%30 == 0){
					mainPanel.setFocusable(true);//�̹����� ������ ���ƿ��� �ٽ� Ű ����
					mainPanel.requestFocus();	//�̹����� ������ ���ƿ��� �ٽ� Ű ����
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
					mainPanel.setFocusable(true);//�̹����� ������ ���ƿ��� �ٽ� Ű ����
					mainPanel.requestFocus();	//�̹����� ������ ���ƿ��� �ٽ� Ű ����

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
					mainPanel.setFocusable(true);//�̹����� ������ ���ƿ��� �ٽ� Ű ����
					mainPanel.requestFocus();//�̹����� ������ ���ƿ��� �ٽ� Ű ����
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
					mainPanel.setFocusable(true);//�̹����� ������ ���ƿ��� �ٽ� Ű ����
					mainPanel.requestFocus();//�̹����� ������ ���ƿ��� �ٽ� Ű ����
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
			buttonToggler(RHOME);//Ȩ���۵�
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
			panel.setLayer(mainPanel, 9);// mainPanel �� ������ ������ 
			mainPanel.setFocusable(true);// mainPanel �� ��Ŀ���� �� �ְ� ����
			mainPanel.requestFocus();// ��Ŀ�� �����ֱ� ���ϸ� �ȵ� Ű�԰����ش�
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
			Ranking.clear();//��ŷ�г��� �߳����� �ϱ����� �ʱ�ȭ�̴�
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
			mainPanel.setFocusable(true);// mainPanel �� ��Ŀ���� �� �ְ� ����
			mainPanel.requestFocus();// ��Ŀ�� �����ֱ� ���ϸ� �ȵ� 
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
				try{                                                       //txt���� �о����
					Scanner scan = new Scanner(file);
					while(scan.hasNext()){//���پ� �о �޾Ƶ��̴°�
						lineString = scan.nextLine();
						Scanner scanFile = new Scanner(lineString);
						Name = scanFile.next();
						totalScore = Integer.parseInt(scanFile.next());//�о�帮��

						Ranking.add(new Rank(Name, totalScore));
					}
					scan.close();
				}catch(Exception ex){System.out.println("������ �Ͼ���ϴ�.");}


				Ranking.add(new Rank( playerName,score));

				Collections.sort(Ranking,Collections.reverseOrder());//���粨���� ����ѵڿ� �迭����
				for(Rank rank: Ranking){
					Labellist.add(new JLabel(rankCount+"   "+rank.name+" ("+rank.score+"��)"));//3�������� �󺧿� ���
					if(rankCount==3) break;
					rankCount++;
				}
				for(JLabel label:Labellist){                                   //label ���
					label.setForeground(Color.LIGHT_GRAY);
					label.setFont(new Font("�Ÿ���",Font.CENTER_BASELINE,40));
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
					label.setLocation(x1,y1);       //label ��ġ
					RankPanel.add(label); //RankPanel�� Label�� �Է�
					cont++;//cont�� �ϳ��� ���� ��Ŵ�� ���� x1,y1��ǥ�� �ٲ� �ű⿡ ���ο� ���� ���
				}
				try {                                                        //txt���Ͽ� �Է�
					FileWriter fileWriter = new FileWriter(file,true); 
					fileWriter.write(playerName+" "+Integer.toString(score)+"\n");
					fileWriter.flush();   //���Ͽ� �־��ش�.     
					fileWriter.close();   //������ �ݾ��ش�.
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
		int a =0;//Ǯ �����ʴ� ó�� 100���� �۵� �̶����� �ϳ��� ī��Ʈ���ش� �� �ɸ��� �ʰ� ª�������� �����ӵ��� ��������
		int a1,a2;//0~1������ �������� �޾��� ���̴�
		public void actionPerformed(ActionEvent e){

			if(a%20==0){//a�� 20���� ���������� �������� ������ ������ ��ֹ��� ����
				while(true){//while�� ���� ������ r��r1,r2,r3 �� �Ȱ�ġ�� �Ҷ��
					int r = (int)(Math.random()*4);
					int r1 = (int)(Math.random()*4);
					int r2 = (int)(Math.random()*4);
					int r3 = (int)(Math.random()*4);
					int ch = (int )(Math.random()*16);
					if(t<=20){
						if(r!=r1&&r!=r2&&r!=r3&&r1!=r2&&r1!=r3&&r2!=r3){
							if(ch%2==0){
								pool.add(new PosImageIcon("����.PNG",poolX[r],380,40,20));
								pool.add(new PosImageIcon("������.PNG",poolX[r2],300,40,5));
								pool.add(new PosImageIcon("������.PNG",poolX[r3],340,50,5));
								break;
							}
							else{
								pool.add(new PosImageIcon("������.PNG",poolX[r],300,40,5));
								pool.add(new PosImageIcon("������.PNG",poolX[r1],350,40,20));
								break;
							}}
					}
					if(t>20){//�̷����ؼ� �� 4���� x��ǥ�� �ִµ� �װ��� ��� �����ư��鼭 ������ �Ѵ� ��������
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
								pool.add(new PosImageIcon("����.PNG",poolX[a1],300,40,20));
								pool.add(new PosImageIcon("������.PNG",poolX[a2],350,40,5));
								break;
							}
							else{
								pool.add(new PosImageIcon("������.PNG",poolX[a1],300,50,5));
								pool.add(new PosImageIcon("������.PNG",poolX[a2],350,40,5));
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

			if(ti<6){//�̷����ؼ� 7��°����� ���������ʰ� ������ �Ȼ���� �Ҷ��迭 0�� �����ִϱ� �ð����� ���� �迭�� �̹����� �迭 0�� �̹����� ����
				storyImage.get(0).setImage(storyImage.get(ti+1).getImage());
				ti++;
			}
			if(ti ==6){
				buttonToggler(START+MENU);
				panel.setLayer(startPanel, 7);
				storyTimer.stop();//�� ������ ������ �� Ÿ�̸Ӵ� ����

			}
			frame.repaint();
		}
	} 
} 