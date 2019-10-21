package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jogador implements Runnable{

    public int x = 0, y = 0;
    public int vida = 100;
    public String msg;
    public String dados;

    Socket s;
    Thread thRecebeMsg;
    BufferedReader entrada;
    PrintWriter saida;
    int speed = 15;

    public Jogador(Socket s){
        configurarJogador(s);
    }

    public void configurarJogador(Socket s){
        this.s = s;
        try {
            entrada = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(s.getOutputStream());
            thRecebeMsg = new Thread(this);
            thRecebeMsg.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGame(){

        if (msg != null) {

            if (msg.equals("r")) {
                if (x <= 706) {
                    x += speed;
                    msg = null;
                }
            }

            if (msg.equals("l")) {
                if (x >= 0) {
                    x -= speed;
                    msg = null;
                }
            }

            if (msg.equals("u")) {
                if (y >= 0) {
                    y -= speed;
                    msg = null;
                }
            }

            if (msg.equals("d")) {
                if (y <= 450) {
                    y += speed;
                    msg = null;
                }
            }
            if (msg == "s") {
                //Jogador.setIconSpace(); //troca para soco.
                msg = null;
            }

        }

        if((msg != "r"||msg != "l"||msg != "u"||msg != "d"||msg != "s")){ //ver depois.
            //Jogador.setIconStopped();
        }
        atualizadClientes(); // enviar para o cliente as novas coordenadas
   }

    public void enviarMensagem(){
        dados = x + ":" + y + ":" + vida;
        //System.out.println(dados);
        saida.println(dados);
        saida.flush();
    }

    public void atualizadClientes(){
        for(Jogador cliente:MultiJogador.jogador){
            cliente.enviarMensagem();
        }
    }

    public void receberMensagens() {
        new Thread(() -> {
        try {
            String msg;
            while ((msg = entrada.readLine()) != null) {
                System.out.println(msg);
                this.msg = msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }).start();
    }

    @Override
    public void run() {
        receberMensagens();
        while (true) {
            try {
                updateGame();
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}