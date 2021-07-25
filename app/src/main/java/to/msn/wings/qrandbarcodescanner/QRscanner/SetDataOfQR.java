package to.msn.wings.qrandbarcodescanner.QRscanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import to.msn.wings.qrandbarcodescanner.QRscanner.CreateQR;
import to.msn.wings.qrandbarcodescanner.R;

public class SetDataOfQR extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
    }

    /**決済に関するQGコードを生成する関数への遷移*/
    public void CreateQR_onClick(View v){
        Intent i = new Intent(this, CreateQR.class);
        EditText remittance = findViewById(R.id.remittance);
        i.putExtra("remittance", remittance.getText().toString());
        EditText studentNumber = findViewById(R.id.studentNumber);
        i.putExtra("studentNumber", studentNumber.getText().toString());
        EditText destination = findViewById(R.id.destination);
        i.putExtra("destination", destination.getText().toString());
        /**決済データである事を示すデータ番号001を追加*/
        i.putExtra("dataType", "001");
        if(remittance.length() != 0 && studentNumber.length() != 0 && destination.length() != 0){
            startActivity(i);
        }
        else{
            Toast.makeText(this, "There is a blank.", Toast.LENGTH_SHORT).show();
        }
    }

    /**戻るボタン*/
    public void Back_onClick(View v){
        finish();
    }

}
