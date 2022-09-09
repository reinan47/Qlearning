package Interface;

import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import BuscaReforco.BuscaReforcoQuestao2;
import BuscaReforco.BuscaReforcoQuestao1;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Reinan
 */
public class BuscaQuestao1 extends JPanel{
    JButton b = new JButton("Exit");
    public BuscaQuestao1(){
        this.setLayout(null);
        this.setSize(1000,700);
        this.setBackground(SystemColor.LIGHT_GRAY);
        b.setBounds(450, 600, 100, 40);
        b.setBackground(SystemColor.RED);
        b.setBorderPainted(false);
        add(b);
        
        b.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.exit(0);
        });
    }
   
    public static void main(String[] args) throws InterruptedException, IOException{
        JButton agente = new JButton();
        JButton objetivo = new JButton();
        JButton []parede = new JButton[500];
        BuscaReforcoQuestao1 br = new BuscaReforcoQuestao1();
        JLabel titulo = new JLabel("BUSCA EM REFORÇO");
        String caminho = "C:\\Users\\Reinan\\Desktop\\BuscaPorReforco\\DadosDaBusca.txt"; 
        
        BuscaQuestao1 p = new BuscaQuestao1();
        JFrame ja = new JFrame("");        
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                br.tabuleiro[i][j] = 1;
            }
        }
        p.setBounds(0, 0, 50, 800);
        ja.setBounds(0, 0, 1000, 700);
        ja.setLocationRelativeTo(null);
        ja.getContentPane().add(p);
        ja.setVisible(true);
        ja.setDefaultCloseOperation(ja.EXIT_ON_CLOSE);
        
        
        titulo.setBounds(440, 10, 200, 30);
       
        p.add(titulo);
        int cont = 0;
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(br.tabuleiro[i][j] == 1){
                   parede[cont++] = new JButton();
                }
            }
        }
        cont = 0;
        boolean teste = true;
        
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(br.tabuleiro[i][j] == 1){

                    parede[cont].setBounds(70+(j*50), 40+(i*30),48, 4);
                    parede[cont].setBackground(SystemColor.BLACK);
                    parede[cont].setBorderPainted(false);
                    p.add(parede[cont]);
                    p.revalidate();
                    ja.repaint();
                    cont++;    
                }
            }
        } 
                 
        objetivo.setBounds(70+(11*50), 40+(15*30), 49, 29);
        objetivo.setBackground(SystemColor.GREEN);
        objetivo.setBorderPainted(false);
        p.add(objetivo);
        p.revalidate();
        p.repaint();

        double fatorAprendizado = 0.9;
        double recompensa = 0;
        String []acao = {"le","ri","up","do"};


       
        
        int lin = (int) (Math.random()*18);
        int col = (int) (Math.random()*17);
        int boneco = br.tabuleiro[lin][col];
        FileWriter dadosTB =  new FileWriter(new File(caminho));
        int contAux = 0;
        int i = 1;
        int j = 1;
        String aux;
        BuscaQuestao1 b = new BuscaQuestao1();
        double s1=0,s2=0,s3=0,s4=0;
        br.tabuleiro[11][15] = 10;
        while(contAux < 50000){
            aux = acao[(int)(Math.random()*4)];
            double acaoAux = b.maxAcao(s1, s2, s3, s4);
            
                if(aux == "up"){
                    if(br.tabuleiro[i-1][j] == 1 && i-1 > 0)
                       i-=1;
                }
                else if(aux == "do"){
                    if(br.tabuleiro[i+1][j] == 1 && i+1 < 17)
                       i+=1;
                }
                else if(aux == "le"){
                    if(br.tabuleiro[i][j-1] == 1 && j-1 > 0)
                       j-=1;
                }
                else if(aux == "ri"){
                    if(br.tabuleiro[i][j+1] == 1 && j+1 < 16)
                       j+=1;
                }
            TimeUnit.MILLISECONDS.sleep(1);

            recompensa = br.tabuleiro[i][j]; 
            agente.setBounds(70+(j*50), 40+(i*30), 48, 30);
            agente.setBackground(SystemColor.RED);
            agente.setBorderPainted(false);
            p.add(agente);
            p.revalidate();
            ja.repaint();
            if(br.tabuleiro[i][j] == 10){
                dadosTB.append("l:" + i + " c:" + j + aux + ":" + (recompensa + fatorAprendizado*acaoAux) + "\n");
                lin = (int) (Math.random()*18);
                col = (int) (Math.random()*17);
            }
            dadosTB.append("l:" + i + " c:" + j + aux + ":" + (recompensa + fatorAprendizado*acaoAux) + "\n");

            contAux++;       
        }
        dadosTB.close();
    }
    public double maxAcao(double a,double b, double c, double d){
        double aux = a;
        if(aux < b){
            aux = b;
        }
        if(aux < c){
            aux = c;
        }
        if(aux < d){
            aux = d;
        }
        return aux;
    }
}

