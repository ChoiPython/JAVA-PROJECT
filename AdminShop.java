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
			
	
			// 닫기(확인) 버튼 생성 & 설정 & 이벤트
			JButton closeButton = new JButton("닫기");
			SetBack(closeButton);
			SetFont(closeButton, 14);
			SetDefBut(closeButton, true, false, false);
//			closeButton.setForeground(Color.white); // 글자 색상
			closeButton.setBounds(680, 500, 80, 40);
			closeButton.addActionListener(new CloseFrame());
			adminShoppane.add(closeButton);

			
			
			// 창 사이즈 및 보임 여부
			setSize(800, 600);
			setVisible(true);
			
			// 창이 가운데 나오게 함.
			setLocationRelativeTo(null);
			
			// 창 크기 변환x
			setResizable(false);
			
			
			
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
			new AdminShop();
		}

}
