package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.produk.ProdukDetailActivity;

public class VariasiAdapter extends RecyclerView.Adapter<VariasiAdapter.VariasiViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> listDataVariasi = new ArrayList<>();
    private Pref pref;

    ProdukDetailActivity produkDetailActivity;

    public VariasiAdapter(Context context, ArrayList<HashMap<String, String>> listDataVariasi, ProdukDetailActivity produkDetailActivity) {
        this.context = context;
        this.listDataVariasi = listDataVariasi;
        this.produkDetailActivity = produkDetailActivity;
    }

    @NonNull
    @Override
    public VariasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_variasi_detail_produk, parent, false);
        return new VariasiViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VariasiViewHolder holder, int position) {

        HashMap<String, String> item = new HashMap<>();
        item = this.listDataVariasi.get(position);
        pref = new Pref(context.getApplicationContext());

        holder.txtVariasi.setText(item.get("variasi"));
        String nama_variasi = item.get("variasi");
        String id_variasi = item.get("id_variasi");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anda Memilih : "+nama_variasi, Toast.LENGTH_SHORT).show();
                produkDetailActivity.getVariasi = id_variasi;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != listDataVariasi ? listDataVariasi.size() : 0);
    }

    public class VariasiViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearVariasi;
        TextView txtVariasi;

        public VariasiViewHolder(@NonNull View itemView) {
            super(itemView);
            linearVariasi = itemView.findViewById(R.id.linear_item_variasi);
            txtVariasi = itemView.findViewById(R.id.txt_item_nama_variasi);
        }
    }
}
