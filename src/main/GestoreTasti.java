package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestoreTasti implements KeyListener {

    public boolean pulsanteSu, pulsangeGiu, pulsanteSin, pulsanteDes;

    @Override
    public void keyPressed(KeyEvent e) {
        int codPulsante = e.getKeyCode();

        if(codPulsante == KeyEvent.VK_W){
            pulsanteSu = true;
        }else if(codPulsante == KeyEvent.VK_S){
            pulsangeGiu = true;
        }else if(codPulsante == KeyEvent.VK_A){
            pulsanteSin = true;
        }else if(codPulsante == KeyEvent.VK_D){
            pulsanteDes = true;
        }else if(codPulsante == KeyEvent.VK_DOWN){
            pulsangeGiu = true;
        }else if(codPulsante == KeyEvent.VK_LEFT){
            pulsanteSin = true;
        }else if(codPulsante == KeyEvent.VK_RIGHT){
            pulsanteDes = true;
        }else if(codPulsante == KeyEvent.VK_UP){
            pulsanteSu = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codPulsante = e.getKeyCode();

        if(codPulsante == KeyEvent.VK_W){
            pulsanteSu = false;
        }else if(codPulsante == KeyEvent.VK_S){
            pulsangeGiu = false;
        }else if(codPulsante == KeyEvent.VK_A){
            pulsanteSin = false;
        }else if(codPulsante == KeyEvent.VK_D){
            pulsanteDes = false;
        }else if(codPulsante == KeyEvent.VK_DOWN){
            pulsangeGiu = false;
        }else if(codPulsante == KeyEvent.VK_LEFT){
            pulsanteSin = false;
        }else if(codPulsante == KeyEvent.VK_RIGHT){
            pulsanteDes = false;
        }else if(codPulsante == KeyEvent.VK_UP){
            pulsanteSu = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
