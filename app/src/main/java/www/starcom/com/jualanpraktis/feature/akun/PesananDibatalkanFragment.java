package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PenghasilanBatalAdapter;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PesananDibatalkanFragment extends Fragment {

    String urlimage1 = "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//83/MTA-4603141/dettol_dettol_hand_sanitizer_50ml_full02_rae7qksc.jpg";
    String urlimage2 = "https://ecs7.tokopedia.net/img/cache/700/VqbcmM/2020/10/6/162986da-65e8-4f00-afbf-ad8812e5a2eb.jpg";

    ListPenghasilanSaya[] listPenghasilan = new ListPenghasilanSaya[]{

            new ListPenghasilanSaya("Hand Sanitizer", urlimage1, "29 Desember 2020", "Proses", "Rp.25.000"),
            new ListPenghasilanSaya("Crewneck Billie eilish", urlimage2, "29 Desember 2020", "Proses", "Rp.250.000")
    };

    RecyclerView rvPesananBatal;

    public PesananDibatalkanFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_pesanan_dibatalkan, container, false);

        rvPesananBatal = rootView.findViewById(R.id.recycler_penghasilan_saya_batalkan);

        loadData();

        return rootView;
    }

    private void loadData() {

        PenghasilanBatalAdapter adapter = new PenghasilanBatalAdapter(listPenghasilan, getActivity());
        rvPesananBatal.setHasFixedSize(true);
        rvPesananBatal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPesananBatal.setAdapter(adapter);

    }
}