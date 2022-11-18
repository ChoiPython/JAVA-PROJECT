import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Login extends JFrame implements ActionListener {
	DBA dba=new DBA();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public  Login(String title, int xsize, int ysize, Boolean visible)   {
		getContentPane().setBackground(Color.GRAY);
		setTitle("xx회사 로그인 창");	// 화면 제목
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container loginPane = getContentPane();
		
		// 폰트 지정 - PLAIN-기본, BOLD - 굵게, ITALIC - 기울임
		Font font = new Font("돋움", Font.BOLD, 50);
		
		loginPane.setLayout(null);
		JLabel test = new JLabel("XX회사");
		test.setLocation(210, 20);
		test.setSize(200, 200);
		test.setFont(font);
		loginPane.add(test);
		
		lblNewLabel = new JLabel(" 아이디(이름)");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(144, 180, 119, 40);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("패스워드(사원번호)");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(108, 230, 132, 40);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(261, 189, 183, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(261, 240, 183, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(468, 239, 91, 23);
		btnNewButton.addActionListener(this);
		getContentPane().add(btnNewButton);
		
		
		setSize(596, 405);	// 화면 사이즈
		setVisible(visible);	// 
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		User user=new User();
		JButton b=(JButton)e.getSource();
		if(textField_1.getText().equals("")||textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
		}
		else {
			user=dba.login(textField_1.getText(),textField.getText());
			if(user.getName()==null||Integer.toString(user.getId())==null) {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
			}
			else if(user.getId()==0&&user.getName().equals("admin")) {
				//user=dba.login(textField_1.getText(),textField.getText());
				ArrayList<User> list=new ArrayList<>();
				dba.selectAllData(list);
				this.dispose();
				new adminGui(list);

			}
			else if (Integer.toString(user.getId())!=null) {
				//user=dba.login(textField_1.getText(),textField.getText());
				this.dispose();
				new MyUser(user);
			}
			else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public static void main(String args[]) {
		// 로그인 화면
		new Login("로그인창", 500, 500, true);
	}
}
