package www.starcom.com.jualanpraktis.adapter;

import android.app.Activity;
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
import java.util.List;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.akun.ProdukFavoritActivity;
import www.starcom.com.jualanpraktis.model.ListFavorit;
import www.starcom.com.jualanpraktis.model_retrofit.DataFavoriteItem;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder> {

    Context context;
    private ArrayList<HashMap<String, String>> data;

    public FavoritAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FavoritViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorit, parent, false);
        return new FavoritViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritViewHolder holder, int position) {
        HashMap<String, String> item = new HashMap<>();
        item = this.data.get(position);

        final String imagename = item.get("gambar");
        final String imageUrl = "http://trading.my.id/img/"+imagename;

        holder.txtNamaBarang.setText(item.get("nama_produk"));
        holder.txtHargaBarang.setText(item.get("berat"));

        Glide.with(context)
                .load(imageUrl)
                .into(holder.imgFavorit);

    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
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
