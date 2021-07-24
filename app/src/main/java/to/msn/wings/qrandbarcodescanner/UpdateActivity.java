package to.msn.wings.qrandbarcodescanner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText payee_input, payer_input, payment_input;
    Button update_button, delete_button;

    String id, payee, payer, payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        payee_input = findViewById(R.id.payee_input2);
        payer_input = findViewById(R.id.payer_input2);
        payment_input = findViewById(R.id.payment_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(payee);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                payee = payee_input.getText().toString().trim();
                payer = payer_input.getText().toString().trim();
                payment = payment_input.getText().toString().trim();
                myDB.updateData(id, payee, payer, payment);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            payee = getIntent().getStringExtra("title");
            payer = getIntent().getStringExtra("author");
            payment = getIntent().getStringExtra("pages");

            //Setting Intent Data
            payee_input.setText(payee);
            payer_input.setText(payer);
            payment_input.setText(payment);
            Log.d("stev", payee +" " + payer + " " + payment);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + payee + " ?");
        builder.setMessage("Are you sure you want to delete " + payee + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
