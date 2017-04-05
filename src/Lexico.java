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

    public void abreArquivo(String caminho) throws IOException {
        String x="";
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader br = new BufferedReader(arquivo);
            StringBuffer line = new StringBuffer();
            do {
                x = br.readLine();
                line.append(x);
            } while (x != null);
            this.codigo = line.toString();

            br.close();
            arquivo.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Token getToken(Token token) {

        if (token.getpRead() != -1) {
            this.token = Lex(token.getpRead(), token.getLinha());
            if (this.token.getpRead() != -1) {
                System.out.println(this.token.toString());
                return this.token;
            }
        }
        return null;
    }

    private Token Lex(int pRead, int linha) {

        StringBuilder sb = new StringBuilder();
        int estado = 1;
        Character aux = ' ';
        while ((codigo.length()-4) != pRead){
            if (pRead < codigo.length()) {
                aux = this.codigo.charAt(pRead);
            }
            switch (estado) {
                case 1:

                    if (Character.isLetter(aux)) {
                        estado = 2;
                        sb.append(aux);
                    }
                    else if (Character.isDigit(aux)){
                        estado = 10;
                        sb.append(aux);
                    }
                    else if (aux.equals('=')) {
                        estado = 4;
                        sb.append(aux);
                    } else if (aux.equals('*')) {
                        sb.append(aux);
                        estado = 5;
                    } else if (aux.equals('/')) {
                        sb.append(aux);
                        estado = 6;
                    } else if (aux.equals('+')) {
                        sb.append(aux);
                        estado = 7;
                    } else if (aux.equals('-')) {
                        sb.append(aux);
                        estado = 8;
                    } else if (aux.equals(';')) {
                        sb.append(aux);
                        estado = 9;
                    } else if (aux.equals(' ')) {
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
                    token = new Token(sb.toString(), "Ident", linha, pRead-1);
                    return token;

                case 4:

                    token = new Token(sb.toString(), "Atrib", linha, pRead);
                     return token;

                case 5:

                    token = new Token(sb.toString(), "Mult", linha, pRead);
                    return token;

                case 6:

                    token = new Token(sb.toString(), "Div", linha, pRead);
                    return token;

                case 7:

                    token = new Token(sb.toString(), "Soma", linha, pRead);
                    return token;
                case 8:

                    token = new Token(sb.toString(), "Sub", linha, pRead);
                    return token;
                case 9:
                    token = new Token(sb.toString(), "PteVir", linha, pRead);
                    linha++;
                    return token;


                case 10:
                    if (Character.isDigit(aux)) {
                        estado = 10;
                        sb.append(aux);
                    } else if (aux.equals('.')){
                        estado = 11;
                    } else{
                        estado = 13;
                    }

                    break;
                case 11:
                    if (Character.isDigit(aux)) {
                        estado = 12;
                        sb.append(aux);
                    }else{
                        token = new Token(sb.toString(), "Erro-Token", linha, pRead-1);
                        return token;
                    }

                case 12:
                    if (Character.isDigit(aux)){
                                estado = 12;
                                sb.append(aux);
                            } else{
                                token = new Token(sb.toString(), "Num", linha, pRead-1);
                                return token;

                            }
                        break;
                case 13:
                    token = new Token(sb.toString(), "Num", linha, pRead-1);
                    return token;

                default:
                    token = new Token(sb.toString(), "Num", linha, -1);
                    return token;
            }
            pRead++;
        }

        return token = new Token("","",-1,-1);
    }
}
