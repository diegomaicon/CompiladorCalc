import java.io.IOException;

/**
 * Created by Diego on 27/03/2017.
 */


public class Index{

        public static void main(String[] args) throws IOException {

                Lexico lexico = new Lexico();
                lexico.abreArquivo("teste.calc");

                Token t = new Token();
                t.setLinha(1);
                t.setpRead(0);

                do {
                        t = lexico.Lex(t.getpRead(),t.getLinha());
                        if(t.getpRead() != -1){
                                System.out.println(t.toString());
                        }
                }while(t.getpRead() != -1);
        }
}
