import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class ViewGui extends JFrame {

    private JPanel contentPane;
    

    public static void main(String[] args) {
//        new ViewGui();
    }

    public ViewGui(User user) {
        setBackground(UIManager.getColor("CheckBox.background"));
        setTitle("사원 상세보기"); //폼 제목
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 599, 300);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("scrollbar"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Container c = getContentPane();
        c.setLayout(null);


        JButton updateBtn = new JButton("수정");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                ModifyGui md = new ModifyGui(user);
                md.setVisible(true);
                new ViewGui(user).setUser(user);
            }
        });
        updateBtn.setBounds(450, 160, 90, 30);
        contentPane.add(updateBtn);

        JButton deleteBtn = new JButton("삭제");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == deleteBtn) {
                	int delid = user.getId();
                	DBA db = new DBA();
                	db.deleteData(delid);
                	int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
                	// 삭제
                	if(result == JOptionPane.YES_OPTION) {
                		JOptionPane.showMessageDialog(null, "성공적으로 삭제되었습니다.");
                		dispose();  
//                		System.exit(0);
                		
                		
                	}
                }
            }
        });
        deleteBtn.setBounds(450, 200, 90, 30);
        contentPane.add(deleteBtn);

        JLabel name = new JLabel("이름 |");
        name.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        name.setBounds(200, 70, 133, 50);
        contentPane.add(name);

        JLabel position = new JLabel("직급 |");
        position.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        position.setBounds(200, 100, 100, 50);
        contentPane.add(position);

        JLabel department = new JLabel("부서 |");
        department.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        department.setBounds(200, 130, 133, 50);
        contentPane.add(department);

        JLabel point = new JLabel("포인트 |");
        point.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        point.setBounds(180, 160, 150, 50);
        contentPane.add(point);


    }

    public void setUser(User user) {

        JLabel uname = new JLabel(user.getName());
        uname.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        uname.setBounds(260, 70, 133, 50);
        contentPane.add(uname);
        contentPane.add(uname);

        JLabel uposition = new JLabel(user.getRank());
        uposition.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        uposition.setBounds(260, 100, 100, 50);
        contentPane.add(uposition);

        JLabel udepartment = new JLabel(user.getDepart());
        udepartment.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        udepartment.setBounds(260, 130, 133, 50);
        contentPane.add(udepartment);

        JLabel upoint = new JLabel(String.valueOf(user.getPoint()));
        upoint.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        upoint.setBounds(260, 160, 150, 50);
        contentPane.add(upoint);
        String imgaddr = user.getImgaddr();

        JLabel imageLabel = new JLabel("");
	      imageLabel.setBounds(30, 30, 150, 187);
	      contentPane.add(imageLabel);
	      javax.swing.ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource("/employee.jpg")); //이미지 파일명
	      imageLabel.setIcon(icon);

    }
}