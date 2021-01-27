package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PenghasilanSayaAdapter extends RecyclerView.Adapter<PenghasilanSayaAdapter.PenghasilanSayaViewHolder> {

    ArrayList<HashMap<String, String>> listPenghasilanProses = new ArrayList<>();
    Context context;
    private Pref pref;

    public PenghasilanSayaAdapter(ArrayList<HashMap<String, String>> listPenghasilanProses, Context context) {
        this.listPenghasilanProses = listPenghasilanProses;
        this.context = context;
    }

    @NonNull
    @Override
    public PenghasilanSayaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghasilan_saya, parent, false);
        return new PenghasilanSayaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenghasilanSayaViewHolder holder, int position) {

        HashMap<String, String> item = new HashMap<>();
        item = this.listPenghasilanProses.get(position);
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
        return (null != listPenghasilanProses ? listPenghasilanProses.size() : 0);
    }

    public class PenghasilanSayaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBarang;
        TextView txtNama, txtTanggal, txtStatus, txtTotal;

        public PenghasilanSayaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.img_item_penghasilan_saya);
            txtNama = itemView.findViewById(R.id.text_item_nama_barang_penghasilansaya);
            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_penghasilansaya);
            txtStatus = itemView.findViewById(R.id.text_item_status_penghasilansaya);
            txtTotal = itemView.findViewById(R.id.text_item_total_penghasilansaya);
        }
    }
}
