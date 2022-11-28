
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;



class ImagePanel extends JPanel {
    Image image;
    Toolkit toolkit = getToolkit();

    void setPath(String path) {
        image = toolkit.getImage(path); //이미지 경로 저장
    }

    public void paint(Graphics g) { //이미지 불러오기
        g.clearRect(0, 0, getWidth(), getHeight());
        if (image != null)
            g.drawImage(image, 0, 0, this);
    }
}

public class ModifyGui extends JFrame {

   private JPanel contentPane;
   private JTextField nameField;	// 사원이름
   private JTextField partField;	// 부서
   private JTextField rankField;	// 직급
   private JTextField idField;		// 사원번호
   private JTextField pointField;	// 포인트
   private User user;


   public static void main(String[] args) {
//	   User user = new User();
//	   user.setName("최주영");
//	   user.setDepart("a");
//	   user.setId(123);
//	   user.setPoint(20);
//	   user.setRank("학생");
//	   new ModifyGui(user);
   }


   public ModifyGui(User user) {
	   this.user = user;
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 704, 428);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      // 제목 라벨
      JLabel titleLabel = new JLabel("관리자");
      titleLabel.setBounds(274, 20, 99, 36);
      titleLabel.setForeground(Color.BLUE);
      titleLabel.setFont(new Font("궁서체", Font.BOLD | Font.ITALIC, 30));
      contentPane.add(titleLabel);
      
      // 공백? 
      JLabel empytyLabel = new JLabel();
      empytyLabel.setBounds(27, 104, 156, 164);
      contentPane.add(empytyLabel);
      
      // 부서 라벨
      JLabel partLabel = new JLabel("부서");
      partLabel.setBounds(216, 131, 50, 15);
      contentPane.add(partLabel);
      
      // 직급 라벨
      JLabel rankLabel = new JLabel("직급");
      rankLabel.setBounds(216, 179, 50, 15);
      contentPane.add(rankLabel);
      
      // 사원번호 라벨
//      JLabel idLabel = new JLabel("사원번호");
//      idLabel.setBounds(195, 230, 50, 15);
//      contentPane.add(idLabel);
      
      // 포인트 라벨
      JLabel pointLabel = new JLabel("포인트");
      pointLabel.setBounds(195, 230, 50, 15);
      contentPane.add(pointLabel);
      
      // 이름 텍스트필드
      nameField = new JTextField(user.getName());
      nameField.setBounds(257, 83, 360, 21);
      contentPane.add(nameField);
      nameField.setColumns(10);
      
      // 부서 텍스트 필드
      partField = new JTextField(user.getDepart());
      partField.setBounds(257, 128, 360, 21);
      partField.setColumns(10);
      contentPane.add(partField);
      
      // 직급 텍스트 필드
      rankField = new JTextField(user.getRank());
      rankField.setBounds(257, 176, 360, 21);
      rankField.setColumns(10);
      contentPane.add(rankField);
      
      // 사원번호 텍스트필드
//      idField = new JTextField("사원번호 삭제");
//      idField.setBounds(257, 227, 360, 21);
//      idField.setColumns(10);
//      contentPane.add(idField);
      
      // 포인트 텍스트 필드
      pointField = new JTextField(Integer.toString(user.getPoint()));
      pointField.setBounds(257, 227, 360, 21);
      pointField.setColumns(10);
      contentPane.add(pointField);
      javax.swing.ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource("/employee.jpg")); //이미지 파일명
      empytyLabel.setIcon(icon);
      
      // 사진선택 버튼
      JButton selectimg = new JButton("사진 선택\r\n");
      selectimg.setBounds(61, 279, 91, 30);
      contentPane.add(selectimg);
      
      // 수정 버튼
      JButton modiButton = new JButton("수정");
      modiButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(e.getSource()==modiButton) {
            	DBA db = new DBA();
            	// set data
            	int id = user.getId();
            	String name = nameField.getText();
            	String depart = partField.getText();
            	String rank = rankField.getText();
            	int point = Integer.parseInt(pointField.getText());
//            	String imgaddr = 
            	// 수정 진행
            	db.updateData(id, name, depart, rank, point, null);
            	// user set
            	user.setAll(name, depart, rank, point);
            	JOptionPane.showMessageDialog(null,"수정되었습니다");  //버튼1 클릭시 "등록되었습니다" 메세지창 출력
            	// 상세보기 다시 띄우기
            	ViewGui vGui = new ViewGui(user);
            	vGui.setUser(user);
            	vGui.setVisible(true);
            	
            	
            	
               
            }
            dispose();
         }
      });
      modiButton.setBounds(526, 314, 91, 30);
      contentPane.add(modiButton);
      
      JLabel lblNewLabel_2 = new JLabel("이름");
      lblNewLabel_2.setBounds(216, 86, 50, 15);
      contentPane.add(lblNewLabel_2);
      //------------------------------------------//
        Container contentPane = getContentPane();

        setLocation(100, 100);
        setPreferredSize(new Dimension(700, 450));


        FileDialog image = new FileDialog(this,"사진 선택", FileDialog.LOAD); //이미지 파일 읽기
        
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setBounds(0, 0, 0, 0);
        contentPane.add(imagePanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 0, 0);
        JTextField text = new JTextField(30);
        controlPanel.add(text);
        contentPane.add(controlPanel);
        
        //??
//        JLabel lblNewLabel_6 = new JLabel("");
//        lblNewLabel_6.setBounds(69, 113, 164, 161);
//        getContentPane().add(lblNewLabel_6);

        selectimg.addActionListener(e -> {  //버튼을 클릭했을때 이미지 디렉토리 경로와 파일이름 가져오기
            image.setVisible(true);
            if (image.getFile() != null) {
                ImageIcon imageIcon = new ImageIcon(image.getDirectory() + image.getFile());
                empytyLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(156, 161, Image.SCALE_SMOOTH)));//getScaledInstance 를 사용하여 사진을 크기에 맞춰서 출력
            }
            imagePanel.repaint(); //repaint 메소드 호출
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
   }

}