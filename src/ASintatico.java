import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import javax.swing.text.html.HTML;
import java.io.IOException;

/**
 * Created by Diego on 05/04/2017.
 */
public class ASintatico {
    private Lexico lexico = new Lexico();
    private Token t = new Token();


    public void start(){
        try {
            lexico.abreArquivo("teste.calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        t  =  new Token(TagToken.TKNull,"",1,0);
        this.t = lexico.getToken(t);
        if(t.getpRead() != -1){
            definitions_list(t);
        }else consome(TagToken.TKErroToken);
    }

    private void definitions_list(Token token){
        def(token);
        R_definitions_list(token);
    }

    private void def(Token token){
        if(token.getIdToken().equals(TagToken.TKclass)) {
            class_def(token);
        }else{
            function_def(token);
        }
    }

    private void R_definitions_list(Token token){
        if(token.getIdToken().equals(TagToken.TKclass) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKIdent)){
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

        if(token.getIdToken().equals(TagToken.TK2pto)) {
            consome(TagToken.TK2pto);
            consome(TagToken.TKIdent);
        }
    }

    private void member_def_list(Token token){

        member_def(token);
        R_member_def_list(token);
    }

    private void R_member_def_list(Token token) {

        if (token.getIdToken().equals(TagToken.TKstatic) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKIdent)){
            member_def_list(token);
        }
    }

    private void member_def(Token token){

        op_static(token);
        R_member_def(token);
    }

    private void R_member_def(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){
            var_name_list(token);
        }
        else if(token.getIdToken().equals(TagToken.TKFunc)){
            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_formal_arg_list(token);
            consome(TagToken.TKFechaPar);
            consome(TagToken.TKPteVir);
        }
    }

    private void op_static(Token token){

        if(token.getIdToken().equals(TagToken.TKstatic)){
            consome(TagToken.TKstatic);
        }
    }

    private void var_name_list(Token token){
        consome(TagToken.TKIdent);
        op_vector(token);
        R_var_name_list(token);
    }

    private void R_var_name_list(Token token){
        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            var_name_list(token);
        }
    }

    private void op_vector(Token token){
        if(token.getIdToken().equals(TagToken.TKAbreCouchete)){
            consome(TagToken.TKAbreCouchete);
            consome(TagToken.TKNumInteiro);
            consome(TagToken.TKFechaCouchete);
        }
    }

    private void op_formal_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){
            formal_arg_list(token);
        }
    }

    private void formal_arg_list(Token token){

        consome(TagToken.TKIdent);
        R_formal_arg_list(token);
    }

    private void R_formal_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
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

        if(token.getIdToken().equals(TagToken.TKIdent)){
            consome(TagToken.TKIdent);
            consome(TagToken.TK4tpo);
        }
    }

    private void op_parameters(Token token){

        op_formal_arg_list(token);
        op_temp_list(token);
    }

    private void op_temp_list(Token token){

        if(token.getIdToken().equals(TagToken.TKPteVir)){
            consome(TagToken.TKPteVir);
            temp_list(token);
        }
    }

    private void temp_list(Token token){

        consome(TagToken.TKIdent);
        R_temp_list(token);
    }

    private void R_temp_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            temp_list(token);
        }
    }

    private void statement_list(Token token){

        if(token.getIdToken().equals(TagToken.TKif) || token.getIdToken().equals(TagToken.TKwhile) || token.getIdToken().equals(TagToken.TKdo) || token.getIdToken().equals(TagToken.TKPteVir) ||
                token.getIdToken().equals(TagToken.TKAbreChav) || token.getIdToken().equals(TagToken.TKfor) || token.getIdToken().equals(TagToken.TKbreak) || token.getIdToken().equals(TagToken.TKcontinue) ||
                token.getIdToken().equals(TagToken.TKreturn) || token.getIdToken().equals(TagToken.TKSoma) || token.getIdToken().equals(TagToken.TKSub) || token.getIdToken().equals(TagToken.TKDiferente) ||
                token.getIdToken().equals(TagToken.TKPlusPlus) || token.getIdToken().equals(TagToken.TKSubSub) || token.getIdToken().equals(TagToken.TKIdent) || token.getIdToken().equals(TagToken.TKAbrePar) ||
                token.getIdToken().equals(TagToken.TKnew) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) ||
                token.getIdToken().equals(TagToken.TKString) || token.getIdToken().equals(TagToken.TKnil) || token.getIdToken().equals(TagToken.TKFechaChav) )
        {
            statement(token);
            statement_list(token);
        }

    }

    private void statement(Token token){

        if(token.getIdToken().equals(TagToken.TKif)){

            consome(TagToken.TKif);
            consome(TagToken.TKAbrePar);
            exp(token);
            consome(TagToken.TKFechaPar);
            statement(token);
            op_else(token);

        }else if(token.getIdToken().equals(TagToken.TKwhile)) {

            consome(TagToken.TKwhile);
            consome(TagToken.TKAbrePar);
            exp(token);
            consome(TagToken.TKFechaPar);
            statement(token);


        }else if(token.getIdToken().equals(TagToken.TKdo)) {

            consome(TagToken.TKdo);
            statement(token);
            consome(TagToken.TKwhile);
            consome(TagToken.TKAbrePar);
            exp(token);
            consome(TagToken.TKFechaPar);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKAbreChav)) {

            consome(TagToken.TKAbreChav);
            statement_list(token);
            consome(TagToken.TKFechaChav);

        }else if(token.getIdToken().equals(TagToken.TKfor)) {

            consome(TagToken.TKfor);
            consome(TagToken.TKAbrePar);
            exp(token);
            consome(TagToken.TKPteVir);
            exp(token);
            consome(TagToken.TKPteVir);
            exp(token);
            consome(TagToken.TKFechaPar);
            statement(token);

        }else if(token.getIdToken().equals(TagToken.TKbreak)) {

            consome(TagToken.TKbreak);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKcontinue)) {

            consome(TagToken.TKcontinue);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKreturn)) {

            consome(TagToken.TKreturn);
            op_expression(token);

        }else if(token.getIdToken().equals(TagToken.TKSoma) || token.getIdToken().equals(TagToken.TKSub) || token.getIdToken().equals(TagToken.TKNegacao) || token.getIdToken().equals(TagToken.TKPlusPlus)
                || token.getIdToken().equals(TagToken.TKSubSub) || token.getIdToken().equals(TagToken.TKIdent) || token.getIdToken().equals(TagToken.TKAbrePar) || token.getIdToken().equals(TagToken.TKnew)
                || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) || token.getIdToken().equals(TagToken.TKString)
                || token.getIdToken().equals(TagToken.TKnil)) {

            op_expression(token);
            consome(TagToken.TKPteVir);
        }
    }

    private void op_else(Token token){

        if(token.getIdToken().equals(TagToken.TKelse)){

            consome(TagToken.TKelse);
            statement(token);
        }
        //ou vazio
    }

    private void op_expression(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma) || token.getIdToken().equals(TagToken.TKSub) || token.getIdToken().equals(TagToken.TKDiferente) || token.getIdToken().equals(TagToken.TKPlusPlus)
                || token.getIdToken().equals(TagToken.TKSubSub) || token.getIdToken().equals(TagToken.TKIdent) || token.getIdToken().equals(TagToken.TKAbrePar) || token.getIdToken().equals(TagToken.TKnew)
                || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) || token.getIdToken().equals(TagToken.TKString)
                || token.getIdToken().equals(TagToken.TKnil)) {

            exp(token);
        }
        //ou vazio
    }

    private void op_arguments(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){

            arg_list(token);
        }
        //ou vazio
    }

    private void arg_list(Token token){

        consome(TagToken.TKIdent);
        R_arg_list(token);
    }

    private void R_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            arg_list(token);
        }
        //ou vazio
    }

    private void lvalue(Token token){

        consome(TagToken.TKIdent);
        op_vector(token);
    }

    private void exp(Token token){

        atrib(token);
        R_exp(token);
    }

    private void R_exp(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){

            consome(TagToken.TKVirgula);
            atrib(token);
            R_exp(token);
        }
        //ou vazio
    }

    private void atrib(Token token){

        or(token);
        R_atrib(token);
    }

    private void R_atrib(Token token){

        if(token.getIdToken().equals(TagToken.TKAtrib)){

            consome(TagToken.TKAtrib);
            or(token);
            R_atrib(token);
        }
    }

    private void or(Token token){

        and(token);
        R_or(token);
    }

    private void R_or(Token token){

        if(token.getIdToken().equals(TagToken.TKOr)) {

            consome(TagToken.TKOr);
            and(token);
            R_or(token);
        }
        //ou vazio
    }

    private void and(Token token){

        rel(token);
        R_and(token);
    }

    private void R_and(Token token){

        if(token.getIdToken().equals(TagToken.TKAnd)){

            consome(TagToken.TKAnd);
            rel(token);
            R_and(token);
        }
        //ou vazio
    }

    private void rel(Token token){

        soma(token);
        R_rel(token);
    }

    private void R_rel(Token token){

        if(token.getIdToken().equals(TagToken.TKAtrib)){

            consome(TagToken.TKAtrib);
            soma(token);

        }else if(token.getIdToken().equals(TagToken.TKDiferente)){

            consome(TagToken.TKDiferente);
            soma(token);

        }else if(token.getIdToken().equals(TagToken.TKMenor)){

            consome(TagToken.TKMenor);
            soma(token);

        }else if(token.getIdToken().equals(TagToken.TKMenorIgual)){

            consome(TagToken.TKMenorIgual);
            soma(token);

        }else if(token.getIdToken().equals(TagToken.TKMaior)){

            consome(TagToken.TKMaior);
            soma(token);

        }else if(token.getIdToken().equals(TagToken.TKMaiorIgual)){

            consome(TagToken.TKMaiorIgual);
            soma(token);
        }
        //ou vazio
    }

    private void soma(Token token){

        mult(token);
        R_soma(token);
    }

    private void R_soma(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma)){

            consome(TagToken.TKSoma);
            mult(token);
            R_soma(token);

        }else if(token.getIdToken().equals(TagToken.TKSub)){

            consome(TagToken.TKSub);
            mult(token);
            R_soma(token);
        }
        //ou vazio
    }

    private void mult(Token token){

        uno(token);
        R_mult(token);
    }

    private void R_mult(Token token){

        if(token.getIdToken().equals(TagToken.TKMult)){

            consome(TagToken.TKMult);
            uno(token);
            R_mult(token);

        }else if(token.getIdToken().equals(TagToken.TKDiv)){

            consome(TagToken.TKDiv);
            uno(token);
            R_mult(token);

        }else if(token.getIdToken().equals(TagToken.TKMod)){

            consome(TagToken.TKMod);
            uno(token);
            R_mult(token);
        }
        //ou vazio
    }

    private void uno(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma)){

            consome(TagToken.TKSoma);
            uno(token);

        }else if(token.getIdToken().equals(TagToken.TKSub)){

            consome(TagToken.TKSub);
            uno(token);

        }else if(token.getIdToken().equals(TagToken.TKDiferente)){

            consome(TagToken.TKDiferente);
            uno(token);

        }else if(token.getIdToken().equals(TagToken.TKPlusPlus)){

            consome(TagToken.TKPlusPlus);
            uno(token);

        }else if(token.getIdToken().equals(TagToken.TKSubSub)){

            consome(TagToken.TKSubSub);
            uno(token);
        }else{
            pos(token);
        }
    }

    private void pos(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){
            lvalue(token);
            R_pos(token);
        }else{
            valor(token);
        }
    }

    private void R_pos(Token token){

        if(token.getIdToken().equals(TagToken.TKPlusPlus)){
            consome(TagToken.TKPlusPlus);
        }else{
            consome(TagToken.TKSubSub);
        }
    }

    private void valor(Token token){

        if(token.getIdToken().equals(TagToken.TKAbrePar)){

            consome(TagToken.TKAbrePar);
            exp(token);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKnew)){

            consome(TagToken.TKnew);
            consome(TagToken.TKIdent);
            consome(TagToken.TKAbrePar);
            op_arguments(token);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKFunc)){

            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_arguments(token);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKNumInteiro)){

            consome(TagToken.TKNumInteiro);

        }else if(token.getIdToken().equals(TagToken.TKNumFloat)){

            consome(TagToken.TKNumFloat);

        }else if(token.getIdToken().equals(TagToken.TKString)){

            consome(TagToken.TKString);

        }else if(token.getIdToken().equals(TagToken.TKnil)){

            consome(TagToken.TKnil);

        }else{

            consome(TagToken.TKIdent);
            R_var_name(token);
        }
    }

    private void R_var_name(Token token){

        if(token.getIdToken().equals(TagToken.TKSeta)){

            consome(TagToken.TKSeta);
            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_arguments(token);
            consome(TagToken.TKFechaPar);
        }else{
            op_index(token);
        }
    }

    private void op_index(Token token){

        if(token.getIdToken().equals(TagToken.TKAbreCouchete)){

            consome(TagToken.TKAbreCouchete);
            exp(token);
            consome(TagToken.TKFechaCouchete);
        }
        //ou vazio
    }

    private void  consome(TagToken token){

        if (this.t.getIdToken().equals(token)){
            this.t = lexico.getToken(this.t);
        } else{
            System.out.println("Erro linha "+t.getLinha()+" : Era esperado token: '"+token+"' mas veio '"+this.t.getLexema()+"'.");
            System.exit(0);
        }//
    }

}