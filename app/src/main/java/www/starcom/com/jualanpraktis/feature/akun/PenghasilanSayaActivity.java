package www.starcom.com.jualanpraktis.feature.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PenghasilanBatalAdapter;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSayaAdapter;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSelesaiAdapter;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PenghasilanSayaActivity extends AppCompatActivity {

    @BindView(R.id.imgBackPenghasilanSaya)
    ImageView imgBackPenghasilanSaya;
    @BindView(R.id.toolbarPenghasilanSaya)
    Toolbar toolbarPenghasilanSaya;
    @BindView(R.id.text_total_penghasilan_anda)
    TextView textTotalPenghasilanAnda;
    @BindView(R.id.btn_riwayat_pencairan)
    Button btnRiwayatPencairan;
    @BindView(R.id.headerPenghasilanSaya)
    LinearLayout headerPenghasilanSaya;
    @BindView(R.id.txt_pesanan_proses_penghasilan_saya)
    TextView txtPesananProsesPenghasilanSaya;
    @BindView(R.id.txt_pesanan_selesai_penghasilan_saya)
    TextView txtPesananSelesaiPenghasilanSaya;
    @BindView(R.id.txt_pesanan_batal_penghasilan_saya)
    TextView txtPesananBatalPenghasilanSaya;
    @BindView(R.id.scrollPenghasilanSaya)
    HorizontalScrollView scrollPenghasilanSaya;
    @BindView(R.id.garis_grey_penghasilan_saya)
    View garisGreyPenghasilanSaya;
    @BindView(R.id.text_totalpenghasilan_pesanan_proses)
    TextView textTotalpenghasilanPesananProses;
    @BindView(R.id.spinner_tanggal_awal_penghasilan_saya)
    Spinner spinnerTanggalAwalPenghasilanSaya;
    @BindView(R.id.spinner_tanggal_akhir_penghasilan_saya)
    Spinner spinnerTanggalAkhirPenghasilanSaya;
    @BindView(R.id.relative_total_pesanan_proses)
    RelativeLayout relativeTotalPesananProses;
    @BindView(R.id.text_totalpenghasilan_pesanan_selesai)
    TextView textTotalpenghasilanPesananSelesai;
    @BindView(R.id.relative_total_pesanan_selesai)
    RelativeLayout relativeTotalPesananSelesai;
    @BindView(R.id.text_totalpenghasilan_pesanan_dibatalkan)
    TextView textTotalpenghasilanPesananDibatalkan;
    @BindView(R.id.relative_total_pesanan_dibatalkan)
    RelativeLayout relativeTotalPesananDibatalkan;
    @BindView(R.id.recycler_penghasilan_saya)
    RecyclerView recyclerPenghasilanSaya;
    @BindView(R.id.recycler_penghasilan_saya_selesai)
    RecyclerView recyclerPenghasilanSayaSelesai;
    @BindView(R.id.recycler_penghasilan_saya_batalkan)
    RecyclerView recyclerPenghasilanSayaBatalkan;

    String urlimage1 = "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//83/MTA-4603141/dettol_dettol_hand_sanitizer_50ml_full02_rae7qksc.jpg";
    String urlimage2 = "https://ecs7.tokopedia.net/img/cache/700/VqbcmM/2020/10/6/162986da-65e8-4f00-afbf-ad8812e5a2eb.jpg";

    ListPenghasilanSaya[] listPenghasilan = new ListPenghasilanSaya[]{

            new ListPenghasilanSaya("Hand Sanitizer", urlimage1, "29 Desember 2020", "Proses", "Rp.25.000"),
            new ListPenghasilanSaya("Crewneck Billie eilish", urlimage2, "29 Desember 2020", "Proses", "Rp.250.000")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penghasilan_saya);
        ButterKnife.bind(this);

        populateData();
        txtPesananProsesPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.colorPrimary));
        clickPesananProses();
        clickPesananSelesai();
        clickPesananBatal();

        btnRiwayatPencairan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PenghasilanSayaActivity.this, RiwayatPencairanActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickPesananBatal() {

        txtPesananBatalPenghasilanSaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPesananBatalPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.colorPrimary));
                txtPesananProsesPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                txtPesananSelesaiPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                relativeTotalPesananSelesai.setVisibility(View.GONE);
                relativeTotalPesananDibatalkan.setVisibility(View.VISIBLE);
                relativeTotalPesananProses.setVisibility(View.GONE);
                recyclerPenghasilanSaya.setVisibility(View.GONE);
                recyclerPenghasilanSayaSelesai.setVisibility(View.GONE);
                recyclerPenghasilanSayaBatalkan.setVisibility(View.VISIBLE);
                populateDataBatal();
            }
        });

    }


    private void clickPesananSelesai() {

        txtPesananSelesaiPenghasilanSaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPesananBatalPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                txtPesananProsesPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                txtPesananSelesaiPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.colorPrimary));
                relativeTotalPesananSelesai.setVisibility(View.VISIBLE);
                relativeTotalPesananDibatalkan.setVisibility(View.GONE);
                relativeTotalPesananProses.setVisibility(View.GONE);
                recyclerPenghasilanSaya.setVisibility(View.GONE);
                recyclerPenghasilanSayaSelesai.setVisibility(View.VISIBLE);
                recyclerPenghasilanSayaBatalkan.setVisibility(View.GONE);
                populateDataSelesai();
            }
        });

    }

    private void clickPesananProses() {

        txtPesananProsesPenghasilanSaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPesananProsesPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.colorPrimary));
                txtPesananBatalPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                txtPesananSelesaiPenghasilanSaya.setTextColor(ContextCompat.getColor(PenghasilanSayaActivity.this, R.color.grayDark));
                relativeTotalPesananSelesai.setVisibility(View.GONE);
                relativeTotalPesananDibatalkan.setVisibility(View.GONE);
                relativeTotalPesananProses.setVisibility(View.VISIBLE);
                recyclerPenghasilanSaya.setVisibility(View.VISIBLE);
                recyclerPenghasilanSayaSelesai.setVisibility(View.GONE);
                recyclerPenghasilanSayaBatalkan.setVisibility(View.GONE);
                populateData();
            }
        });

    }

    private void populateData() {



        PenghasilanSayaAdapter adapter = new PenghasilanSayaAdapter(listPenghasilan, PenghasilanSayaActivity.this);
        recyclerPenghasilanSaya.setHasFixedSize(true);
        recyclerPenghasilanSaya.setLayoutManager(new LinearLayoutManager(PenghasilanSayaActivity.this));
        recyclerPenghasilanSaya.setAdapter(adapter);

    }

    private void populateDataSelesai(){

        PenghasilanSelesaiAdapter adapter = new PenghasilanSelesaiAdapter(listPenghasilan, PenghasilanSayaActivity.this);
        recyclerPenghasilanSayaSelesai.setHasFixedSize(true);
        recyclerPenghasilanSayaSelesai.setLayoutManager(new LinearLayoutManager(PenghasilanSayaActivity.this));
        recyclerPenghasilanSayaSelesai.setAdapter(adapter);

    }


    private void populateDataBatal() {

        PenghasilanBatalAdapter adapter = new PenghasilanBatalAdapter(listPenghasilan, PenghasilanSayaActivity.this);
        recyclerPenghasilanSayaBatalkan.setHasFixedSize(true);
        recyclerPenghasilanSayaBatalkan.setLayoutManager(new LinearLayoutManager(PenghasilanSayaActivity.this));
        recyclerPenghasilanSayaBatalkan.setAdapter(adapter);

    }
}