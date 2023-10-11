package br.edu.ifsp.dmo.exemploactivitycomresultado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Com o uso do callback não existe mais o código de requisição. A verificação
    //se dá nos dados que chegam pela intent.
    //private static final int MESSAGE_REQUEST_CODE = 99;
    public static final String KEY_MESSAGE = "mensagem";

    // Objeto LAUNCHER que fará o tratamento do resultado usando um callback.
    private ActivityResultLauncher<Intent> resultLauncher;

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview_message);
        button = findViewById(R.id.button_get_message);
        button.setOnClickListener(v -> entrarMensagem());

        // Define-se o listener que irá monitorar os resultados da activity.
        // Para o registro é informado o Contrato (ActivityResultContracts) e
        // o método de Callback que trata o resultado, quando esse chegar
        // até a activity.
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == RESULT_OK){
                            String str = o.getData().getStringExtra(KEY_MESSAGE);
                            textView.setText(str);
                        }
                    }
                }
        );
    }

    private void entrarMensagem(){
        Intent intent = new Intent(this, InputActivity.class);
        //O método startActivityForResult() está depreciado, dessa forma, a chamada
        //de outra activity que gera resultados é feira pelo LAUNCHER.
//        startActivityForResult(intent, MESSAGE_REQUEST_CODE);
        resultLauncher.launch(intent);
    }

    //Método comentado porque na abordagem de criação de um CallBack não é mais
    //invocado o método onActivityResult() da Activity, e sim o método definido
    //na criação do callback.

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK) {
//            if (requestCode == MESSAGE_REQUEST_CODE) {
//                String str = data.getStringExtra(KEY_MESSAGE);
//                textView.setText(str);
//            }
//        }
//    }

}