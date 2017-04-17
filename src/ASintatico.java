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
        }else consome("Eof");
    }

    private void prog(Token token){
        seqInst(token);
    }

    private void seqInst(Token token){
        instrucao(token);
        //espera um ponto e virgula
        consome(";");
        seqInst(token);
    }

    private void instrucao(Token token){

        if (token.getLexema().equals("print")) {
            //espera um print
            consome("print");
            //espera um identificador
            consome("ident");
            //espera um ponto e virgula
            consome("ptovir");
        } else if(token.getLexema().equals("ident")) {
            //espera identificador
            consome("ident");
            //espera atribuicao
            consome("atribuicao");

            exp(token);
        }
    }


    private void valor(Token token){

        if(token.getLexema().equals("ident")){
            //espera um identificador
            consome("ident");
        }else if(token.getLexema().equals("num")) {
            //espera um numero
            consome("num");
        }else if(token.getLexema().equals("abraPar")){
            consome("abrePar");
            exp(token);
            consome("fechaPar");
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
        if (token.getLexema().equals("soma")){
            consome("soma");
            soma(token);
        }
        else if(token.getLexema().equals("sub")){
            consome("sub");
            soma(token);
        }
    }


    private void mult(Token token){
        valor(token);
        restMult(token);
    }

    private void restMult(Token token){
        if (token.getLexema().equals("mult")){
            consome("mult");
            mult(token);
        }
        else if(token.getLexema().equals("div")){
            consome("div");
            mult(token);
        }
    }
    private void  consome(String token){

        if (this.t.getLexema().equals(token)){
            this.t = lexico.getToken(this.t);
        } else{
            System.out.println("Era esperado "+token+" mas veio"+this.t.getLexema());
            return;
        }
    }

}