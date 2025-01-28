package pl.polsl.rafal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Exchanger {
    private JPanel mainPanel;
    private JTextField fieldWpisz;
    private JComboBox boxWybor;
    private JRadioButton rBeuro;
    private JRadioButton rBdolar;
    private JRadioButton rBfunt;
    private JLabel wynikLabel;
    private JButton bPrzelicz;

    private final Map<String, Double> kursy;

    public Exchanger() {
        JFrame converter = new JFrame("Przelicznik walut");
        converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        converter.setContentPane(mainPanel);
        converter.setResizable(false);
        converter.setSize(350, 240);
        converter.setVisible(true);


        kursy = new HashMap<>();
        kursy.put("PLN", 4.20);
        kursy.put("USD", 1.04);
        kursy.put("GBP", 0.84);
        kursy.put("CHF", 0.94);

        bPrzelicz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przeliczKwote();
            }
        });
    }

    private void przeliczKwote() {
        try {
            double kwota = Double.parseDouble(fieldWpisz.getText());
            String wejscie = (String) boxWybor.getSelectedItem();
            double kurs = kursy.getOrDefault(wejscie, 1.0);
            double kwotaWEuro = kwota / kurs;
            double wynikKwota = 0;

            if (rBeuro.isSelected()) {
                wynikKwota = kwotaWEuro;
            } else if (rBdolar.isSelected()) {
                wynikKwota = kwotaWEuro * kursy.get("USD");
            } else if (rBfunt.isSelected()) {
                wynikKwota = kwotaWEuro * kursy.get("GBP");
            } else {
                JOptionPane.showMessageDialog(null, "Wybierz walutę docelową!", "Błąd", JOptionPane.WARNING_MESSAGE);
                return;
            }
            wynikLabel.setText(String.format("Wynik: %.2f", wynikKwota));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Błąd: Podaj poprawną kwotę w formacie liczbowym!", "Błąd danych wejściowych", JOptionPane.ERROR_MESSAGE);
        }
    }

}

