package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.PencairanAdapter;
import www.starcom.com.jualanpraktis.model.ListPencairan;

public class RiwayatPencairanActivity extends AppCompatActivity {

    @BindView(R.id.imgBackRiwayatPencairan)
    ImageView imgBackRiwayatPencairan;
    @BindView(R.id.recycler_riwayat_pencairan)
    RecyclerView recyclerRiwayatPencairan;

    ListPencairan[] listPencairan = new ListPencairan[]{
            new ListPencairan("29 Des 2020", "17 : 00", "Rp. 450.000"),
            new ListPencairan("28 Des 2020", "14 : 00", "Rp.750.000")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pencairan);
        ButterKnife.bind(this);

        imgBackRiwayatPencairan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadData();
    }

    private void loadData() {

        PencairanAdapter adapter = new PencairanAdapter(listPencairan, RiwayatPencairanActivity.this);
        recyclerRiwayatPencairan.setHasFixedSize(true);
        recyclerRiwayatPencairan.setLayoutManager(new LinearLayoutManager(RiwayatPencairanActivity.this));
        recyclerRiwayatPencairan.setAdapter(adapter);

    }
}