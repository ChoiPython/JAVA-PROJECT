import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class adminGui extends JFrame implements ActionListener {
    JTextField searchText;
    JButton searchBtn;    // 검색버튼
    JButton insertBtn;    // 등록버튼
    JButton shopBtn;    // 상점버튼
    JButton refreshBtn;    // 새로고침버튼
    JButton viewBtn; //상세보기버튼
    JTable users;
    JScrollPane scroll;
    JComboBox YearBox;
    JComboBox MonthBox;

    private LocalDate nowDate = LocalDate.now();;

    public adminGui(ArrayList<User2> list) {
        setTitle("관리자");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentpane = getContentPane();
        contentpane.setBackground(Color.LIGHT_GRAY);
        getContentPane().setLayout(null);

        //검색
        JLabel searchLabel = new JLabel("이름 :");
        searchLabel.setBounds(70, 40, 67, 25);
        searchLabel.setFont(new Font("", Font.BOLD, 15));
        contentpane.add(searchLabel);
        searchText = new JTextField();
        searchText.setBounds(130, 40, 280, 25);
        contentpane.add(searchText);
        searchBtn = new JButton("검색");
        searchBtn.setBounds(430, 40, 67, 25);
        searchBtn.addActionListener(this);
        contentpane.add(searchBtn);
        refreshBtn = new JButton("새로고침");
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

        viewBtn = new JButton("상세보기");
        viewBtn.setBounds(650, 294, 110, 30);
        viewBtn.addActionListener(this);
        contentpane.add(viewBtn);

        //테이블 데이터 set
        String column[] = {"사원번호", "사원이름", "출근", "지각", "결근"};
        String[][] s = new String[100][7];//= {{null,null,null,null,null,null,null}};
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 0)
                    s[i][j] = list.get(i).getId() + "";
                if (j == 1)
                    s[i][j] = list.get(i).getName();
                if (j == 2)
                    s[i][j] = list.get(i).getAttendance() + "";
                if (j == 3)
                    s[i][j] = list.get(i).getTardy() + "";
                if (j == 4)
                    s[i][j] = list.get(i).getAbsence() + "";
            }
        }

        tableSetting(column, s);
        users.getTableHeader().setReorderingAllowed(false);
        users.getTableHeader().setResizingAllowed(false);

        // 테이블 스크롤 바 생성
        scroll = new JScrollPane(users);
        scroll.setBounds(40, 172, 580, 358);
        contentpane.add(scroll);

        YearBox = new JComboBox();
        YearBox.setBounds(47, 103, 90, 23);
        String[] year = new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        YearBox.setModel(new DefaultComboBoxModel(year));
        getContentPane().add(YearBox);

        MonthBox = new JComboBox();
        MonthBox.setBounds(150, 103, 54, 23);
        String [] month = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        MonthBox.setModel(new DefaultComboBoxModel(month));
        getContentPane().add(MonthBox);
        
        // 콤보박스 디폴트 선택 - 현재 월 선택
        int intmon = nowDate.getMonthValue();
        String nowmonth = Integer.toString(intmon);
        if(Arrays.stream(month).anyMatch(str -> str.equals(nowmonth))) {
        	MonthBox.setSelectedIndex(intmon-1);
        }
        
        // 년 선택
        int nowyear = nowDate.getYear();
        String stryear = Integer.toString(nowyear);
        if(Arrays.stream(year).anyMatch(str -> str.equals(stryear))) {
        	YearBox.setSelectedIndex(nowyear - 2022);
        }
        

        //퇴근 버튼
        JButton getoffBtn = new JButton("퇴근");
        getoffBtn.addActionListener(e -> {
        	nowDate = LocalDate.now();
            System.out.println("nowDate = " + nowDate);
        });
        getoffBtn.setBounds(650, 495, 67, 23);
        getContentPane().add(getoffBtn);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);    // 화면중간출력
        setResizable(false);            // 크기조절

        YearBox.addActionListener(e -> {
            refreshBtn.doClick();
        });
        MonthBox.addActionListener(e -> {
            refreshBtn.doClick();
        });
    }

    private void tableSetting(String[] column, String[][] s) {   //table 선택가능하지만 수정불가
        TableModel model = new DefaultTableModel(s, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 테이블 생성
        users = new JTable(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 등록
        if (e.getSource() == insertBtn) {
            new RegistGui();
        } else if (e.getSource() == viewBtn) {
            //            ViewGui viewgui = new ViewGui(); // 무조건 화면 열림
            if (!users.getSelectionModel().isSelectionEmpty()
                && users.getSelectedRows().length == 1) { //users 테이블 1개만 선택
                int row = users.getSelectedRow();
                DBA db = new DBA();
                if (users.getModel().getValueAt(row, 0) == null) {
                    JOptionPane.showMessageDialog(null, "빈데이터는 조회할수없습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User user = db.selectIdData(Integer.parseInt((String)users.getModel().getValueAt(row, 0)));
                ViewGui viewgui = new ViewGui(user);
                viewgui.setUser(user);
                viewgui.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "셀을 한개만 선택해주세요", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == shopBtn) {
            AdminShop adminShop = new AdminShop();
            adminShop.setLocation(600, 100);
            setLocation(200, 100);
        }
        // 검색이벤트
        else if (e.getSource() == searchBtn) {
            String date = YearBox.getSelectedItem().toString() + '-' + MonthBox.getSelectedItem().toString() + "-01";
            Date d = Date.valueOf(date);
            if (searchText.getText().equals("")) {
            } else {
                DBA db = new DBA();
                ArrayList<User2> list = new ArrayList<>();
                scroll.setVisible(false);
                db.selectNameData(list, d, searchText.getText());
                String column[] = {"사원번호", "사원이름", "출근", "지각", "결근"};
                String[][] s = new String[100][7];
                for (int i = 0; i < list.size(); i++) {
                    s[i][0] = list.get(i).getId() + "";
                    s[i][1] = list.get(i).getName();
                    s[i][2] = list.get(i).getAttendance() + "";
                    s[i][3] = list.get(i).getTardy() + "";
                    s[i][4] = list.get(i).getAbsence() + "";
                }

                users = new JTable(s, column);
                users.getTableHeader().setReorderingAllowed(false);
                users.getTableHeader().setResizingAllowed(false);
                scroll = new JScrollPane(users);
                scroll.setBounds(40, 172, 580, 358);
                getContentPane().add(scroll);
            }
        }
        // 새로고침 이벤트
        else if (e.getSource() == refreshBtn) {
            String date = YearBox.getSelectedItem().toString() + '-' + MonthBox.getSelectedItem().toString() + "-01";
            Date d = Date.valueOf(date);
            searchText.setText("");
            DBA db = new DBA();
            ArrayList<User2> list = new ArrayList<>();
            scroll.setVisible(false);
            db.selectAllData(list, d);

            String column[] = {"사원번호", "사원이름", "출근", "지각", "결근"};
            String[][] s = new String[100][7];
            for (int i = 0; i < list.size(); i++) {
                s[i][0] = list.get(i).getId() + "";
                s[i][1] = list.get(i).getName();
                s[i][2] = list.get(i).getAttendance() + "";
                s[i][3] = list.get(i).getTardy() + "";
                s[i][4] = list.get(i).getAbsence() + "";
            }

            tableSetting(column, s);
            users.getTableHeader().setReorderingAllowed(false);
            users.getTableHeader().setResizingAllowed(false);
            scroll = new JScrollPane(users);
            scroll.setBounds(40, 172, 580, 358);
            getContentPane().add(scroll);
        }
    }
}
