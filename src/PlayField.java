import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayField {
    static private JFrame framePF = null;
    static private JLabel step = new JLabel();
    static private Dimension d = null;

    static private int pos = 0;
    static private String action = "X";
    static private int numGame = 0;

    static private List<Integer> posX = null;
    static private List<Integer> posO = null;

    static private int[][] winCom = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    static private void play(JButton btn) {
        btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn.setText(action);
                btn.setFont(new Font(btn.getFont().getName(), Font.BOLD, 210));
                btn.setEnabled(false);

                if (action.equals("X")) posX.add(Integer.parseInt(btn.getToolTipText()));
                else posO.add(Integer.parseInt(btn.getToolTipText()));
                action = action.equals("X") ? "O" : "X";

                if (posX.size() > 2 || posO.size() > 2) {
                    win();
                }

                if (numGame % 2 != 0 && action.equals("O")) {
                    step.setText("Хід гравця: " + MenuGame.getNamePlayer2());
                } else if (numGame % 2 != 0 && action.equals("X")) {
                    step.setText("Хід гравця: " + MenuGame.getNamePlayer1());
                }else if (numGame % 2 == 0 && action.equals("O")) {
                    step.setText("Хід гравця: " + MenuGame.getNamePlayer1());
                } else if (numGame % 2 == 0 && action.equals("X")) {
                    step.setText("Хід гравця: " + MenuGame.getNamePlayer2());
                }
            }
        });
    }

    static private void start() {
        framePF = new JFrame();
        action = "X";
        numGame++;
        pos = 0;
        posX = new ArrayList<>();
        posO = new ArrayList<>();
        d = new Dimension(200, 200);

        if (numGame % 2 != 0) {
            step.setText("Хід гравця: " + MenuGame.getNamePlayer1());
        } else if (numGame % 2 == 0) {
            step.setText("Хід гравця: " + MenuGame.getNamePlayer2());
        }
    }

    static private void win() {
        posX.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        posO.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });


        for (int elWCA[] : winCom) {
            if (searchInList(posX, elWCA[0]) && searchInList(posX, elWCA[1]) && searchInList(posX, elWCA[2])) {
                MenuGame.winner("Перемога: " + MenuGame.getNamePlayer1());
                if (numGame % 2 != 0) {
                    MenuGame.setScore(1, 0);
                } else {
                    MenuGame.setScore(0, 1);
                }
                framePF.setVisible(false);
                break;
            } else if (searchInList(posO, elWCA[0]) && searchInList(posO, elWCA[1]) && searchInList(posO, elWCA[2])) {
                MenuGame.winner("Перемога: " + MenuGame.getNamePlayer2());
                if (numGame % 2 != 0) {
                    MenuGame.setScore(0, 1);
                } else {
                    MenuGame.setScore(1, 0);
                }
                framePF.setVisible(false);
                break;
            } else if ((posX.size() == 5 || posO.size() == 5) && elWCA.equals(winCom[7])) {
                MenuGame.winner("Нічия");
                MenuGame.setScore(0, 0);
                framePF.setVisible(false);
            }
        }

    }

    public void game() {
        start();

        framePF.setSize(600, 700);
        framePF.setVisible(true);
        framePF.getContentPane().setLayout(null);
        framePF.setTitle("Хрестики - Нулики");

        JButton btn1 = new JButton();
        JPanel panel1 = new JPanel();
        buttonSet(btn1, panel1, 0, 0, d);
        play(btn1);

        JButton btn2 = new JButton();
        JPanel panel2 = new JPanel();
        buttonSet(btn2, panel2, 200, 0, d);
        play(btn2);

        JButton btn3 = new JButton();
        JPanel panel3 = new JPanel();
        buttonSet(btn3, panel3, 400, 0, d);
        play(btn3);

        JButton btn4 = new JButton();
        JPanel panel4 = new JPanel();
        buttonSet(btn4, panel4, 0, 200, d);
        play(btn4);

        JButton btn5 = new JButton();
        JPanel panel5 = new JPanel();
        buttonSet(btn5, panel5, 200, 200, d);
        play(btn5);

        JButton btn6 = new JButton();
        JPanel panel6 = new JPanel();
        buttonSet(btn6, panel6, 400, 200, d);
        play(btn6);

        JButton btn7 = new JButton();
        JPanel panel7 = new JPanel();
        buttonSet(btn7, panel7, 0, 400, d);
        play(btn7);

        JButton btn8 = new JButton();
        JPanel panel8 = new JPanel();
        buttonSet(btn8, panel8, 200, 400, d);
        play(btn8);

        JButton btn9 = new JButton();
        JPanel panel9 = new JPanel();
        buttonSet(btn9, panel9, 400, 400, d);
        play(btn9);

        JPanel panelStep = new JPanel();
        Dimension dimension = new Dimension(600, 65);
        buttonSet(step, panelStep, 0, 600, dimension);
        step.setSize(dimension);
        panelStep.setBounds(0, 600, 600, 65);
        step.setHorizontalAlignment(SwingConstants.CENTER);
        step.setVerticalAlignment(SwingConstants.CENTER);
        step.setFont(new Font(step.getFont().getName(), Font.BOLD, 35));
    }

    static private void buttonSet(JComponent btn, JPanel panel, int posX, int posY, Dimension dim) {
        btn.setSize(framePF.getWidth() / 3, framePF.getWidth() / 3);
        panel.add(btn);
        panel.setBounds(posX, posY, framePF.getWidth() / 3, framePF.getWidth() / 3);
        btn.setMinimumSize(dim);
        btn.setPreferredSize(dim);
        btn.setMaximumSize(dim);
        panel.setLayout(null);
        framePF.add(panel);
        btn.setToolTipText(String.valueOf(pos++));
    }

    static private boolean searchInList(List<Integer> list, int number) {
        for (int el : list) {
            if (el == number) {
                return true;
            }
        }
        return false;
    }

    public static void setNumGame(int numGame) {
        PlayField.numGame = numGame;
    }
}
