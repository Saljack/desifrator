/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sifry;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author saljack
 */
public class MainWindow extends JFrame {

    private Graf pnl_graf = null;
    JSlider sl_posun;
    JSpinner spn_posun;
    JSpinner spn_rozdil;

    public MainWindow() {
        setTitle("Graf");
        setSize(600, 400);
        setMinimumSize(new Dimension(150, 150));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel basic = new JPanel();
        add(basic);
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        double[] abeceda = getPocet();

        pnl_graf = new Graf(abeceda);
        basic.add(pnl_graf);

        JPanel bottom = new JPanel();
        bottom.setAlignmentX(1f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

        sl_posun = new JSlider(0, 25, 0);
        spn_posun = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
        sl_posun.setPaintLabels(true);
        sl_posun.setPaintTicks(true);
        sl_posun.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                int value = sl_posun.getValue();
                spn_posun.setValue(value);
                pnl_graf.setPosun(value);

            }
        });
        bottom.add(sl_posun);
        bottom.add(new JLabel("Posun:"));
        spn_posun = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
        spn_posun.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                int value = (Integer) spn_posun.getValue();
                sl_posun.setValue(value);
                pnl_graf.setPosun(value);
            }
        });
        bottom.add(spn_posun);

        bottom.add(new JLabel("Rozdil:"));
        spn_rozdil = new JSpinner(new SpinnerNumberModel(0.5, 0, 3, 0.01));
        spn_rozdil.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                pnl_graf.setRozdil((Double)spn_rozdil.getValue());
            }
        });


        bottom.add(spn_rozdil);


        

        basic.add(bottom);
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
//        basic.add(Box.createRigidArea(new Dimension(0, 15)));

    }

    private double[] getPocet() {
        String str = "IKHODRDTALAQBADJRKTCOHKTCOQABQOOSRCOTCKUBCTWCATABQAJSHAQQDABODTWARNKQCOQSAUBCTOQAJSARNKQTCONDVORFODJRTDHOOJKUBCTKLKTCOQALKUTTCOHWCOJTCOYOAQIAHOQKUJSTCOQOWARHAJYARGDMLOTWOOJIUMAJSGDMAJSGDFOGYARJKTTCOFDJBWKUGSCAVONKQBKTTOJAGGALKUTDTLYTCOJAJYCKWCOQSAUBCTOQWKUGSLOPUOOJNKQOGOVOJHKJTCR";
        return getPocet(str);
    }

    private double[] getPocet(String str) {
        double[] abecd = new double[26];
        String upstr = str.toUpperCase();
        for (int i = 0; i < abecd.length; ++i) {
            abecd[i] = 0;
        }

        for (int i = 0; i < upstr.length(); ++i) {
            ++abecd[upstr.charAt(i) - 'A'];
        }
        double percent = getPercent(abecd);

        for (int i = 0; i < abecd.length; ++i) {
            abecd[i] = abecd[i]/percent;
        }

        return abecd;
    }

    /**
     * Get one per cent from all char in array
     * @param abc - array with nbr of character
     * @return - one percet
     */
    private double getPercent(double [] abc) {
        int sum = 0;
        for (int i = 0; i < abc.length; ++i) {
            sum += abc[i];
        }

        double percent = sum / 100.0;

        for (int i = 0; i < abc.length; ++i) {
            System.out.println((char) (i + 'A') + "=" +abc[i] / percent);
        }

        return percent;

    }
}
