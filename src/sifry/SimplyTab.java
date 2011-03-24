/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sifry;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author saljack
 */
public class SimplyTab extends JPanel {
    private String text;
    private JLabel lblIC;
    private MainWindow mw;
    private JTextField txfPass;
    private JTextArea txaCrypt;
    private JTextArea txaDecoded;

    public SimplyTab(MainWindow mw) {
        this.mw = mw;
        initGUI();
    }

    private void initGUI() {
        lblIC = new JLabel();
        setLayout(new GridLayout(4, 3));
        
        JButton btnLoadText = new JButton("Načíst text");
        btnLoadText.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mw.loadTextDlg();
            }
        });

        txfPass = new JTextField();
        txaDecoded = new JTextArea();
        txaDecoded.setLineWrap(true);
        txaDecoded.setEditable(false);
        JButton btnSubPass = new JButton("Dešifruj");
        btnSubPass.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txaDecoded.setText(Desifrovani.substituceSKlicem(text, txfPass.getText().toUpperCase()));
            }
        });
        JLabel empty = new JLabel("ee");
        txaCrypt = new JTextArea();
        txaCrypt.setLineWrap(true);
        txaCrypt.setEditable(false);

        add(txaCrypt);
        add(btnLoadText);
        add(txaDecoded);
        
        add(new JLabel("IC"));
        add(lblIC);
        add(empty);

        add(new JLabel("Heslo"));
        add(txfPass);
        add(btnSubPass);

        add(new JLabel("OT: "));
//        add(lblDecoded);

    }

    public void setText(String str){
        text = str;
        lblIC.setText(Desifrovani.IC(text)+"");
        txaCrypt.setText(str);
    }
}
