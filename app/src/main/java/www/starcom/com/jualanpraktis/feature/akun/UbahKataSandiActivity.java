package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.starcom.com.jualanpraktis.R;

public class UbahKataSandiActivity extends AppCompatActivity {

    @BindView(R.id.imgBackUbahPassword)
    ImageView imgBackUbahPassword;
    @BindView(R.id.edt_password_lama_ubah)
    EditText edtPasswordLamaUbah;
    @BindView(R.id.btn_lanjut_password)
    Button btnLanjutPassword;
    @BindView(R.id.linear_password_lama)
    LinearLayout linearPasswordLama;
    @BindView(R.id.edt_password_baru_ubah)
    EditText edtPasswordBaruUbah;
    @BindView(R.id.edt_password_konfirmasi_ubah)
    EditText edtPasswordKonfirmasiUbah;
    @BindView(R.id.btn_oke_password)
    Button btnOkePassword;
    @BindView(R.id.linear_password_baru)
    LinearLayout linearPasswordBaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_kata_sandi);
        ButterKnife.bind(this);

        btnLanjutPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiPassword();
            }
        });
    }

    private void validasiPassword() {

        String passwordLama = edtPasswordLamaUbah.getText().toString();

        if (passwordLama.isEmpty()){
            edtPasswordLamaUbah.setError("Tidak Boleh Kosong");
            edtPasswordLamaUbah.requestFocus();
        }else{
            linearPasswordLama.setVisibility(View.GONE);
            linearPasswordBaru.setVisibility(View.VISIBLE);
        }

    }
}