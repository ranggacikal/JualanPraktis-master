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

public class PenghasilanSelesaiAdapter extends RecyclerView.Adapter<PenghasilanSelesaiAdapter.PenghasilanSelesaiViewHolder> {

    ArrayList<HashMap<String, String>> listPenghasilanSelesai = new ArrayList<>();
    Context context;
    private Pref pref;


    public PenghasilanSelesaiAdapter(ArrayList<HashMap<String, String>> listPenghasilanSelesai, Context context) {
        this.listPenghasilanSelesai = listPenghasilanSelesai;
        this.context = context;
    }

    @NonNull
    @Override
    public PenghasilanSelesaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghasilan_saya_selesai, parent, false);
        return new PenghasilanSelesaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenghasilanSelesaiViewHolder holder, int position) {

        HashMap<String, String> item = new HashMap<>();
        item = this.listPenghasilanSelesai.get(position);
        pref = new Pref(context.getApplicationContext());

        holder.txtNama.setText(item.get("nama_produk"));
        holder.txtTanggal.setText(item.get("tanggal_transaksi"));
        holder.txtTotal.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return (null != listPenghasilanSelesai ? listPenghasilanSelesai.size() : 0);
    }

    public class PenghasilanSelesaiViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBarang;
        TextView txtNama, txtTanggal, txtStatus, txtTotal;

        public PenghasilanSelesaiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.img_item_penghasilan_saya_selesai);
            txtNama = itemView.findViewById(R.id.text_item_nama_barang_penghasilansaya_selesai);
            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_penghasilansaya_selesai);
            txtTotal = itemView.findViewById(R.id.text_item_total_penghasilansaya_selesai);
        }
    }
}
