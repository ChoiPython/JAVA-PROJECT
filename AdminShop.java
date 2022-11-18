import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class AdminShop extends JFrame{
		private int coin = 100000;
		private JLabel coinLabel;
		public static String firitem = "치킨";
		public static String secitem = "피자";
		public static String thritem = "아이폰";
		
		public AdminShop() {
			setTitle("상점");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Container adminShoppane = getContentPane();
			
			
			
			adminShoppane.setLayout(null);
				
//			// 폰트 설정
//			Font font = new Font("돋움", Font.BOLD, 20);
			
			
			// 이미지
			// 휴가권 - 1
			ImageIcon vacationImage = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
			
			// 반차 - 0.5
			ImageIcon halfvationimage = new ImageIcon(this.getClass().getResource("/휴가권.jpg"));
			
			//랜덤박스
			ImageIcon randomboxImage = new ImageIcon(this.getClass().getResource("/선물상자.jpg"));
			
			// 타이틀 라벨 생성
			JLabel titleJLabel = new JLabel("관리자 - 상점");
			SetFont(titleJLabel, 30);
			titleJLabel.setBounds(400, 5, 240, 80);
			
			
			// 이미지 라벨 생성 & 이미지 적용 - setIcon
			JLabel randomboxLabel = new JLabel(randomboxImage, JLabel.CENTER);
			JLabel vacationLabel = new JLabel(vacationImage, JLabel.CENTER);
			JLabel halfvactionJLabel = new JLabel(halfvationimage, JLabel.CENTER);
			
			
			// 이미지 라벨 설정 setBounds(x, y, w, h) margin - 40
			vacationLabel.setBounds(90, 80, 200, 400);
			halfvactionJLabel.setBounds(320, 80, 200, 400);		
			randomboxLabel.setBounds(550, 80, 200, 400);
			
			// 물품 라벨 생성
			JLabel vanameLabel = new JLabel("상점 : 8점");
			JLabel halfnameLabel = new JLabel("상점 : 4점");
			JLabel rannameLabel = new JLabel("상점 : 1점");
			
			
			// 물품 라벨 설정
			vanameLabel.setBounds(130, 560, 100, 50);
			SetFont(vanameLabel, 20);
			
			halfnameLabel.setBounds(360, 560, 100, 50);
			SetFont(halfnameLabel, 20);
			
			rannameLabel.setBounds(595, 560, 100, 50);
			SetFont(rannameLabel, 20);
			
			
			// 상벌점 현황 라벨
			coinLabel = new JLabel("상/벌점 : " + coin);

			
			// 상벌점 라벨 설정
			coinLabel.setBounds(790, 100, 200,100);
			SetFont(coinLabel, 20);
			
			
			// 추가
			// 제목 라벨
			adminShoppane.add(titleJLabel);
			
			// 이미지 라벨
			adminShoppane.add(vacationLabel);
			adminShoppane.add(halfvactionJLabel);
			adminShoppane.add(randomboxLabel);
			
			// 물품 라벨
			adminShoppane.add(vanameLabel);
			adminShoppane.add(halfnameLabel);
			adminShoppane.add(rannameLabel);
			
			// 상벌점 현황 라벨
			adminShoppane.add(coinLabel);
			
			
			
			
			
			

			
			// 구매 버튼
			JButton vacationButton= new JButton("휴가 - 구매");
			JButton halfvacationButton = new JButton("반차 - 구매");
			JButton randomBoxButton = new JButton("뽑기 - 구매");
			
			//public void SetDefBut(JButton button, Boolean area, Boolean border, Boolean focous) {
//			vacationButton.setContentAreaFilled(false); - 배경 유무
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
			vacationBuyMessage.SetPrice(8);
			vacationButton.addActionListener(vacationBuyMessage);
			
			ShowBuyMessage halfvacationBuyMessage = new ShowBuyMessage();
			halfvacationBuyMessage.SetPrice(4);
			halfvacationButton.addActionListener(halfvacationBuyMessage);
			
			ShowBuyMessage randomBuyMessage = new ShowBuyMessage();
			randomBuyMessage.SetPrice(1);
			randomBoxButton.addActionListener(randomBuyMessage);
			
			
			// 닫기(확인) 버튼 생성 & 설정 & 이벤트
			JButton closeButton = new JButton("닫기");
			SetBack(closeButton);
			SetFont(closeButton, 14);
			SetDefBut(closeButton, true, false, false);
//			closeButton.setForeground(Color.white); // 글자 색상
			closeButton.setBounds(880, 700, 80, 40);
			closeButton.addActionListener(new CloseFrame());
			
			
			// 상품 지정 버튼?
			JButton firitemButton = new JButton("1등 상품 지정");
			JButton secitemButton = new JButton("2등 상품 지정");
			JButton thritemButton = new JButton("3등 상품 지정");
			
			
			// 상품 지정 버튼 설정
			firitemButton.setBounds(80, 700, 200, 40);
			SetBack(firitemButton);
			SetFont(firitemButton, 20);
			SetDefBut(firitemButton, true, false, false);
			
			secitemButton.setBounds(310, 700, 200, 40);
			SetBack(secitemButton);
			SetFont(secitemButton, 20);
			SetDefBut(secitemButton, true, false, false);
			
			thritemButton.setBounds(540, 700, 200, 40);
			SetBack(thritemButton);
			SetFont(thritemButton, 20);
			SetDefBut(thritemButton, true, false, false);
			
			
			// 상품 지정 버튼 - 이벤트 처리
			SelectItems firSelectItems = new SelectItems();
			firSelectItems.SetRank(1);
			firitemButton.addActionListener(firSelectItems);
			
			SelectItems secSelectItems = new SelectItems();
			secSelectItems.SetRank(2);
			secitemButton.addActionListener(secSelectItems);
			
			SelectItems thrSelectItems = new SelectItems();
			thrSelectItems.SetRank(3);
			thritemButton.addActionListener(thrSelectItems);
			
			
			// 상품 정보 확인 버튼 - 확률(1등 1%, 2등 3%, 3등 4%), 각 등수 상품
			JButton itemsinfoButton = new JButton("상품 확인");
			SetFont(itemsinfoButton, 20);
			SetDefBut(itemsinfoButton, false, false, false);
			itemsinfoButton.setBounds(750, 500, 150, 50);
			itemsinfoButton.addActionListener(new ShowItemsInfo());
						
			
			// 추가
			// 구매 버튼
			adminShoppane.add(vacationButton);
			adminShoppane.add(halfvacationButton);
			adminShoppane.add(randomBoxButton);
			
			// 닫기 버튼
			adminShoppane.add(closeButton);
			
			// 상품 지정 버튼
			adminShoppane.add(firitemButton);
			adminShoppane.add(secitemButton);
			adminShoppane.add(thritemButton);
			
			// 상품 확인 버튼
			adminShoppane.add(itemsinfoButton);
			
			
			// 폰트설정
			SetFont(vacationButton, 20);
			SetFont(halfvacationButton, 20);
			SetFont(randomBoxButton, 20);
			
			
			// 창 사이즈 및 보임 여부
			setSize(1000, 800);
			setVisible(true);
			
			// 창이 가운데 나오게 함.
			setLocationRelativeTo(null);
			
			// 창 크기 변환x
			setResizable(false);
			
			
			
		}
		
//				JOptionPane.showMessageDialog(null, "버튼 클릭 됨", "메시지 박스");
//				JOptionPane.showMessageDialog(null, "버튼 클릭 됨", "메시지 박스", JOptionPane.WARNING_MESSAGE);	// 경고
//				JOptionPane.showMessageDialog(null, "버튼 클릭 됨", "메시지 박스", JOptionPane.ERROR_MESSAGE);		// 에러
//				JOptionPane.showMessageDialog(null, "버튼 클릭 됨", "메시지 박스", JOptionPane.INFORMATION_MESSAGE);	// 정보
//				JOptionPane.showMessageDialog(null, "버튼 클릭 됨", "메시지 박스", JOptionPane.QUESTION_MESSAGE);
		// 상점 - 메시지 박스 이벤트 처리
		class ShowBuyMessage implements ActionListener {
			int price;
			public void SetPrice(int price) {
				this.price = price;
				
			}
			// 
			@Override
			public void actionPerformed(ActionEvent e) {
				int buy = JOptionPane.showConfirmDialog(null, "버튼 클릭 됨", "메시지 박스", JOptionPane.YES_NO_OPTION);
				// 구매
				if (buy == JOptionPane.YES_OPTION) { 
					if (coin < price) {
						JOptionPane.showMessageDialog(null, "보유 상점이 부족합니다.", "구매 실패", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						coin -= price;
						JOptionPane.showMessageDialog(null, "구매되었습니다. \n현재 보유 코인 : " + coin, "구매 성공", JOptionPane.INFORMATION_MESSAGE);
						coinLabel.setText("상/벌점 : " + coin);
					}
					
				}

			}
		}
		
		// 닫기 - 이벤트 처리
		class CloseFrame implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
//				System.exit(0);
				
			}
		}
		// 상품 지정 버튼
		class SelectItems implements ActionListener {
			int val;
			public void SetRank(int val) {
				this.val = val;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				String item = JOptionPane.showInputDialog(null, val + "등 상품을 지정하세요");
				
				// Cancel
				if(item == null) {
//					JOptionPane.showMessageDialog(null, "상품이 등록되지 않았습니다.");
				}
				
				// yes
				else {
					if(val == 1) {
						firitem = item;	
						System.out.println(item);
					}
					
					else if(val == 2) {
						secitem = item;
					}
					
					else if(val == 3) {
						thritem = item;
					}
				}
				
				
			}


		}
		
		// 상품 정보 확인 - 확률 x 버전
		class ShowItemsInfo implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "상품 확인 \n1등 : " + firitem + ", 2등 : " + secitem + ", 3등 : " + thritem, "상품 정보 확인", JOptionPane.INFORMATION_MESSAGE);
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
			new AdminShop();
		}

}
