package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class GestoreMappa {
    PannelloGioco pg;
    Mappa[] tile;
    int[][] mapTileNum;

    public GestoreMappa(PannelloGioco pg){
        this.pg = pg;

        tile = new Mappa[10];
        mapTileNum = new int [pg.massimeColonneMondo][pg.massimeRigheMondo];

        getBlockImage();
        caricaMappa("/maps/mondo01.txt");
        //caricaMappa("maps/mondo02.txt");
    }

    public void getBlockImage(){
        try{
            tile[0] = new Mappa();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/dirt.png")));

            tile[1] = new Mappa();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/cobblestone.png")));

            tile[2] = new Mappa();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/water.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void caricaMappa(String filePath){
        try{
            InputStream input = getClass().getResourceAsStream(filePath);
            assert input != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            int colonna = 0;
            int riga = 0;

            while(colonna < pg.altezzaMondo && riga < pg.larghezzaMondo){
                String line = br.readLine();
                while(colonna < pg.maxColonneOrizSchermo) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[colonna]);

                    mapTileNum[colonna][riga] = num;
                    colonna++;
                }
                if(colonna == pg.maxColonneOrizSchermo){
                    colonna = 0;
                    riga++;
                }
            }
            br.close();
        }catch (Exception ignored){
        }
    }

    public void draw(Graphics2D g2){

        int colonneMondo = 0;
        int righeMondo = 0;

        while(colonneMondo < pg.massimeColonneMondo && righeMondo < pg.massimeRigheMondo){

            int tileNum = mapTileNum[colonneMondo][righeMondo];

            int xMondo = righeMondo * pg.grandezzaTile;
            int yMondo = colonneMondo * pg.grandezzaTile;
            int xSchermo = xMondo - pg.giocatore.xMondo + pg.giocatore.Xschermo;
            int ySchermo = yMondo - pg.giocatore.yMondo + pg.giocatore.Yschermo;

            g2.drawImage(tile[tileNum].image, xSchermo, ySchermo, pg.tileScala, pg.tileScala,null);
            colonneMondo++;

            if(colonneMondo == pg.massimeColonneMondo){
                colonneMondo = 0;
                righeMondo++;
            }
        }
    }
}
