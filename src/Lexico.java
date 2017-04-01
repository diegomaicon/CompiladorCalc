/**
 * Created by Diego on 27/03/2017.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Lexico {
    private Token token;
    private String codigo;
    private int L;

    public Lexico() {
    }

    public void abreArquivo(String caminho) throws IOException{

         try {
             FileReader arquivo = new FileReader(caminho);
             BufferedReader br = new BufferedReader(arquivo);
             StringBuffer line = new StringBuffer();
             do {
                line.append(br.readLine());
             }while (br.readLine() != null);
             this.codigo=line.toString();

             br.close();
             arquivo.close();

         }catch(IOException ex){
                 ex.printStackTrace();
             }


    }

    public Token Lex(int pRead,int linha) {
        StringBuilder sb = new StringBuilder();
        int estado = 1;
        Character aux;
        for (int i = pRead; i <= this.codigo.length();i++) {
            aux = this.codigo.charAt(i);

            switch (estado) {
                case 1:

                    if (Character.isLetter(aux)) {
                        estado = 2;
                        sb.append(aux);
                    } else if (aux.equals('=')) {
                        estado = 4;
                    } else if (aux.equals('*')) {
                        estado = 5;
                    } else if (aux.equals('/')) {
                        estado = 6;
                    } else if (aux.equals('+')) {
                        estado = 7;
                    } else if (aux.equals('-')) {
                        estado = 8;
                    } else  if(aux.equals('\n')){
                        linha++;
                        estado = 1;
                    } else if(aux.equals(' ')){
                        estado = 1;
                    }
                    break;

                case 2:
                    if (Character.isLetter(aux) || Character.isDigit(aux)) {
                        estado = 2;
                        sb.append(aux);
                    } else estado = 3;
                    break;

                case 3:
                    token = new Token(sb.toString(),"Ident",linha,i);
                    return token;

                case 4:
                    if(aux.equals('=')){
                        token = new Token(aux.toString(),"Atrib",linha,i);
                        return token;
                    }
                    break;

            }

        }

        return null;
    }


}
