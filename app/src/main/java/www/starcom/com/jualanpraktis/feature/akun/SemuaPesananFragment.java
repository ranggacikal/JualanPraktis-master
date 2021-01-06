package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.StatusTransaksiAdapter;
import www.starcom.com.jualanpraktis.model.ListStatusTransaksi;

public class SemuaPesananFragment extends Fragment {

    String urlimage1 = "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//83/MTA-4603141/dettol_dettol_hand_sanitizer_50ml_full02_rae7qksc.jpg";
    String urlimage2 = "https://ecs7.tokopedia.net/img/cache/700/VqbcmM/2020/10/6/162986da-65e8-4f00-afbf-ad8812e5a2eb.jpg";

    ListStatusTransaksi[] listStatusTransaksi = new ListStatusTransaksi[]{

            new ListStatusTransaksi("#ID99887732", "28 Desember 2020", "Hand Sanitizer", "Kesehatan/Medis", "Rp. 15.000", "Rp. 25.000",
                    "Rp. 10. 000", "Dipesan", urlimage1),
            new ListStatusTransaksi("#ID55533627", "29 Desember 2020", "Crewneck", "Pakaian", "Rp. 150.000", "Rp. 250.000",
                    "Rp. 100. 000", "Dipesan", urlimage2)
    };

    RecyclerView rvSemuaPesanan;

    public SemuaPesananFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_semua_pesanan,container,false);

        rvSemuaPesanan = rootView.findViewById(R.id.recycler_status_transaksi_semua);

        loadRecycler();

        return rootView;
    }

    private void loadRecycler() {

            StatusTransaksiAdapter adapter = new StatusTransaksiAdapter(getActivity(), listStatusTransaksi);
            rvSemuaPesanan.setHasFixedSize(true);
            rvSemuaPesanan.setAdapter(adapter);
            rvSemuaPesanan.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}