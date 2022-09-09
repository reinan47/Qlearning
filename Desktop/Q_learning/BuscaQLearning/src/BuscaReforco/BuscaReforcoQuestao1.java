/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package BuscaReforco;

/**
 *
 * @author Reinan
 */
public class BuscaReforcoQuestao1{
    private int agente;
    private int objetivo;
    public int [][] tabuleiro = new int[18][17];

    public BuscaReforcoQuestao1() {
    }

    public void setAgente(int agente) {
        this.agente = agente;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public int getBoneco() {
        return agente;
    }

    public int getObjetivo() {
        return objetivo;
    }

}
