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
import www.starcom.com.jualanpraktis.model.ListFavorit;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder> {

    Context context;
    ListFavorit[] listFavorit;

    public FavoritAdapter(Context context, ListFavorit[] listFavorit) {
        this.context = context;
        this.listFavorit = listFavorit;
    }

    @NonNull
    @Override
    public FavoritViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorit, parent, false);
        return new FavoritViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritViewHolder holder, int position) {

        final String imageUrl = listFavorit[position].getImage();

        holder.txtNamaBarang.setText(listFavorit[position].getNama_barang());
        holder.txtHargaBarang.setText(listFavorit[position].getHarga_barang());

        Glide.with(context)
                .load(imageUrl)
                .into(holder.imgFavorit);

    }

    @Override
    public int getItemCount() {
        return listFavorit.length;
    }

    public class FavoritViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFavorit;
        TextView txtNamaBarang, txtHargaBarang;

        public FavoritViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFavorit = itemView.findViewById(R.id.img_item_favorit);
            txtNamaBarang = itemView.findViewById(R.id.text_nama_barang_favorit);
            txtHargaBarang = itemView.findViewById(R.id.text_id_harga_favorit);
        }
    }
}
