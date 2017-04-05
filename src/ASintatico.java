/**
 * Created by Diego on 05/04/2017.
 */
public class ASintatico {
   private Lexico lexico;
   private Token t;


    public void ASintatico(){
        lexico = new Lexico();

        this.t.setLinha(1);
        this.t.setpRead(0);
        this.t = lexico.Lex(t.getpRead(),t.getLinha());

       // getToken();
        prog(t);
        consome(TKEof);
    }

    public void prog(Token token){
        seqInst(token);
    }

    public void seqInst(Token token){

        if (token.getLexema().equals(TKPrint) || token.getLexema().equals(TKIdent)){
            inst(token);
            consome(TKPtoeVir);
        }
    }

    public void valor(Token token){

            if(token.equals(TKIdent)){
                consome(TKIdent);
            }
            else if(token.equals(TKNum)) {
                consome(TKNum);
            }
            else {
                consome(TKAbrePar);
                exp(token);
                consome(TKfechaPar);
            }
    }

    public void exp(Token token){
        soma(token);
    }

     public void soma(Token token){
        mult(token);
        restSoma(token);
    }

    public void restSoma(Token token){
        if (token.equals(TKSoma)){
            consome(TKSoma);
            soma(token);
        }
        else if(token.equals(TKSub)){
            consome(TKSub);
            soma(token);
        }
    }


    public void mult(Token token){
        valor(token);
        restMult(token);
    }

    public void restMult(Token token){
        if (token.equals(TKMult)){
            consome(TKMult);
            mult(token);
        }
        else if(token.equals(TKDiv)){
            consome(TKDiv);
            mult(token);
        }
    }
   public void  consome(Token token,t){
       if (token.equals(t)){
           getToken();
       } else{
               print("Era esperado ", tokenString(t))," mas veio", lexema);
       }
   }
}
