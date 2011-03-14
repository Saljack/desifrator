/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sifry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

/**
 *
 * @author saljack
 */
public class Graf extends JPanel {

    private int posun = 2;
    private double [] abeceda = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153,	0.772, 4.025, 2.406, 6.749,
                                7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974, 0.074};
    private double[] text;
    double percent = -1;
    final int POCET = 26;
    private double rozdil = 0.5;
    
    public Graf(double[] text) {
        this.text = text;
        repaint();
        addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                repaint();
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });
        initPercent();
    }

    private void initPercent() {
        int sum = 0;
        for (int i = 0; i < text.length; ++i) {
            sum += text[i];
        }

        percent = sum / 100.0;

        for (int i = 0; i < text.length; ++i) {
            System.out.println((char) (i + 'A') + "=" + text[i] / percent);
        }

    }

    public void setRozdil(double roz){
        rozdil = roz;
        repaint();
    }

    public void posun(boolean doleva) {
        if (doleva) {
            --posun;
        } else {
            ++posun;
        }
        repaint();
    }

    /**
     * Nastavi posun
     * @param posun - posun od 0 po 25
     */
    public void setPosun(int posun){
        this.posun = posun;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        final int POSUN = 20;//O kolik posunout
        final int MERITKO = 15; //Maximum  %
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;



        Dimension dim = this.getSize();

        //po kolika delat rozestupy
        int po = (dim.width - 2 * POSUN) / (abeceda.length - 1);


        g2d.drawRect(POSUN, POSUN, dim.width - (2 * POSUN), dim.height - (2 * POSUN));


        g2d.setStroke(new BasicStroke(2.5f));

        double pe = (dim.height - 2 * POSUN) / MERITKO;
        g2d.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
        for (int i = 0; i < MERITKO; ++i) {
            g2d.drawString(i + "", 2, (int) (dim.height - (POSUN + pe * i)));
        }


        for (int pis = 0; pis < abeceda.length; ++pis) {
            g2d.drawString((char) (pis + 'A') + "", POSUN + pis * po, dim.height - (POSUN / 2));
        }


        g2d.setColor(Color.red);

        //ABECEDA
        Dimension start = new Dimension(POSUN, (int) (dim.height - (pe * (abeceda[0]))));
        Dimension end = new Dimension(start.width + po, (int) (dim.height - (pe * (abeceda[1])) - POSUN));
        int i = 2;
        while (i < POCET) {
            g2d.drawLine(start.width, start.height, end.width, end.height);
            start.setSize(end.width, end.height);
            end.setSize(start.width + po, (int) (dim.height - (pe * (abeceda[i])) - POSUN));
            ++i;
        }
        g2d.drawLine(start.width, start.height, end.width, end.height);

        //POSUNUTE
        g2d.setColor(Color.BLUE);
        start.setSize(POSUN, (int) (dim.height - (pe * (text[(posun)%POCET]))));
        end.setSize(start.width + po, (int) (dim.height - (pe * (text[(1+posun)%POCET])) - POSUN));
        i = 2;
        while (i < POCET) {
            g2d.drawLine(start.width, start.height, end.width, end.height);

            //Mozna shoda s pismenem
            int nad = 12;
            int pred = 1;
            for(int zn = 0; zn < abeceda.length; ++zn){
                double roz = Math.abs(abeceda[zn]-text[(i+posun-1)%POCET]);
                if(roz < rozdil){
                    g2d.drawString((char)(zn+'A')+"", end.width, (end.height-nad*pred));
                    ++pred;
                }
            }


            start.setSize(end.width, end.height);
            end.setSize(start.width + po, (int) (dim.height - (pe * (text[(i+posun)%POCET])) - POSUN));
            ++i;
        }
        g2d.drawLine(start.width, start.height, end.width, end.height);


        //DEFAULT bez posunu
        g2d.setColor(Color.BLUE);
        float[] dash2 = { 2f, 0f, 4f }; //prerusovana cara
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f ));
        
        start.setSize(POSUN, (int) (dim.height - (pe * (text[0]))));
        end.setSize(start.width + po, (int) (dim.height - (pe * (text[1])) - POSUN));

        i = 2;
        while (i < POCET) {
            g2d.drawLine(start.width, start.height, end.width, end.height);
            start.setSize(end.width, end.height);
            end.setSize(start.width + po, (int) (dim.height - (pe * (text[i] )) - POSUN));
            ++i;


        }
        g2d.drawLine(start.width, start.height, end.width, end.height);
    }
}
