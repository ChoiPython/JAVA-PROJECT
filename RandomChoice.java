import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class RandomChoice extends JFrame{
	ImageIcon img = new ImageIcon(this.getClass().getResource("/일반뽑기버튼.png"));
	ImageIcon imgchange = new ImageIcon(this.getClass().getResource("/뽑기선택.jpg"));
	DBA dba = new DBA();
	int[] shopdata = dba.GetShop();
	int percent = shopdata[0]; // 뽑기 확률
	int[] randomnum = new int[percent];	// 당첨될 4개 숫자
	int count; // 뽑기 횟수
	JButton[] button = new JButton[100];
	String[] bttext = new String[100];
	Container randchoicepane;
	GetItem get;
	int id;
	
	
	public RandomChoice(int count, int id) {
		this.id = id;
		this.count = count;
		setTitle("뽑기창");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		randchoicepane = getContentPane();
		// 그리드 레이아웃 사용
		randchoicepane.setLayout(new GridLayout(10, 10));
		
		// 랜덤 수 지정
		SetRandom();
		for(int i=0; i<bttext.length; i++) {
			bttext[i] = Integer.toString(i);
		}
		
		// 버튼 생성 & set
		for(int i=0; i < 100; i++) {
			button[i] = new JButton(bttext[i]);
			button[i].setText(Integer.toString(i));
			button[i].setSize(70,50);
			button[i].setIcon(img);
			button[i].setRolloverIcon(imgchange); // 마우스 선택(포커스)시 이미지 변경
			get = new GetItem();	// 이벤트 클래스 생성
			get.GetButNum(i);				// 고유 숫자 설정
			button[i].addActionListener(get);	// 이벤트 적용
			randchoicepane.add(button[i]);		// 버튼 추가
		}
		

		// 프레임 set
		setSize(700, 500);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	// 경품 당첨
	class GetItem implements ActionListener {
		int num;
		
		// 고유 번호 받아오기 0~99 - 100개
		public void GetButNum(int num) {
			this.num = num;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String[] text = new String[percent];
			JButton newbutton = (JButton) e.getSource();
			int good=0; // 당첨여부

//			String [] winnum = new String[percent];
			
			for(int i=0; i<percent; i++) {
				text[i] = Integer.toString(randomnum[i]);
				System.out.println(text[i]);
			}
			
			for(int i=0; i<percent; i++) {
				if(newbutton.getText().equals(text[i])) {
					good = 1;
				}
			}
			// 당첨
			if (good == 1) {
				count -= 1;
				System.out.println("당첨");
				JOptionPane.showMessageDialog(newbutton, "당첨!!");
				dba.Buyhalf(id, 1);

				// count =0이면 뽑기 종료
				if (count != 0) {

					new RandomChoice(count, id);
				} else {
				}
			}
			
			// 꽝
			else {
				// 현재 기회
				count-=1;
				if(count >= 1) {
					System.out.println("당첨X");
					JOptionPane.showMessageDialog(newbutton, "꽝..  잔여횟수 : " + count);
					new RandomChoice(count, id);
				}
				
				else {
					System.out.println("당첨X");
					JOptionPane.showMessageDialog(newbutton, "다음기회에...");

				}
			}
			ArrayList<String> winnum = new ArrayList<String>(Arrays.asList(text));
;			// 결과
			for(int i=0; i<100; i++) {

				if (winnum.contains(button[i].getText())) {
					button[i].setIcon(null);
					button[i].setBackground(Color.red);
					button[i].setText("당첨");
				}

				else {
					button[i].setRolloverIcon(null);
					button[i].setIcon(null);
					button[i].setText("꽝");

				}
				

				button[i].setEnabled(false);	// 버튼 비활성화
			}
			
			newbutton.setBackground(Color.white); // 내 선택
			setLocation(200, 100);
				
		}
	}
	
	public class OverEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	
	// 랜덤 수 설정
	public void SetRandom() {
		// 랜덤 수 지정
		for(int i=0; i < randomnum.length; i++) {
			randomnum[i] = (int) (Math.random() * 99);
		}
		System.out.println(Arrays.toString(randomnum));
		
		// 모두 다른지 확인
		for(int i=1; i < randomnum.length; i++) {
			int num = randomnum[i-1];
			int[] copy = Arrays.copyOfRange(randomnum, i, percent);	// 배열 지정 범위 복사

			// 로직
			while(Arrays.stream(copy).anyMatch(a -> a == num)) {
				count += 1;
				System.out.print("동일 값 발생 : ");

				// 다시 바꿔주기
				for(int j = i; j <= percent-1; j++) {
					randomnum[j] = (int) (Math.random() * 99);
					copy = Arrays.copyOfRange(randomnum, i, percent);
				}
				System.out.println(Arrays.toString(randomnum));
				
			}
			

		}
	}
	
	
	// 버튼 설정 공통 - 디자인
	public void ButtonSet(JButton button) {
		
		
	}
	
	
	
	
	public static void main(String[] args) {
//		for(int i=0; i<10; i ++) {
//			
//		}
		
//		new RandomChoice();

	}

}
