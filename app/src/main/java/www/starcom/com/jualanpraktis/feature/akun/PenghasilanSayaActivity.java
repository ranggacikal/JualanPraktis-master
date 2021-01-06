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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.MainActivity;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PenghasilanBatalAdapter;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSayaAdapter;
import www.starcom.com.jualanpraktis.adapter.PenghasilanSelesaiAdapter;
import www.starcom.com.jualanpraktis.feature.belanja_bulanan.ListBelanjaBulananFragment;
import www.starcom.com.jualanpraktis.feature.pembayaran.ListPembayaranFragment;
import www.starcom.com.jualanpraktis.feature.pesanan_saya.ListPesananFragment;
import www.starcom.com.jualanpraktis.home_dashboard;
import www.starcom.com.jualanpraktis.katalog;
import www.starcom.com.jualanpraktis.keranjang;
import www.starcom.com.jualanpraktis.model.ListPenghasilanSaya;

public class PenghasilanSayaActivity extends AppCompatActivity {

    @BindView(R.id.btn_riwayat_pencairan)
    Button btnRiwayatPencairan;

    public static PenghasilanSayaActivity instance;
    TabLayout tabLayout;

    private PesananSelesaiFragment pesananSelesaiFragment;
    private PesananDiprosesFragment pesananDiprosesFragment;
    private PesananDibatalkanFragment pesananDibatalkanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penghasilan_saya);
        ButterKnife.bind(this);

        btnRiwayatPencairan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PenghasilanSayaActivity.this, RiwayatPencairanActivity.class);
                startActivity(intent);
            }
        });

        instance=this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();

    }

    public static PenghasilanSayaActivity getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutPenghasilanSaya);
    }

    private void setupTabLayout() {
//        tabLayout.removeAllTabs();
        pesananDiprosesFragment = new PesananDiprosesFragment();
        pesananSelesaiFragment = new PesananSelesaiFragment();
        pesananDibatalkanFragment = new PesananDibatalkanFragment();
        // tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Pesanan Sedang Di Proses"));
        tabLayout.addTab(tabLayout.newTab().setText("Pesanan Selesai"));
        tabLayout.addTab(tabLayout.newTab().setText("Pesanan Dibatalkan"));

    }
    private void bindWidgetsWithAnEvent()
    {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(pesananDiprosesFragment);
                break;
            case 1 :
                replaceFragment(pesananSelesaiFragment);
                break;
            case 2 :
                replaceFragment(pesananDibatalkanFragment);
                break;
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framePenghasilanSaya, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}