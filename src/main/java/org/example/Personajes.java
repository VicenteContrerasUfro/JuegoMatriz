package org.example;

public class Personajes {
    private int x, y;
    private int vida;
    private int ataque;

    public Personajes(int x, int y, int vida, int ataque) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.ataque = ataque;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void mover(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }

    public void atacar(Personajes enemigo) {
        enemigo.setVida(enemigo.getVida() - this.ataque);
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }
}
