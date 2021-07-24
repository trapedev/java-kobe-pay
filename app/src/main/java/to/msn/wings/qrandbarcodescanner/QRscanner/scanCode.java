package to.msn.wings.qrandbarcodescanner.QRscanner;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import to.msn.wings.qrandbarcodescanner.AddActivity;
import to.msn.wings.qrandbarcodescanner.AddCashDataActivity;
import to.msn.wings.qrandbarcodescanner.MainActivity;
import to.msn.wings.qrandbarcodescanner.R;

public class scanCode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scancode);
        scanCode();
    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents()); /**取得結果の表示*/
                String res = result.getContents(); /**取得結果を文字列として置いておく*/
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() { /**"ScanAgain"をクリックした場合の処理*/
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                });
                builder.setNegativeButton("finish", new DialogInterface.OnClickListener() { /**"finish"をクリックした場合の処理*/
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(res.startsWith("001")){ /**取得結果の文字列の先頭3文字が001の場合、決済確認画面へ*/
                            Intent i = new Intent(scanCode.this, AddCashDataActivity.class);
                            i.putExtra("result", res);
                            startActivity(i);
                        }
                        else if(res.startsWith("http")){ /**取得結果の文字列の先頭がhttp(httpsも含む)の場合、ウェブ画面へ*/
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(res));
                            startActivity(i);
                        }
                        else{/**上記以外の場合は何もなし*/
                            finish();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this, "No Result", Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**"Scan again"をクリックした場合のイベント*/
    public void scanCode_onClick(View v){
        Intent i = new Intent(this, scanCode.class);
        startActivity(i);
    }

    /**"Back"をクリックした場合のイベント*/
    public void backTab2_onClick(View v){
        Intent i = new Intent(this, to.msn.wings.qrandbarcodescanner.MainActivity.class);
        startActivity(i);
    }
}
