import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.*;

public class MyUser extends JFrame implements ActionListener{
	private JLabel uname;
	private JLabel uposition;
	private JLabel udepartment;
	private JLabel upoint;
	private JButton store;
	private JButton vacation;
	private User user;
	private JButton refresh;
	private ImageIcon img;
	
    Calendar calendar1 = Calendar.getInstance();
    int hour = calendar1.get(Calendar.HOUR_OF_DAY);
    int min = calendar1.get(Calendar.MINUTE);
    int sec = calendar1.get(Calendar.SECOND);
	String Am_Pm;
	String min_str;
	public MyUser(User u)
	{
		user=u;
		setTitle("이용자 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(SystemColor.LIGHT_GRAY);
		if(user.getImgaddr()==null) {
			img = new ImageIcon(this.getClass().getResource("/employee.jpg"));
		}else {
			img = new ImageIcon(user.getImgaddr());
		}
		JLabel imageLabel = new JLabel(img, JLabel.CENTER);
		imageLabel.setBounds(30, 30, 150, 187);
		c.add(imageLabel);
		
		if(hour>12)
		{
			Am_Pm = "오후";
			hour = hour - 12;
		}
		else
		{
			 Am_Pm = "오전";
		}
		if(min < 10)
		{
			min_str = "0" + min;
		}
		else
		{
			min_str = "" + min;
		}
		JLabel time = new JLabel(Am_Pm + " " + hour + ":" + min_str);
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
		upoint=new JLabel(user.getPoint()+"");
		upoint.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		upoint.setSize(150, 50);
		upoint.setLocation(260, 160);
		c.add(upoint);
		
		store = new JButton("상점");
		store.setFont(new Font("돋음", Font.PLAIN, 18));
		store.setSize(100, 50);
		store.setLocation(300, 200);
		store.addActionListener(this);
		c.add(store);
		
		vacation = new JButton("휴가/반차");
		vacation.setFont(new Font("돋음", Font.PLAIN, 18));
		vacation.setSize(150, 50);
		vacation.setLocation(410, 200);
		vacation.addActionListener(this);
		c.add(vacation);
		
		refresh=new JButton("새로고침");
		refresh.setFont(new Font("돋음", Font.PLAIN, 10));
		refresh.setSize(80, 20);
		refresh.setLocation(500, 3);
		refresh.addActionListener(this);
		c.add(refresh);
		
		setSize(600,300);
		setVisible(true);
		setLocationRelativeTo(null);	// 화면중간출력
		setResizable(false);			// 크기조절
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==store) {
			UserShop userShop = new UserShop(user);
			userShop.setLocation(600, 100);
			setLocation(200, 100);
		}
		else if(e.getSource()==vacation) {
			User u=user;
			new Vacation(u);
		}
		else if(e.getSource()==refresh) {
			DBA db=new DBA();
			User user=new User();
			user=db.selectIdData(this.user.getId());
			this.user=user;
			
			uname.setText(user.getName());
			uposition.setText(user.getRank());
			udepartment.setText(user.getDepart());
			upoint.setText(user.getPoint()+"");
			if(user.getImgaddr()==null) {
				img = new ImageIcon(this.getClass().getResource("/employee.jpg"));
			}else {
				img = new ImageIcon(user.getImgaddr());
			}
		}
	}
}