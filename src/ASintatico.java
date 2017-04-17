/**
 * Created by Diego on 05/04/2017.
 */
public class ASintatico {
    private Lexico lexico = new Lexico();
    private Token t = new Token("","",1,0);


    public void ASintatico(){
    }

    public void start(){
        this.t = lexico.getToken(t);
         if(t.getpRead() != -1){
            prog(t);
        }else consome(TKEof);
    }

    private void prog(Token token){
        seqInst(token);
    }

    private void seqInst(Token token){
        instrucao(token);
        if (token.getLexema().equals(TKPtoeVir)){
            consome(TKPtoeVir);
        }
        seqInst(token);
    }

    private void instrucao(Token token){
        if (token.getLexema().equals(TKPrint)) {
            token = lexico.getToken(token);
        } else if(token.getLexema().equals(TKIdent)) {
            token = lexico.getToken(token);
        } else if(token.getLexema().equals(TKPtoeVir)){
            consome(token);
        }

        if (token.getLexema().equals(TKIdent)){
            token = lexico.getToken(token);
        } else if(token.getLexema().equals(TKAtrib)) {
            token = lexico.getToken(token);
            exp(token);
        }
    }


    private void valor(Token token){

        if(token.getLexema().equals(TKIdent)){
            consome(TKIdent);
        }
        else if(token.getLexema().equals(TKNum)) {
            consome(TKNum);
        }
        else {
            consome(TKAbrePar);
            exp(token);
            consome(TKfechaPar);
        }
    }

    private void exp(Token token){
        soma(token);
    }

    private void soma(Token token){
        mult(token);
        restSoma(token);
    }

    private void restSoma(Token token){
        if (token.getLexema().equals(TKSoma)){
            consome(TKSoma);
            soma(token);
        }
        else if(token.getLexema().equals(TKSub)){
            consome(TKSub);
            soma(token);
        }
    }


    private void mult(Token token){
        valor(token);
        restMult(token);
    }

    private void restMult(Token token){
        if (token.getLexema().equals(TKMult)){
            consome(TKMult);
            mult(token);
        }
        else if(token.equals(TKDiv)){
            consome(TKDiv);
            mult(token);
        }
    }
    private void  consome(Token token){
                start();
        /*
        if (token.getLexema().equals(t)){
              token = lexico.getToken();
        } else{
            System.out.println("Era esperado ", tokenString(t));," mas veio", lexema);
        }
    }
    */
}
