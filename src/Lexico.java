/**
 * Created by Diego on 27/03/2017.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Lexico {
    private Token token;
    private String codigo;


    public Lexico() {
    }

    public void abreArquivo(String caminho) throws IOException {
        String x = "";
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader br = new BufferedReader(arquivo);
            StringBuffer line = new StringBuffer();
            x = br.readLine();
            while (x != null) {
                line.append(x);
                line.append('\n');
                x = br.readLine();
            }
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
        while (codigo.length() != pRead) {
            if (pRead < codigo.length()) {
                aux = this.codigo.charAt(pRead);
            }
            switch (estado) {
                case 1:
                    if (aux.equals(' ')){
                        estado = 1;
                    } else if (Character.isLetter(aux)) {
                        estado = 2;
                        sb.append(aux);
                    } else if (Character.isDigit(aux)) {
                        estado = 10;
                        sb.append(aux);
                    } else if (aux.equals('=')) {
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
                    } else if (aux.equals('(')) {
                        sb.append(aux);
                        estado = 14;
                    } else if (aux.equals(')')) {
                        sb.append(aux);
                        estado = 15;
                    } else if (aux.equals('{')) {
                        sb.append(aux);
                        estado = 16;
                    } else if (aux.equals('}')) {
                        sb.append(aux);
                        estado = 17;
                    } else if (aux.equals(',')) {
                        sb.append(aux);
                        estado = 18;
                    } else if (aux.equals('[')) {
                        sb.append(aux);
                        estado = 19;
                    } else if (aux.equals(']')) {
                        sb.append(aux);
                        estado = 20;
                    } else if (aux.equals(':')) {
                        sb.append(aux);
                        estado = 21;
                    } else if (aux.equals('%')) {
                        sb.append(aux);
                        estado = 22;
                    } else if (aux.equals('>')) {
                        sb.append(aux);
                        estado = 23;
                    } else if (aux.equals('<')) {
                        sb.append(aux);
                        estado = 24;
                    } else if (aux.equals('&')) {
                        sb.append(aux);
                        estado = 25;
                    } else if (aux.equals('|')) {
                        sb.append(aux);
                        estado = 26;
                    } else if (aux.equals('!')) {
                        sb.append(aux);
                        estado = 27;
                    } else if (aux.equals('"')) {
                        sb.append(aux);
                        estado = 30;
                    } else if (aux.equals('\n')) {
                        estado = 1;
                        linha++;
                    }
                    break;

                case 2:
                    if (Character.isLetter(aux) || Character.isDigit(aux) || aux.equals('_')) {
                        estado = 2;
                        sb.append(aux);
                    } else{
                        estado = 3;
                        pRead--;
                    }
                    break;

                case 3:
                    //Buscando palavras reservadas
                    switch (sb.toString().toUpperCase()){
                        case "CLASS":
                            token = new Token(TagToken.TKclass, sb.toString(), linha, pRead );
                            return token;
                        case "IF":
                            token = new Token(TagToken.TKif, sb.toString(), linha, pRead );
                            return token;
                        case "WHILE":
                            token = new Token(TagToken.TKwhile, sb.toString(), linha, pRead );
                            return token;
                        case "DO":
                            token = new Token(TagToken.TKdo, sb.toString(), linha, pRead );
                            return token;
                        case "BREAK":
                            token = new Token(TagToken.TKbreak, sb.toString(), linha, pRead );
                            return token;
                        case "FOR":
                            token = new Token(TagToken.TKfor, sb.toString(), linha, pRead );
                            return token;
                        case "CONTINUE":
                            token = new Token(TagToken.TKcontinue, sb.toString(), linha, pRead );
                            return token;
                        case "RETURN":
                            token = new Token(TagToken.TKreturn, sb.toString(), linha, pRead );
                            return token;
                        case "ELSE":
                            token = new Token(TagToken.TKelse, sb.toString(), linha, pRead );
                            return token;
                        case "STATIC":
                            token = new Token(TagToken.TKstatic, sb.toString(), linha, pRead );
                            return token;
                        case "NEW":
                            token = new Token(TagToken.TKnew, sb.toString(), linha, pRead );
                            return token;
                        case "NIL":
                            token = new Token(TagToken.TKnil, sb.toString(), linha, pRead );
                            return token;
                        case "PRINT":
                            token = new Token(TagToken.TKprint, sb.toString(), linha, pRead );
                            return token;
                        case "THIS":
                            token = new Token(TagToken.TKthis, sb.toString(), linha, pRead);
                            return token;
                        default:
                            if(aux.equals('(')){
                                token = new Token(TagToken.TKFunc, sb.toString(), linha, pRead );
                                return token;
                            } else{
                                token = new Token(TagToken.TKIdent, sb.toString(), linha, pRead);
                                return token;
                            }
                    }


                case 4:
                    if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKIgualIgual, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TKAtrib, sb.toString(), linha, pRead);
                        return token;
                    }

                case 5:
                    if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKMultAtrib, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TKMult, sb.toString(), linha, pRead);
                        return token;
                    }

                case 6:
                    if (aux.equals('*') || aux.equals('/')) {
                        estado = 50;     //Consome comentários
                    } else if (aux.equals(' ')) {
                        token = new Token(TagToken.TKDiv, sb.toString(), linha, pRead);
                        return token;
                    }
                    break;
                case 7:
                    if (aux.equals('+')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKPlusPlus, sb.toString(), linha, pRead+1);
                        return token;
                    } else if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKSomaAtrib, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TKSoma, sb.toString(), linha, pRead);
                        return token;
                    }

                case 8:
                    if (aux.equals('-')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKSubSub, sb.toString(), linha, pRead+1);
                        return token;
                    } else if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKSubAtrib, sb.toString(), linha, pRead + 1);
                        return token;
                    } else if (aux.equals('>')) {
                            sb.append(aux);
                            token = new Token(TagToken.TKSeta, sb.toString(), linha, pRead+1);
                            return token;
                    } else{
                        token = new Token(TagToken.TKSub, sb.toString(), linha, pRead);
                        return token;
                    }

                case 9:
                    token = new Token(TagToken.TKPteVir, sb.toString(), linha, pRead);
                    return token;

                case 10:
                    if (Character.isDigit(aux)) {
                        estado = 10;
                        sb.append(aux);
                    } else if (aux.equals('.')) {
                        sb.append(aux);
                        estado = 11;
                    } else {
                        estado = 13;
                    }

                    break;
                case 11:
                    if (Character.isDigit(aux)) {
                        estado = 12;
                        sb.append(aux);
                    } else {
                        token = new Token(TagToken.TKErroToken, sb.toString(), linha, pRead - 1);
                        return token;
                    }
                    break;
                case 12: //Pega numero Float

                    if (Character.isDigit(aux) || aux.equals('E') || aux.equals('e') || aux.equals('+') || aux.equals('-')) {
                        estado = 12;
                        sb.append(aux);
                    } else {
                        token = new Token(TagToken.TKNumFloat, sb.toString(), linha, pRead);
                        return token;

                    }
                    break;

                case 13: //Pega numero inteiro
                    token = new Token(TagToken.TKNumInteiro, sb.toString(), linha, pRead-1);
                    return token;

                case 14: // Abre parentese
                    token = new Token(TagToken.TKAbrePar, sb.toString(), linha, pRead);
                    return token;

                case 15:  //Fecha parentese
                    token = new Token(TagToken.TKFechaPar, sb.toString(), linha, pRead);
                    return token;

                case 16: // Abre Chave
                    token = new Token(TagToken.TKAbreChav, sb.toString(), linha, pRead);
                    return token;

                case 17:  //Fecha Chave
                    token = new Token(TagToken.TKFechaChav, sb.toString(), linha, pRead);
                    return token;

                case 18:  //Virgula
                    token = new Token(TagToken.TKVirgula, sb.toString(), linha, pRead);
                    return token;

                case 19:  //Abre Couchete
                    token = new Token(TagToken.TKAbreCouchete, sb.toString(), linha, pRead);
                    return token;

                case 20:  //Fecha Couchete
                    token = new Token(TagToken.TKFechaCouchete, sb.toString(), linha, pRead);
                    return token;

                case 21:
                    if (aux.equals(':')) {
                        sb.append(aux);
                        token = new Token(TagToken.TK4tpo, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TK2pto, sb.toString(), linha, pRead);
                        return token;
                    }

                case 22:  //Mod
                    token = new Token(TagToken.TKMod, sb.toString(), linha, pRead);
                    return token;

                case 23: //Maior Igual ou Maior
                    if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKMaiorIgual, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TKMaior, sb.toString(), linha, pRead);
                        return token;
                    }

                case 24:
                    if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKMenorIgual, sb.toString(), linha, pRead+1);
                        return token;
                    } else {
                        token = new Token(TagToken.TKMenor, sb.toString(), linha, pRead);
                        return token;
                    }
                case 25:
                    if (aux.equals('&')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKAnd, sb.toString(), linha, pRead+1);
                        return token;
                    }
                case 26:
                    if (aux.equals('|')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKOr, sb.toString(), linha, pRead + 1);
                        return token;
                    }
                case 27:
                    if (aux.equals('=')) {
                        sb.append(aux);
                        token = new Token(TagToken.TKDiferente, sb.toString(), linha, pRead + 1);
                        return token;
                    } else{
                        token = new Token(TagToken.TKNegacao, sb.toString(), linha, pRead);
                        return token;
                    }
                case 28:
                    if(aux.equals("(")){
                        token = new Token(TagToken.TKFunc, sb.toString(), linha, pRead );
                        return token;
                    } else{
                        token = new Token(TagToken.TKIdent, sb.toString(), linha, pRead);
                        return token;
                    }

                case 30: //Pegar String
                    if (!(aux == '"')) {
                        sb.append(aux);
                        estado = 30;
                    } else if (sb.charAt(sb.length() - 1) == '\\') {
                        sb.append(aux);
                        estado = 30;
                    } else{
                        sb.append(aux);
                        token = new Token(TagToken.TKString, sb.toString(), linha, pRead+1);
                        return token;
                    }
                    break;

                case 50: /* Cosome comentários */
                    if (Character.isDigit(aux) || Character.isLetter(aux)){
                        estado = 50;
                    }else  if (aux.equals('*')){
                        estado = 51;
                    }else if(aux.equals('\n')){
                        estado = 1;
                        sb.deleteCharAt(0);
                    }
                    break;
                case 51:
                    if (aux.equals('/')){
                        estado = 1;
                        sb.deleteCharAt(0);
                    } else estado = 50;
                    break;

                default:
                    token = new Token(TagToken.TKNull, sb.toString(), linha, -1);
                    return token;
            }
            pRead++;
        }

        return token = new Token(TagToken.TKNull, "", -1, -1);
    }
}
