package BuscaReforco;

/**
 *
 * @author Reinan
 */
public class LinhaColuna {
    private int linha;
    private int coluna;  
    private double direita = 0.00;
    private double esquerda = 0.00;
    private double cima = 0.00;
    private double baixo = 0.00;

    public LinhaColuna(int linha, int coluna, double direita, double esquerda, double cima, double baixo) {
        this.linha = linha;
        this.coluna = coluna;
        this.direita = direita;
        this.esquerda = esquerda;
        this.cima = cima;
        this.baixo = baixo;
    }
    

    public int getLinha() {
        return linha;
    }
    
    public int getColuna() {
        return coluna;
    }

    public double getDireita() {
        return direita;
    }

    public double getEsquerda() {
        return esquerda;
    }

    public double getCima() {
        return cima;
    }

    public double getBaixo() {
        return baixo;
    }
    
    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setDireita(double direita) {
        this.direita = direita;
    }

    public void setEsquerda(double esquerda) {
        this.esquerda = esquerda;
    }

    public void setCima(double cima) {
        this.cima = cima;
    }

    public void setBaixo(double baixo) {
        this.baixo = baixo;
    }

}
