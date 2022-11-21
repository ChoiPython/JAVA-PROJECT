import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;


public class RandomChoice extends JFrame{
	ImageIcon img = new ImageIcon(this.getClass().getResource("/일반뽑기버튼.png"));
	ImageIcon imgchange = new ImageIcon(this.getClass().getResource("/뽑기선택.jpg"));
	int[] randomnum = {0, 0, 0, 0};	// 당첨될 4개 숫자
	
	
	public RandomChoice() {
		setTitle("뽑기창");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container randchoicepane = getContentPane();
		// 그리드 레이아웃 사용
		randchoicepane.setLayout(new GridLayout(10, 10));
		
		// 랜덤 수 지정
		SetRandom();
		
		// 버튼 생성 & set
		for(int i=0; i < 100; i++) {
			JButton button = new JButton("" + i);
			button.setSize(50,60);
			button.setIcon(img);
			button.setRolloverIcon(imgchange); // 마우스 선택(포커스)시 이미지 변경
			GetItem get = new GetItem();	// 이벤트 클래스 생성
			get.GetButNum(i);				// 고유 숫자 설정
			button.addActionListener(get);	// 이벤트 적용
			randchoicepane.add(button);		// 버튼 추가
		}
		

		// 프레임 set
		setSize(600, 500);
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
			
			String[] text = new String[4];
			JButton button = (JButton) e.getSource();
			System.out.println(button.getText());
			
			for(int i=0; i<4; i++) {
				text[i] = Integer.toString(randomnum[i]);
			}
			
			// 당첨
			if(button.getText().equals(text[0]) || button.getText().equals(text[1]) || button.getText().equals(text[2]) || button.getText().equals(text[3])) {
				System.out.println("당첨");
			}

			else {
				// 현재 기회 한번
//				JOptionPane.showMessageDialog(button, "다음기회에...");
				System.out.println("당첨X");


					
				}
				
			
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
			int[] copy = Arrays.copyOfRange(randomnum, i, 4);	// 배열 지정 범위 복사
			
			// 로직
			while(Arrays.stream(copy).anyMatch(a -> a == num)) {
				System.out.print("동일 값 발생 : ");

				// 다시 바꿔주기
				for(int j = i; j <= 3; j++) {
					randomnum[j] = (int) (Math.random() * 99);
					copy = Arrays.copyOfRange(randomnum, i, 5);
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
		new RandomChoice();

	}

}
