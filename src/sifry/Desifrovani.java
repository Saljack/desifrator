/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sifry;

/**
 *
 * @author saljack
 */
public class Desifrovani {

    static final int POCETZNAKU = 26;

    public void affini(String str) {
        System.out.println("******************************\nAfinni sifra:");
        for (int a = 0; a < POCETZNAKU; ++a) {
            for (int b = 1; b < POCETZNAKU; ++b) {
                System.out.println("\n" + a + " " + b + ": ");

                for (int z = 0; z < str.length(); ++z) {
                    char znak = (char) (((a * ((str.charAt(z) - 'A') + b)) % 26) + 'A');
                    System.out.print(znak);
                }
            }
        }
    }

    /**
     * spocita IC
     * @param str - text z ktereho se ma IC vypocitat
     * @return IC
     */
    public static double IC(String str) {

        int[] vyskyt = new int[POCETZNAKU];
        str = str.toUpperCase();

        for (int i = 0; i
                < str.length();
                ++i) {
            ++vyskyt[str.charAt(i) - 'A'];
        }

        long sum = 0;
        for (int i = 0; i
                < POCETZNAKU;
                ++i) {
            char znak = (char) (i + 'A');
            sum += vyskyt[i] * (vyskyt[i] - 1);
        }

        double delitel = (str.length() * (str.length() - 1)) / (double) POCETZNAKU;

        return (sum / delitel) / POCETZNAKU;
    }

    public void prostyPosun(String str) {
        System.out.println("******************************\nProsty posun:");

        for (int i = 1; i < POCETZNAKU; ++i) {
            System.out.println("\n" + i + ": ");

            for (int z = 0; z < str.length(); ++z) {
                char znak = (char) ((((str.charAt(z) - 'A') + i) % 26) + 'A');
                System.out.print(znak);
            }
        }
    }

    /**
     * 
     * @param str
     * @param klic
     * @return
     */
    public static String substituceSKlicem(String str, String klic) {
        int[] abeceda = getPosunutouAbecedu(klic);
        str = str.toUpperCase();
        
        char[] txt = new char[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            txt[i] = (char) (abeceda[(str.charAt(i) - 'A')] + 'A');
        }
        return new String(txt);
    }


    /**
     * OPTIMALIZOVAT
     * @param pass
     * @return posunutou abecedu o pass
     */
    public static int[] getPosunutouAbecedu(String pass) {
        int[] abeceda = new int[POCETZNAKU];
        pass = pass.toUpperCase();
        for (int i = 0; i < POCETZNAKU; ++i) {
            abeceda[i] = -1;
        }
        //zapsani klice
        for (int i = 0; i < pass.length(); ++i) {
            int poz = 0;
            int znak = pass.charAt(i) - 'A';
            while (abeceda[poz] != -1) {
                if (abeceda[poz] == znak) {
                    break;
                }
                ++poz;
            }
            if (abeceda[poz] == -1) {
                abeceda[poz] = znak;
            }
        }

        for (int i = 0; i < POCETZNAKU; ++i) {
            int poz = 0;
            while (abeceda[poz] != -1) {
                if (abeceda[poz] == i) {
                    break;
                }
                ++poz;
            }
            if (abeceda[poz] == -1) {
                abeceda[poz] = i;
            }
        }

        int[] abeceda2 = new int[abeceda.length];
        for (int i = 0; i < abeceda.length; ++i) {
            abeceda2[abeceda[i]] = i;
        }
        return abeceda2;
    }
}
