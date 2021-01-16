package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSelesaiAdapter;
import www.starcom.com.jualanpraktis.adapter.StatusDipesanAdapter;
import www.starcom.com.jualanpraktis.api.ConfigRetrofit;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;
import www.starcom.com.jualanpraktis.model_retrofit.model_penghasilan_selesai.DataItem;
import www.starcom.com.jualanpraktis.model_retrofit.model_penghasilan_selesai.ProdukItem;
import www.starcom.com.jualanpraktis.model_retrofit.model_penghasilan_selesai.ResponseDataPenghasilanSelesai;

public class PesananSelesaiFragment extends Fragment {

    RecyclerView rvPesananSelesai;
    TextView txtTotalPenghasilan;

    ShimmerFrameLayout shimmerPenghasilanSelesai;
    loginuser user ;

    ArrayList<HashMap<String, String>> penghasilanSayaSelesai = new ArrayList<>();
    ArrayList<HashMap<String, String>> listProdukSelesai = new ArrayList<>();

    String penghasilan;

    public PesananSelesaiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pesanan_selesai, container, false);

        rvPesananSelesai = rootView.findViewById(R.id.recycler_penghasilan_saya_selesai);
        shimmerPenghasilanSelesai = rootView.findViewById(R.id.shimmerPenghasilanSaya);
        txtTotalPenghasilan = rootView.findViewById(R.id.text_total_penghasilan_saya_selesai);
        txtTotalPenghasilan.setVisibility(View.GONE);

        AndroidNetworking.initialize(getActivity().getApplicationContext());
        user = SharedPrefManager.getInstance(getActivity()).getUser();

        rvPesananSelesai.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPesananSelesai.setHasFixedSize(true);

        loadData();

        return rootView;
    }

    private void loadData() {

        String url = "https://jualanpraktis.net/android/transaksi_selesai.php";

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.post(url)
                .addBodyParameter("customer", user.getId())
                .setTag(getActivity())
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        shimmerPenghasilanSelesai.stopShimmerAnimation();
                        shimmerPenghasilanSelesai.setVisibility(View.GONE);

                        penghasilanSayaSelesai.clear();
                        try {

                            penghasilan = response.getString("penghasilan");
                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0;i<array.length();i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                HashMap<String,String> data = new HashMap<>();
                                data.put("id_transaksi",jsonObject.getString("id_transaksi"));

                                listProdukSelesai.clear();
                                JSONArray produk = jsonObject.getJSONArray("produk");
                                for (int j = 0; j<produk.length(); j++){
                                    JSONObject jsonObject1 = produk.getJSONObject(j);
                                    HashMap<String,String> data2 = new HashMap<>();
//                                    data2.put("id_transaksi", jsonObject1.getString("id_transaksi"));
                                    data2.put("nama_produk", jsonObject1.getString("nama_produk"));
                                    data2.put("keterangan", jsonObject1.getString("ket2"));
                                    data2.put("tanggal_transaksi", jsonObject1.getString("tgl_transaksi"));
                                    data2.put("untung", jsonObject1.getString("untung"));
//                                    dataProdukSemuaPesanan.add(data);
//                                    listProdukSelesai.add(data);
                                    listProdukSelesai.add(data2);
                                    Log.d("listProduk", "onResponse: "+listProdukSelesai);
                                }

                                penghasilanSayaSelesai.add(data);
                            }

                            Log.d("penghasilanSaya", "onResponse: "+penghasilan);
                            Log.d("dataPesananSelesai", "onResponse: "+penghasilanSayaSelesai);
                            rvPesananSelesai.setVisibility(View.VISIBLE);
                            PenghasilanSelesaiAdapter adapter = new PenghasilanSelesaiAdapter(listProdukSelesai, getActivity());
                            rvPesananSelesai.setAdapter(adapter);
                            int penghasilanInt = Integer.parseInt(penghasilan);
                            Locale localID = new Locale("in", "ID");
                            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                            txtTotalPenghasilan.setText(formatRupiah.format(penghasilanInt));
                            txtTotalPenghasilan.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        shimmerPenghasilanSelesai.stopShimmerAnimation();
                        shimmerPenghasilanSelesai.setVisibility(View.GONE);
                        penghasilanSayaSelesai.clear();

                        if (anError.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail

                            // get parsed error object (If ApiError is your class)
                            Toast.makeText(getActivity(), "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            if (anError.getErrorDetail().equals("connectionError")){
                                Toast.makeText(getActivity(), "Tidak ada koneksi internet.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}