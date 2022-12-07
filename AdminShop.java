import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class AdminShop extends JFrame{
		private int coin = 100000;
		private JLabel coinLabel;
		private JLabel percentLabel;
		private JLabel hpriceLabel;
		private JLabel rpriceLabel;
		private JSlider percent;
		private JSlider hprice;	// 반차
		private JSlider rprice;	// 뽑기
		private int[] shopdata;
		private DBA dba = new DBA();
		Container adminShoppane;
		
		public AdminShop() {
			setTitle("상점");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			adminShoppane = getContentPane();
			adminShoppane.setLayout(null);
				
//			// 폰트 설정
//			Font font = new Font("돋움", Font.BOLD, 20);
			
			// 상점 데이터 받아오기
			shopdata = dba.GetShop();
			
			// 닫기(확인) 버튼 생성 & 설정 & 이벤트
			JButton closeButton = new JButton("닫기");
			SetBack(closeButton);
			SetFont(closeButton, 18);
			SetDefBut(closeButton, true, false, false);
//			closeButton.setForeground(Color.white); // 글자 색상
			closeButton.setBounds(580, 300, 80, 40);
			closeButton.addActionListener(new CloseFrame());
			adminShoppane.add(closeButton);
			
			// 설정 완료 버튼
			JButton setButton = new JButton("설정");
			SetBack(setButton);
			SetFont(setButton, 18);
			SetDefBut(setButton, true, false, false);
			setButton.setBounds(480, 300, 80, 40);
			setButton.addActionListener(new SetClear());
			adminShoppane.add(setButton);

			
			// 확률 조정?
			// 트랙바로 조정하기 - 0~100 확률 def - 4(데이터추가시 변수로 변경)
			percent = new JSlider(JSlider.HORIZONTAL, 0, 100, shopdata[0]);
			percent.setMinorTickSpacing(1);		// 작은 눈금 단위
			percent.setMajorTickSpacing(10);	// 큰 눈금 단위
			percent.setBounds(160, 40, 500, 50);
			percent.setPaintTicks(true);	// 눈금 보이게
			percent.setPaintLabels(true);	// 숫자 보이게
			percent.addChangeListener(new ChangePer());
			adminShoppane.add(percent);
			
			// 확률 라벨
			percentLabel =  new JLabel("뽑기 설정 확률 : " + percent.getValue());
			percentLabel.setBounds(10, 0, 120, 100);
			SetFont(percentLabel, 12);
			adminShoppane.add(percentLabel);
			
			// 상품 가격 지정
			// 뽑기
			rprice = new JSlider(JSlider.HORIZONTAL, 0, 100, shopdata[1]);
			rprice.setMinorTickSpacing(1);		// 작은 눈금 단위
			rprice.setMajorTickSpacing(10);	// 큰 눈금 단위
			rprice.setBounds(160, 120, 500, 50);
			rprice.setPaintTicks(true);	// 눈금 보이게
			rprice.setPaintLabels(true);	// 숫자 보이게
			rprice.addChangeListener(new ChangePer());
			adminShoppane.add(rprice);
			
			// 반차
			hprice = new JSlider(JSlider.HORIZONTAL, 0, 100, shopdata[2]);
			hprice.setMinorTickSpacing(1);		// 작은 눈금 단위
			hprice.setMajorTickSpacing(10);	// 큰 눈금 단위
			hprice.setBounds(160, 200, 500, 50);
			hprice.setPaintTicks(true);	// 눈금 보이게
			hprice.setPaintLabels(true);	// 숫자 보이게
			hprice.addChangeListener(new ChangePer());
			adminShoppane.add(hprice);
			
			
			// 가격 라벨
			// 뽑기
			rpriceLabel =  new JLabel("뽑기 설정 가격 : " + rprice.getValue());
			rpriceLabel.setBounds(10, 80, 120, 100);
			SetFont(rpriceLabel, 12);
			adminShoppane.add(rpriceLabel);
			
			// 반차
			hpriceLabel =  new JLabel("반차 설정 가격 : " + hprice.getValue());
			hpriceLabel.setBounds(10, 160, 120, 100);
			SetFont(hpriceLabel, 12);
			adminShoppane.add(hpriceLabel);
			
			
			
			
			
			// 창 사이즈 및 보임 여부
			setSize(700, 400);
			setVisible(true);
			
			// 창이 가운데 나오게 함.
			setLocationRelativeTo(null);
			
			// 창 크기 변환x
			setResizable(false);
			
			
			
		}
		
		
		// 확률 조정 이벤트
		class ChangePer implements ChangeListener {

			public void stateChanged(ChangeEvent e) {
				if(e.getSource() == percent) {
					percentLabel.setText("뽑기 설정 확률 : " + percent.getValue());
					
				}
				else if(e.getSource() == hprice) {
					hpriceLabel.setText("반차 설정 가격 : " + hprice.getValue());
				}
				else if (e.getSource() == rprice) {
					rpriceLabel.setText("뽑기 설정 가격 : " + rprice.getValue());
					
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
		
		// 설정 완료 이벤트
		class SetClear implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(adminShoppane, "설정하시겠습니까?", "설정 확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					// 데이터 설정 
					JOptionPane.showMessageDialog(adminShoppane, "현재 확률 : " + percent.getValue() + " / 반차 현재 가격 : " + hprice.getValue() + " / 뽑기 현재 가격 : " + rprice.getValue());
					dba.SetShop(percent.getValue(), rprice.getValue(), hprice.getValue());
					dispose();
				}
			}
		}


		// 폰트 설정
		public void SetFont(JButton button, int size) {
			Font font = new Font("Sanserif", Font.BOLD, size);
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
			new AdminShop();
		}

}
