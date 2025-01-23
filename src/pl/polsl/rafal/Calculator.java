package pl.polsl.rafal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JButton bAC;
    private JPanel panel2;
    private JLabel print;
    private JButton b0;
    private JButton bPlus;
    private JButton b1;
    private JButton bMinus;
    private JButton b2;
    private JButton bDot;
    private JButton b3;
    private JButton bReturn;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton bMulti;
    private JButton bDivide;
    private JButton b9;
    private JButton b8;
    private JButton b7;
    private JPanel mainPanel;
    private double liczba1 = 0;
    private String operator = "";
    private boolean kropka = false;
    private boolean reset = false;
    private boolean wykonaj = false;


    public Calculator() {
        JFrame okno = new JFrame("Calculator");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setContentPane(mainPanel);
        okno.setResizable(false);
        okno.setSize(350, 240);
        okno.setVisible(true);
        print.setText("0");
        print.setHorizontalAlignment(JLabel.RIGHT);


        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("1");
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("2");
            }

        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("3");
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("4");
            }
        });
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("5");
            }
        });
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("6");
            }
        });
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("7");
            }
        });
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("8");
            }
        });
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wpiszCyfre("9");
            }
        });
        b0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!print.getText().equals("0")) {
                    wpiszCyfre("0");
                }
            }
        });
        bDot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!kropka) {
                    if (reset) {
                        print.setText("0.");
                        reset = false;
                    } else {
                        print.setText(print.getText() + ".");
                    }
                }
                kropka = true;
            }
        });
        bAC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print.setText("0");
                liczba1 = 0;
                kropka = false;
                operator = "";
                reset = false;
                wykonaj = false;
            }
        });

        bPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wykonajOperacje("+");
            }
        });

        bMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wykonajOperacje("-");
            }
        });

        bMulti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wykonajOperacje("*");
            }
        });

        bDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wykonajOperacje("/");
            }
        });

        bReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacja();
                print.setText(String.valueOf(liczba1));
                operator = "";
                liczba1 = 0;
                kropka = false;
                reset = true;
                wykonaj = false;
            }
        });

    }

    private void operacja() {
        double actResult = Double.parseDouble(print.getText());

        switch (operator) {
            case "+":
                liczba1 += actResult;
                break;
            case "-":
                liczba1 -= actResult;
                break;
            case "*":
                liczba1 *= actResult;
                break;
            case "/":
                if (actResult == 0) {
                    liczba1 = 0;
                    print.setText("0");
                    JOptionPane.showMessageDialog(null, "Nie mozna dzielic przez 0!", "Blad", JOptionPane.ERROR_MESSAGE);
                    reset = true;
                    return;
                }
                liczba1 /= actResult;
                break;
            default:
                liczba1 = actResult;
                break;
        }
    }

    private void wpiszCyfre(String cyfra) {
        if (reset || print.getText().equals("0")) {
            print.setText(cyfra);
            reset = false;
        } else {
            print.setText(print.getText() + cyfra);
        }
        wykonaj = false;
    }

    private void wykonajOperacje(String newOperator) {
        if (!wykonaj) {
            operacja();
        }
        operator = newOperator;
        print.setText(String.valueOf(liczba1));
        reset = true;
        kropka = false;
        wykonaj = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
