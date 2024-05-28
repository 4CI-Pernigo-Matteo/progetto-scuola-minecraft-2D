package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws MalformedURLException {

        JFrame finestra = new JFrame();
        finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setResizable(false);
        finestra.setTitle("Minecraft 2D");

        PannelloGioco pannelGioco = new PannelloGioco();
        finestra.add(pannelGioco);

        finestra.pack();

        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);

        //GeneratoreMappa.s(); //per generare mappe automaricamente

//        Toolkit.getDefaultToolkit().getImage(finestra.class.getResource("image.png"))
//        finestra.setIconImage();

        pannelGioco.startGameThread();
    }
}
