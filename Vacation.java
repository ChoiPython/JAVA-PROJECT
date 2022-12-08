import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.swing.*;

class CalendarDataManager extends JFrame{
	User user;
	static int index;
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
	int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;
	Calendar now;
	Container c = getContentPane();
	int to;
	
	public CalendarDataManager(){ 
		setToday(); 
	}
	public void setToday(){
		calYear = today.get(Calendar.YEAR); 
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		makeCalData(today);
	}
	private void makeCalData(Calendar cal){
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
		if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);	//2월달의 경우
		else calLastDate = calLastDateOfMonth[calMonth];	//2월달 제외
		for(int i = 0 ; i<CAL_HEIGHT ; i++){
			for(int j = 0 ; j<CAL_WIDTH ; j++){
				calDates[i][j] = 0;
			}
		}
		for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
			if(i == 0) k = calStartingPos;
			else k = 0;
			for(int j = k ; j<CAL_WIDTH ; j++){
				if(num <= calLastDate) calDates[i][j]=num++;
			}
		}
	}
	private int leapCheck(int year){
		if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
		else return 0;
	}
	public void moveMonth(int mon){
		calMonth += mon;
		if(calMonth>11) while(calMonth>11){
			calYear++;
			calMonth -= 12;
		} else if (calMonth<0) while(calMonth<0){
			calYear--;
			calMonth += 12;
		}
		cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
		makeCalData(cal);
	}
}

public class Vacation extends CalendarDataManager{
	
	String date = new String();
	String day;	//일 가져오기
	JComboBox vac_half;	//휴가/반차 콤보박스
	JLabel vac_num;	//남은 반차 개수
	JPanel calOpPanel;
		JButton todayBut;
		JLabel todayLab;
		JButton lYearBut;
		JButton lMonBut;
		JLabel curMMYYYYLab;
		JButton nMonBut;
		JButton nYearBut;
		ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
	
		JButton dateButs[][] = new JButton[6][7];
		listenForDateButs lForDateButs = new listenForDateButs(); 
		String vacation_halfvac[] = new String[] {"휴가/반차", "휴가", "반차"};
	String WEEK_DAY_NAME[] = { "일", "월", "화", "수", "목", "금", "토" };

	public Vacation(User u){
		this.user=u;
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("휴가/반차 사용 화면");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(420,450);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		
		vac_half = new JComboBox(vacation_halfvac);
		vac_half.setEnabled(true);
		
		vac_num = new JLabel("반차: " + u.getHalfway());
		vac_num.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		
		calOpPanel = new JPanel();
			lYearBut = new JButton("<<");
			lYearBut.setToolTipText("Previous Year");
			lYearBut.addActionListener(lForCalOpButtons);
			
			lMonBut = new JButton("<");
			lMonBut.setToolTipText("Previous Month");
			lMonBut.addActionListener(lForCalOpButtons);
			
			curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>\r"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+"\r / \r"+calYear+"\r</th></tr></table></html>");
		
			nMonBut = new JButton(">");
			nMonBut.setToolTipText("Next Month");
			nMonBut.addActionListener(lForCalOpButtons);
		
			nYearBut = new JButton(">>");
			nYearBut.setToolTipText("Next Year");
			nYearBut.addActionListener(lForCalOpButtons);
			
			calOpPanel.setLayout(new GridBagLayout());
			GridBagConstraints calOpGC = new GridBagConstraints();
		
			calOpGC.anchor = GridBagConstraints.CENTER;
			calOpGC.gridwidth = 2;
			calOpGC.gridx = 6;
			calOpGC.gridy = 0;
			calOpPanel.add(vac_num,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 4;
			calOpGC.gridy = 3;
			calOpPanel.add(vac_half,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 1;
			calOpGC.gridy = 2;
			calOpPanel.add(lYearBut,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 2;
			calOpGC.gridy = 2;
			calOpPanel.add(lMonBut,calOpGC);
			calOpGC.gridwidth = 2;
			calOpGC.gridx = 3;
			calOpGC.gridy = 2;
			calOpPanel.add(curMMYYYYLab,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 5;
			calOpGC.gridy = 2;
			calOpPanel.add(nMonBut,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 6;
			calOpGC.gridy = 2;
			calOpPanel.add(nYearBut,calOpGC);
		
			JPanel calPanel=new JPanel();
			JButton[] weekDaysName = new JButton[7];
			for(int i=0 ; i<CAL_WIDTH ; i++){
				weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
				weekDaysName[i].setBorderPainted(false);
				weekDaysName[i].setContentAreaFilled(false);
				weekDaysName[i].setForeground(Color.WHITE);
				if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50));
				else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200));
				else weekDaysName[i].setBackground(new Color(150, 150, 150));
				weekDaysName[i].setOpaque(true);
				weekDaysName[i].setFocusPainted(false);
				calPanel.add(weekDaysName[i]);
			}
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					dateButs[i][j]=new JButton();
					dateButs[i][j].setBorderPainted(false);
					dateButs[i][j].setContentAreaFilled(false);
					dateButs[i][j].setBackground(Color.WHITE);
					dateButs[i][j].setOpaque(true);
					dateButs[i][j].addActionListener(lForDateButs);
					dateButs[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for(int a = 0;a<CAL_HEIGHT;a++)
							{
								for(int b=0 ; b<CAL_WIDTH ; b++)
								{
									if(e.getSource() == dateButs[a][b])
									{
										String ss[]=new String[10];
										String s=curMMYYYYLab.getText();
										StringTokenizer st=new StringTokenizer(s,"\r &nbsp;");
										int count=st.countTokens();
										for(int i=0;i<count;i++) {
											ss[i]=st.nextToken();//index 5==m 7==y
										}
										day = dateButs[a][b].getText();	//일 가져오기
										String date=ss[7]+"-"+ss[5]+"-"+day;
										Date d=Date.valueOf(date);
										DBA db=new DBA();
										to=db.TOCount(d);
										JOptionPane.showMessageDialog(null, "남은 인원 " + (3-to) + "/3");
									}
								}
							}
							
						}
					});
					calPanel.add(dateButs[i][j]);
				}
			}
			
			vac_half.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			            JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
			            index = cb.getSelectedIndex();// 선택된 아이템의 인덱스
			       }
			  });
			
			JPanel frameBottomPanel;
			JButton AppBtn = new JButton("신청");
			AppBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ss[]=new String[10];
					if(e.getSource() == AppBtn)
					{
						int res;
						String s=curMMYYYYLab.getText();
						StringTokenizer st=new StringTokenizer(s,"\r &nbsp;");
						int count=st.countTokens();
						for(int i=0;i<count;i++) {
							ss[i]=st.nextToken();//index 5==m 7==y
						}
						String date=ss[7]+"-"+ss[5]+"-"+day;
						Date d=Date.valueOf(date);
						System.out.println(user.getId());
						System.out.println(d);
						DBA db=new DBA();
						
						int todayyear = today.get(Calendar.YEAR);
						int todaymonth = today.get(Calendar.MONTH)+1;
						if(todaymonth == 13)
							todaymonth = 1;
						int todayday = today.get(Calendar.DAY_OF_MONTH);
						if(to>=3) {
							JOptionPane.showMessageDialog(null, "TO가 가득 찼습니다.");
						}
						else{
							if(Integer.parseInt(ss[7]) > todayyear)	//올해 이후
							{
								if(index == 0)	//휴학/반차 선택
					            	JOptionPane.showMessageDialog(null, "휴가와 반차중 하나 선택하십시오.");
					            else if(index == 1)	//휴가 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"휴가");
					            	if(res==1) {
						            	int h=user.getHalfway()-2;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
					            else if(index == 2)	//반차 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"반차");
					            	if(res==1) {
						            	int h=user.getHalfway()-1;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
							}
							else if(Integer.parseInt(ss[7]) == todayyear && Integer.parseInt(ss[5]) > todaymonth)	//올해, 이번달 이후
							{
								if(index == 0)	//휴학/반차 선택
					            	JOptionPane.showMessageDialog(null, "휴가와 반차중 하나 선택하십시오.");
					            else if(index == 1)	//휴가 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"휴가");
					            	if(res==1) {
						            	int h=user.getHalfway()-2;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
					            else if(index == 2)	//반차 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"반차");
					            	if(res==1) {
						            	int h=user.getHalfway()-1;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
							}	
							else if(Integer.parseInt(ss[7]) == todayyear && Integer.parseInt(ss[5]) == todaymonth && Integer.parseInt(day) > todayday)	//올해, 이번달, 오늘 이후
							{
								if(index == 0)	//휴학/반차 선택
					            	JOptionPane.showMessageDialog(null, "휴가와 반차중 하나 선택하십시오.");
					            else if(index == 1)	//휴가 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"휴가");
					            	if(res==1) {
						            	int h=user.getHalfway()-2;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
					            else if(index == 2)	//반차 선택
					            {
					            	res=db.leaveApplication(user.getId(), d,"반차");
					            	if(res==1) {
						            	int h=user.getHalfway()-1;
										db.updatehalfway(user.getId(), h);
										JOptionPane.showMessageDialog(null, "신청되었습니다.");
										
					            	}
					            	else {
					            		JOptionPane.showMessageDialog(null, "신청 실패");
					            	}
					            }
							}
							else {
								JOptionPane.showMessageDialog(null, "휴가와 반차신청은 내일부터 신청가능합니다.");								
							}
						}
					}
				}
			});
			JLabel bottomInfo = new JLabel(date);
			
			calPanel.setLayout(new GridLayout(0,7,2,2));
			calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			showCal();
						
		JPanel infoPanel = new JPanel();
						
		JPanel frameSubPanelWest = new JPanel();
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 90;
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel,BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel,BorderLayout.CENTER);

		
		JPanel frameSubPanelEast = new JPanel();
		Dimension infoPanelSize=infoPanel.getPreferredSize();
		infoPanelSize.height = 65;
		infoPanel.setPreferredSize(infoPanelSize);
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(infoPanel,BorderLayout.NORTH);

		
		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 410;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
		
		
		frameBottomPanel = new JPanel();
		frameBottomPanel.add(bottomInfo);
		frameBottomPanel.add(AppBtn);
		

		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
		mainFrame.add(frameSubPanelEast, BorderLayout.CENTER);
		mainFrame.add(frameBottomPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		focusToday(); 
	
	}
	private void focusToday(){
		if(today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
	}
	private void showCal(){
		for(int i=0;i<CAL_HEIGHT;i++){
			for(int j=0;j<CAL_WIDTH;j++){
				String fontColor="black";
				if(j==0) fontColor="red";
				else if(j==6) fontColor="blue";
				
				File f =new File("MemoData/"+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDates[i][j]<10?"0":"")+calDates[i][j]+".txt");
				if(f.exists()){
//					dateButs[i][j].setText("<html><b><font color="+fontColor+">"+calDates[i][j]+"</font></b></html>");
					dateButs[i][j].setText(calDates[i][j] + "");
				}
				else 
//					dateButs[i][j].setText("<html><font color="+fontColor+">"+calDates[i][j]+"</font></html>");
					dateButs[i][j].setText(calDates[i][j] + "");

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if(calMonth == today.get(Calendar.MONTH) &&
						calYear == today.get(Calendar.YEAR) &&
						calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)){
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}
				
				if(calDates[i][j] == 0) dateButs[i][j].setVisible(false);
				else dateButs[i][j].setVisible(true);
			}
		}
	}
	private class ListenForCalOpButtons implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lYearBut) moveMonth(-12);
			else if(e.getSource() == lMonBut) moveMonth(-1);
			else if(e.getSource() == nMonBut) moveMonth(1);
			else if(e.getSource() == nYearBut) moveMonth(12);
			
			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>\r"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+"\r / \r"+calYear+"\r</th></tr></table></html>");
			showCal();
		}
	}
	private class listenForDateButs implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int k=0,l=0;
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					if(e.getSource() == dateButs[i][j]){ 
						k=i;
						l=j;
					}
				}
			}
	
			if(!(k ==0 && l == 0)) calDayOfMon = calDates[k][l];

			cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
			
			String dDayString = new String();
			int dDay=((int)((cal.getTimeInMillis() - today.getTimeInMillis())/1000/60/60/24));
			if(dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)) 
					&& (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))) dDayString = "Today"; 
			else if(dDay >=0) dDayString = "D-"+(dDay+1);
			else if(dDay < 0) dDayString = "D+"+(dDay)*(-1);
		
		}
	}
	public static void main(String[] args){

	}
}

