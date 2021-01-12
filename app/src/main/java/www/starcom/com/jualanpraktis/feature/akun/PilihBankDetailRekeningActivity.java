package www.starcom.com.jualanpraktis.feature.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PilihBankAdapter;
import www.starcom.com.jualanpraktis.interfaces.PilihBankClickInterface;
import www.starcom.com.jualanpraktis.model.ListBank;

public class PilihBankDetailRekeningActivity extends AppCompatActivity {

    @BindView(R.id.imgToolbarPilihBank)
    ImageView imgToolbarPilihBank;
    @BindView(R.id.edt_cari_bank)
    EditText edtCariBank;
    @BindView(R.id.img_search_cari_bank)
    ImageView imgSearchCariBank;
    @BindView(R.id.recycler_bank_populer)
    RecyclerView recyclerBankPopuler;
    @BindView(R.id.recycler_bank_lainnya)
    RecyclerView recyclerBankLainnya;

    public ArrayList<String> namaBank = new ArrayList<>();

    public static final String ExtraNama = "extraNama";
    public static final String ExtraRekening = "extraRekening";

    String bankBca = "https://logos-download.com/wp-content/uploads/2017/03/BCA_logo_Bank_Central_Asia.png";
    String bankMandiri = "https://logos-download.com/wp-content/uploads/2016/06/Bank_Mandiri_logo_white_bg.png";
    String dbs = "https://logos-download.com/wp-content/uploads/2016/12/DBS_Bank_logo_logotype.png";
    String ocbc = "https://logos-download.com/wp-content/uploads/2016/12/OCBC_Bank_logo_logotype_Singapore.png";
    String maybangk = "https://cdn.freebiesupply.com/logos/thumbs/2x/maybank-logo.png";
    String bni = "https://promo.agate.id/wp-content/uploads/2018/09/Logo-Bank-BNI-PNG-570x261.png";
    String bri = "https://image.psikolif.com/wp-content/uploads/2019/04/Logo-BRI-Bank-Rakyat-Indonesia-PNG-Terbaru.png";
    String btn = "https://seeklogo.com/images/B/bank-tabungan-negara-btn-logo-ED226D0731-seeklogo.com.png";
    String bjb = "https://1.bp.blogspot.com/-JoHD59tSwf4/XuL5Dzb-8LI/AAAAAAAAA7g/VCR_rfIpK_oDb_qbJ3K0ObGiHgwKloe5gCLcBGAsYHQ/w1200-h630-p-k-no-nu/Logo%2BBank%2BBJB.png";



    ListBank[] listBankItem = new ListBank[]{
            new ListBank("BCA", bankBca),
            new ListBank("MANDIRI", bankMandiri),
            new ListBank("DBS BANK", dbs),
            new ListBank("OCBC BANK", ocbc),
            new ListBank("MAYBANK", maybangk),
            new ListBank("BNI", bni),
            new ListBank("BRI", bri),
            new ListBank("BTN", btn),
            new ListBank("BJB", bjb)
    };

    public String nama;
    public String rekening;

    public PilihBankDetailRekeningActivity(){

    }

    DetailRekeningBankActivity detailRekeningBankActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bank_detail_rekening);
        ButterKnife.bind(this);

        nama = getIntent().getStringExtra(ExtraNama);
        rekening = getIntent().getStringExtra(ExtraRekening);

        loadRecyclerPopulerBank();
    }

    private void loadRecyclerPopulerBank() {

        PilihBankAdapter adapter = new PilihBankAdapter(PilihBankDetailRekeningActivity.this, listBankItem, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PilihBankDetailRekeningActivity.this, 3,
                LinearLayoutManager.VERTICAL, false);
        recyclerBankPopuler.setHasFixedSize(true);
        recyclerBankPopuler.setLayoutManager(gridLayoutManager);
        recyclerBankPopuler.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}