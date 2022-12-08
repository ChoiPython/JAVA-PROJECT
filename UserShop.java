import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserShop extends JFrame{
	private int coin;
	private JLabel coinLabel;
	private DBA db=new DBA();
	private User user;
	private Container usershoppane;
	int[] shopdata = db.GetShop();
	int rprice = shopdata[1];	// 뽑기 가격
	private int hprice = shopdata[2]; // 반차 가격 - db로 관리자에서 수정가능하게?
	private JButton vacationButton;
	private JButton halfvacationButton;
	private JButton randomBoxButton;
	
	public UserShop(User u) {
		user = u;
		setTitle("상점");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		usershoppane = getContentPane();
		usershoppane.setBackground(Color.LIGHT_GRAY);
		
		usershoppane.setLayout(null);
			
		// 폰트 설정
//		Font font = new Font("돋움", Font.BOLD, 20);
		
		
		// 이미지
		// 휴가권 - 1
		ImageIcon vicon = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
		Image getvimg = vicon.getImage();
		Image vimg = getvimg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon vacationImage = new ImageIcon(vimg);
		
		// 반차 - 0.5
		ImageIcon hicon = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
		Image gethimg = vicon.getImage();
		Image himg = getvimg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon halfvationimage = new ImageIcon(himg);
		
		//랜덤박스 
		ImageIcon ricon = new ImageIcon(this.getClass().getResource("/선물상자.jpg"));
		Image getrimg = ricon.getImage();
		Image rimg = getrimg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon randomboxImage = new ImageIcon(rimg);
		
		
		// 타이틀 라벨 생성
		JLabel titleJLabel = new JLabel("사용자 - 상점");
		SetFont(titleJLabel, 30);
		titleJLabel.setBounds(300, 5, 200, 80);
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
		vacationLabel.setBounds(40, 80, 200, 200);
		halfvactionJLabel.setBounds(260, 80, 200, 200);		
		randomboxLabel.setBounds(480, 80, 200, 200);
		
		// 물품가격 라벨 생성
		JLabel vanameLabel = new JLabel("포인트 : " + hprice * 2 + "점");
		JLabel halfnameLabel = new JLabel("포인트 : " + hprice + "점");
		JLabel rannameLabel = new JLabel("포인트 :" + rprice + "점");
		// 물품 라벨  폰트 & 크기 & 위치 설정
		vanameLabel.setBounds(80, 355, 150, 50);
		SetFont(vanameLabel, 20);
		
		halfnameLabel.setBounds(300, 355, 150, 50);
		SetFont(halfnameLabel, 20);
		
		rannameLabel.setBounds(520, 355, 150, 50);
		SetFont(rannameLabel, 20);
		//추가
		usershoppane.add(vanameLabel);
		usershoppane.add(halfnameLabel);
		usershoppane.add(rannameLabel);
		
		
		// 포인트 현황 라벨
		coinLabel = new JLabel("포인트 : " + user.getPoint());
		// 포인트 라벨 설정
		coinLabel.setBounds(700, 100, 150,100);
		SetFont(coinLabel, 20);
		// 추가
		usershoppane.add(coinLabel);
		
		
		// 구매 버튼
		vacationButton= new JButton();
		halfvacationButton = new JButton();
		randomBoxButton = new JButton();
		
		
//		vacationButton.setContentAreaFilled(false); - 배경 유무
		// 구매 버튼 설정
		vacationButton.setBounds(65, 300, 150, 50);
		SetBack(vacationButton);
		SetDefBut(vacationButton, true, false, false);

		halfvacationButton.setBounds(285, 300, 150, 50);
		SetBack(halfvacationButton);
		SetDefBut(halfvacationButton, true, false, false);

		randomBoxButton.setBounds(505, 300, 150, 50);
		SetBack(randomBoxButton);
		SetDefBut(randomBoxButton, true, false, false);
		
		// 버튼 이미지 설정
		ImageIcon buyimg = new ImageIcon(this.getClass().getResource("/구매버튼이미지.png"));
		Image getbuyimg = buyimg.getImage();
		getbuyimg = getbuyimg.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		ImageIcon setbuyimg = new ImageIcon(getbuyimg);
		vacationButton.setIcon(setbuyimg);
		vacationButton.setContentAreaFilled(false);
		
		halfvacationButton.setIcon(setbuyimg);
		halfvacationButton.setContentAreaFilled(false);
		
		randomBoxButton.setIcon(setbuyimg);
		randomBoxButton.setContentAreaFilled(false);
		
		// 구매 버튼 이벤트 설정
		// 휴가
		BuyVac vacationBuy = new BuyVac();
		vacationBuy.SetPrice(hprice*2);
		vacationButton.addActionListener(vacationBuy);
		// 반차
		BuyVac halfvacationBuy = new BuyVac();
		halfvacationBuy.SetPrice(hprice);
		halfvacationButton.addActionListener(halfvacationBuy);
		// 랜덤뽑기
		BuyRandom randomBuy = new BuyRandom();
		randomBuy.SetPrice(1);
		randomBoxButton.addActionListener(randomBuy);
		
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
		closeButton.setBounds(740, 400, 80, 40);
		closeButton.addActionListener(new CloseFrame());
		// 닫기 버튼 추가
		usershoppane.add(closeButton);
		
		// 닫기 버튼 이미지
//		ImageIcon closeimg = new ImageIcon(this.getClass().getResource("/닫기버튼.png"));
//		Image getcloseimg = closeimg.getImage();
//		getcloseimg = getcloseimg.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
//		ImageIcon setcloseimg = new ImageIcon(getcloseimg);
//		closeButton.setIcon(setcloseimg);
//		closeButton.setContentAreaFilled(false);
		

		
		// 폰트설정
		SetFont(vacationButton, 20);
		SetFont(halfvacationButton, 20);
		SetFont(randomBoxButton, 20);
		
		
		// 창 사이즈 및 보임 여부
		setSize(860, 500);
		setVisible(true);
		
		// 창이 가운데 나오게 함.
		setLocationRelativeTo(null);
		
		// 창 크기 변환x
		setResizable(false);
		
		
		
	}

	// 상점 - 뽑기 이벤트
	class BuyRandom implements ActionListener {
		int price;
		public void SetPrice(int price) {
			this.price = price;
			
		}
		
		// 구매 조건
		@Override
		public void actionPerformed(ActionEvent e) {
			coin = user.getPoint();
			int buy = 0;	// 구매 개수
			int select;
			
			try {
				String check = JOptionPane.showInputDialog(usershoppane, "몇개 구매하시겠습니까?", "뽑기 구매", JOptionPane.NO_OPTION);
				// 공백예외
				if(check.equals("")) {
					JOptionPane.showMessageDialog(usershoppane, "공백입력불가 / 숫자를 입력해주세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(usershoppane, "숫자를 입력하세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(usershoppane, "보유 포인트가 부족합니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					coin -= price*buy;
					user.setPoint(coin);
					
					JOptionPane.showMessageDialog(usershoppane, "구매되었습니다. \n현재 보유 코인 : " + coin, "구매 성공", JOptionPane.INFORMATION_MESSAGE);
					db.updatecoin(user.getId(), user.getPoint());
					coinLabel.setText("포인트 : " + user.getPoint());
				
					// 카드 뒤집기 이벤트
					new RandomChoice(buy, user.getId());
					
				
				}
				
			}
			else if (buy == 0) {
				System.out.println(buy);
				JOptionPane.showMessageDialog(usershoppane, "0개는 구매할 수 없습니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(usershoppane, "1개 이상을 선택해주세요", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	
	// 상점 - 휴가 & 반차 구매
	class BuyVac implements ActionListener {
		int price;
		public void SetPrice(int price) {
			this.price = price;
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String text;
			if(e.getSource() == halfvacationButton) {
				text = "반차 구매";
			}
			else {
				text = "휴가 구매";
			}
			
			coin = user.getPoint();
			int buy = 0;	// 구매 개수
			
			try {
				String check = JOptionPane.showInputDialog(usershoppane, "몇개 구매하시겠습니까?", text, JOptionPane.NO_OPTION);
				// 공백예외
				if(check.equals("")) {
					JOptionPane.showMessageDialog(usershoppane, "공백입력불가 / 숫자를 입력해주세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(usershoppane, "숫자를 입력하세요!", "구매실패", JOptionPane.ERROR_MESSAGE);
				buy = -1;
			}

			// cancel
			catch (Exception e2) {
				System.out.println("다른 예외");
				System.out.println(e2);
				buy = -1;

			}
			System.out.println(buy);
			if (buy >= 1) { 
				if (coin < price*buy) {
					JOptionPane.showMessageDialog(usershoppane, "보유 포인트가 부족합니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					coin -= price*buy;
					user.setPoint(coin);
					
					JOptionPane.showMessageDialog(usershoppane, "구매되었습니다. \n현재 보유 코인 : " + coin, "구매 성공", JOptionPane.INFORMATION_MESSAGE);
					db.updatecoin(user.getId(), user.getPoint());
					coinLabel.setText("포인트 : " + user.getPoint());
				
					// 반차 구매 이벤트
					if(e.getSource() == halfvacationButton) {
						// update 반차
						db.Buyhalf(user.getId(), buy);
					}
					
					// 휴가 구매 이벤트
					else {
						// update 휴가
						db.Buyhalf(user.getId(), buy*2);
					}
				
				}
				
			}
			else if (buy == 0) {
				System.out.println(buy);
				JOptionPane.showMessageDialog(usershoppane, "0개는 구매할 수 없습니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else {
				JOptionPane.showMessageDialog(usershoppane, "1개 이상을 선택해주세요", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
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
		Font font = new Font("Sanserif", Font.BOLD, size);
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
		user.setPoint(200);
		new UserShop(user);
	}
}
