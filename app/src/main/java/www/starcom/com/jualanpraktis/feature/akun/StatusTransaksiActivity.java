package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.StatusTransaksiAdapter;
import www.starcom.com.jualanpraktis.model.ListStatusTransaksi;

public class StatusTransaksiActivity extends AppCompatActivity {

    @BindView(R.id.imgBackStatusTransaksi)
    ImageView imgBackStatusTransaksi;
    @BindView(R.id.keranjang)
    TextView keranjang;
    @BindView(R.id.toolbarStatusTransaksi)
    Toolbar toolbarStatusTransaksi;
    @BindView(R.id.txt_semua_status_transaksi)
    TextView txtSemuaStatusTransaksi;
    @BindView(R.id.txt_dipesan_status_transaksi)
    TextView txtDipesanStatusTransaksi;
    @BindView(R.id.txt_dikemas_status_transaksi)
    TextView txtDikemasStatusTransaksi;
    @BindView(R.id.txt_dikirim_status_transaksi)
    TextView txtDikirimStatusTransaksi;
    @BindView(R.id.txt_diterima_status_transaksi)
    TextView txtDiterimaStatusTransaksi;
    @BindView(R.id.txt_dibatalkan_status_transaksi)
    TextView txtDibatalkanStatusTransaksi;
    @BindView(R.id.txt_tukar_status_transaksi)
    TextView txtTukarStatusTransaksi;
    @BindView(R.id.txt_dikembalikan_status_transaksi)
    TextView txtDikembalikanStatusTransaksi;
    @BindView(R.id.scrollStatusTransaksi)
    HorizontalScrollView scrollStatusTransaksi;
    @BindView(R.id.garis_grey)
    View garisGrey;
    @BindView(R.id.recycler_status_transaksi)
    RecyclerView recyclerStatusTransaksi;

    public static final String ExtraNamaForm = "extraNamaForm";

    String urlimage1 = "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//83/MTA-4603141/dettol_dettol_hand_sanitizer_50ml_full02_rae7qksc.jpg";
    String urlimage2 = "https://ecs7.tokopedia.net/img/cache/700/VqbcmM/2020/10/6/162986da-65e8-4f00-afbf-ad8812e5a2eb.jpg";

    ListStatusTransaksi[] listStatusTransaksi = new ListStatusTransaksi[]{

            new ListStatusTransaksi("#ID99887732", "28 Desember 2020", "Hand Sanitizer", "Kesehatan/Medis", "Rp. 15.000", "Rp. 25.000",
                    "Rp. 10. 000", "Dipesan", urlimage1),
            new ListStatusTransaksi("#ID55533627", "29 Desember 2020", "Crewneck", "Pakaian", "Rp. 150.000", "Rp. 250.000",
                    "Rp. 100. 000", "Dipesan", urlimage2)
    };
    @BindView(R.id.recycler_status_transaksi_batal)
    RecyclerView recyclerStatusTransaksiBatal;

    ListStatusTransaksi[] listStatusTransaksiBatal = new ListStatusTransaksi[]{

            new ListStatusTransaksi("#ID99887732", "28 Desember 2020", "Hand Sanitizer", "Kesehatan/Medis", "Rp. 15.000", "Rp. 25.000",
                    "Rp. 10. 000", "Dibatalkan", urlimage1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_transaksi);
        ButterKnife.bind(this);

        txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
        recyclerStatusTransaksiBatal.setVisibility(View.GONE);
        loadData();

        String nama_form = getIntent().getStringExtra(ExtraNamaForm);
//        if (nama_form.equals("alasanPembatalan")) {
//
//            txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
//            recyclerStatusTransaksi.setVisibility(View.GONE);
//            recyclerStatusTransaksiBatal.setVisibility(View.VISIBLE);
//            loadDataBatal();
//        }
        clickTextSemua();
        clickTextDipesan();
        clickTextDikemas();
        clickTextDikirim();
        clickTextDiterima();
        clickTextDibatalkan();
        clickTextTukar();
        clickTextDikembalikan();
    }

    private void loadDataBatal() {

        StatusTransaksiAdapter adapter = new StatusTransaksiAdapter(StatusTransaksiActivity.this, listStatusTransaksiBatal);
        recyclerStatusTransaksiBatal.setHasFixedSize(true);
        recyclerStatusTransaksiBatal.setAdapter(adapter);
        recyclerStatusTransaksiBatal.setLayoutManager(new LinearLayoutManager(StatusTransaksiActivity.this));

    }

    private void loadData() {

        StatusTransaksiAdapter adapter = new StatusTransaksiAdapter(StatusTransaksiActivity.this, listStatusTransaksi);
        recyclerStatusTransaksi.setHasFixedSize(true);
        recyclerStatusTransaksi.setAdapter(adapter);
        recyclerStatusTransaksi.setLayoutManager(new LinearLayoutManager(StatusTransaksiActivity.this));

    }

    private void clickTextDikembalikan() {

        txtDikembalikanStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }

    private void clickTextTukar() {

        txtTukarStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }

    private void clickTextDibatalkan() {

        txtDibatalkanStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
                recyclerStatusTransaksiBatal.setVisibility(View.VISIBLE);
                loadDataBatal();

            }
        });

    }

    private void clickTextDikemas() {

        txtDikemasStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }

    private void clickTextDiterima() {

        txtDiterimaStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }

    private void clickTextDikirim() {

        txtDikirimStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }

    private void clickTextDipesan() {

        txtDipesanStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.VISIBLE);
                loadData();
            }
        });
    }

    private void clickTextSemua() {

        txtSemuaStatusTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSemuaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.colorPrimary));
                txtDibatalkanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikemasStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikembalikanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDikirimStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDipesanStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtDiterimaStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                txtTukarStatusTransaksi.setTextColor(ContextCompat.getColor(StatusTransaksiActivity.this, R.color.grayDark));
                recyclerStatusTransaksi.setVisibility(View.GONE);
            }
        });

    }
}