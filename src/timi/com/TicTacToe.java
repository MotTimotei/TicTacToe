package timi.com;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel tittle_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();//Timi
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    private int btns_clicked = 0;

    TicTacToe(){//Try to see what happens
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 570);
        frame.getContentPane().setBackground(new Color(20, 189, 172));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(20, 189, 172));
        textfield.setForeground(new Color(13, 161, 146));
        textfield.setFont(new Font("Monaco", Font.PLAIN, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setOpaque(true);

        tittle_panel.setLayout(new BorderLayout());
        tittle_panel.setBounds(0, 0, 450, 120);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(60, 60, 60));

        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Gill Sans MT", Font.PLAIN, 120));
            buttons[i].setBackground(new Color(20, 189, 172));
            buttons[i].setFocusable(false);
            buttons[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(13, 161, 146)));
            if(i%3==1)buttons[i].setBorder(BorderFactory.createMatteBorder(0, 6, 0, 6, new Color(13, 161, 146)));
            if(i == 3 || i == 4 || i ==5)buttons[i].setBorder(BorderFactory.createMatteBorder(6, 0, 6, 0, new Color(13, 161, 146)));
            if(i == 4)buttons[i].setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, new Color(13, 161, 146)));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        tittle_panel.add(textfield);
        frame.add(tittle_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();

    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(84, 84, 84));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check();

                    }
                } else {
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(242, 235, 211));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }
    public void firstTurn(){
        if(random.nextInt(2) == 0){
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }
    public void check(){
        btns_clicked++;
        String[] winner = new String[4];
        if(rows()!=null || coll()!=null || diag()!=null){
            if(rows()!=null)winner = rows();
            else if(coll()!=null)winner = coll();
            else if(diag()!=null)winner = diag();
            textfield.setText(winner[0]+" wins!");
            finish(Integer.parseInt(winner[1]), Integer.parseInt(winner[2]), Integer.parseInt(winner[3]));
        }
        if(btns_clicked == 9){
            textfield.setText("Draw!");
            again();
        }
    }
    public String[] rows(){
        int i = 0;
        for(int j = 0;j<3;j++){
            if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText())
            && !buttons[i].getText().equals("")) {
                return new String[]{buttons[i].getText(), String.valueOf(i), String.valueOf(i + 1), String.valueOf(i + 2)};
            }
            i= i+3;
        }
        return null;
    }
    public String[] coll(){
        int i = 0;
        for(int j = 0;j<3;j++){
            if(buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
            && !buttons[i].getText().equals("")) {
                return new String[]{buttons[i].getText(), String.valueOf(i), String.valueOf(i + 3), String.valueOf(i + 6)};
            }
            i++;
        }
        return null;
    }
    public String[] diag(){
        if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
        && !buttons[0].getText().equals("")) return new String[]{buttons[0].getText(), String.valueOf(0), String.valueOf(4), String.valueOf(8)};
        else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
        && !buttons[2].getText().equals("")) return new String[]{buttons[2].getText(), String.valueOf(2), String.valueOf(4), String.valueOf(6)};
        else return null;
    }

    public void finish(int a, int b, int c){
        for(int i = 0;i<9;i++){
            if(buttons[i].getText().equals(""))buttons[i].setText(" ");
            if(i == a || i == b || i == c)buttons[i].setFont(buttons[i].getFont().deriveFont(140f));
            else buttons[i].setForeground(new Color(13, 161, 146));
        }
        again();
    }
    public void again(){
        JOptionPane pane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(pane, "Would you like to play again?", "Reset", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)restart();
        else System.exit(0);
    }
    public void restart(){
        for(int i = 0;i<9;i++){
            firstTurn();
            buttons[i].setText("");
            btns_clicked = 0;
        }
    }

}
