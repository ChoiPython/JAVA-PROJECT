import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
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
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(468, 239, 91, 23);
		btnNewButton.addActionListener(this);
		getContentPane().add(btnNewButton);
		
		pwtextField = new JPasswordField();
		pwtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){ 
				btnNewButton.doClick();  //엔터키를 누르면 btnNewButton(로그인 버튼)을 누르는거와 같음
	            }
			}
		});
		pwtextField.setBounds(261, 240, 183, 21);

		getContentPane().add(pwtextField);
		pwtextField.setColumns(10);
		
		
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
			JOptionPane.showMessageDialog(null, "필드값이 비어있습니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
		}
		else {
			if (!isNumeric(pwtextField.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호는 숫자로만 입력해주세요","로그인 실패",JOptionPane.WARNING_MESSAGE);
				return;
			}
			user=dba.login(pwtextField.getText(),idtextField.getText());

			if(user.getName()==null||Integer.toString(user.getId())==null) {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다.","로그인 실패",JOptionPane.WARNING_MESSAGE);
			}

			else if(user.getId()==0&&user.getName().equals("admin")) {
				//user=dba.login(textField_1.getText(),textField.getText());
				ArrayList<User2> list=new ArrayList<>();
				Date d=Date.valueOf("2022-01-01");
				dba.selectAllData(list,d);
				this.dispose();
				adminGui adgui = new adminGui(list);
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


	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	public static void main(String args[]) {
		// 로그인 화면
		Login login = new Login("로그인창", 500, 500, true);
		login.idtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					login.pwtextField.requestFocus();
				}
			}
		});
	}
}


