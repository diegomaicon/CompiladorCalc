import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

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
            definitions_list(t);
        }else consome("Eof");
    }

    private void definitions_list(Token token){
        def(token);
        R_definitions_list(token);
    }

    private void def(Token token){
        if(token.equals(TagToken.TKclass)) {
            class_def(token);
        }else{
            function_def(token);
        }
    }

    private void R_definitions_list(Token token){
        if(token.equals(TagToken.TKclass) || token.equals(TagToken.TKFunc) || token.equals(TagToken.TKIdent)){
            definitions_list(token);
        }
        //ou vazio
    }

    private void class_def(Token token){

        consome(TagToken.TKclass);
        consome(TagToken.TKIdent);
        class_base(token);
        consome(TagToken.TKAbreChav);
        member_def_list(token);
        consome(TagToken.TKFechaChav);
    }

    private void class_base(Token token){

        if(token.equals(TagToken.TK2pto)) {
            consome(TagToken.TK2pto);
            consome(TagToken.TKIdent);
        }
    }

    private void member_def_list(Token token){

        member_def(token);
        R_member_def_list(token);
    }

    private void R_member_def_list(Token token) {

        if (token.equals(TagToken.TKstatic) || token.equals(TagToken.TKFunc) || token.equals(TagToken.TKIdent)){
            member_def_list(token);
        }
    }

    private void member_def(Token token){

        op_static(token);
        R_member_def(token);
    }

    private void R_member_def(Token token){

        if(token.equals(TagToken.TKIdent)){
            var_name_list(token);
        }else{
            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_formal_arg_list(token);
            consome(TagToken.TKFechaPar);
            consome(TagToken.TKPteVir);
        }
    }

    private void op_static(Token token){

        if(token.equals(TagToken.TKstatic)){
            consome(TagToken.TKstatic);
        }
    }

    private void var_name_list(Token token){

        consome(TagToken.TKIdent);
        op_vector(token);
        R_var_name_list(token);
    }

    private void R_var_name_list(Token token){

        if(token.equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            var_name_list(token);
        }
    }

    private void op_vector(Token token){

        if(token.equals(TagToken.TKAbreCouchete)){
            consome(TagToken.TKAbreCouchete);
            consome(TagToken.TKNumInteiro);
            consome(TagToken.TKFechaCouchete);
        }
    }

    private void op_formal_arg_list(Token token){

        if(token.equals(TagToken.TKIdent)){
            formal_arg_list(token);
        }
    }

    private void formal_arg_list(Token token){

        consome(TagToken.TKIdent);
        R_formal_arg_list(token);
    }

    private void R_formal_arg_list(Token token){

        if(token.equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            formal_arg_list(token);
        }
    }

    private void function_def(Token token){

        op_class_owner(token);
        consome(TagToken.TKFunc);
        consome(TagToken.TKAbrePar);
        op_parameters(token);
        consome(TagToken.TKFechaPar);
        consome(TagToken.TKAbreChav);
        statement_list(token);
        consome(TagToken.TKFechaChav);
    }

    private void op_class_owner(Token token){

        if(token.equals(TagToken.TKIdent)){
            consome(TagToken.TKIdent);
            consome(TagToken.TK4tpo);
        }
    }

    private void op_parameters(Token token){

        op_formal_arg_list(token);
        op_temp_list(token);
    }

    private void op_temp_list(Token token){

        if(token.equals(TagToken.TKPteVir)){
            consome(TagToken.TKPteVir);
            temp_list(token);
        }
    }

    private void temp_list(Token token){

        consome(TagToken.TKIdent);
        R_temp_list(token);
    }

    private void R_temp_list(Token token){

        if(token.equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            temp_list(token);
        }
    }

    private void statement_list(Token token){

    }

    private void statement(Token token){

    }

    private void op_else(Token token){

    }

    private void op_expression(Token token){

    }

    private void op_arguments(Token token){

    }

    private void arg_list(Token token){

    }

    private void R_arg_list(Token token){

    }

    private void lvalue(Token token){

    }

    private void exp(Token token){

    }

    private void R_exp(Token token){

    }

    private void atrib(Token token){

    }

    private void R_atrib(Token token){

    }

    private void or(Token token){

    }

    private void R_or(Token token){

    }

    private void and(Token token){

    }

    private void R_and(Token token){

    }

    private void rel(Token token){

    }

    private void R_rel(Token token){

    }

    private void soma(Token token){

    }

    private void R_soma(Token token){

    }

    private void mult(Token token){

    }

    private void R_mult(Token token){

    }

    private void uno(Token token){

    }

    private void pos(Token token){

    }

    private void R_pos(Token token){

    }

    private void valor(Token token){

    }

    private void R_var_name(Token token){

    }

    private void op_index(Token token){

    }

    private void  consome(String token){

        if (this.t.getLexema().equals(token)){
            this.t = lexico.getToken(this.t);
        } else{
            System.out.println("Era esperado "+token+" mas veio"+this.t.getLexema());
            return;
        }//
    }

}