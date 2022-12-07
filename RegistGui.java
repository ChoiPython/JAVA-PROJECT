import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    String imgaddr = null;
    private JPanel contentPane;
    private JTextField idTextField;        // 사원번호
    private JTextField nameTextField;    // 사원이름
    private JTextField departTextField;    // 부서
    private JTextField rankTextField;    // 직급
    private JTextField pointTextField;    // 상벌점->포인트로 변경
    private JTextField halfwayTextField;    // 반차

    private final List<JTextField> fieldList;

    public static void main(String[] args) {
        new RegistGui();
    }

    public RegistGui() {

        setTitle("사원정보 등록창"); //폼 제목
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 679, 440);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
     // 창 크기 변환x
     		setResizable(false);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 제목 라벨
        JLabel titleLabel = new JLabel("관리자");
        titleLabel.setBounds(274, 20, 99, 36);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("궁서체", Font.BOLD | Font.ITALIC, 30));
        contentPane.add(titleLabel);


        // 사원번호 라벨
        JLabel idLabel = new JLabel("사번");
        idLabel.setBounds(216, 86, 50, 15);
        contentPane.add(idLabel);

        // 이름 라벨
        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(216, 131, 50, 15);
        contentPane.add(nameLabel);

        // 부서 라벨
        JLabel departLabel = new JLabel("부서");
        departLabel.setBounds(216, 179, 50, 15);
        contentPane.add(departLabel);

        // 직급 라벨
        JLabel rankLabel = new JLabel("직급");
        rankLabel.setBounds(216, 230, 50, 15);
        contentPane.add(rankLabel);

        // 반차 라벨
        JLabel halfwayLabel = new JLabel("반차");
        halfwayLabel.setBounds(216, 278, 80, 15);
        contentPane.add(halfwayLabel);

        // 상벌점 라벨->포인트 라벨
        JLabel pointLabel = new JLabel("포인트");
        pointLabel.setBounds(206, 323, 50, 15);
        contentPane.add(pointLabel);

        // 사원번호 텍필
        idTextField = new JTextField();
        idTextField.setBounds(257, 83, 360, 21);
        contentPane.add(idTextField);
        idTextField.setName("사원번호");
        idTextField.setColumns(10);

        // 사원이름 텍필
        nameTextField = new JTextField();
        nameTextField.setBounds(257, 128, 360, 21);
        nameTextField.setColumns(10);
        nameTextField.setName("사원이름");
        contentPane.add(nameTextField);

        // 부서 텍필
        departTextField = new JTextField();
        departTextField.setBounds(257, 176, 360, 21);
        departTextField.setColumns(10);
        departTextField.setName("부서");
        contentPane.add(departTextField);

        // 직급 텍필
        rankTextField = new JTextField();
        rankTextField.setBounds(257, 227, 360, 21);
        rankTextField.setColumns(10);
        rankTextField.setName("직급");
        contentPane.add(rankTextField);

        // 반차 텍필
        halfwayTextField = new JTextField();
        halfwayTextField.setBounds(257, 274, 360, 21);
        halfwayTextField.setColumns(10);
        halfwayTextField.setName("반차");
        contentPane.add(halfwayTextField);

        // 상벌점 텍필 ->포인트 텍필
        pointTextField = new JTextField();
        pointTextField.setBounds(257, 320, 360, 21);
        pointTextField.setColumns(10);
        pointTextField.setName("포인트");
        contentPane.add(pointTextField);

        JLabel imgLabel = new JLabel("");
        imgLabel.setBounds(27, 104, 156, 157);
        contentPane.add(imgLabel);
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource("/employee.jpg")); //이미지 파일명
        imgLabel.setIcon(icon);

        // 사진선택 버튼
        JButton selecimg = new JButton("사진 선택\r\n");
        selecimg.setBounds(61, 279, 91, 30);
        contentPane.add(selecimg);

        fieldList=Arrays.asList(idTextField,nameTextField,departTextField,rankTextField,pointTextField,halfwayTextField);
        // 등록 버튼
        JButton registButton = new JButton("등록");
        registButton.setBounds(526, 361, 91, 30);
        registButton.addActionListener(e -> {
            if (e.getSource() == registButton) {
                List<String> fieldNames = fieldList.stream()
                    .filter(i -> i.getText().equals("")).map(Component::getName)
                    .collect(Collectors.toList());
                if (fieldNames.size() > 0) {
                    JOptionPane.showMessageDialog(null, String.join(",",fieldNames)+" 필드값이 비어있습니다.", "로그인 실패", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                DBA db = new DBA();
                int id, halfway, point;
                String name, depart, rank;
                id = Integer.parseInt(idTextField.getText());
                halfway = Integer.parseInt(halfwayTextField.getText());
                point = Integer.parseInt(pointTextField.getText());
                name = nameTextField.getText();
                depart = departTextField.getText();
                rank = rankTextField.getText();
                try {
                    db.insertData(id, name, depart, rank, halfway, point, imgaddr);
                } catch (RuntimeException ex) {
                    Throwable cause = ex.getCause();
                    if (cause instanceof SQLIntegrityConstraintViolationException) {
                        JOptionPane.showMessageDialog(null, "이미 존재하는 사원번호 입니다.");
                    }
                    return;
                }
                JOptionPane.showMessageDialog(null, "등록되었습니다"); //버튼1 클릭시 "등록되었습니다" 메세지창 출력
            }
            dispose();
        });
        contentPane.add(registButton);

        //------------------------------------------//
        // 이미지 파일 선택하는 거인듯?
        Container contentPane = getContentPane();

        setLocation(100, 100);
        setPreferredSize(new Dimension(700, 450));

        FileDialog image = new FileDialog(this, "사진 선택", FileDialog.LOAD); //이미지 파일 읽기

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setBounds(0, 0, 0, 0);
        contentPane.add(imagePanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 0, 0);
        JTextField text = new JTextField(30);
        controlPanel.add(text);
        contentPane.add(controlPanel);

        selecimg.addActionListener(e -> {  //버튼을 클릭했을때 이미지 디렉토리 경로와 파일이름 가져오기
            image.setVisible(true);
            if (image.getFile() != null) {
                ImageIcon imageIcon = new ImageIcon(image.getDirectory() + image.getFile());
                imgLabel.setIcon(new ImageIcon(imageIcon.getImage()
                    .getScaledInstance(156, 161, Image.SCALE_SMOOTH)));//getScaledInstance 를 사용하여 사진을 크기에 맞춰서 출력
                imgaddr = image.getDirectory() + image.getFile();
            }
            imagePanel.repaint(); //repaint 메소드 호출
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);    // 화면 중간 출력
    }
}