package ws.com.br.callrestwebservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, Runnable{
    protected static final String CATEGORIA = "livro";
    protected static final String URL = "http://192.168.0.15:8080/br.com.comandaquatro/rest/hello/client";

    //Hadler utilizado para atualizar a view
    private Handler handler = new Handler();
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.arquivo);
        Button b = (Button) findViewById(R.id.btDownload);
        b.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        dialog = ProgressDialog.show(this, "Exemplo", "Buscando texto, por favor aguarde...", false, true);

        //Faz download em uma thread
        new Thread(this).start();
    }

    @Override
    public void run(){
        try{
            //Faz o download
            final String arquivo = Http.getInstance(Http.NORMAL).downloadArquivo(URL);
            Log.i(CATEGORIA, "Texto retornado: " + arquivo);

            //Precisa do handler para atualizar a view de outra thread
            handler.post(new Runnable() {
                @Override
                public void run() {
                    TextView text = (TextView) findViewById(R.id.texto);
                    text.setText(arquivo);
                }
            });
        }catch (Throwable e){
            Log.i(CATEGORIA, e.getMessage(), e);
        }finally{
            dialog.dismiss();
        }
    }
}
