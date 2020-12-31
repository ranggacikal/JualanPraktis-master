package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.model.ListPencairan;

public class PencairanAdapter extends RecyclerView.Adapter<PencairanAdapter.PencairanViewHolder> {

    ListPencairan[] pencarianItems;
    Context context;

    public PencairanAdapter(ListPencairan[] pencarianItems, Context context) {
        this.pencarianItems = pencarianItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PencairanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_pencairan, parent, false);
        return new PencairanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PencairanViewHolder holder, int position) {

        holder.txtTanggal.setText(pencarianItems[position].getTanggal_pencairan());
        holder.txtWaktu.setText(pencarianItems[position].getWaktu_pencairan());
        holder.txtTotal.setText(pencarianItems[position].getTotal_pencarian());

    }

    @Override
    public int getItemCount() {
        return pencarianItems.length;
    }

    public class PencairanViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtWaktu, txtTotal;

        public PencairanViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_pencairan);
            txtWaktu = itemView.findViewById(R.id.text_item_waktu_pencairan);
            txtTotal = itemView.findViewById(R.id.text_item_total_pencairan);
        }
    }
}
