package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.akun.RincianStatusTransaksiActivity;
import www.starcom.com.jualanpraktis.model.ListStatusTransaksi;

public class StatusTransaksiAdapter extends RecyclerView.Adapter<StatusTransaksiAdapter.StatusTransaksiViewHolder> {

    Context context;
    ListStatusTransaksi[] listStatusTransaksi;

    public StatusTransaksiAdapter(Context context, ListStatusTransaksi[] listStatusTransaksi) {
        this.context = context;
        this.listStatusTransaksi = listStatusTransaksi;
    }

    @NonNull
    @Override
    public StatusTransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_status_transaksi, parent, false);
        return new StatusTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusTransaksiViewHolder holder, int position) {

        String url = listStatusTransaksi[position].getImage();
        Glide.with(context)
                .load(url)
                .into(holder.imgBarang);

        holder.txtId.setText(listStatusTransaksi[position].getId());
        holder.txtTanggal.setText(listStatusTransaksi[position].getTanggal());
        holder.txtNama.setText(listStatusTransaksi[position].getNama_barang());
        holder.txtVariasi.setText(listStatusTransaksi[position].getVariasi());
        holder.txtHargaJual.setText(listStatusTransaksi[position].getHarga_jual());
        holder.txthargaProduk.setText(listStatusTransaksi[position].getHarga_produk());
        holder.txtKeuntungan.setText(listStatusTransaksi[position].getKeuntungan());
        holder.txtStatus.setText(listStatusTransaksi[position].getStatus_pesanan());

        holder.linearProdukLain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RincianStatusTransaksiActivity.class);
                intent.putExtra(RincianStatusTransaksiActivity.ExtraId, listStatusTransaksi[position].getId());
                intent.putExtra(RincianStatusTransaksiActivity.ExtraTanggal, listStatusTransaksi[position].getTanggal());
                intent.putExtra(RincianStatusTransaksiActivity.ExtraStatus, listStatusTransaksi[position].getStatus_pesanan());
                intent.putExtra(RincianStatusTransaksiActivity.ExtraImage, listStatusTransaksi[position].getImage());
                intent.putExtra(RincianStatusTransaksiActivity.ExtraNama, listStatusTransaksi[position].getNama_barang());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listStatusTransaksi.length;
    }

    public class StatusTransaksiViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBarang;
        LinearLayout linearProdukLain;
        TextView txtId, txtTanggal, txtNama, txtVariasi, txthargaProduk, txtHargaJual, txtKeuntungan, txtStatus;

        public StatusTransaksiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarang = itemView.findViewById(R.id.img_list_gambar_status_transaksi);
            linearProdukLain = itemView.findViewById(R.id.linear_list_produk_lain_status);
            txtId = itemView.findViewById(R.id.text_list_id_status_transaksi);
            txtTanggal = itemView.findViewById(R.id.text_list_tanggal_status_transaksi);
            txtNama = itemView.findViewById(R.id.text_list_nama_status_transaksi);
            txtVariasi = itemView.findViewById(R.id.text_list_variasi_status_transaksi);
            txthargaProduk = itemView.findViewById(R.id.text_list_hargaproduk_status_transaksi);
            txtHargaJual = itemView.findViewById(R.id.text_list_hargajual_status_transaksi);
            txtKeuntungan = itemView.findViewById(R.id.text_list_keuntungan_status_transaksi);
            txtStatus = itemView.findViewById(R.id.text_list_status_transaksi);
        }
    }
}
