package www.starcom.com.jualanpraktis.feature.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import www.starcom.com.jualanpraktis.adapter.StatusTransaksiAdapter;
import www.starcom.com.jualanpraktis.feature.belanja_bulanan.ListBelanjaBulananFragment;
import www.starcom.com.jualanpraktis.feature.pembayaran.ListPembayaranFragment;
import www.starcom.com.jualanpraktis.feature.pesanan_saya.ListPesananFragment;
import www.starcom.com.jualanpraktis.home_dashboard;
import www.starcom.com.jualanpraktis.katalog;
import www.starcom.com.jualanpraktis.keranjang;
import www.starcom.com.jualanpraktis.model.ListStatusTransaksi;

public class StatusTransaksiActivity extends AppCompatActivity {

    public static  StatusTransaksiActivity instance;
    private TabLayout tabLayout;
    private SemuaPesananFragment semuaPesananFragment;
    private DipesanFragment dipesanFragment;
    private DibatalkanFragment dibatalkanFragment;
    private DikemasFragment dikemasFragment;
    private DikirimFragment dikirimFragment;
    private DiterimaFragment diterimaFragment;
    private DitukarFragment ditukarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_transaksi);
        ButterKnife.bind(this);

        instance=this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static StatusTransaksiActivity getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutStatusTransaksi);
    }
    private void setupTabLayout() {
        
        semuaPesananFragment = new SemuaPesananFragment();
        dipesanFragment = new DipesanFragment();
        dibatalkanFragment = new DibatalkanFragment();
        dikemasFragment = new DikemasFragment();
        dikirimFragment = new DikirimFragment();
        diterimaFragment = new DiterimaFragment();
        ditukarFragment = new DitukarFragment();
        // tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Semua"));
        tabLayout.addTab(tabLayout.newTab().setText("Dipesan"));
        tabLayout.addTab(tabLayout.newTab().setText("Dikemas"));
        tabLayout.addTab(tabLayout.newTab().setText("Dikirim"));
        tabLayout.addTab(tabLayout.newTab().setText("Diterima"));
        tabLayout.addTab(tabLayout.newTab().setText("Dibatalkan"));
        tabLayout.addTab(tabLayout.newTab().setText("Tukar"));

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
                replaceFragment(semuaPesananFragment);
                break;
            case 1 :
                replaceFragment(dipesanFragment);
                break;
            case 2 :
                replaceFragment(dikemasFragment);
                break;
            case 3 :
                replaceFragment(dikirimFragment);
                break;
            case 4 :
                replaceFragment(diterimaFragment);
                break;
            case 5 :
                replaceFragment(dibatalkanFragment);
                break;
            case 6 :
                replaceFragment(ditukarFragment);
                break;
//            case 4 :
//                if (pref.getLoginMethod().equals("coorperate")){
//                    replaceFragment(akunCoorperateFragment);
//                }else {
//                    replaceFragment(AkunFragment);
//                }
//                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameStatusTransaksi, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}