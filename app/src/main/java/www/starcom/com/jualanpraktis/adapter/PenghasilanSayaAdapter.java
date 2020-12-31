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

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PenghasilanSayaAdapter extends RecyclerView.Adapter<PenghasilanSayaAdapter.PenghasilanSayaViewHolder> {

    private ListPenghasilanSaya[] listPenghasilanSaya;
    Context context;

    public PenghasilanSayaAdapter(ListPenghasilanSaya[] listPenghasilanSaya, Context context) {
        this.listPenghasilanSaya = listPenghasilanSaya;
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

        String image = listPenghasilanSaya[position].getImage_barang();
        Glide.with(context)
                .load(image)
                .into(holder.imgBarang);

        holder.txtNama.setText(listPenghasilanSaya[position].getNama_barang());
        holder.txtTanggal.setText(listPenghasilanSaya[position].getTanggal());
        holder.txtStatus.setText(listPenghasilanSaya[position].getStatus());
        holder.txtTotal.setText(listPenghasilanSaya[position].getTotal());

    }

    @Override
    public int getItemCount() {
        return listPenghasilanSaya.length;
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
