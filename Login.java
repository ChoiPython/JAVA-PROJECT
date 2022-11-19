import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Login extends JFrame implements ActionListener {
	DBA dba=new DBA();	// db 생성
	private JTextField idtextField;	// id 입력 박스
	private JTextField pwtextField;	// pw 입력 박스
	private JLabel idLabel;		// id 라벨 생성
	private JLabel pwJLabel;	// pw 라벨 생성
	
	public  Login(String title, int xsize, int ysize, Boolean visible)   {
		getContentPane().setBackground(Color.GRAY);
		setTitle("xx회사 로그인 창");	// 화면 제목
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container loginPane = getContentPane();
		//test
		// 폰트 지정 - PLAIN-기본, BOLD - 굵게, ITALIC - 기울임
		Font font = new Font("돋움", Font.BOLD, 50);
		
		
		loginPane.setLayout(null);
		JLabel test = new JLabel("XX회사");
		test.setLocation(210, 20);
		test.setSize(200, 200);
		test.setFont(font);
		loginPane.add(test);
		
		idLabel = new JLabel(" 아이디(이름)");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		idLabel.setBounds(144, 180, 119, 40);
		getContentPane().add(idLabel);
		
		pwJLabel = new JLabel("패스워드(사원번호)");
		pwJLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		pwJLabel.setBounds(108, 230, 132, 40);
		getContentPane().add(pwJLabel);
		
		idtextField = new JTextField();
		idtextField.setBounds(261, 189, 183, 21);
		getContentPane().add(idtextField);
		idtextField.setColumns(10);
		
		pwtextField = new JTextField();
		pwtextField.setBounds(261, 240, 183, 21);
		getContentPane().add(pwtextField);
		pwtextField.setColumns(10);
		
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
		if(pwtextField.getText().equals("")||idtextField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
		}
		else {
			user=dba.login(pwtextField.getText(),idtextField.getText());
			
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
