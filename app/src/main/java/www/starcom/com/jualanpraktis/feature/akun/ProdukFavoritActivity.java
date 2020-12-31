package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.FavoritAdapter;
import www.starcom.com.jualanpraktis.model.ListFavorit;

public class ProdukFavoritActivity extends AppCompatActivity {

    @BindView(R.id.imgBackPenghasilanSaya)
    ImageView imgBackPenghasilanSaya;
    @BindView(R.id.img_search_favorit)
    ImageView imgSearchFavorit;
    @BindView(R.id.img_favorit)
    ImageView imgFavorit;
    @BindView(R.id.img_notif_favorit)
    ImageView imgNotifFavorit;
    @BindView(R.id.recycler_produk_favorit)
    RecyclerView recyclerProdukFavorit;

    ListFavorit[] listFavorit = new ListFavorit[]{

            new ListFavorit("Converse Chuck Taylor", "Rp. 989.000", "https://miro.medium.com/max/1119/1*EJZVatqzK2uqWVX3aXc8gw.jpeg"),
            new ListFavorit("Hoodie Billie Eilish", "Rp. 240.000", "https://apparelsuit.com/wp-content/uploads/2019/11/Billie-Eilish-Loser-Hoodie-Billie-Eilish-FanBillie-Eilish-Sweatshirt-Billie-Eilish-Hoodie-Billie-Eilish-Unisex-Billie-Eilish.jpg")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_favorit);
        ButterKnife.bind(this);

        loadRecycler();
    }

    private void loadRecycler() {

        FavoritAdapter adapter = new FavoritAdapter(ProdukFavoritActivity.this, listFavorit);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProdukFavoritActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerProdukFavorit.setAdapter(adapter);
        recyclerProdukFavorit.setLayoutManager(gridLayoutManager);
        recyclerProdukFavorit.setHasFixedSize(true);
    }
}