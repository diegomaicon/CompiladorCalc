import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import javax.swing.text.html.HTML;
import java.io.IOException;

/**
 * Created by Diego e Ronan on 05/04/2017.
 */
public class ASintatico {
    private Lexico lexico = new Lexico();
    private Token t = new Token();

    /**
     *  Inicia analisador sintático
     * @param codigo
     */
    public void start(String codigo){
        try {
            //Passa codigo Fonte para Lexico
            lexico.abreArquivo(codigo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        t  =  new Token(TagToken.TKNull,"",1,0);
        this.t = lexico.getToken(t);
        if(t.getpRead() != -1){
            definitions_list(this.t);

        }else consome(TagToken.TKErroToken);
    }

    private void definitions_list(Token token){
        def(this.t);
        R_definitions_list(this.t);
    }

    private void def(Token token){
        if(token.getIdToken().equals(TagToken.TKclass)) {
            class_def(this.t);
        }else{
            function_def(this.t);
        }
    }

    private void R_definitions_list(Token token){

        if(token.getIdToken().equals(TagToken.TKclass) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKIdent)){
            definitions_list(this.t);
        }
        //ou vazio
    }

    private void class_def(Token token){

        consome(TagToken.TKclass);
        consome(TagToken.TKIdent);
        class_base(this.t);
        consome(TagToken.TKAbreChav);
        member_def_list(this.t);
        consome(TagToken.TKFechaChav);
    }

    private void class_base(Token token){

        if(token.getIdToken().equals(TagToken.TK2pto)) {
            consome(TagToken.TK2pto);
            consome(TagToken.TKIdent);
        }
        //ou vazio
    }

    private void member_def_list(Token token){

        member_def(this.t);
        R_member_def_list(this.t);
    }

    private void R_member_def_list(Token token) {

        if (token.getIdToken().equals(TagToken.TKstatic) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKIdent)){
            member_def_list(this.t);
        }
        //ou vazio
    }

    private void member_def(Token token){

        op_static(this.t);
        R_member_def(this.t);
    }

    private void R_member_def(Token token){

        if(token.getIdToken().equals(TagToken.TKFunc)){
            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_formal_arg_list(this.t);
            consome(TagToken.TKFechaPar);
            consome(TagToken.TKPteVir);
        }else{
            var_name_list(this.t);
            consome(TagToken.TKPteVir);
        }
    }

    private void op_static(Token token){

        if(token.getIdToken().equals(TagToken.TKstatic)){
            consome(TagToken.TKstatic);
        }
        //ou vazio
    }

    private void var_name_list(Token token){
        consome(TagToken.TKIdent);
        op_vector(this.t);
        R_var_name_list(this.t);
    }

    private void R_var_name_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            var_name_list(this.t);
        }
        //ou vazio
    }

    private void op_vector(Token token){

        if(token.getIdToken().equals(TagToken.TKAbreCouchete)){
            consome(TagToken.TKAbreCouchete);
            consome(TagToken.TKNumInteiro);
            consome(TagToken.TKFechaCouchete);
        }
        //ou vazio
    }

    private void op_formal_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){

            formal_arg_list(this.t);
        }
        //ou vazio
    }

    private void formal_arg_list(Token token){

        if (token.getIdToken().equals(TagToken.TKIdent)){
            consome(TagToken.TKIdent);
            R_formal_arg_list(this.t);
        } else{
            consome(TagToken.TKPteVir);
            formal_arg_list(this.t);
        }
    }

    private void R_formal_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            formal_arg_list(this.t);
        }
        //ou vazio
    }

    private void function_def(Token token){

        op_class_owner(this.t);
        consome(TagToken.TKFunc);
        consome(TagToken.TKAbrePar);
        op_parameters(this.t);
        consome(TagToken.TKFechaPar);
        consome(TagToken.TKAbreChav);
        statement_list(this.t);
        consome(TagToken.TKFechaChav);
    }

    private void op_class_owner(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){

            consome(TagToken.TKIdent);
            consome(TagToken.TK4tpo);
        }
        //ou vazio
    }

    private void op_parameters(Token token){

        op_formal_arg_list(this.t);
        op_temp_list(this.t);
    }

    private void op_temp_list(Token token){

        if(token.getIdToken().equals(TagToken.TKPteVir)){
            consome(TagToken.TKPteVir);
            temp_list(this.t);
        }
        //ou vazio
    }

    private void temp_list(Token token){

        consome(TagToken.TKIdent);
        R_temp_list(this.t);
    }

    private void R_temp_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            temp_list(this.t);
        }
        //ou vazio
    }

    private void statement_list(Token token){

        if(token.getIdToken().equals(TagToken.TKif) || token.getIdToken().equals(TagToken.TKwhile) || token.getIdToken().equals(TagToken.TKdo) || token.getIdToken().equals(TagToken.TKPteVir) ||
                token.getIdToken().equals(TagToken.TKAbreChav) || token.getIdToken().equals(TagToken.TKfor) || token.getIdToken().equals(TagToken.TKprint) || token.getIdToken().equals(TagToken.TKbreak) || token.getIdToken().equals(TagToken.TKcontinue) ||
                token.getIdToken().equals(TagToken.TKreturn) || token.getIdToken().equals(TagToken.TKSoma) || token.getIdToken().equals(TagToken.TKSub) || token.getIdToken().equals(TagToken.TKDiferente) ||
                token.getIdToken().equals(TagToken.TKPlusPlus) || token.getIdToken().equals(TagToken.TKSubSub) || token.getIdToken().equals(TagToken.TKIdent) || token.getIdToken().equals(TagToken.TKAbrePar) ||
                token.getIdToken().equals(TagToken.TKnew) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) ||
                token.getIdToken().equals(TagToken.TKString) || token.getIdToken().equals(TagToken.TKnil) )
        {
            statement(this.t);
            statement_list(this.t);
        }
        //ou vazio

    }

    private void statement(Token token){

        if(token.getIdToken().equals(TagToken.TKif)){

            consome(TagToken.TKif);
            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKFechaPar);
            statement(this.t);
            op_else(this.t);

        }else if(token.getIdToken().equals(TagToken.TKwhile)) {

            consome(TagToken.TKwhile);
            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKFechaPar);
            statement(this.t);


        }else if(token.getIdToken().equals(TagToken.TKdo)) {

            consome(TagToken.TKdo);
            statement(this.t);
            consome(TagToken.TKwhile);
            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKFechaPar);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKAbreChav)) {

            consome(TagToken.TKAbreChav);
            statement_list(this.t);
            consome(TagToken.TKFechaChav);

        }else if(token.getIdToken().equals(TagToken.TKfor)) {

            consome(TagToken.TKfor);
            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKPteVir);
            exp(this.t);
            consome(TagToken.TKPteVir);
            exp(this.t);
            consome(TagToken.TKFechaPar);
            statement(this.t);

        }else if(token.getIdToken().equals(TagToken.TKprint)) {
            consome(TagToken.TKprint);
            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKbreak)) {

            consome(TagToken.TKbreak);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKcontinue)) {

            consome(TagToken.TKcontinue);
            consome(TagToken.TKPteVir);

        }else if(token.getIdToken().equals(TagToken.TKreturn)) {

            consome(TagToken.TKreturn);
            op_expression(this.t);

        }else if(token.getIdToken().equals(TagToken.TKPteVir)) {
            consome(TagToken.TKPteVir);
        }else{

            op_expression(this.t);
            consome(TagToken.TKPteVir);
        }
    }

    private void op_else(Token token){

        if(token.getIdToken().equals(TagToken.TKelse)){

            consome(TagToken.TKelse);
            statement(this.t);
        }
        //ou vazio
    }

    private void op_expression(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma) || token.getIdToken().equals(TagToken.TKSub) || token.getIdToken().equals(TagToken.TKDiferente) || token.getIdToken().equals(TagToken.TKPlusPlus)
                || token.getIdToken().equals(TagToken.TKSubSub) || token.getIdToken().equals(TagToken.TKIdent) || token.getIdToken().equals(TagToken.TKAbrePar) || token.getIdToken().equals(TagToken.TKnew)
                || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) || token.getIdToken().equals(TagToken.TKString)
                || token.getIdToken().equals(TagToken.TKnil) || token.getIdToken().equals(TagToken.TKthis)) {

            exp(this.t);
        }
        //ou vazio
    }

    private void op_arguments(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)  || token.getIdToken().equals(TagToken.TKAbrePar) || token.getIdToken().equals(TagToken.TKnew) || token.getIdToken().equals(TagToken.TKFunc) || token.getIdToken().equals(TagToken.TKNumInteiro) || token.getIdToken().equals(TagToken.TKNumFloat) || token.getIdToken().equals(TagToken.TKString) || token.getIdToken().equals(TagToken.TKnil)){
            arg_list(this.t);
        }

        //ou vazio
    }

    private void arg_list(Token token) {
            valor(this.t);
            R_arg_list(this.t);
    }

    private void R_arg_list(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            arg_list(this.t);
        }
        //ou vazio
    }

    private void lvalue(Token token){

        consome(TagToken.TKIdent);
        op_vector(this.t);
    }

    private void exp(Token token){

        atrib(this.t);
        R_exp(this.t);
    }

    private void R_exp(Token token){

        if(token.getIdToken().equals(TagToken.TKVirgula)){
            consome(TagToken.TKVirgula);
            atrib(this.t);
            R_exp(this.t);
        }
        //ou vazio
    }

    private void atrib(Token token){
        boolean aux1;
        aux1 = or(this.t);
        R_atrib(this.t,aux1);

    }

    private void R_atrib(Token token,boolean flag){
        boolean aux1;
        if(token.getIdToken().equals(TagToken.TKAtrib)){
            if (!flag) {
                System.out.println("Erro linha "+(t.getLinha()+1)+" : Atribuição inválida");
                System.exit(0);
            }
            consome(TagToken.TKAtrib);
            aux1 = or(this.t);
            R_atrib(this.t,aux1);


        }
        //ou vazio
    }

    private boolean or(Token token){
        boolean aux1,aux2;
        aux1 = and(this.t);
        aux2 =  R_or(this.t);

        if (aux1 && aux2) {
            return true;
        }else return false;
    }

    private boolean R_or(Token token){

        if(token.getIdToken().equals(TagToken.TKOr)) {

            consome(TagToken.TKOr);
            and(this.t);
            R_or(this.t);
            return false;
        }
        //ou vazio
        return true;
    }

    private boolean and(Token token){
        boolean aux1,aux2;
        aux1 =  rel(this.t);
        aux2 = R_and(this.t);

        if (aux1 && aux2) {
            return true;
        }else return false;
    }

    private boolean R_and(Token token){

        if(token.getIdToken().equals(TagToken.TKAnd)){

            consome(TagToken.TKAnd);
            rel(this.t);
            R_and(this.t);
            return false;
        }
        //ou vazio
        return true;
    }

    private boolean rel(Token token){
        boolean aux1,aux2;

        aux1 =  soma(this.t);
        aux2 = R_rel(this.t);

        if (aux1 && aux2) {
            return true;
        }else return false;
    }

    private boolean R_rel(Token token){

        if(token.getIdToken().equals(TagToken.TKIgualIgual)){

            consome(TagToken.TKIgualIgual);
            soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKDiferente)){

            consome(TagToken.TKDiferente);
            soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKMenor)){

            consome(TagToken.TKMenor);
            soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKMenorIgual)){

            consome(TagToken.TKMenorIgual);
            soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKMaior)){

            consome(TagToken.TKMaior);
            soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKMaiorIgual)){

            consome(TagToken.TKMaiorIgual);
            soma(this.t);
            return false;
        }
        //ou vazio
        return true;
    }

    private boolean soma(Token token){
        boolean aux1,aux2;
        aux1 = mult(this.t);
        aux2 = R_soma(this.t);


        if (aux1 && aux2) {
            return true;
        }else return false;
    }

    private boolean R_soma(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma)){

            consome(TagToken.TKSoma);
            mult(this.t);
            R_soma(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKSub)){

            consome(TagToken.TKSub);
            mult(this.t);
            R_soma(this.t);
            return false;
        }
        //ou vazio
        return true;
    }

    private boolean mult(Token token){
        boolean aux1,aux2;

        aux1 = uno(this.t);
        aux2 = R_mult(this.t);

        if (aux1 && aux2) {
            return true;
        }else return false;
    }

    private boolean R_mult(Token token){

        if(token.getIdToken().equals(TagToken.TKMult)){

            consome(TagToken.TKMult);
            uno(this.t);
            R_mult(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKDiv)){

            consome(TagToken.TKDiv);
            uno(this.t);
            R_mult(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKMod)){

            consome(TagToken.TKMod);
            uno(this.t);
            R_mult(this.t);
            return false;
        }
        //ou vazio
        return true;
    }

    private boolean uno(Token token){

        if(token.getIdToken().equals(TagToken.TKSoma)){

            consome(TagToken.TKSoma);
            uno(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKSub)){

            consome(TagToken.TKSub);
            uno(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKDiferente)){

            consome(TagToken.TKDiferente);
            uno(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKPlusPlus)){

            consome(TagToken.TKPlusPlus);
            lvalue(this.t);
            return false;
        }else if(token.getIdToken().equals(TagToken.TKSubSub)){

            consome(TagToken.TKSubSub);
            lvalue(this.t);
            return false;
        }else{
           return pos(this.t);
        }
    }

    private void R_pos(Token token){

        if(token.getIdToken().equals(TagToken.TKPlusPlus)){
            consome(TagToken.TKPlusPlus);
        }else{
            consome(TagToken.TKSubSub);
        }
    }

    private boolean pos(Token token){

        if(token.getIdToken().equals(TagToken.TKIdent)){
            consome(TagToken.TKIdent);
            pos_pos(this.t);
            return true;
        }else{
            valor(this.t);
            return false;
        }
    }

    private void pos_pos(Token token){

        if(token.getIdToken().equals(TagToken.TKSeta)){
            R_var_name(this.t);
        }else if ( token.getIdToken().equals(TagToken.TKAbreCouchete) || token.getIdToken().equals(TagToken.TKPlusPlus) || token.getIdToken().equals(TagToken.TKSubSub)){
            ini_vector(this.t);
        }
        //ou vazio
    }

    private void R_ini_vector(Token token){

        if(token.getIdToken().equals(TagToken.TKNumInteiro)){
            consome(TagToken.TKNumInteiro);
            consome(TagToken.TKFechaCouchete);
            R_pos(this.t);
        }else{
            exp(this.t);
            consome(TagToken.TKFechaCouchete);
        }
    }

    private void valor(Token token){

        if(token.getIdToken().equals(TagToken.TKAbrePar)){

            consome(TagToken.TKAbrePar);
            exp(this.t);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKnew)){

            consome(TagToken.TKnew);
            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_arguments(this.t);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKFunc)){

            consome(TagToken.TKFunc);
            consome(TagToken.TKAbrePar);
            op_arguments(this.t);
            consome(TagToken.TKFechaPar);

        }else if(token.getIdToken().equals(TagToken.TKNumFloat)){

            consome(TagToken.TKNumFloat);

        }else if(token.getIdToken().equals(TagToken.TKString)){

            consome(TagToken.TKString);

        }else if(token.getIdToken().equals(TagToken.TKnil)) {
            consome(TagToken.TKnil);

        }else {
           consome(TagToken.TKNumInteiro);

        }


    }

    private void R_var_name(Token token){

        consome(TagToken.TKSeta);
        consome(TagToken.TKFunc);
        consome(TagToken.TKAbrePar);
        op_arguments(this.t);
        consome(TagToken.TKFechaPar);
    }

    private void ini_vector(Token token){

        if(token.getIdToken().equals(TagToken.TKAbreCouchete)){
            consome(TagToken.TKAbreCouchete);
            R_ini_vector(this.t);
        }else{
            R_pos(this.t);
        }
    }

    /**
     *  Consome Toke e busca o proximo.
     * @param token
     */
    private void  consome(TagToken token){

        if (this.t.getIdToken().equals(token)){
            this.t = lexico.getToken(this.t);
        } else{
            System.out.println("Erro linha "+(t.getLinha()+1)+" : Era esperado token: '"+token+"' mas veio '"+this.t.getLexema()+"'.");
            System.exit(0);
        }//

        if (this.t == null){
            System.out.println("\n Sintaxe está ok !!!");
            System.exit(0);
        }
    }

}