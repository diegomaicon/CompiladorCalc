import java.io.IOException;

/**
 * Created by Diego on 27/03/2017.
 */


public class Index{


        public static void main(String[] args) throws IOException {
                Token t = new Token(TagToken.TKNull,"",1,0);

                Lexico lexico = new Lexico();
                lexico.abreArquivo("teste.calc");

               while (t != null){
                t = lexico.getToken(t);
                System.out.println(t.toString());
               }


               // ASintatico a = new ASintatico();
                //a.start();

        }
}
