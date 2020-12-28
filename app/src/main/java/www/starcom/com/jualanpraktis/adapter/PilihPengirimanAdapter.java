package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.form_pemesanan.FormTransaksiActivity;

public class PilihPengirimanAdapter extends RecyclerView.Adapter<PilihPengirimanAdapter.PilihPengirimanViewHolder> {

    Context context;
    private ArrayList<HashMap<String, String>> data;
    ArrayList<String> hargaOngkirArray = new ArrayList<>();
    int harga_ongkir;
    String nama_kurir;
    private FormTransaksiActivity formTransaksiActivity;


    public PilihPengirimanAdapter(Context context, ArrayList<HashMap<String, String>> data, FormTransaksiActivity formTransaksiActivity) {
        this.context = context;
        this.data = data;
        this.formTransaksiActivity = formTransaksiActivity;
    }

    @NonNull
    @Override
    public PilihPengirimanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_pilihan_pengiriman, parent, false);
        return new PilihPengirimanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihPengirimanViewHolder holder, int position) {

        holder.linearRodaDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pengiriman Roda Dua", Toast.LENGTH_SHORT).show();
                harga_ongkir = 10000;
                nama_kurir = "motor";
                formTransaksiActivity.dataOngkir.add(harga_ongkir);
                formTransaksiActivity.dataKurir.add(nama_kurir);
            }
        });

        holder.linearRodaEmpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pengiriman Roda Empat", Toast.LENGTH_SHORT).show();
                harga_ongkir = 50000;
                nama_kurir = "mobil";
                formTransaksiActivity.dataOngkir.add(harga_ongkir);
                formTransaksiActivity.dataKurir.add(nama_kurir);
            }
        });

        holder.txtNamaVendor.setText(data.get(position).get("nama_vendor"));

//        for (int i = 0; i<getItemCount(); i++){
//
//            hargaOngkirArray.add(String.valueOf(harga_ongkir));
//
//        }


    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    public class PilihPengirimanViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearRodaDua, linearRodaEmpat;
        TextView txtNamaVendor;

        public PilihPengirimanViewHolder(@NonNull View itemView) {
            super(itemView);

            linearRodaDua = itemView.findViewById(R.id.linear_ekspedisi_roda_dua);
            linearRodaEmpat = itemView.findViewById(R.id.linear_ekspedisi_roda_empat);
            txtNamaVendor = itemView.findViewById(R.id.text_nama_vendor_pilih_pengiriman);

        }
    }
}
