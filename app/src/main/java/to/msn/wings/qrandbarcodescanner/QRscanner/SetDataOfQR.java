package to.msn.wings.qrandbarcodescanner.QRscanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import to.msn.wings.qrandbarcodescanner.QRscanner.CreateQR;
import to.msn.wings.qrandbarcodescanner.R;

public class SetDataOfQR extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
    }

    //QGコードを生成する関数への遷移
    public void CreateQR_onClick(View v){
        Intent i = new Intent(this, CreateQR.class);
        EditText remittance = findViewById(R.id.remittance);
        i.putExtra("remittance", remittance.getText().toString());
        EditText studentNumber = findViewById(R.id.studentNumber);
        i.putExtra("studentNumber", studentNumber.getText().toString());
        EditText destination = findViewById(R.id.destination);
        i.putExtra("destination", destination.getText().toString());
        startActivity(i);
    }

}
