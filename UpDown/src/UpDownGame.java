import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;








public class UpDownGame extends JPanel {

	//this = new GamePanel();

	int minLimit = 1,maxLimit = 100;
	int Limit = 0;

	public enum UserTurn{user1, user2, user3};
	private JPanel labelPanel = new JPanel();	// 상태 라벨 점수확인
	public JPanel gamePanel = new JPanel();	// 이 안에는 힌트와 정답버튼 게임 이미지뜨는 패널  정답패널  유저 보이는거
	private JLabel user1Count, user2Count, turnLabel, user1Label, user2Label,playLabel;
	private JLabel LimitLabel;
	JLabel SGround = new JLabel();
	JLabel MGround = new JLabel();
	JLabel LGround = new JLabel();
	public JPanel panel, countSet, turnLabelPanel, menuPanel,userPanel,LabelPanel;

	public JFrame answer = new JFrame(); //정답 맞추는 프레임  정답 버튼 부르면 뜸

	public JTextField outanswer = new JTextField();			// 정답을 작성하는 곳


	private JButton quit;						// Quit : 끝내기 버튼
	private JButton start;						// 랭킹등록 버튼
	private JButton answerb;

	public JTextField user1,user2;
	String my, you;
	String mLimit,MLimit;
	private Font font;

	private boolean turnCheck=false;
	private boolean startCheck=false;
	public boolean userCheck = false;


	private ImageIcon User1Icon, User2Icon,User1Paly,User2Paly,playgroundL,playground,playgroundS,playstand,UpIcon,DownIcon;





	int Useranswer,user2answer;

	String UserAnswer;
	int answercheck;

	ObjectInputStream reader;	// 수신용 스트림
	ObjectOutputStream writer;	// 송신용 스트림

	public UpDownGame(){

		panel = new JPanel();
		countSet = new JPanel();
		turnLabelPanel = new JPanel();
		menuPanel = new JPanel();
		userPanel = new JPanel();
		LabelPanel = new JPanel();

		quit = new JButton("Quit");
		quit.setBackground(new Color(215,235,209));
		start = new JButton("Start");
		start.setBackground(new Color(229,238,206));
		answerb = new JButton("answer");
		answerb.setBackground(new Color(229,238,206));
		
		answerb.addActionListener(new AnswerButtonListener());
		quit.addActionListener(new QuitListener());
		User1Icon = new ImageIcon("images/User1count.PNG");		// 이미지 아이콘 객체생성
		User2Icon = new ImageIcon("images/User2count.PNG");


		User1Paly = new ImageIcon("images/User1.PNG");		// 이게 유저들의 뒷모습
		User2Paly = new ImageIcon("images/User2.PNG");


		playgroundL  = new ImageIcon("images/배경.PNG");
		playgroundS  = new ImageIcon("images/작은배경.PNG");
		playground  = new ImageIcon("images/회전배경.PNG");

		playstand = new ImageIcon("images/standby.PNG");	
		UpIcon  = new ImageIcon("images/UP.PNG");
		DownIcon  = new ImageIcon("images/Down.PNG");

		//이건 유저의 턴과 수를 세어주는 라벨
		user1Count = new JLabel(User1Icon, SwingConstants.CENTER);
		user1Count.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		user2Count = new JLabel(User2Icon, SwingConstants.CENTER);
		user2Count.setHorizontalTextPosition(SwingConstants.HORIZONTAL);


		//이건 유저의 모습들과함께 순서를 보여준다 1 2 3은 순서를 보여주는것
		user1Label = new JLabel(User1Paly, SwingConstants.CENTER);
		user1Label.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		user2Label = new JLabel(User2Paly, SwingConstants.CENTER);
		user2Label.setHorizontalTextPosition(SwingConstants.HORIZONTAL);


		//이게 정답 버튼을 누르고 정답을 치면 나오는 이미지라벨
		playLabel = new JLabel("",playstand, SwingConstants.CENTER);
		playLabel.setHorizontalTextPosition(SwingConstants.HORIZONTAL);

		turnLabel = new JLabel("Turn ", User1Icon, SwingConstants.CENTER);
		turnLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		user1 = new JTextField();
		user2 = new JTextField();

		mLimit = new String(Integer.toString(minLimit));
		MLimit = new String(Integer.toString(maxLimit));
		LimitLabel = new JLabel("한계 : "+mLimit+ "~~"+ MLimit);

	
		//이게 배경화면에서 돌아댕기는 이미지
		
		
				LGround = new JLabel(playgroundL);
			
				MGround = new JLabel(playground);
			 SGround = new JLabel(playgroundS);
		
		//폰트설정
		font = new Font("Showcard Gothic", Font.PLAIN, 25);
		turnLabel.setFont(font);
		font = new Font("MD아롱체", Font.PLAIN, 25);
		user1.setFont(font);
		user2.setFont(font);

		user1.setEditable(false);
		user2.setEditable(false);



		countSet.setLayout(null);

		user1.setBounds(20,15,70,40);
		user1Count.setBounds(90,10,40,50);
		user2.setBounds(130,15,70,40);
		user2Count.setBounds(200,10,40,50);

		turnLabel.setBounds(360,5,120,60);

		user1Label.setBounds(30, 350, 150, 150);
		user2Label.setBounds(140, 350, 150, 150);


		user1.setHorizontalAlignment(JTextField.CENTER);
		user2.setHorizontalAlignment(JTextField.CENTER);

		font = new Font("Bauhaus 93", Font.PLAIN, 20);
		user1Count.setFont(font);
		user2Count.setFont(font);


		countSet.add(user1);
		countSet.add(user1Count);
		countSet.add(user2);
		countSet.add(user2Count);


		turnLabelPanel.add(turnLabel);
		countSet.add(turnLabelPanel);


		userPanel.add(BorderLayout.SOUTH,MGround);
		userPanel.add(BorderLayout.CENTER,user1Label);
		userPanel.add(BorderLayout.EAST,user2Label);




		start.addActionListener(new StartButtonListener());
		countSet.setLayout(new BoxLayout(countSet, BoxLayout.X_AXIS));
		labelPanel.add(BorderLayout.WEST,countSet);


		menuPanel.setLayout(new GridLayout(1,4));// 고정된 크기를 가진 여백 고정 범위 첨가.	
		menuPanel.add(start);

		menuPanel.add(quit);
		menuPanel.add(answerb);

		menuPanel.add(LimitLabel);
		menuPanel.setBackground(Color.white);

		panel.add(menuPanel);
		panel.add(userPanel);



		countSet.setPreferredSize(new Dimension(55,55));
		countSet.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));



		add(LGround);		
		add(SGround);		
LGround.setBounds(50, 100, 80, 50);
SGround.setBounds(400, 180, 80, 50);

		LabelPanel.add(playLabel);
		LabelPanel.setBackground(Color.white);


		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, countSet);
		add(BorderLayout.CENTER,LabelPanel);
		add(BorderLayout.SOUTH, panel);//이거 나중에 그리드 레이아웃으로 변경



		user1Count.setBackground(Color.white);
		user2Count.setBackground(Color.white);
		user1.setBackground(Color.white);
		user2.setBackground(Color.WHITE);
		turnLabel.setBackground(Color.WHITE);
		turnLabelPanel.setBackground(Color.WHITE);
		userPanel.setBackground(Color.WHITE);
		panel.setBackground(Color.white);
		countSet.setBackground(Color.WHITE);

		this.setBackground(Color.WHITE);


	}




	public void setStartCheck(boolean x)
	{
		startCheck = x;
	}
	public void setTurnCheck(boolean x)
	{
		turnCheck = x;
	}
	private class QuitListener implements ActionListener 	// 종료 버튼(모든 프레임과 창이  강제로 닫힌다.)
	{    
		public void actionPerformed(ActionEvent event) 
		{ 	
			System.exit(100);			// 클라이언트 완전 종료 
		}	



	}

	public void gameSet(String my, String you,int Answer){

		Limit = 0;
		minLimit =1;
		maxLimit = 100;
		mLimit = Integer.toString(minLimit); 
		MLimit = Integer.toString(maxLimit);  
		LimitLabel.setText("한계 : "+mLimit+ "~~"+ MLimit);
		Useranswer = Answer;
		turnLabel.setIcon(User1Icon);
	
		//정답프린트
		System.out.printf("정답  : %d    \n",Useranswer);


		if(userCheck) {
			user1.setText(my); 
			user2.setText(you); 
		}
		if(!userCheck) {
			user1.setText(you); 
			user2.setText(my);
		}

		if(this.turnCheck){ countSet.setBackground(new Color(100,200,40));}
		else { countSet.setBackground(Color.red);}
	}

	public class StartButtonListener implements ActionListener {
		 Random r =  new Random();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!startCheck){ 

				try {

					startCheck = true;
					turnCheck = true;
					userCheck = true;
					Useranswer = (int)(r.nextInt(100)+1);
					if(Useranswer <=0)
						Useranswer =1;
					if(Useranswer >=100)
						Useranswer =100;
					writer.writeObject(new UpDownMessage(UpDownMessage.MsgType.GAME_START, userCheck,Useranswer));
					writer.flush();

				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
					ex.printStackTrace();
				}
			}
			else  JOptionPane.showMessageDialog(null, "게임이 진행중입니다.");
		}
	}
	public class AnswerButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(turnCheck){
				UserAnswer = JOptionPane.showInputDialog("정답을 입력하세요");

				if(isStringDouble(UserAnswer)){

					answercheck = Integer.parseInt( UserAnswer );
					if(LimitCheck(answercheck)){
						try {					
							writer.writeObject(new UpDownMessage(UpDownMessage.MsgType.GAME_INFO,  answercheck,minLimit,maxLimit));
							writer.flush();
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
							ex.printStackTrace();
						}
					}
					else JOptionPane.showMessageDialog(null, " 정답을 한계 사이의 수를 입력하세요.");
				}
				else{
					UserAnswer = JOptionPane.showInputDialog("정답을 숫자로 다시 입력하세요");
					if(isStringDouble(UserAnswer)){

						answercheck = Integer.parseInt( UserAnswer );
						if(LimitCheck(answercheck)){
							try {
								writer.writeObject(new UpDownMessage(UpDownMessage.MsgType.GAME_INFO,  answercheck,minLimit,maxLimit));
								writer.flush();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
								ex.printStackTrace();
							}
						}
						else JOptionPane.showMessageDialog(null, " 정답을 한계 사이의 수를 입력하세요.");
					}
					else JOptionPane.showMessageDialog(null, " 정답을 숫자로 입력하지 않았습니다.\n        (재시도 하세요.)");

				}

			}
			else  JOptionPane.showMessageDialog(null, " 정답을 입력할수 없습니다.");
		}
	}
	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public boolean LimitCheck(int AN){
		if(Limit == 0){
			if(minLimit <= AN && AN <= maxLimit) {


				LimitAdjustment(AN);
				return true;		

			}
			else return false;
		}
		else{
			if(minLimit < AN && AN < maxLimit) {


				LimitAdjustment(AN);
				return true;		

			}
			if(minLimit == AN)
				return false;
			else if(maxLimit == AN)
				return false;
			else return false;
		}

	}
	public void LimitAdjustment(int AN){


		if(AN>Useranswer) maxLimit = AN-1;
		else minLimit = AN+1;
		mLimit = (Integer.toString(minLimit));
		MLimit = (Integer.toString(maxLimit));
		LimitLabel.setText("한계 : "+mLimit+ "~~"+ MLimit);
	}

	public void GameOverCheck(int i,int min,int max){
		int check;
		boolean overCheck = false;

		minLimit = min;
		maxLimit = max;

		if(i==Useranswer){ 
			overCheck=true;
			if(turnLabel.getIcon() == User1Icon)
				JOptionPane.showMessageDialog(null, user1.getText()+"님  WIN !!"); 
			else 
				JOptionPane.showMessageDialog(null, user2.getText()+"님  WIN !!"); 
		}


		else if(i>Useranswer)
		{
			overCheck=false;
			check = 1;
			BoardSet(check);
		}

		else if(i<Useranswer)
		{
			overCheck=false;
			check = 2;
			BoardSet(check);
		}
		if(overCheck)
		{
			
			playLabel.setIcon(playstand);
			LGround.setIcon(playgroundS);
			SGround.setIcon(playgroundL);
			startCheck = false;
			turnCheck = false;
			userCheck = false;

		}


	}

	public void BoardSet(int check){
		turnCheck=!turnCheck;
		mLimit = Integer.toString(minLimit); 
		MLimit = Integer.toString(maxLimit);  
		LimitLabel.setText("한계 : "+mLimit+ "~~"+ MLimit);
		LGround.setIcon(playgroundS);
		SGround.setIcon(playgroundL);
		if(this.turnCheck){countSet.setBackground(new Color(100,200,40));}
		else { countSet.setBackground(Color.red);}

		if(user1.getBackground().equals(Color.GREEN)) System.out.println("체인지");//user1.setBackground(Color.RED);
		if(user1.getBackground().equals(Color.RED)) user1.setBackground(Color.GREEN);
		if(user2.getBackground().equals(Color.GREEN)) user1.setBackground(Color.RED);
		if(user2.getBackground().equals(Color.RED)) user1.setBackground(Color.GREEN);

		if(check == 1){

			playLabel.setIcon(DownIcon);
			check =0;
		}

		else if(check == 2){

			playLabel.setIcon(UpIcon);
			check =0;
		}

		if(turnLabel.getIcon() == User1Icon)
			turnLabel.setIcon(User2Icon);
		else 	turnLabel.setIcon(User1Icon);

	}

}


