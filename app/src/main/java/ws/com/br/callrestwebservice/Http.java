package ws.com.br.callrestwebservice;

import java.util.Map;

/**
 * Created by layonn on 26/10/15.
 */
public abstract class Http {
    //Utiliza UrlConnection
    public static final int NORMAL = 1;
    //Utiliza o Jakarta HttpClient
    public static final int JAKARTA = 2;
    public static Http getInstance(int tipo){
        switch(tipo){
            case NORMAL:
                //UrlConnection
                return new HttpNormalImpl();

            case JAKARTA:
                //JAKARTA commons HttpClient
                //return new HttpClientImpl();

            default:
                return new HttpNormalImpl();
        }
    }

    //retorna o texto do arquivo
    public abstract String downloadArquivo(String url);

    //Retorna os bytes da imagem
    public abstract byte[] downloadImagem(String url);

    //Post enviando os parâmetros
    public abstract String doPost(String url, Map map);
}
