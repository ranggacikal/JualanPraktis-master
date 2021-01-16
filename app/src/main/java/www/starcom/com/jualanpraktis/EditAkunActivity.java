package www.starcom.com.jualanpraktis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.Spinner.Gender;
import www.starcom.com.jualanpraktis.Spinner.Kecamatan;
import www.starcom.com.jualanpraktis.Spinner.KotaKabupaten;
import www.starcom.com.jualanpraktis.Spinner.Pekerjaan;
import www.starcom.com.jualanpraktis.Spinner.Pendidikan;
import www.starcom.com.jualanpraktis.Spinner.Penghasilan;
import www.starcom.com.jualanpraktis.Spinner.Provinsi;
import www.starcom.com.jualanpraktis.Spinner.StatusPerkawinan;
import www.starcom.com.jualanpraktis.adapter.FavoritAdapter;
import www.starcom.com.jualanpraktis.api.ConfigRetrofit;
import www.starcom.com.jualanpraktis.feature.akun.AkunFragment;
import www.starcom.com.jualanpraktis.feature.akun.ProdukFavoritActivity;
import www.starcom.com.jualanpraktis.model_retrofit.editakun.ResponseInsertEditAkun;

import static www.starcom.com.jualanpraktis.alamat_pengiriman.BASE_URL;
import static www.starcom.com.jualanpraktis.alamat_pengiriman.KEY;

public class EditAkunActivity extends AppCompatActivity {

    CircleImageView imgProfileEditaKUN;
    LinearLayout linearTambahFoto, linearSimpanEditAkun;
    TextView txtEditFotoProfile, txtjenisKelamin, txttanggalLahjr, txtProvinsi, txtKota, txtKecamatan,
            txtStatusPerkawinan, txtPendidikan, txtPekerjaan, txtPenghasilan;
    CardView cardJenisKelamin, cardTanggalLahir, cardProvinsi, cardKota, cardKecamatan, cardStatusPerkawinan, cardPendidikanTerakhir,
            cardPenghasilan, cardPekerjaan;

    Spinner spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerJenisKelamin, spinnerStatusPerkawinan
            , spinnerPendidikan, spinnerPekerjaan, spinnerPenghasilan;

    CardView cardJenisKelaminAwal, cardProvinsiAwal, cardKotaAwal, cardKecamatanAwal, cardStatusPerkawinanAwal, cardPendidikanAwal
            , cardPekerjaanAwal, cardPenghasilanAwal;

    EditText edtTanggalLahir, edtPassword, edtNama, edtEmail, edtNoHp, edtAlamat, edtJumlahAnak, edtNamaToko, edtNoKtp, edtNoNpwp;

    List<Provinsi> provinsiList = new ArrayList<>();
    List<KotaKabupaten> kotaKabupatenList = new ArrayList<>();
    List<Kecamatan> kecamatanList = new ArrayList<>();

    String id_kota, id_wilayah, id_provinsi;
    String nama_kota, nama_wilayah, nama_provinsi, jenis_kelamin, status_perkawinan, pendidikan, pekerjaan, penghasilan;

    List<Gender> genderList = new ArrayList<>();
    List<StatusPerkawinan> statusPerkawinanList = new ArrayList<>();
    List<Pendidikan> pendidikanList = new ArrayList<>();
    List<Pekerjaan> pekerjaanList = new ArrayList<>();
    List<Penghasilan> penghasilanList = new ArrayList<>();

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    loginuser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        imgProfileEditaKUN = findViewById(R.id.img_profile_edit_akun);
        linearTambahFoto = findViewById(R.id.linear_tambah_foto);
        txtEditFotoProfile = findViewById(R.id.text_edit_foto_profile);
        txtjenisKelamin = findViewById(R.id.text_jenis_kelamin_edit_akun);
        txttanggalLahjr = findViewById(R.id.text_tanggal_lahir_edit_akun);
        txtProvinsi = findViewById(R.id.text_provinsi_edit_akun);
        txtKota = findViewById(R.id.text_kota_edit_akun);
        txtKecamatan = findViewById(R.id.text_kecamatan_edit_akun);
        txtStatusPerkawinan = findViewById(R.id.text_status_perkawinan_edit_akun);
        txtPendidikan = findViewById(R.id.text_pendidikan_edit_akun);
        txtPekerjaan = findViewById(R.id.text_pekerjaan_edit_akun);
        txtPenghasilan = findViewById(R.id.text_penghasilan_edit_akun);
        cardJenisKelamin = findViewById(R.id.card_jenis_kelamin_edit_akun);
        cardTanggalLahir = findViewById(R.id.card_tanggal_lahir_awal);
        cardProvinsi = findViewById(R.id.card_provinsi_edit_akun);
        cardKota = findViewById(R.id.card_kota_edit_akun);
        cardKecamatan = findViewById(R.id.card_kecamatan_edit_akun);
        cardStatusPerkawinan = findViewById(R.id.card_status_perkawinan_edit_akun);
        cardPendidikanTerakhir = findViewById(R.id.card_pendidikan_edit_akun);
        cardPenghasilan = findViewById(R.id.card_penghasilan_edit_akun);
        cardPekerjaan = findViewById(R.id.card_pekerjaan_edit_akun);
        linearSimpanEditAkun = findViewById(R.id.linear_simpan_edit_akun);
        spinnerProvinsi = findViewById(R.id.spinner_provinsi_edit_akun);
        spinnerKotaKabupaten = findViewById(R.id.spinner_kotakabupaten_edit_akun);
        spinnerKecamatan = findViewById(R.id.spinner_kecamatan_edit_akun);
        spinnerJenisKelamin = findViewById(R.id.spinner_jenis_kelamin_edit_akun);
        spinnerStatusPerkawinan = findViewById(R.id.spinner_status_perkawinan_edit_akun);
        spinnerPekerjaan = findViewById(R.id.spinner_pekerjaan_edit_akun);
        spinnerPenghasilan = findViewById(R.id.spinner_penghasilan_edit_akun);
        spinnerPendidikan = findViewById(R.id.spinner_pendidikan_edit_akun);
        cardJenisKelaminAwal = findViewById(R.id.card_jenis_kelamin);
        cardProvinsiAwal = findViewById(R.id.card_provinsi_awal);
        cardKotaAwal = findViewById(R.id.card_kota_awal);
        cardKecamatanAwal = findViewById(R.id.card_kecamatan_awal);
        cardStatusPerkawinanAwal = findViewById(R.id.card_statusperkawinan_awal);
        cardPendidikanAwal = findViewById(R.id.card_pendidikan_awal);
        cardPekerjaanAwal = findViewById(R.id.card_pekerjaan_awal);
        cardPenghasilanAwal = findViewById(R.id.card_penghasilan_awal);
        edtTanggalLahir = findViewById(R.id.edt_tanggal_lahir_edit_akun);
        edtPassword = findViewById(R.id.edt_password_edit_akun);
        edtNama = findViewById(R.id.edt_nama_edit_akun);
        edtEmail = findViewById(R.id.edt_email_edit_akun);
        edtNoHp = findViewById(R.id.edt_nohp_edit_akun);
        edtAlamat = findViewById(R.id.edt_alamat_edit_akun);
        edtJumlahAnak = findViewById(R.id.edt_jumlah_anak_edit_akun);
        edtNamaToko = findViewById(R.id.edt_nama_toko_edit_akun);
        edtNoKtp = findViewById(R.id.edt_noKTP_edit_akun);
        edtNoNpwp = findViewById(R.id.edt_noNPWP_edit_akun);
        user = SharedPrefManager.getInstance(EditAkunActivity.this).getUser();

        AndroidNetworking.initialize(getApplicationContext());

        String link_image = "https://jualanpraktis.net/files/drp/"+user.getKode()+"/"+user.getFoto();

        Glide.with(EditAkunActivity.this)
                .load(link_image)
                .error(R.mipmap.ic_launcher)
                .into(imgProfileEditaKUN);

        edtNama.setText(user.getNama());
        edtEmail.setText(user.getEmail());

        txtEditFotoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImagePicker.Companion.with(EditAkunActivity.this)
//                        .crop(340,340)
//                        .compress(512)
//                        .start();
                Toast.makeText(EditAkunActivity.this, "Already soon", Toast.LENGTH_SHORT).show();
            }
        });

        cardJenisKelaminAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardJenisKelaminAwal.setVisibility(View.GONE);
                cardJenisKelamin.setVisibility(View.VISIBLE);
                getGender();
                getSelectedGender();
            }
        });

        cardProvinsiAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardProvinsi.setVisibility(View.VISIBLE);
                cardProvinsiAwal.setVisibility(View.GONE);
                getProvinsi(spinnerProvinsi);
            }
        });
//        getProvinsi(spinnerProvinsi);
        SelectedItemSpinner(spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan);
//        getGender();

        cardStatusPerkawinanAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStatusPerkawinanAwal.setVisibility(View.GONE);
                cardStatusPerkawinan.setVisibility(View.VISIBLE);
                getStatusPerkawinan();
                getSelectedStatusPerkawinan();
            }
        });

        cardPendidikanAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPendidikanAwal.setVisibility(View.GONE);
                cardPendidikanTerakhir.setVisibility(View.VISIBLE);
                getPendidikan();
                getSelectedPendidikan();
            }
        });

        cardPekerjaanAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPekerjaanAwal.setVisibility(View.GONE);
                cardPekerjaan.setVisibility(View.VISIBLE);
                getPekerjaan();
                getSelectedPekerjaan();
            }
        });

        cardPenghasilanAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPenghasilanAwal.setVisibility(View.GONE);
                cardPenghasilan.setVisibility(View.VISIBLE);
                getPenghasilan();
                getSelectedPenghasilan();
            }
        });

        dateFormatter = new SimpleDateFormat("YYYY-mm-dd", Locale.US);
        cardTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        linearSimpanEditAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanEditAkun();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void simpanEditAkun() {

        String nama = edtNama.getText().toString();
        String nama_toko = edtNamaToko.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String noHp = edtNoHp.getText().toString();
        String no_ktp = edtNoKtp.getText().toString();
        String no_npwp = edtNoNpwp.getText().toString();
        String tanggal_lahir = edtTanggalLahir.getText().toString();
        String alamat = edtAlamat.getText().toString();
        String jumlah_anak = edtJumlahAnak.getText().toString();

        String host = "http://jualanpraktis.net/android/update_akun.php";

        ProgressDialog progressDialog = new ProgressDialog(EditAkunActivity.this);
        progressDialog.setTitle("Menyimpan Perubahan Profile");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ConfigRetrofit.service.editAkun(user.getId(),nama, nama_toko, nama_provinsi, nama_kota, nama_wilayah, alamat, no_ktp, no_npwp,
                noHp, email, jenis_kelamin, tanggal_lahir, status_perkawinan, jumlah_anak, pendidikan, pekerjaan,
                penghasilan, password).enqueue(new Callback<ResponseInsertEditAkun>() {
            @Override
            public void onResponse(Call<ResponseInsertEditAkun> call, Response<ResponseInsertEditAkun> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    String pesan = response.body().getMessage();

                    if (pesan.equals("Perubahan profile berhasil disimpan")){
                        progressDialog.dismiss();
                        Toast.makeText(EditAkunActivity.this, "Berhasil Edit Akun", Toast.LENGTH_SHORT).show();
                        finish();

                        //TO DO SAVE KE SHARED PREFERENCES MANAGER
                        loginuser userUpdate = new loginuser(
                                user.getId(),
                                user.getKode(),
                                nama,
                                nama_toko,
                                nama_provinsi,
                                nama_kota,
                                nama_wilayah,
                                alamat,
                                no_ktp,
                                no_npwp,
                                noHp,
                                email,
                                user.getAtas_nama(),
                                user.getNo_rek(),
                                user.getNama_bank(),
                                user.getFoto()
                        );

                        SharedPrefManager.getInstance(EditAkunActivity.this).userLogin(userUpdate);

                    }else{
                        Toast.makeText(EditAkunActivity.this, "Gagal Edit Data", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(EditAkunActivity.this, "Terjadi Kesalahan Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertEditAkun> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        final HashMap<String, String> params = new HashMap<>();
//        params.put("id_member", user.getId());
//        params.put("nama", nama);
//        params.put("nama_toko", nama_toko);
//        params.put("provinsi", nama_provinsi);
//        params.put("kota", nama_kota);
//        params.put("kecamatan", nama_wilayah);
//        params.put("alamat", alamat);
//        params.put("no_ktp", no_ktp);
//        params.put("no_npwp", no_npwp);
//        params.put("no_hp", noHp);
//        params.put("email", email);
//        params.put("gender", jenis_kelamin);
//        params.put("tgl_lahir", tanggal_lahir);
//        params.put("status_kawin", status_perkawinan);
//        params.put("jumlah_anak", jumlah_anak);
//        params.put("pendidikan", pendidikan);
//        params.put("pekerjaan", pekerjaan);
//        params.put("penghasilan", penghasilan);
//        params.put("password", password);
//
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .build();
//        AndroidNetworking.post("http://jualanpraktis.net/android/update_akun.php")
//                .addBodyParameter(params)
//                .setTag(EditAkunActivity.this)
//                .setPriority(Priority.MEDIUM)
//                .setOkHttpClient(okHttpClient)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        progressDialog.dismiss();
//                        Toast.makeText(EditAkunActivity.this, "Berhasil Memperbarui Akun", Toast.LENGTH_SHORT).show();
//                        finish();
//
//                        try {
//                            Toast.makeText(getApplicationContext(), response.getString("response"), Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//                    @Override
//                    public void onError(ANError anError) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
//                        Log.d("dataParams", "onError: "+params);
//                    }
//
//                });

    }

    private void getSelectedPenghasilan() {

        spinnerPenghasilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Penghasilan penghasilan2 = penghasilanList.get(spinnerPenghasilan.getSelectedItemPosition());
                penghasilan = penghasilan2.getPenghasilan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getSelectedPekerjaan() {

        spinnerPekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Pekerjaan pekerjaan2 = pekerjaanList.get(spinnerPekerjaan.getSelectedItemPosition());
                pekerjaan = pekerjaan2.getPekerjaan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getSelectedPendidikan() {

        spinnerPendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Pendidikan pendidikan2 = pendidikanList.get(spinnerPendidikan.getSelectedItemPosition());
                pendidikan = pendidikan2.getPendidikan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getSelectedGender() {

        spinnerJenisKelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Gender gender = genderList.get(spinnerJenisKelamin.getSelectedItemPosition());
                jenis_kelamin = gender.getGender();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getSelectedStatusPerkawinan(){

        spinnerStatusPerkawinan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StatusPerkawinan statusPerkawinan2 = statusPerkawinanList.get(spinnerStatusPerkawinan.getSelectedItemPosition());
                status_perkawinan = statusPerkawinan2.getStatus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                cardTanggalLahir.setVisibility(View.GONE);
                edtTanggalLahir.setVisibility(View.VISIBLE);
                edtTanggalLahir.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    private void SelectedItemSpinner(Spinner spinnerProvinsi, Spinner spinnerKotaKabupaten, Spinner spinnerKecamatan) {

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Provinsi provinsi = provinsiList.get(spinnerProvinsi.getSelectedItemPosition());
                id_provinsi = provinsi.getProvince_id();
                nama_provinsi = provinsi.getProvince();
                if (id_provinsi != null) {
                    //spinnerData2(id_kota);
                    cardKotaAwal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardKotaAwal.setVisibility(View.GONE);
                            cardKota.setVisibility(View.VISIBLE);
                            getKotaKabupaten(id_provinsi, spinnerKotaKabupaten);
                        }
                    });

                    // Log.d(TAG, id_provinsi);
                } else {
                    // Log.d(TAG,""+ id_provinsi);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerKotaKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   id_kota = ((SObject1.Object1.Results) spinner1.getSelectedItem()).idKota;
                KotaKabupaten kotaKabupaten = kotaKabupatenList.get(spinnerKotaKabupaten.getSelectedItemPosition());
                id_kota = kotaKabupaten.getCity_id();
                nama_kota = kotaKabupaten.getCity_name();
                if (nama_kota != null) {
                    //spinnerData2(id_kota);
                    cardKecamatanAwal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardKecamatanAwal.setVisibility(View.GONE);
                            cardKecamatan.setVisibility(View.VISIBLE);
                            getKecamatan(id_kota, spinnerKecamatan);
                        }
                    });

                    //  Log.d(TAG, id_kota);
                } else {
                    // Log.d(TAG,""+ id_kota);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Log.d(TAG, id_kota);
            }
        });
        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Kecamatan kecamatan = kecamatanList.get(spinnerKecamatan.getSelectedItemPosition());
                id_wilayah = kecamatan.getSubdistrict_id();
                nama_wilayah = kecamatan.getSubdistrict_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getKecamatan(String id_kota, Spinner spinnerKecamatan) {

        AndroidNetworking.get(BASE_URL + "subdistrict?city=" + id_kota)
                .addHeaders("key", KEY)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        kecamatanList.clear();
                        txtKecamatan.setVisibility(View.GONE);
                        try {
                            JSONObject object = response.getJSONObject("rajaongkir");
                            JSONArray array = object.getJSONArray("results");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Kecamatan item = new Kecamatan();
                                item.setSubdistrict_id(obj.getString("subdistrict_id"));
                                item.setSubdistrict_name(obj.getString("subdistrict_name"));
                                kecamatanList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            spinnerKecamatan.setVisibility(View.VISIBLE);
                            ArrayAdapter<Kecamatan> adapter = new ArrayAdapter<Kecamatan>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, kecamatanList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerKecamatan.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

    private void getKotaKabupaten(String id_provinsi, Spinner spinnerKotaKabupaten) {
        AndroidNetworking.get(BASE_URL + "city?province=" + id_provinsi)
                .addHeaders("key", KEY)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        kotaKabupatenList.clear();

                        txtKota.setVisibility(View.GONE);
                        try {
                            JSONObject object = response.getJSONObject("rajaongkir");
                            JSONArray array = object.getJSONArray("results");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                KotaKabupaten item = new KotaKabupaten();
                                item.setCity_id(obj.getString("city_id"));
                                item.setCity_name(obj.getString("city_name"));
                                kotaKabupatenList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<KotaKabupaten> adapter = new ArrayAdapter<KotaKabupaten>(EditAkunActivity.this, R.layout.support_simple_spinner_dropdown_item, kotaKabupatenList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerKotaKabupaten.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void getProvinsi(Spinner spinnerProvinsi) {

        AndroidNetworking.get(BASE_URL + "province")
                .addHeaders("key", KEY)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        provinsiList.clear();
                        txtProvinsi.setVisibility(View.GONE);
                        try {
                            JSONObject object = response.getJSONObject("rajaongkir");
                            JSONArray array = object.getJSONArray("results");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Provinsi item = new Provinsi();
                                item.setProvince_id(obj.getString("province_id"));
                                item.setProvince(obj.getString("province"));
                                provinsiList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();

                            ArrayAdapter<Provinsi> adapter = new ArrayAdapter<>(EditAkunActivity.this, R.layout.support_simple_spinner_dropdown_item, provinsiList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerProvinsi.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

    private void getGender() {

        AndroidNetworking.get("http://jualanpraktis.net/android/gender.php")
                .setTag(EditAkunActivity.this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        txtjenisKelamin.setVisibility(View.GONE);
                        genderList.clear();
                        try {

                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Gender item = new Gender();
                                item.setGender_id(obj.getString("id_gender"));
                                item.setGender(obj.getString("gender"));
                                genderList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<Gender> adapter = new ArrayAdapter<Gender>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, genderList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerJenisKelamin.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void getStatusPerkawinan() {

        AndroidNetworking.get("http://jualanpraktis.net/android/status_perkawinan.php")
                .setTag(EditAkunActivity.this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        txtStatusPerkawinan.setVisibility(View.GONE);
                        statusPerkawinanList.clear();
                        try {

                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                StatusPerkawinan item = new StatusPerkawinan();
                                item.setStatus_id(obj.getString("id_kawin"));
                                item.setStatus(obj.getString("status"));
                                statusPerkawinanList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<StatusPerkawinan> adapter = new ArrayAdapter<StatusPerkawinan>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, statusPerkawinanList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerStatusPerkawinan.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void getPendidikan() {

        AndroidNetworking.get("http://jualanpraktis.net/android/pendidikan.php")
                .setTag(EditAkunActivity.this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        txtPendidikan.setVisibility(View.GONE);
                        pendidikanList.clear();
                        try {

                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Pendidikan item = new Pendidikan();
                                item.setPendidikan_id(obj.getString("id_pend"));
                                item.setPendidikan(obj.getString("pend"));
                                pendidikanList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<Pendidikan> adapter = new ArrayAdapter<Pendidikan>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, pendidikanList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerPendidikan.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

    private void getPekerjaan(){

        AndroidNetworking.get("http://jualanpraktis.net/android/pekerjaan.php")
                .setTag(EditAkunActivity.this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        txtPekerjaan.setVisibility(View.GONE);
                        pekerjaanList.clear();
                        try {

                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Pekerjaan item = new Pekerjaan();
                                item.setPekerjaan_id(obj.getString("id_pekerjaan"));
                                item.setPekerjaan(obj.getString("perkerjaan"));
                                pekerjaanList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<Pekerjaan> adapter = new ArrayAdapter<Pekerjaan>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, pekerjaanList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerPekerjaan.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });


    }

    private void getPenghasilan(){

        AndroidNetworking.get("http://jualanpraktis.net/android/penghasilan.php")
                .setTag(EditAkunActivity.this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        txtPenghasilan.setVisibility(View.GONE);
                        penghasilanList.clear();
                        try {

                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Penghasilan item = new Penghasilan();
                                item.setPenghasilan_id(obj.getString("id_penghasilan"));
                                item.setPenghasilan(obj.getString("penghasilan"));
                                penghasilanList.add(item);
                            }


                            //   List<JenisPerangkat> list =respone.getJenisPerangkatArrayList();
                            ArrayAdapter<Penghasilan> adapter = new ArrayAdapter<Penghasilan>(EditAkunActivity.this
                                    , R.layout.support_simple_spinner_dropdown_item, penghasilanList);
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //  spinner.setPrompt("Jenis Perangkat : ");
                            spinnerPenghasilan.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }
}