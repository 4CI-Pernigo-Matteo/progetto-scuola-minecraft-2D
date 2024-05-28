package main;

import javax.swing.*;
import java.awt.*;

public class PannelloGioco extends JPanel implements Runnable{
    //Impostazioni schermo
    public int larghezzaSchermo = 1500; //1500 px.
    public int altezzaSchermo = 700; //700 px.
    public int grandezzaTile = 32; //32x32
    final int scala = 3;
    public int tileScala = grandezzaTile * scala;
    public int maxColonneOrizSchermo = 16;
    public int maxColonneVertSchermo = 12;
    int FramePerSecondo = 75;

    //Impostazioni mappa
    public final int massimeColonneMondo = 50;
    public final int massimeRigheMondo = 50;
    public final int larghezzaMondo = grandezzaTile * massimeColonneMondo;
    public final int altezzaMondo = grandezzaTile * massimeRigheMondo;

    GestoreMappa gestoreMappa = new GestoreMappa(this);
    GestoreTasti gestoreTasti = new GestoreTasti();
    Thread threadGioco;
    public Giocatore giocatore = new Giocatore(this, gestoreTasti);

    public PannelloGioco(){
        this.setPreferredSize(new Dimension(larghezzaSchermo, altezzaSchermo));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // per performance migliori
        this.addKeyListener(gestoreTasti);
        this.setFocusable(true);
    }

    public void startGameThread(){
        threadGioco = new Thread(this);
        threadGioco.start();
    }

    @Override
    public void run() {
        while (threadGioco != null){
            double intervalloDisegno = 1000.0 / FramePerSecondo; // Intervallo di disegno in millisecondi

            long startTime = System.currentTimeMillis();

            update();

            repaint();
            //coso per limitare gli fps (se non c'è accadono cose strane ma che hanno senso...)
            try {
                long tempoPassato = System.currentTimeMillis() - startTime;
                long tempoRimanente = (long) (intervalloDisegno - tempoPassato);
                if(tempoRimanente < 0){
                    tempoRimanente = 0;
                }
                Thread.sleep(tempoRimanente);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        giocatore.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        gestoreMappa.draw(g2);
        giocatore.draw(g2);

        g2.dispose();
    }
}
