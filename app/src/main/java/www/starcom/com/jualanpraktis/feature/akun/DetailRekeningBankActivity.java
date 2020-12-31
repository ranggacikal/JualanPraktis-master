package www.starcom.com.jualanpraktis.feature.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;

public class DetailRekeningBankActivity extends AppCompatActivity {

    @BindView(R.id.imgToolbarInputRekening)
    ImageView imgToolbarInputRekening;
    @BindView(R.id.edt_nama_buku_tabungan)
    EditText edtNamaBukuTabungan;
    @BindView(R.id.edt_no_rekening)
    EditText edtNoRekening;
    @BindView(R.id.txt_nama_bank)
    TextView txtNamaBank;
    @BindView(R.id.relative_nama_bank)
    RelativeLayout relativeNamaBank;
    @BindView(R.id.btn_simpan_detail_rekening_bank)
    Button btnSimpanDetailRekeningBank;

    ArrayList<String> dataRekening = new ArrayList<>();

    public static final String ExtraNamaBank = "extraNamaBank";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekening_bank);
        ButterKnife.bind(this);

        if (dataRekening.size() > 0){

            for (int i = 0; i<dataRekening.size(); i++){

                edtNamaBukuTabungan.setText(i);
                edtNoRekening.setText(i);
            }

        }

        relativeNamaBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihBank();
            }
        });
    }

    private void pilihBank() {

        String nama = edtNamaBukuTabungan.getText().toString();
        String rekening = edtNoRekening.getText().toString();

        dataRekening.add(nama);
        dataRekening.add(rekening);

        Intent intent = new Intent(DetailRekeningBankActivity.this, PilihBankDetailRekeningActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 || resultCode == RESULT_OK){

            String nama_bank = data.getStringExtra("extraNamaBank");
            txtNamaBank.setText(nama_bank);

        }
    }
}