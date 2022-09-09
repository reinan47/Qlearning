package Interface;

import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import BuscaReforco.BuscaReforcoQuestao2;
import BuscaReforco.LinhaColuna;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Reinan
 */
public class BuscaQuestao2 extends JPanel{
    public BuscaQuestao2(){
        this.setLayout(null);
        this.setSize(1000,700);
        this.setBackground(SystemColor.LIGHT_GRAY);
    }
   
    public static void main(String[] args) throws InterruptedException, IOException{
        JButton agente = new JButton();
        JButton objetivo = new JButton();
        JButton []parede = new JButton[1921];
        BuscaReforcoQuestao2 br = new BuscaReforcoQuestao2();
        String caminho = "C:\\Users\\Reinan\\Desktop\\Q_learning\\DadosDaBusca.txt"; 
        
        BuscaQuestao2 p = new BuscaQuestao2();
        JFrame ja = new JFrame("");        
        
        p.setBounds(0, 0, 50, 800);
        ja.setBounds(0, 0, 1000, 700);
        ja.setLocationRelativeTo(null);
        ja.getContentPane().add(p);
        ja.setVisible(true);
        ja.setDefaultCloseOperation(ja.EXIT_ON_CLOSE);
        
        
       
        int cont = 0;
        for(int i = 0 ; i < 61 ; i++){
            for(int j = 0 ; j < 61 ; j++){
                if(br.tabuleiro[i][j] == 1){
                   parede[cont++] = new JButton();
                }
            }
        }
        cont = 0;
        for(int i = 0 ; i < 61 ; i++){
            for(int j = 0 ; j < 61 ; j++){
                if(br.tabuleiro[i][j] == 1){
                    parede[cont].setBounds(200+(j*10), 20+(i*10), 10, 10);
                    parede[cont].setBackground(SystemColor.BLACK);
                    parede[cont].setBorderPainted(false);
                    p.add(parede[cont]);
                    p.revalidate();
                    p.repaint();
                    cont++;    
                }
            }
        }
        FileWriter dadosTB =  new FileWriter(new File(caminho));        

       for(int i = 0 ; i < 61 ; i++){
            for(int j = 0 ; j < 61 ; j++){
                if(br.tabuleiro[i][j] != 1){
                    dadosTB.write(i +";"+j+";B"+0.00+";C"+0.00+";E"+0.00+";D"+0.00 + "\n");
                }          
            }
        }
        dadosTB.close();
        BufferedReader carregarDados =  new BufferedReader(new FileReader(caminho));
        LinhaColuna []lc = new LinhaColuna[1925];
        int linha;
        int coluna;
        double direita = 0;
        double esquerda = 0;
        double cima =0 ;
        double baixo = 0;
        String []lineCarga = new String[6];
        String line = carregarDados.readLine();
        lineCarga = line.split(";");
        int contLinha = 0;
        while(carregarDados.ready()){
            line = carregarDados.readLine();
            lineCarga = line.split(";");
            
            linha = Integer.parseInt(lineCarga[0]);
            coluna = Integer.parseInt(lineCarga[1]);
            esquerda = Double.parseDouble(lineCarga[2].substring(1));
            direita = Double.parseDouble(lineCarga[3].substring(1));
            cima = Double.parseDouble(lineCarga[4].substring(1));
            baixo = Double.parseDouble(lineCarga[5].substring(1));
  
            lc[contLinha] =  new LinhaColuna(linha, coluna, cima, baixo, esquerda, direita);
            contLinha++;
        }
        carregarDados.close();

        BuscaQuestao2 b = new BuscaQuestao2();
        
        objetivo.setBounds(200+(29*10), 20+(0*10), 10, 10);
        objetivo.setBackground(SystemColor.GREEN);
        objetivo.setBorderPainted(false);
        p.add(objetivo);
        ja.revalidate();
        ja.repaint();
        //int i = (int) (Math.random()*5);
        int i = (int) (Math.random()*61);
        //int j = (int) (Math.random()*5);
        int j = (int) (Math.random()*61);
        while(br.tabuleiro[i][j] == 1){
            //i = (int) (Math.random()*5);
            i = (int) (Math.random()*61);
            //j = (int) (Math.random()*5);
            j = (int) (Math.random()*61); 
        }
        int contAux = 0;
        String []acao = {"esquerda","direita","cima","baixo"};
        String aux;
        double fatorAprendizado = 0.9;
        double recompensa = 0;
        double acaoAux = 0;
        double s1=0,s2=0,s3=0,s4=0;
        int salvaL = i;
        int salvaC = j;
        //comecando as movimentações
        while(contAux < 50000){
            aux = acao[(int)(Math.random()*4)];
            if(br.tabuleiro[i+1][j] != 1 || br.tabuleiro[i+1][j] == 10 
                    || br.tabuleiro[i-1][j] != 1 || br.tabuleiro[i-1][j] == 10
                    || br.tabuleiro[i][j+1] != 1 || br.tabuleiro[i][j+1] == 10
                    || br.tabuleiro[i][j-1] != 1 || br.tabuleiro[i][j-1] == 10){
                if(aux == "cima"){
                    if(br.tabuleiro[i-1][j] != 1)
                       i-=1;
                }
                else if(aux == "baixo"){
                    if(br.tabuleiro[i+1][j] != 1)
                       i+=1;
                }
                else if(aux == "esquerda"){
                    if(br.tabuleiro[i][j-1] != 1)
                       j-=1;
                }
                else if(aux == "direita"){
                    if(br.tabuleiro[i][j+1] != 1)
                       j+=1;
                }
            }

            recompensa = br.tabuleiro[i][j]; 
            agente.setBounds(200+(j*10), 20+(i*10), 10, 10);
            agente.setBackground(SystemColor.RED);
            agente.setBorderPainted(false);
            p.add(agente);
            p.revalidate();
            p.repaint();
            TimeUnit.MILLISECONDS.sleep(5);
            if(br.tabuleiro[i+1][j] == 10 
                    ||br.tabuleiro[i-1][j] == 10
                    ||br.tabuleiro[i][j+1] == 10
                    ||br.tabuleiro[i][j-1] == 10){
               
                i = (int) (Math.random()*61);
                //j = (int) (Math.random()*5);
                i = (int) (Math.random()*61);
                //j = (int) (Math.random()*5);
                while(br.tabuleiro[i][j] == 1){
                    i = (int) (Math.random()*61);
                    //j = (int) (Math.random()*5);
                    i = (int) (Math.random()*61);
                    //j = (int) (Math.random()*5);
                }
                //TimeUnit.SECONDS.sleep(5);
            }

            for(int a = 0 ; a < lc.length ; a ++){
                if(lc[a] == null)break;
                if(lc[a].getLinha() == i && lc[a].getColuna() == j){
                    s1 = lc[a].getBaixo();
                    s2 = lc[a].getCima();
                    s3 = lc[a].getEsquerda();
                    s4 = lc[a].getDireita();
                    lc[a].setLinha(i);
                    lc[a].setColuna(j);
                }
                
                acaoAux = b.maxAcao(s1, s2, s3, s4);
                if(i < salvaL){
                    lc[a].setBaixo(recompensa + fatorAprendizado * acaoAux);
                }
                if(i > salvaL){
                    lc[a].setCima(recompensa + fatorAprendizado * acaoAux);
                }
                if(j < salvaC){
                    lc[a].setDireita(recompensa + fatorAprendizado * acaoAux);
                }
                if(j > salvaC){
                    lc[a].setEsquerda(recompensa + fatorAprendizado * acaoAux);
                }
            }
            salvaL = i;
            salvaC = j;
            contAux++;       
        }
        dadosTB.close();
        FileWriter cargafinal =  new FileWriter(new File("C:\\Users\\Reinan\\Desktop\\Q_learning\\Dados.txt"));
        for(int c= 0 ; c < 1925 ; c++){
            if(lc[c] == null)break;
            System.out.println(lc[c].getLinha()+";"+lc[c].getColuna()+";C"+lc[c].getCima()+";B"+lc[c].getBaixo()+";E"+lc[c].getEsquerda()+";D"+lc[c].getDireita()+"\n");
        }
        for(int c = 0 ; c < 1925 ; c++){
            if(lc[c] == null)break;
            cargafinal.write(lc[c].getLinha()+";"+lc[c].getColuna()+";C"+lc[c].getCima()+";B"+lc[c].getBaixo()+";E"+lc[c].getEsquerda()+";D"+lc[c].getDireita()+"\n");
        }
        cargafinal.close();
    }
    public double maxAcao(double cima,double baixo, double esquerda, double direita){
        double aux = cima;
        if(aux < baixo){
            aux = baixo;
        }
        if(aux < esquerda){
            aux = esquerda;
        }
        if(aux < direita){
            aux = direita;
        }
        return aux;
    }
}

