package jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraCompleta {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            criarGUI();
        });
    }

    public static void criarGUI() {
        JFrame janela = new JFrame("Calculadora Completa");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(300, 400);
        janela.setLayout(new BorderLayout());
        janela.setLocationRelativeTo(null);

        JTextField campoTexto = new JTextField();
        campoTexto.setEditable(false);
        janela.add(campoTexto, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(4, 4));

        JButton[] botoes = new JButton[16];
        String[] botoesLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (int i = 0; i < 16; i++) {
            botoes[i] = new JButton(botoesLabels[i]);
            final String label = botoesLabels[i];
            botoes[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("C")) {
                        campoTexto.setText("");
                    } else if (label.equals("=")) {
                        String expression = campoTexto.getText();
                        try {
                            double result = eval(expression);
                            campoTexto.setText(String.valueOf(result));
                        } catch (Exception ex) {
                            campoTexto.setText("Erro");
                        }
                    } else {
                        campoTexto.setText(campoTexto.getText() + label);
                    }
                }
            });
            painelBotoes.add(botoes[i]);
        }

        janela.add(painelBotoes, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // Função para avaliar a expressão matemática
    public static double eval(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ')
                    nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length())
                    throw new RuntimeException("Caractere inesperado: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+'))
                        x += parseTerm(); // Adição
                    else if (eat('-'))
                        x -= parseTerm(); // Subtração
                    else
                        return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*'))
                        x *= parseFactor(); // Multiplicação
                    else if (eat('/'))
                        x /= parseFactor(); // Divisão
                    else
                        return x;
                }
            }

            double parseFactor() {
                if (eat('+'))
                    return parseFactor(); // + unário
                if (eat('-'))
                    return -parseFactor(); // - unário

                double x;
                int startPos = this.pos;
                if (eat('(')) { // Parênteses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // Números
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Caractere inesperado: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }
}
