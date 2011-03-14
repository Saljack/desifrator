/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sifry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author saljack
 */
public class MainWindow extends JFrame {

    private Graf pnl_graf = null;
    private JSlider sl_posun;
    private JSpinner spn_posun;
    private JSpinner spn_rozdil;
    private JTextArea ta_text;
    private JDialog textDialog;
    private JDialog icDialog;
    private String text;

    public MainWindow() {
        initGUI();
    }

    /**
     *  SEM ZADEJTE VAS TEXT, KTERY SE MA POROVNAT
     * @return
     */
    private double[] getPocet() {
        String str = "IKHODRDTALAQBADJRKTCOHKTCOQABQOOSRCOTCKUBCTWCATABQAJSHAQQDABODTWARNKQCOQSAUBCTOQAJSARNKQTCONDVORFODJRTDHOOJKUBCTKLKTCOQALKUTTCOHWCOJTCOYOAQIAHOQKUJSTCOQOWARHAJYARGDMLOTWOOJIUMAJSGDMAJSGDFOGYARJKTTCOFDJBWKUGSCAVONKQBKTTOJAGGALKUTDTLYTCOJAJYCKWCOQSAUBCTOQWKUGSLOPUOOJNKQOGOVOJHKJTCR";
        return getPocet(str);
    }

    /**
     * Pole s procenty cetnosti vyskytu pismena
     * @param str
     * @return
     */
    private double[] getPocet(String str) {
        double[] abecd = new double[26];
        String upstr = str.toUpperCase();
        for (int i = 0; i < abecd.length; ++i) {
            abecd[i] = 0;
        }

        for (int i = 0; i < upstr.length(); ++i) {
            int znak = upstr.charAt(i) - 'A';
            if (znak >= 0 && znak < 26) {
                ++abecd[znak];
            }
        }
        double percent = getPercent(abecd);

        //nastavi v procentech
        for (int i = 0; i < abecd.length; ++i) {
            abecd[i] = abecd[i] / percent;
        }

        return abecd;
    }

    /**
     * Get one per cent from all char in array
     * @param abc - array with nbr of character
     * @return - one percet
     */
    private double getPercent(double[] abc) {
        int sum = 0;
        for (int i = 0; i < abc.length; ++i) {
            sum += abc[i];
        }

        double percent = sum / 100.0;

        for (int i = 0; i < abc.length; ++i) {
            System.out.println((char) (i + 'A') + "=" + abc[i] / percent);
        }

        return percent;

    }

    private void showAbout() {
        JDialog about = new JDialog(this, "About");
        this.setEnabled(false);


        about.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void windowClosing(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
                System.out.println("AHOJ");
                JDialog about = (JDialog) e.getSource();
                about.getOwner().setEnabled(true);
            }

            public void windowClosed(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void windowIconified(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void windowDeiconified(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void windowActivated(WindowEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        about.setSize(300, 200);
        String str = "<html><h1>Desifrator</h1>"
                + "<b>Version:</b> 0.1<br>"
                + "by Saljack <br>"
                + "<a href=\"mailto:saljacky@gmail.com\">saljacky@gmail.com</a>"
                + "</html>";
        JLabel lbl = new JLabel(str);

        about.add(lbl);
        about.setVisible(true);

    }

    /**
     * initialization menu
     */
    private void initMenu() {
        //MENU
        JMenuBar menubar = new JMenuBar();
        //File menu
        JMenu menufile = new JMenu("File");
        JMenuItem mi_loadText = new JMenuItem("Load text");
        menufile.add(mi_loadText);

        JMenuItem mi_IC = new JMenuItem("IC");
        menufile.add(mi_IC);
        mi_IC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showIC();
            }
        });

        JMenuItem mi_exit = new JMenuItem("Exit");
        menufile.add(mi_exit);
        mi_exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        mi_loadText.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                loadTextDlg();
            }
        });


        JMenu menuabout = new JMenu("About");
        JMenuItem mi_about = new JMenuItem("About Desifrator");



        mi_about.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });

        menuabout.add(mi_about);
        menubar.add(menufile);
        menubar.add(menuabout);
        setJMenuBar(menubar);
    }

    /**
     *  initialization gui
     */
    private void initGUI() {
        setTitle("Desifrator");
        setSize(600, 400);
        setMinimumSize(new Dimension(150, 150));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel basic = new JPanel();
        add(basic);
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
//        double[] abeceda = getPocet();

        pnl_graf = new Graf();

        basic.add(pnl_graf);



        JPanel bottom = new JPanel();
        bottom.setAlignmentX(1f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

        sl_posun = new JSlider(0, 25, 0);
        spn_posun = new JSpinner(new SpinnerNumberModel(0, 0, 25, 0));
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
                pnl_graf.setRozdil((Double) spn_rozdil.getValue());
            }
        });


        bottom.add(spn_rozdil);




        basic.add(bottom);
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        initMenu();
    }

    private void loadTextDlg() {
        textDialog = new JDialog(this, "Input text");
        textDialog.setSize(300, 200);

        ta_text = new JTextArea();
        ta_text.setLineWrap(true);
        BorderLayout ll = new BorderLayout();

        textDialog.setLayout(ll);
        textDialog.add(new JLabel("Input text:"), BorderLayout.PAGE_START);
        textDialog.add(ta_text, BorderLayout.CENTER);

        JButton btn_setText = new JButton("Set text");
        btn_setText.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setText(ta_text.getText());
//                textDialog.setVisible(false);
                textDialog.dispose();
            }
        });

        textDialog.add(btn_setText, BorderLayout.PAGE_END);
        textDialog.setVisible(true);
    }

    private void setText(String str) {
        System.out.println(str);
        text = str.toUpperCase();
        pnl_graf.setText(getPocet(text));
    }

    private void showIC(){
        icDialog = new JDialog(this,"IC");
        icDialog.setSize(300,200);
        String str = "<html>"
                + "Text:<br><p>"
                + text
                + "</p><br><br><h1>IC: "
                + Desifrovani.IC(text)
                + "</h1></html>";
        JLabel lbl = new JLabel(str);
        icDialog.add(lbl);
        icDialog.setVisible(true);
    }
}
