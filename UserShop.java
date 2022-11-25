import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserShop extends JFrame{
	private int coin;
	private JLabel coinLabel;
	private DBA db=new DBA();
	private User user;
	private int price;
	
	public UserShop(User u) {
		user = u;
		setTitle("상점");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container usershoppane = getContentPane();
		
		
		usershoppane.setLayout(null);
			
		// 폰트 설정
//		Font font = new Font("돋움", Font.BOLD, 20);
		
		
		// 이미지
		// 휴가권 - 1
		ImageIcon vacationImage = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
		
		// 반차 - 0.5
		ImageIcon halfvationimage = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
		
		//랜덤박스
		ImageIcon randomboxImage = new ImageIcon(this.getClass().getResource("/선물상자.jpg"));
		
		// 타이틀 라벨 생성
		JLabel titleJLabel = new JLabel("사용자 - 상점");
		SetFont(titleJLabel, 30);
		titleJLabel.setBounds(400, 5, 200, 80);
		// 추가
		usershoppane.add(titleJLabel);
		
		
		// 이미지 라벨 생성 & 이미지 적용 - setIcon
		JLabel randomboxLabel = new JLabel(randomboxImage, JLabel.CENTER);
		JLabel vacationLabel = new JLabel(vacationImage, JLabel.CENTER);
		JLabel halfvactionJLabel = new JLabel(halfvationimage, JLabel.CENTER);
		// 추가
		usershoppane.add(vacationLabel);
		usershoppane.add(halfvactionJLabel);
		usershoppane.add(randomboxLabel);
		
		// 이미지 라벨 설정 setBounds(x, y, w, h) margin - 40
		vacationLabel.setBounds(90, 80, 200, 400);
		halfvactionJLabel.setBounds(320, 80, 200, 400);		
		randomboxLabel.setBounds(550, 80, 200, 400);
		
		// 물품가격 라벨 생성
		JLabel vanameLabel = new JLabel("포인트 : 36점");
		JLabel halfnameLabel = new JLabel("포인트 : 18점");
		JLabel rannameLabel = new JLabel("포인트 : 1점");
		// 물품 라벨 설정
		vanameLabel.setBounds(120, 555, 150, 50);
		SetFont(vanameLabel, 20);
		
		halfnameLabel.setBounds(350, 555, 150, 50);
		SetFont(halfnameLabel, 20);
		
		rannameLabel.setBounds(590, 555, 150, 50);
		SetFont(rannameLabel, 20);
		//추가
		usershoppane.add(vanameLabel);
		usershoppane.add(halfnameLabel);
		usershoppane.add(rannameLabel);
		
		
		// 포인트 현황 라벨
		coinLabel = new JLabel("포인트 : " + u.getReward());
		// 포인트 라벨 설정
		coinLabel.setBounds(790, 100, 200,100);
		SetFont(coinLabel, 20);
		// 추가
		usershoppane.add(coinLabel);

		
		// 구매 버튼
		JButton vacationButton= new JButton("휴가 - 구매");
		JButton halfvacationButton = new JButton("반차 - 구매");
		JButton randomBoxButton = new JButton("뽑기 - 구매");
		
		
//		vacationButton.setContentAreaFilled(false); - 배경 유무
		// 구매 버튼 설정
		vacationButton.setBounds(110, 500, 150, 50);
		SetBack(vacationButton);
		SetDefBut(vacationButton, true, false, false);

		halfvacationButton.setBounds(340, 500, 150, 50);
		SetBack(halfvacationButton);
		SetDefBut(halfvacationButton, true, false, false);

		randomBoxButton.setBounds(575, 500, 150, 50);
		SetBack(randomBoxButton);
		SetDefBut(randomBoxButton, true, false, false);

		
		// 구매 버튼 이벤트 설정
		ShowBuyMessage vacationBuyMessage = new ShowBuyMessage();
		vacationBuyMessage.SetPrice(36);
		vacationButton.addActionListener(vacationBuyMessage);
		
		ShowBuyMessage halfvacationBuyMessage = new ShowBuyMessage();
		halfvacationBuyMessage.SetPrice(18);
		halfvacationButton.addActionListener(halfvacationBuyMessage);
		
		ShowBuyMessage randomBuyMessage = new ShowBuyMessage();
		randomBuyMessage.SetPrice(1);
		randomBoxButton.addActionListener(randomBuyMessage);
		
		// 구매 버튼 추가
		usershoppane.add(vacationButton);
		usershoppane.add(halfvacationButton);
		usershoppane.add(randomBoxButton);
		
		
		// 닫기(확인) 버튼 생성 & 설정 & 이벤트
		JButton closeButton = new JButton("닫기");
		SetBack(closeButton);
		SetFont(closeButton, 14);
		SetDefBut(closeButton, true, false, false);
//		closeButton.setForeground(Color.white); // 글자 색상
		closeButton.setBounds(880, 600, 80, 40);
		closeButton.addActionListener(new CloseFrame());
		// 닫기 버튼 추가
		usershoppane.add(closeButton);

		
		// 폰트설정
		SetFont(vacationButton, 20);
		SetFont(halfvacationButton, 20);
		SetFont(randomBoxButton, 20);
		
		
		// 창 사이즈 및 보임 여부
		setSize(1000, 700);
		setVisible(true);
		
		// 창이 가운데 나오게 함.
		setLocationRelativeTo(null);
		
		// 창 크기 변환x
		setResizable(false);
		
		
		
	}

	// 상점 - 메시지 박스 이벤트 처리
	class ShowBuyMessage implements ActionListener {
		int price;
		public void SetPrice(int price) {
			this.price = price;
			
		}
		
		// 구매 조건
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			coin = user.getReward();
			int buy = 0;
			int select;
			
			try {
				String check = JOptionPane.showInputDialog(null, "몇개 구매하시겠습니까?", "구매개수", JOptionPane.NO_OPTION);
				// 공백예외
				if(check.equals("")) {
					JOptionPane.showMessageDialog(null, "공백입력불가 / 숫자를 입력해주세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
					buy = -1;
				}
				
				else {					
					buy = Integer.parseInt(check);
				}
				
			}
			// 문자열 예외
			catch (NumberFormatException ex) {
				System.out.println(ex);
				System.out.println(buy);
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
				buy = -1;
			}
			
			// cancel
			catch (Exception e2) {
				System.out.println("다른 예외");
				System.out.println(e2);
				buy = -1;

			}
			
			// 구매
			if (buy >= 1) { 
				if (coin < price*buy) {
					JOptionPane.showMessageDialog(null, "보유 포인트가 부족합니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					coin -= price*buy;
					user.setReward(coin);
					
					JOptionPane.showMessageDialog(null, "구매되었습니다. \n현재 보유 코인 : " + coin, "구매 성공", JOptionPane.INFORMATION_MESSAGE);
					db.updatecoin(user.getId(), user.getReward());
					coinLabel.setText("포인트 : " + user.getReward());
				
					// 카드 뒤집기 이벤트
					if(button.getText().equals("뽑기 - 구매")) {
						new RandomChoice(buy);
					}
				
				}
				
			}
			else if (buy == 0) {
				System.out.println(buy);
				JOptionPane.showMessageDialog(null, "0개는 구매할 수 없습니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	// 닫기 - 이벤트 처리
	class CloseFrame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
	// 폰트 설정
	public void SetFont(JButton button, int size) {
		Font font = new Font("돋움", Font.BOLD, size);
		button.setFont(font);
	}
	
	public void SetFont(JLabel label, int size) {
		Font font = new Font("궁서", Font.BOLD, size);
		label.setFont(font);
	}

	// 버튼 설정 - 기본
	public void SetBack(JButton button) {
		button.setBackground(Color.black);
		button.setForeground(Color.white);
	}
	
	public void SetDefBut(JButton button, Boolean area, Boolean border, Boolean focous) {
		button.setContentAreaFilled(area);
		button.setBorderPainted(border);
		button.setFocusPainted(focous);
	}
	
	public void SetLocation() {
		
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setReward(20);
		new UserShop(user);
	}
}
