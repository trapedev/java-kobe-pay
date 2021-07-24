package to.msn.wings.qrandbarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCashDataActivity extends AppCompatActivity {

    //EditText payee_input, payer_input, payment_input;
    Button cash_button;
    boolean judge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cashdata);

        /**インテントを取得*/
        Intent i = getIntent();

        /**結果を表す文字列の定義*/
        String result = i.getStringExtra("result");

        /**resultの部分文字列の抽出*/
        String payee = result.substring(3,67);
        String payer = result.substring(67,131);
        String payment = result.substring(131, (int)result.length());

        /**結果*/
        TextView payeeResult = findViewById(R.id.payee);
        TextView payerResult = findViewById(R.id.payer);
        TextView paymentResult = findViewById(R.id.payment);

        /**結果の表示*/
        payeeResult.setText(String.format("Payee : %s", payee));
        payerResult.setText(String.format("Payer : %s", payer));
        paymentResult.setText(String.format("Amount of payment : %s", payment));

        cash_button = findViewById(R.id.cash_button);
        cash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddCashDataActivity.this);
                if(!judge){
                    judge = true;
                    //MyDatabaseHelper myDB = new MyDatabaseHelper(AddCashDataActivity.this);
                    /**myDB.addBook(payee_input.getText().toString().trim(),
                     payer_input.getText().toString().trim(),
                     Integer.valueOf(payment_input.getText().toString().trim()));*/
                    myDB.addBook(payee.trim(),payer.trim(), Integer.valueOf(payment.trim()));
                }
                else{
                    myDB.AlreadyAdded();
                }
            }
        });
    }

    public void Termination_onClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void CheckYourBalance_onClick(View v){
        Intent i = new Intent(this, DatabaseAdd.class);
        startActivity(i);
    }
}
