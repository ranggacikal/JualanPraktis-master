package www.starcom.com.jualanpraktis.feature.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.SearchResultsActivity;
import www.starcom.com.jualanpraktis.adapter.FavoritAdapter;
import www.starcom.com.jualanpraktis.api.ConfigRetrofit;
import www.starcom.com.jualanpraktis.model.ListFavorit;
import www.starcom.com.jualanpraktis.model_retrofit.DataFavoriteItem;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseDataFavorite;

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

    loginuser user;

    ArrayList<HashMap<String, String>> favoritList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_favorit);
        ButterKnife.bind(this);

        recyclerProdukFavorit.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProdukFavoritActivity.this, 2, GridLayoutManager.VERTICAL,
                false);
        recyclerProdukFavorit.setLayoutManager(gridLayoutManager);

        user = SharedPrefManager.getInstance(ProdukFavoritActivity.this).getUser();

        imgSearchFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdukFavoritActivity.this, SearchResultsActivity.class);
                startActivity(intent);
            }
        });

        imgNotifFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProdukFavoritActivity.this, NotifikasiActivity.class));
            }
        });

        imgBackPenghasilanSaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getFavorit();
    }

    private void getFavorit() {
        HashMap<String,String> param = new HashMap<>();
        param.put("customer", user.getId());

        AndroidNetworking.post("https://jualanpraktis.net/android/list-favorit.php")
                .addBodyParameter(param)
                .setTag(ProdukFavoritActivity.this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  progressDialog.dismiss();
                        try {
                            favoritList.clear();
                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0;i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                HashMap<String,String> item = new HashMap<>();
                                item.put("nama_produk",object.getString("nama_produk"));
                                item.put("diskon",object.getString("disc"));
                                item.put("gambar", object.getString("image_o"));
                                item.put("kode",object.getString("kode"));
                                item.put("berat",object.getString("berat"));
                                favoritList.add(item);
                            }
                            FavoritAdapter adapter = new FavoritAdapter(ProdukFavoritActivity.this, favoritList);
                            recyclerProdukFavorit.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        if (anError.getErrorCode() != 0) {
                            Toast.makeText(ProdukFavoritActivity.this, "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            if (anError.getErrorDetail().equals("connectionError")){
                                Toast.makeText(ProdukFavoritActivity.this, "Tidak ada koneksi internet.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ProdukFavoritActivity.this, "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

    }

//    private void loadRecycler() {
//
//        FavoritAdapter adapter = new FavoritAdapter(ProdukFavoritActivity.this, listFavorit);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProdukFavoritActivity.this, 2, LinearLayoutManager.VERTICAL, false);
//        recyclerProdukFavorit.setAdapter(adapter);
//        recyclerProdukFavorit.setLayoutManager(gridLayoutManager);
//        recyclerProdukFavorit.setHasFixedSize(true);
//    }
}