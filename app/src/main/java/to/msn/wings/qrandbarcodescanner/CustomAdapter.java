package to.msn.wings.qrandbarcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList payment_id, payee, payer, payment;

    /**
    定義
    payment_id = 支払いID
    payee = 支払先
    payer = 支払者
    payment = 支払額
    */

    /**コンストラクタ*/
    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList payment_id,
                  ArrayList payee,
                  ArrayList payer,
                  ArrayList payment){
        this.activity = activity;
        this.context = context;
        this.payment_id = payment_id;
        this.payee = payee;
        this.payer = payer;
        this.payment = payment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * 現在日時をyyyy/MM/dd HH:mm:ss形式で取得
     */
    public static String getNowDate(){
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.payment_id_txt.setText(String.valueOf(payment_id.get(position)));
        holder.payee_txt.setText(String.valueOf(payee.get(position)));
        holder.payer_txt.setText(String.valueOf(payer.get(position)));
        holder.payment_txt.setText(String.valueOf(payment.get(position)));
        holder.datetime_txt.setText(getNowDate());
        /**Recyclerview onClickListener*/
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(payment_id.get(position)));
                intent.putExtra("title", String.valueOf(payee.get(position)));
                intent.putExtra("author", String.valueOf(payer.get(position)));
                intent.putExtra("pages", String.valueOf(payment.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return payment_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView payment_id_txt, payee_txt, payer_txt, payment_txt, datetime_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            payment_id_txt = itemView.findViewById(R.id.payment_id_txt);
            payee_txt = itemView.findViewById(R.id.payee_txt);
            payer_txt = itemView.findViewById(R.id.payer_txt);
            payment_txt = itemView.findViewById(R.id.payment_txt);
            datetime_txt = itemView.findViewById(R.id.datetime_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            /**Animate Recyclerview*/
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
