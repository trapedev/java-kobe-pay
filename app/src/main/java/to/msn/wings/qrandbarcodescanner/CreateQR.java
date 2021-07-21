package to.msn.wings.qrandbarcodescanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.security.MessageDigest;

public class CreateQR extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_main);

        //インテントを取得
        Intent i = getIntent();
        //String txtName = i.getStringExtra("txtName");
        String remittance = i.getStringExtra("remittance");
        String studentNumber = i.getStringExtra("studentNumber");
        String destination = i.getStringExtra("destination");
        //取得したデータをハッシュ化
        //String data = SHA256(txtName);
        String txtName = remittance + studentNumber + destination;
        String data = SHA256(txtName);

        // QRCodeの作成
        //Bitmap qrCodeBitmap = this.createQRCode("http://nlinks-engineers.hatenablog.com/");
        Bitmap qrCodeBitmap = this.createQRCode(data);

        // QRCodeの作成に成功した場合
        if (qrCodeBitmap != null)
        {
            // 結果をImageViewに表示
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(qrCodeBitmap);
        }
    }

    /*
    //QRCode作成
    public void onClickQRCodeCreate(View view)
    {
        //インテントを取得
        Intent i = getIntent();
        String txtName = i.getStringExtra("txtName");
        //取得したデータをハッシュ化
        String data = SHA256(txtName);

        // QRCodeの作成
        //Bitmap qrCodeBitmap = this.createQRCode("http://nlinks-engineers.hatenablog.com/");
        Bitmap qrCodeBitmap = this.createQRCode(data);

        // QRCodeの作成に成功した場合
        if (qrCodeBitmap != null)
        {
            // 結果をImageViewに表示
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(qrCodeBitmap);
        }
    }
    */

    private Bitmap createQRCode(String contents)
    {
        Bitmap qrBitmap = null;
        try {
            // QRコードの生成
            QRCodeWriter qrcodewriter = new QRCodeWriter();
            BitMatrix qrBitMatrix = qrcodewriter.encode(contents,
                    BarcodeFormat.QR_CODE,
                    900,
                    900);

            qrBitmap = Bitmap.createBitmap(900, 900, Bitmap.Config.ARGB_8888);
            qrBitmap.setPixels(this.createDot(qrBitMatrix), 0, 900, 0, 0, 900, 900);
        }
        catch(Exception ex)
        {
            // エンコード失敗
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
        finally
        {
            return qrBitmap;
        }
    }

    // ドット単位の判定
    private int[] createDot(BitMatrix qrBitMatrix)
    {
        // 縦幅・横幅の取得
        int width = qrBitMatrix.getWidth();
        int height = qrBitMatrix.getHeight();
        // 枠の生成
        int[] pixels = new int[width * height];

        // データが存在するところを黒にする
        for (int y = 0; y < height; y++)
        {
            // ループ回数盤目のOffsetの取得
            int offset = y * width;
            for (int x = 0; x < width; x++)
            {
                // データが存在する場合
                if (qrBitMatrix.get(x, y))
                {
                    pixels[offset + x] = Color.BLACK;
                }
                else
                {
                    pixels[offset + x] = Color.WHITE;
                }
            }
        }
        // 結果を返す
        return pixels;
    }

    //ボタンクリック時に呼び出されるメゾット
    public void btnBack_onClick(View v){
        finish();
    }

    //SHA-256でハッシュ化
    String SHA256(String inputString) {
        try {
            //メッセージ・ダイジェストは、任意サイズのデータを取得して固定長のハッシュ値を出力する安全な一方向のハッシュ機能です。
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(inputString.getBytes("UTF-8"));

            StringBuffer hexStr = new StringBuffer(); // 16進数としてハッシュ値を持つ

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);

                if(hex.length() == 1){
                    hexStr.append('0');
                }
                hexStr.append(hex);
            }

            return hexStr.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
