
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class MyInventory {

	private JFrame frame;
	private JTextField textField;	// 사용권
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


	public static void main(String[] args) {
		new MyInventory();
	}


	public MyInventory() {

		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.info);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.info);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textField.setText("사용권");
		textField.setBounds(0, 0, 132, 58);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(SystemColor.info);
		textField_1.setText("특별상품");
		textField_1.setEditable(false);
		textField_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(132, 0, 161, 58);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(SystemColor.info);
		textField_2.setEditable(false);
		textField_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("상/벌점");
		textField_2.setBounds(290, 0, 146, 58);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("월차 | 0개\r\n반차 | 2개");
		textField_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textField_3.setBackground(SystemColor.info);
		textField_3.setBounds(0, 57, 132, 196);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText("피자 | 1개");
		textField_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textField_4.setBackground(SystemColor.info);
		textField_4.setBounds(132, 57, 161, 196);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setText("상점 | 2점\r\n벌점 | 0점");
		textField_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		textField_5.setBackground(SystemColor.info);
		textField_5.setBounds(290, 57, 146, 196);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		frame.setVisible(true);
		frame.setLocation(800, 400);
//		frame.setResizable(false); // 화면 크기 수정여부
	}
}
