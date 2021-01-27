package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PenghasilanBatalAdapter extends RecyclerView.Adapter<PenghasilanBatalAdapter.PenghasilanBatalViewHolder> {

    ArrayList<HashMap<String, String>> listPenghasilanSaya = new ArrayList<>();
    Context context;
    private Pref pref;

    public PenghasilanBatalAdapter(ArrayList<HashMap<String, String>> listPenghasilanSaya, Context context) {
        this.listPenghasilanSaya = listPenghasilanSaya;
        this.context = context;
    }

    @NonNull
    @Override
    public PenghasilanBatalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghasilan_saya_batal, parent, false);
        return new PenghasilanBatalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenghasilanBatalViewHolder holder, int position) {

        HashMap<String, String> item = new HashMap<>();
        item = this.listPenghasilanSaya.get(position);
        pref = new Pref(context.getApplicationContext());

        String image = item.get("gambar");
        String url = "https://trading.my.id/img/"+image;


        Glide.with(context)
                .load(url)
                .into(holder.imgBarang);

        holder.txtNama.setText(item.get("nama_produk"));
        holder.txtTanggal.setText(item.get("tanggal_transaksi"));
        holder.txtTotal.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return (null != listPenghasilanSaya ? listPenghasilanSaya.size() : 0);
    }

    public class PenghasilanBatalViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBarang;
        TextView txtNama, txtTanggal, txtStatus, txtTotal;
        public PenghasilanBatalViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.img_item_penghasilan_saya_batal);
            txtNama = itemView.findViewById(R.id.text_item_nama_barang_penghasilansaya_batal);
            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_penghasilansaya_batal);
            txtTotal = itemView.findViewById(R.id.text_item_total_penghasilansaya_batal);
        }
    }
}
