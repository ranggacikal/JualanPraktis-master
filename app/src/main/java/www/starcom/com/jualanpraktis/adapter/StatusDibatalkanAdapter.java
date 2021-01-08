package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.R;

public class StatusDibatalkanAdapter extends RecyclerView.Adapter<StatusDibatalkanAdapter.StatusDibatalkanViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> listStatusDibatalkan = new ArrayList<>();
    private Pref pref;

    public StatusDibatalkanAdapter(Context context, ArrayList<HashMap<String, String>> listStatusDibatalkan) {
        this.context = context;
        this.listStatusDibatalkan = listStatusDibatalkan;
    }

    @NonNull
    @Override
    public StatusDibatalkanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_status_transaksi, parent, false);
        return new StatusDibatalkanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusDibatalkanViewHolder holder, int position) {
        HashMap<String, String> item = new HashMap<>();
        item = this.listStatusDibatalkan.get(position);
        pref = new Pref(context.getApplicationContext());

        holder.txtId.setText(item.get("id_transaksi"));
        holder.txtTanggal.setText(item.get("tanggal"));
        holder.txtNama.setText(item.get("nama_produk"));
        holder.txtVariasi.setText(item.get("variasi"));
        holder.txtHargaJual.setText(item.get("harga_jual"));
        holder.txthargaProduk.setText(item.get("harga_produk"));
        holder.txtKeuntungan.setText(item.get("untung"));
        holder.txtStatus.setText(item.get("status_pesanan"));
    }

    @Override
    public int getItemCount() {
        return (null != listStatusDibatalkan ? listStatusDibatalkan.size() : 0);
    }

    public class StatusDibatalkanViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearProdukLain;
        TextView txtId, txtTanggal, txtNama, txtVariasi, txthargaProduk, txtHargaJual, txtKeuntungan, txtStatus;

        public StatusDibatalkanViewHolder(@NonNull View itemView) {
            super(itemView);
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
