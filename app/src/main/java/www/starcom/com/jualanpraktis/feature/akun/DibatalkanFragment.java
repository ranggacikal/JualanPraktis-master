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

public class DibatalkanFragment extends Fragment {

    String urlimage1 = "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//83/MTA-4603141/dettol_dettol_hand_sanitizer_50ml_full02_rae7qksc.jpg";
    ListStatusTransaksi[] listStatusTransaksiBatal = new ListStatusTransaksi[]{

            new ListStatusTransaksi("#ID99887732", "28 Desember 2020", "Hand Sanitizer", "Kesehatan/Medis", "Rp. 15.000", "Rp. 25.000",
                    "Rp. 10. 000", "Dibatalkan", urlimage1)
    };

    RecyclerView rvDibatalkan;

    public DibatalkanFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_dibatalkan, container, false);

        rvDibatalkan = rootView.findViewById(R.id.recycler_status_transaksi_dibatalkan);

        loadDibatalkan();

        return rootView;
    }

    private void loadDibatalkan() {

        StatusTransaksiAdapter adapter = new StatusTransaksiAdapter(getActivity(), listStatusTransaksiBatal);
        rvDibatalkan.setHasFixedSize(true);
        rvDibatalkan.setAdapter(adapter);
        rvDibatalkan.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}