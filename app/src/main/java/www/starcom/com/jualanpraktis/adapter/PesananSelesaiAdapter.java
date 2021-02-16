package www.starcom.com.jualanpraktis.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import www.starcom.com.jualanpraktis.EditAkunActivity;
import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.daftar;

public class PesananSelesaiAdapter extends RecyclerView.Adapter<PesananSelesaiAdapter.PesananSelesaiViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> listSelesai = new ArrayList<>();
    private Pref pref;

    loginuser user;

    Dialog alertDialog;

    public PesananSelesaiAdapter(Context context, ArrayList<HashMap<String, String>> listSelesai) {
        this.context = context;
        this.listSelesai = listSelesai;
    }

    @NonNull
    @Override
    public PesananSelesaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_transaksi_selesai, parent, false);
        return new PesananSelesaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananSelesaiViewHolder holder, int position) {
        HashMap<String, String> item = new HashMap<>();
        item = this.listSelesai.get(position);
        pref = new Pref(context.getApplicationContext());

        user = SharedPrefManager.getInstance(context).getUser();

        String image = item.get("gambar");
        String url = "https://jualanpraktis.net/img/"+image;


        Glide.with(context)
                .load(url)
                .into(holder.imgBarang);

        holder.txtId.setText(item.get("id_transaksi"));
        holder.txtTanggal.setText(item.get("tanggal"));
        holder.txtNama.setText(item.get("nama_produk"));
        holder.txtVariasi.setText(item.get("variasi"));
        holder.txthargaProduk.setText(item.get("harga_produk"));
        holder.txtHargaJual.setText(item.get("harga_jual"));
        holder.txtKeuntungan.setText(item.get("untung"));

        String id_member = user.getId();
        String id_transaksi = item.get("id_transaksi");

        holder.linearPesananSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.dialog_pesanan_selesai);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button btnSelesai = alertDialog.findViewById(R.id.btn_selesaikan_pesanan_selesai);
                Button btnBatal = alertDialog.findViewById(R.id.btn_batal_pesanan_selesai);

                alertDialog.show();

                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnSelesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateStatusTransaksi(id_member, id_transaksi);
                    }
                });
            }
        });

    }

    private void updateStatusTransaksi(String id_member, String id_transaksi) {

        String host = "https://jualanpraktis.net/android/update_status_transaksi.php";

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Menyelesaikan pesanan");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Map<String, String> params = new HashMap<String, String>();
        params.put("id_member", id_member);
        params.put("id_transaksi", id_transaksi);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(host)
                .addBodyParameter(params)
                .setTag(context)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Berhasil Menyelesaikan pesanan", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();


                        try {
                            Toast.makeText(context, response.getString("response"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(context, anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }

                });

    }

    @Override
    public int getItemCount() {
        return (null != listSelesai ? listSelesai.size() : 0);
    }

    public class PesananSelesaiViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearPesananSelesai, linearAfterUpdate;
        TextView txtId, txtTanggal, txtNama, txtVariasi, txthargaProduk, txtHargaJual, txtKeuntungan;
        ImageView imgBarang;

        public PesananSelesaiViewHolder(@NonNull View itemView) {
            super(itemView);
            linearPesananSelesai = itemView.findViewById(R.id.linear_list_pesanan_selesai);
            linearAfterUpdate = itemView.findViewById(R.id.linear_list_pesanan_selesai_update);
            txtId = itemView.findViewById(R.id.text_list_id_status_transaksi_selesai);
            txtTanggal = itemView.findViewById(R.id.text_list_tanggal_status_transaksi_selesai);
            txtNama = itemView.findViewById(R.id.text_list_nama_status_transaksi_selesai);
            txtVariasi = itemView.findViewById(R.id.text_list_variasi_status_transaksi_selesai);
            txthargaProduk = itemView.findViewById(R.id.text_list_hargaproduk_status_transaksi_selesai);
            txtHargaJual = itemView.findViewById(R.id.text_list_hargajual_status_transaksi_selesai);
            txtKeuntungan = itemView.findViewById(R.id.text_list_keuntungan_status_transaksi_selesai);
            imgBarang = itemView.findViewById(R.id.img_list_gambar_status_transaksi_selesai);
        }
    }
}
