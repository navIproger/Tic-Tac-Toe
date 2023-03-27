import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuGame {
    private static JFrame frameMenu = new JFrame();

    private static JButton btnStart = null;
    private static JButton btnReset = null;
    private static JEditorPane namePlayer1 = null;
    private static JEditorPane namePlayer2 = null;
    private static JLabel labelGame = null;
    private static JLabel winner = null;
    private static JLabel score = null;

    private static int scorePl1 = 0;
    private static int scorePl2 = 0;

    private static void createEl(JPanel panel, JComponent component, int width, int height, int x, int y) {
        Dimension d = new Dimension(width, height);
        panel.add(component);
        component.setSize(width, height);
        component.setMinimumSize(d);
        component.setPreferredSize(d);
        component.setMaximumSize(d);
        panel.setBounds(x, y, width, height);
        panel.setLayout(null);
        frameMenu.add(panel);
    }

    public void playGame() {
        frameMenu.setSize(600, 635);
        frameMenu.setVisible(true);
        frameMenu.getContentPane().setLayout(null);
        frameMenu.setTitle("Хрестики - Нолики: Меню");

        JPanel panelBtnStart = new JPanel();
        JPanel panelBtnReset = new JPanel();
        JPanel panelName1 = new JPanel();
        JPanel panelName2 = new JPanel();
        JPanel panelNameGame = new JPanel();
        JPanel panelWinner = new JPanel();
        JPanel panelScore = new JPanel();

        btnStart = new JButton();
        btnReset = new JButton();
        namePlayer1 = new JEditorPane();
        namePlayer2 = new JEditorPane();
        labelGame = new JLabel();
        winner = new JLabel();
        score = new JLabel();

        btnStart.setText("Почати");
        btnStart.setFont(new Font(btnStart.getFont().getName(), Font.BOLD, 70));
        createEl(panelBtnStart, btnStart, 300, 100, 150, 400);

        btnStart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayField pf = new PlayField();
                pf.game();
            }
        });

        btnReset.setText("Скинути");
        btnReset.setFont(new Font(btnReset.getFont().getName(), Font.BOLD, 45));
        createEl(panelBtnReset, btnReset, 300, 50, 150, 500);

        btnReset.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setScore(-1, -1);
                namePlayer1.setText("Гравець 1");
                namePlayer2.setText("Гравець 2");
                winner.setText("Переможеть");
                PlayField.setNumGame(0);
            }
        });

        createEl(panelName1, namePlayer1, 175, 25, 50, 200);
        namePlayer1.setText("Гравець 1");

        createEl(panelName2, namePlayer2, 175, 25, 350, 200);
        namePlayer2.setText("Гравець 2");

        createEl(panelNameGame, labelGame, 400, 50, 100, 50);
        labelGame.setText("ХРЕСТИКИ - НУЛИКИ");
        labelGame.setFont(new Font(labelGame.getFont().getName(), Font.BOLD, 35));
        labelGame.setHorizontalAlignment(SwingConstants.CENTER);

        createEl(panelWinner, winner, 600, 40, 0, 100);
        winner.setText("Переможець");
        winner.setFont(new Font(winner.getFont().getName(), Font.BOLD, 35));
        winner.setHorizontalAlignment(SwingConstants.CENTER);

        createEl(panelScore, score, 50, 25, 287, 200);
        score.setText(scorePl1 + " : " + scorePl2);
    }

    public static void winner(String winner) {
        MenuGame.winner.setText(winner);
    }

    public static String getNamePlayer1() {
        return namePlayer1.getText();
    }

    public static String getNamePlayer2() {
        return namePlayer2.getText();
    }

    public static void setScore(int score1, int score2) {
        if (score1 >= 0 && score2 >= 0) {
            scorePl1 += score1;
            scorePl2 += score2;
        } else {
            scorePl1 = 0;
            scorePl2 = 0;
        }
        score.setText(scorePl1 + " : " + scorePl2);
    }
}
