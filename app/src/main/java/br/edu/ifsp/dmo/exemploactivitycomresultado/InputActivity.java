package br.edu.ifsp.dmo.exemploactivitycomresultado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editText = findViewById(R.id.edittext_message);
        button = findViewById(R.id.button_save);
        button.setOnClickListener(v -> salvar());
    }

    private void salvar(){
        String str = editText.getText().toString();
        if(str.isEmpty()){
            Toast.makeText(this, "Você não digitou uma mensagem.", Toast.LENGTH_SHORT).show();
            // Como a mensagem está em branco, está se considerando que a ação da activity
            // foi cancelada. Nesse caso, não se retorna o resultado, apenas se configura
            // que o resultado foi cancelado.
            setResult(RESULT_CANCELED);
        }else{
            // Como a activity gera resultados, inclui-se o resultado em um objeto
            // do tipo Intent para que seja recuperado na activity que chamou essa
            // para a entrada de dados.
            Intent intent = new Intent();
            intent.putExtra(MainActivity.KEY_MESSAGE, str);

            // Como resultados informa-se que houve sucesso (RESULT_OK) e se inclui
            // a intent com os dados de retorno.
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}