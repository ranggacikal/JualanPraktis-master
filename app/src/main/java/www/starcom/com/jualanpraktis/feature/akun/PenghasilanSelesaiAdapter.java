package www.starcom.com.jualanpraktis.feature.akun;

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

public class PenghasilanSelesaiAdapter extends RecyclerView.Adapter<PenghasilanSelesaiAdapter.PenghasilanSelesaiViewHolder> {

    ListPenghasilanSaya[] penghasilanSayaItem;
    Context context;

    public PenghasilanSelesaiAdapter(ListPenghasilanSaya[] penghasilanSayaItem, Context context) {
        this.penghasilanSayaItem = penghasilanSayaItem;
        this.context = context;
    }

    @NonNull
    @Override
    public PenghasilanSelesaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghasilan_saya, parent, false);
        return new PenghasilanSelesaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenghasilanSelesaiViewHolder holder, int position) {

        String image = penghasilanSayaItem[position].getImage_barang();
        Glide.with(context)
                .load(image)
                .into(holder.imgBarang);

        holder.txtNama.setText(penghasilanSayaItem[position].getNama_barang());
        holder.txtTanggal.setText(penghasilanSayaItem[position].getTanggal());
        holder.txtTotal.setText(penghasilanSayaItem[position].getTotal());
        holder.txtStatus.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return penghasilanSayaItem.length;
    }

    public class PenghasilanSelesaiViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBarang;
        TextView txtNama, txtTanggal, txtStatus, txtTotal;

        public PenghasilanSelesaiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.img_item_penghasilan_saya);
            txtNama = itemView.findViewById(R.id.text_item_nama_barang_penghasilansaya);
            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_penghasilansaya);
            txtStatus = itemView.findViewById(R.id.text_item_status_penghasilansaya);
            txtTotal = itemView.findViewById(R.id.text_item_total_penghasilansaya);
        }
    }
}
