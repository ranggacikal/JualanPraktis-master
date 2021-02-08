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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PenghasilanBatalAdapter;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSayaAdapter;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PesananDiprosesFragment extends Fragment {


    loginuser user ;

    ArrayList<HashMap<String, String>> penghasilanProses = new ArrayList<>();
    ArrayList<HashMap<String, String>> listProdukProses = new ArrayList<>();

    RecyclerView rvPesananProses;
    ShimmerFrameLayout shimmerProses;
    TextView txtPotensi, txtKosong;

    String potensiPendapatan;

    public PesananDiprosesFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_pesanan_diproses, container, false);

        AndroidNetworking.initialize(getActivity().getApplicationContext());
        user = SharedPrefManager.getInstance(getActivity()).getUser();

        rvPesananProses = rootView.findViewById(R.id.recycler_penghasilan_saya);
        shimmerProses = rootView.findViewById(R.id.shimmerPenghasilanSayaProses);
        txtPotensi = rootView.findViewById(R.id.text_total_potensi_pendapatan);
        txtKosong = rootView.findViewById(R.id.text_kosong_pesanan_proses);

        rvPesananProses.setHasFixedSize(true);
        rvPesananProses.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        return rootView;
    }

    private void loadData() {

        String url = "https://jualanpraktis.net/android/transaksi_diproses.php";

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
                        shimmerProses.stopShimmerAnimation();
                        shimmerProses.setVisibility(View.GONE);

                        penghasilanProses.clear();
                        try {

                            potensiPendapatan = response.getString("penghasilan");
                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0;i<array.length();i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                HashMap<String,String> data = new HashMap<>();
                                data.put("id_transaksi",jsonObject.getString("id_transaksi"));

                                listProdukProses.clear();
                                JSONArray produk = jsonObject.getJSONArray("produk");
                                for (int j = 0; j<produk.length(); j++){
                                    JSONObject jsonObject1 = produk.getJSONObject(j);
                                    HashMap<String,String> data2 = new HashMap<>();
//                                    data2.put("id_transaksi", jsonObject1.getString("id_transaksi"));
                                    data.put("nama_produk", jsonObject1.getString("nama_produk"));
                                    data.put("gambar", jsonObject1.getString("image_o"));
                                    data.put("variasi", jsonObject1.getString("ket2"));
                                    data.put("jumlah", jsonObject1.getString("jumlah"));
                                    data.put("harga_produk", jsonObject1.getString("harga_produk"));
                                    data.put("harga_jual", jsonObject1.getString("harga_jual"));
                                    data.put("untung", jsonObject1.getString("untung"));
//                                    dataProdukSemuaPesanan.add(data);
//                                    listProdukSelesai.add(data);
                                    listProdukProses.add(data2);

                                }

                                Log.d("listProses", "onResponse: "+data.toString()+" + Potensi: "+potensiPendapatan);
                                penghasilanProses.add(data);
                            }

                            Log.d("potonganSaya", "onResponse: "+potensiPendapatan);
                            Log.d("dataPesananSelesai", "onResponse: "+penghasilanProses);
                            rvPesananProses.setVisibility(View.VISIBLE);
                            PenghasilanSayaAdapter adapter = new PenghasilanSayaAdapter(penghasilanProses, getActivity());
                            rvPesananProses.setAdapter(adapter);

                            if (penghasilanProses.isEmpty()){
                                txtKosong.setVisibility(View.VISIBLE);
                            }

                            int potonganInt = Integer.parseInt(potensiPendapatan);
                            txtPotensi.setText("Rp"+NumberFormat.getInstance().format(potonganInt));
                            txtPotensi.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        shimmerProses.stopShimmerAnimation();
                        shimmerProses.setVisibility(View.GONE);
                        penghasilanProses.clear();

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