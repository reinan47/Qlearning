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
        //tirar comentario para deixar atabela vazia padrão com tds as ações com 0.00
        //e depois de rodar a primeira vez pode comentar
        /*FileWriter dadosTB =  new FileWriter(new File(caminho));        

        for(int i = 0 ; i < 61 ; i++){
            for(int j = 0 ; j < 61 ; j++){
                if(br.tabuleiro[i][j] != 1){
                    dadosTB.write(i+";"+j+";B"+0.00+";C"+0.00+";E"+0.00+";D"+0.00 + "\n");
                }          
            }
        }
        dadosTB.close();*/
        BufferedReader carregarDados =  new BufferedReader(new FileReader(caminho));
        LinhaColuna []lc = new LinhaColuna[1925];
        int linha;
        int coluna;
        double direita = 0;
        double esquerda = 0;
        double cima =0 ;
        double baixo = 0;
        String []lineCarga = new String[6];
        
        int contLinha = 0;
        while(carregarDados.ready()){
            String line = carregarDados.readLine();
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
        int i =(int) (Math.random()*61);
        //int j = (int) (Math.random()*5);
        int j = (int) (Math.random()*61);
        while(br.tabuleiro[i][j] == 1){
            //i = (int) (Math.random()*5);
            i = (int) (Math.random()*61);
            //j = (int) (Math.random()*5);
            j = (int) (Math.random()*61); 
        }
        i = 1;
        j = 29;
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
        while(contAux < 80000){
            aux = acao[(int)(Math.random()*4)];
            if(aux == "cima"){
                if(br.tabuleiro[i-1][j] != 1)
                   i-=1;
            }
            if(aux == "baixo"){
                if(br.tabuleiro[i+1][j] != 1)
                    i+=1;
            }
            if(aux == "esquerda"){
                if(br.tabuleiro[i][j-1] != 1)
                    j-=1;
            }
            if(aux == "direita"){
                if(br.tabuleiro[i][j+1] != 1)
                    j+=1;
           }

            recompensa = br.tabuleiro[i][j]; 
            agente.setBounds(200+(j*10), 20+(i*10), 10, 10);
            agente.setBackground(SystemColor.RED);
            agente.setBorderPainted(false);
            p.add(agente);
            p.revalidate();
            p.repaint();
            //TimeUnit.MILLISECONDS.sleep(1000);
            //salvar as informações no vetor da estrutura do arquivo
            for(int a = 0 ; a < lc.length ; a ++){
                if(lc[a] == null)break;
                if(lc[a].getLinha() == salvaL && lc[a].getColuna() == salvaC){

                    lc[a].setLinha(salvaL);
                    lc[a].setColuna(salvaC);
                    
                    acaoAux = b.maxAcao(s1, s2, s3, s4);
                    
                    if(i > salvaL){
                        lc[a].setBaixo(recompensa + fatorAprendizado * acaoAux);
                    }
                    else if(i < salvaL){
                       lc[a].setCima(recompensa + fatorAprendizado * acaoAux);
                    }
                    else if(j < salvaC){
                        lc[a].setEsquerda(recompensa + fatorAprendizado * acaoAux);
                    }
                    else if(j > salvaC){
                        lc[a].setDireita(recompensa + fatorAprendizado * acaoAux);
                    }
                    s1 = lc[a].getBaixo();
                    s2 = lc[a].getCima();
                    s3 = lc[a].getEsquerda();
                    s4 = lc[a].getDireita();
                }
            }
            
            
            if(br.tabuleiro[i][j] == 10){
               
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
                i = 1;
                j = 29;
            }
            salvaL = i;
            salvaC = j;
            contAux++;       
        }
        FileWriter cargafinal =  new FileWriter(new File(caminho));
        for(int c = 0 ; c < 1925 ; c++){
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

