package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Giocatore extends Entita {
    PannelloGioco gp;
    GestoreTasti keyH;

    public final int Xschermo;
    public final int Yschermo;

    public Giocatore(PannelloGioco pg, GestoreTasti keyH){
        this.gp = pg;
        this.keyH = keyH;

        Xschermo = pg.larghezzaSchermo/2 - (pg.grandezzaTile/2);
        Yschermo = pg.altezzaSchermo/2 - (pg.grandezzaTile/2);

        setValoriDefault();
        getImmaginePLayer();
    }

    public void setValoriDefault(){

        xMondo = 750;
        yMondo = 350;
        velocita = 4;
        direzione = "giu";
    }

    public  void getImmaginePLayer(){
        try{
            su1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_up_1.png")));
            su2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_up_2.png")));
            giu1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_down_1.png")));
            giu2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_down_2.png")));
            sin1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_left_1.png")));
            sin2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_left_2.png")));
            des1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_right_1.png")));
            des2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Palyer/player_right_2.png")));

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public  void update(){

        if(keyH.pulsanteSu || keyH.pulsangeGiu || keyH.pulsanteSin || keyH.pulsanteDes){
            if(keyH.pulsanteSu){
                direzione = "su";
                yMondo = yMondo - velocita;
            }else if(keyH.pulsangeGiu){
                direzione = "giu";
                yMondo = yMondo + velocita;
            }else if(keyH.pulsanteSin){
                direzione = "sin";
                xMondo = xMondo - velocita;
            }else if(keyH.pulsanteDes){
                direzione = "des";
                xMondo = xMondo + velocita;
            }

            contatoreSprite++;
            if(contatoreSprite > 10){
                if(numSprite == 1){
                    numSprite = 2;
                }else if(numSprite == 2){
                    numSprite = 1;
                }
                contatoreSprite = 0;
            }
        }
    }

    public  void draw(Graphics2D g2){
        BufferedImage immagine = null;

        switch (direzione) {
            case "su" -> {
                if (numSprite == 1) {
                    immagine = su1;
                }
                if (numSprite == 2) {
                    immagine = su2;
                }
            }
            case "giu" -> {
                if (numSprite == 1) {
                    immagine = giu1;
                }
                if (numSprite == 2) {
                    immagine = giu2;
                }
            }
            case "sin" -> {
                if (numSprite == 1) {
                    immagine = sin1;
                }
                if (numSprite == 2) {
                    immagine = sin2;
                }
            }
            case "des" -> {
                if (numSprite == 1) {
                    immagine = des1;
                }
                if (numSprite == 2) {
                    immagine = des2;
                }
            }
        }
        g2.drawImage(immagine, Xschermo, Yschermo, gp.tileScala, gp.tileScala, null);
    }
}
