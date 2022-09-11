/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package BuscaReforco;

/**
 *
 * @author Reinan
 */
public class BuscaQLearning1{

    private int agente;
    private int objetivo;
    
    public int[][] tabuleiro = {
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,10,1},
        {1,1,1,1,1,1,1,1,1,1}  
        
    
    };
            

    public BuscaQLearning1() {
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
