package to.msn.wings.qrandbarcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText payee_input, payer_input, payment_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        payee_input = findViewById(R.id.payee_input);
        payer_input = findViewById(R.id.payer_input);
        payment_input = findViewById(R.id.payment_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(payee_input.getText().toString().trim(),
                        payer_input.getText().toString().trim(),
                        Integer.valueOf(payment_input.getText().toString().trim()));
            }
        });
    }
}