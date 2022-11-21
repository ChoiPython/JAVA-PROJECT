import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class adminGui extends JFrame implements ActionListener {
	JTextField searchText;
	JButton searchBtn;	// 검색버튼
	JButton insertBtn;	// 등록버튼
	JButton shopBtn;	// 상점버튼
	JButton refreshBtn;	// 새로고침버튼
	JButton viewBtn; //상세보기버튼
	JTable users;
	JScrollPane scroll;
	
	
	public adminGui(ArrayList<User> list){
		setTitle("관리자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentpane = getContentPane();
		contentpane.setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		//검색
		JLabel searchLabel=new JLabel("이름 :");
		searchLabel.setBounds(70, 40, 67, 25);
		searchLabel.setFont(new Font("", Font.BOLD, 15));
		contentpane.add(searchLabel);
		searchText=new JTextField();
		searchText.setBounds(130, 40, 280, 25);
		contentpane.add(searchText);
		searchBtn=new JButton("검색");
		searchBtn.setBounds(430, 40, 67, 25);
		searchBtn.addActionListener(this);
		contentpane.add(searchBtn);
		refreshBtn =new JButton("새로고침");
		refreshBtn.setBounds(530, 40, 90, 25);
		refreshBtn.addActionListener(this);
		contentpane.add(refreshBtn);
		
		//등록,수정,삭제 버튼, 상점 버튼
		insertBtn = new JButton("등록");
		insertBtn.setBounds(650, 357, 110, 30);
		insertBtn.addActionListener(this);
		contentpane.add(insertBtn);
		
		shopBtn = new JButton("상점");
		shopBtn.setBounds(650, 416, 110, 30);
		shopBtn.addActionListener(this);
		contentpane.add(shopBtn);
		
		viewBtn=new JButton("상세보기");
		viewBtn.setBounds(650,294,110,30);
		viewBtn.addActionListener(this);
		contentpane.add(viewBtn);
		
		//테이블 데이터 set
		String column[]= {"사원번호","사원이름","만근","지각","무단결근"};
		String[][] s=new String[100][7];//= {{null,null,null,null,null,null,null}};
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<7;j++) {
				if(j==0) s[i][j]=list.get(i).getId()+"";
				if(j==1) s[i][j]=list.get(i).getName();
				if(j==2) s[i][j]=list.get(i).getDepart();
				if(j==3) s[i][j]=list.get(i).getRank();
				if(j==4) s[i][j]=list.get(i).getHalfway()+"";
				if(j==5) s[i][j]=list.get(i).getReward()+"";
				if(j==6) s[i][j]=list.get(i).getPoint()+"";
			}
		}

		
		// 테이블 생성
		users=new JTable(s,column);		
		users.getTableHeader().setReorderingAllowed(false);
		users.getTableHeader().setResizingAllowed(false);
		
		// 테이블 스크롤 바 생성
		scroll=new JScrollPane(users);
		scroll.setBounds(40, 172, 580, 358);
		contentpane.add(scroll);
		
	
		JComboBox YearBox=new JComboBox();
		YearBox.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		YearBox.setBounds(47, 103, 90, 23);		
		getContentPane().add(YearBox);
		
	
	
		JComboBox MonthBox = new JComboBox();
		MonthBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		MonthBox.setBounds(150, 103, 54, 23);
		getContentPane().add(MonthBox);
		
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);	// 화면중간출력
		setResizable(false);			// 크기조절
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 등록
		if(e.getSource()==insertBtn) {
			new RegistGui();
		}
		else if(e.getSource()==viewBtn) {
			ViewGui viewgui = new ViewGui();
			viewgui.setVisible(true);
		}
		
		else if(e.getSource()==shopBtn) {
			AdminShop adminShop = new AdminShop();
			adminShop.setLocation(600, 100);
			setLocation(200, 100);
		}
		// 검색이벤트
		else if(e.getSource()==searchBtn) {
			if(searchText.getText().equals("")) {				
			}
			else {
				DBA db=new DBA();
				ArrayList<User> list=new ArrayList<>();
				scroll.setVisible(false);
				db.selectNameData(list, searchText.getText());
				
				String column[]= {"사원번호","사원이름","만근","지각","무단결근"};
				String[][] s=new String[100][7];
				for(int i=0;i<list.size();i++) {
					s[i][0]=list.get(i).getId()+"";
					s[i][1]=list.get(i).getName();
					s[i][2]=list.get(i).getDepart();
					s[i][3]=list.get(i).getRank();
					s[i][4]=list.get(i).getHalfway()+"";
					s[i][5]=list.get(i).getReward()+"";
					s[i][6]=list.get(i).getPoint()+"";
				}
				
				users=new JTable(s,column);	
				users.getTableHeader().setReorderingAllowed(false);
				users.getTableHeader().setResizingAllowed(false);
				scroll=new JScrollPane(users);
				scroll.setBounds(40, 172, 580, 358);
				getContentPane().add(scroll);
			}
		}
		// 새로고침 이벤트
		else if(e.getSource()==refreshBtn) {
			searchText.setText("");
			DBA db=new DBA();
			ArrayList<User> list=new ArrayList<>();
			scroll.setVisible(false);
			db.selectAllData(list);
			
			String column[]= {"사원번호","사원이름","만근","지각","무단결근"};
			String[][] s=new String[100][7];
			for(int i=0;i<list.size();i++) {
				s[i][0]=list.get(i).getId()+"";
				s[i][1]=list.get(i).getName();
				s[i][2]=list.get(i).getDepart();
				s[i][3]=list.get(i).getRank();
				s[i][4]=list.get(i).getHalfway()+"";
				s[i][5]=list.get(i).getReward()+"";
				s[i][6]=list.get(i).getPoint()+"";
			}
			
			users=new JTable(s,column);	
			users.getTableHeader().setReorderingAllowed(false);
			users.getTableHeader().setResizingAllowed(false);
			scroll=new JScrollPane(users);
			scroll.setBounds(40, 172, 580, 358);
			getContentPane().add(scroll);
		}
	}
}
