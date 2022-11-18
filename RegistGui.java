
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class ImagePanel2 extends JPanel {
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

public class RegistGui extends JFrame {

   private JPanel contentPane;
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTextField textField_5;

   public static void main(String[] args) {
	   new RegistGui();
   }

   public RegistGui() {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 679, 440);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      
      
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("관리자");
      lblNewLabel.setBounds(274, 20, 99, 36);
      lblNewLabel.setForeground(Color.BLUE);
      lblNewLabel.setFont(new Font("궁서체", Font.BOLD | Font.ITALIC, 30));
      contentPane.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setBounds(27, 104, 156, 164);
      contentPane.add(lblNewLabel_1);
      
      JLabel lblNewLabel_1_1 = new JLabel("부서");
      lblNewLabel_1_1.setBounds(216, 131, 50, 15);
      contentPane.add(lblNewLabel_1_1);
      
      JLabel lblNewLabel_1_2 = new JLabel("직급");
      lblNewLabel_1_2.setBounds(216, 179, 50, 15);
      contentPane.add(lblNewLabel_1_2);
      
      JLabel lblNewLabel_1_3 = new JLabel("사원번호");
      lblNewLabel_1_3.setBounds(195, 230, 50, 15);
      contentPane.add(lblNewLabel_1_3);
      
      JLabel lblNewLabel_1_4 = new JLabel("상/벌점");
      lblNewLabel_1_4.setBounds(205, 323, 50, 15);
      contentPane.add(lblNewLabel_1_4);
      
      textField = new JTextField();
      textField.setBounds(257, 83, 360, 21);
      contentPane.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setBounds(257, 128, 360, 21);
      textField_1.setColumns(10);
      contentPane.add(textField_1);
      
      textField_2 = new JTextField();
      textField_2.setBounds(257, 176, 360, 21);
      textField_2.setColumns(10);
      contentPane.add(textField_2);
      
      textField_3 = new JTextField();
      textField_3.setBounds(257, 227, 360, 21);
      textField_3.setColumns(10);
      contentPane.add(textField_3);
      
      textField_4 = new JTextField();
      textField_4.setBounds(257, 320, 360, 21);
      textField_4.setColumns(10);
      contentPane.add(textField_4);
      javax.swing.ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource("/employee.jpg")); //이미지 파일명
        lblNewLabel_1.setIcon(icon);
      
      JButton btnNewButton = new JButton("사진 선택\r\n");
      btnNewButton.setBounds(61, 279, 91, 30);
      contentPane.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("등록");
      btnNewButton_1.setBounds(526, 361, 91, 30);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnNewButton_1) {
               JOptionPane.showMessageDialog(null,"등록되었습니다"); //버튼1 클릭시 "등록되었습니다" 메세지창 출력
            }
            dispose();
         }
      });
      contentPane.add(btnNewButton_1);
      
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

        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setBounds(69, 113, 164, 161);
        getContentPane().add(lblNewLabel_6);
        
        textField_5 = new JTextField();
        textField_5.setBounds(257, 274, 360, 21);
        textField_5.setColumns(10);
        contentPane.add(textField_5);
        
        JLabel lblNewLabel_3 = new JLabel("사원번호 확인");
        lblNewLabel_3.setBounds(175, 278, 80, 15);
        contentPane.add(lblNewLabel_3);

      btnNewButton.addActionListener(e -> {  //버튼을 클릭했을때 이미지 디렉토리 경로와 파일이름 가져오기
            image.setVisible(true);
            if (image.getFile() != null) {
                ImageIcon imageIcon = new ImageIcon(image.getDirectory() + image.getFile());
                lblNewLabel_1.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(156, 161, Image.SCALE_SMOOTH)));//getScaledInstance 를 사용하여 사진을 크기에 맞춰서 출력
            }
            imagePanel.repaint(); //repaint 메소드 호출
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);	// 화면 중간 출력
        setResizable(false);
   }

}