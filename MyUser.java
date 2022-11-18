import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.LineNumberInputStream;

public class MyUser extends JFrame implements ActionListener{
	private JLabel uname;
	private JLabel uposition;
	private JLabel udepartment;
	private JLabel upoint;
	private JButton store;
	private JButton inventory;
	

	public MyUser(User user)
	{
		setTitle("이용자 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(SystemColor.info);
		ImageIcon img = new ImageIcon("C:\\Users\\이창건\\Documents\\20212950\\2학년\\JAVA\\LCG\\JavaTeamProject\\src\\Images\\tstaff.jpg");
		JLabel imageLabel = new JLabel(img, JLabel.CENTER);
		imageLabel.setBounds(30, 30, 150, 187);
		c.add(imageLabel);
		
		JLabel time = new JLabel("시간) 2022-11-14//07-56 (출근시간");
		time.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		time.setSize(350, 50);
		time.setLocation(200, 20);
		c.add(time);
		
		JLabel name = new JLabel("이름 |");
		name.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		name.setSize(133, 50);
		name.setLocation(200, 70);
		c.add(name);
		uname=new JLabel(user.getName());
		uname.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		uname.setSize(133, 50);
		uname.setLocation(260, 70);
		c.add(uname);
		
		JLabel position = new JLabel("직급 |");
		position.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		position.setSize(100, 50);
		position.setLocation(200, 100);
		c.add(position);
		uposition=new JLabel(user.getRank());
		uposition.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		uposition.setSize(100, 50);
		uposition.setLocation(260, 100);
		c.add(uposition);
		
		JLabel department = new JLabel("부서 |");
		department.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		department.setSize(133, 50);
		department.setLocation(200, 130);
		c.add(department);
		udepartment=new JLabel(user.getDepart());
		udepartment.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		udepartment.setSize(133, 50);
		udepartment.setLocation(260, 130);
		c.add(udepartment);
		
		JLabel point = new JLabel("상벌점 |");
		point.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		point.setSize(150, 50);
		point.setLocation(180, 160);
		c.add(point);
		upoint=new JLabel(user.getReward()+"");
		upoint.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		upoint.setSize(150, 50);
		upoint.setLocation(260, 160);
		c.add(upoint);
		
		store = new JButton("상점");
		store.setFont(new Font("돋음", Font.PLAIN, 18));
		store.setSize(100, 50);
		store.setLocation(350, 200);
		store.addActionListener(this);
		c.add(store);
		
		inventory = new JButton("내 가방");
		inventory.setFont(new Font("돋음", Font.PLAIN, 18));
		inventory.setSize(100, 50);
		inventory.setLocation(460, 200);
		inventory.addActionListener(this);
		c.add(inventory);
		
		setSize(600,300);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==store) {
			UserShop ushop = new UserShop();
			ushop.setLocation(600, 100);
			setLocation(200, 200);
			
		}
		
		else if(e.getSource()==inventory) {
			new MyInventory();
		}
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setId(20212503);
		new MyUser(user);
	}


}