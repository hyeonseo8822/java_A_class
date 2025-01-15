package day07_08_db;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JLabel idL = new JLabel("아이디");
    JLabel pwL = new JLabel("비밀번호");

    JTextField id = new JTextField();
    JPasswordField password = new JPasswordField(); // JPasswordField로 변경

    JButton log = new JButton("로그인");
    JButton join = new JButton("회원가입");
    JButton exit = new JButton("프로그램 종료");

    Operator o = null;

    public Login(Operator oo) {
        o = oo;
        setTitle("로그인");

        centerPanel.setPreferredSize(new Dimension(260, 80));
        westPanel.setPreferredSize(new Dimension(210, 75));
        eastPanel.setPreferredSize(new Dimension(90, 75));
        southPanel.setPreferredSize(new Dimension(290, 40));

        idL.setPreferredSize(new Dimension(50, 30));
        pwL.setPreferredSize(new Dimension(50, 30));

        id.setPreferredSize(new Dimension(140, 30));
        password.setPreferredSize(new Dimension(140, 30));

        log.setPreferredSize(new Dimension(75, 63));
        join.setPreferredSize(new Dimension(135, 25));
        exit.setPreferredSize(new Dimension(135, 25));

        setContentPane(basePanel); // 기본 컨테이너로 설정

        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(southPanel, BorderLayout.SOUTH);
        basePanel.add(westPanel, BorderLayout.WEST);
        basePanel.add(eastPanel, BorderLayout.EAST);

        westPanel.setLayout(new FlowLayout());
        eastPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());

        // westPanel 컴포넌트
        westPanel.add(idL);
        westPanel.add(id);
        westPanel.add(pwL);
        westPanel.add(password);

        // eastPanel 컴포넌트
        eastPanel.add(log);

        southPanel.add(exit);
        southPanel.add(join);

        ButtonListener bl = new ButtonListener();

        log.addActionListener(bl);
        exit.addActionListener(bl);
        join.addActionListener(bl);

        setSize(310, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            String uid = id.getText();
            String upass = new String(password.getPassword()); // JPasswordField에서 비밀번호 가져오기

            if (b.getText().equals("프로그램 종료")) {
                System.out.println("프로그램 종료");
                System.exit(0);
            } else if (b.getText().equals("회원가입")) {
            	
            } else if (b.getText().equals("로그인")) {
                if (uid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("로그인 실패 > 로그인 정보 미입력");
                } else if (uid != null && upass != null) {
                    if (o.db.loginCheck(uid, upass)) {
                        System.out.println("로그인 성공");
                        JOptionPane.showMessageDialog(null, "로그인 성공");
                    } else {
                        System.out.println("로그인 실패 > 로그인 정보 불일치");
                        JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Operator 객체 생성 및 Login 인스턴스 실행
        Operator operator = new Operator(); // Operator 클래스를 적절히 구현해야 합니다.
        new Login(operator);
    }
}
