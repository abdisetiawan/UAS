package com.example.SI_KRS;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SI_KRS.Model.Dosen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDosenActivity extends AppCompatActivity {
    EditText edtNama, edtNidn, edtAlamat, edtEmail, edtGelar;
    DataDosenService service;
    ProgressDialog progressDialog;
    String Nama, NIDN, Alamat, Email, Gelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_dosen);
        this.setTitle("SI-KRS - Hai Admin");
        Button btnSimpan = (Button) findViewById(R.id.btnSmMhs);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);

                builder.setMessage("Simpan Gak Ni Bro?")
                        .setNegativeButton("Ora", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateDosenActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Iyo", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestInsertDosen();
                                ValidasiData();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private void ValidasiData() {
        Nama = edtNama.getText().toString();
        NIDN  = edtNidn.getText().toString();
        Alamat = edtAlamat.getText().toString();
        Gelar = edtGelar.getText().toString();
        Email = edtEmail.getText().toString();

        if(TextUtils.isEmpty(Nama)){
            edtNama.setError("Nama tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(NIDN)){
            edtNidn.setError("NIDN tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Alamat)){
            edtAlamat.setError("Alamat tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Gelar)){
            edtGelar.setError("Gelar tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Email)){
            edtEmail.setError("Email tidak boleh Kosong");
        }
    }


    private void requestInsertDosen() {
        edtNama = (EditText) findViewById(R.id.txtNmMhs);
//        String strNama = edtNama.getText().toString();
//
//        if(TextUtils.isEmpty(strNama)) {
//            edtNama.setError("Silahkan Mengisi Nama Dosen");
//            return;
//        }
        edtNidn = (EditText) findViewById(R.id.txtNIM);
//        String strNidn = edtNidn.getText().toString();
//
//        if(TextUtils.isEmpty(strNidn)) {
//            edtNidn.setError("Silahkan Mengisi NIDN Dosen");
//            return;
//        }
        edtAlamat = (EditText) findViewById(R.id.txtAlMhs);
        String strAlamat = edtAlamat.getText().toString();

//        if(TextUtils.isEmpty(strAlamat)) {
//            edtAlamat.setError("Silahkan Mengisi Alamat Dosen");
//            return;
//        }
        edtEmail = (EditText) findViewById(R.id.txtEmMhs);
//        String strEmail = edtEmail.getText().toString();
//
//        if(TextUtils.isEmpty(strEmail)) {
//            edtEmail.setError("Gunakan Email Fakultas");
//            return;
//        }
        edtGelar = (EditText) findViewById(R.id.txtGelarDosen);
//        String strGelar = edtGelar.getText().toString();
//
//        if(TextUtils.isEmpty(strGelar)) {
//            edtGelar.setError("Silahkan Mengisi Gelar Dosen");
//            return;
//        }
        service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
        progressDialog = ProgressDialog.show(this, null, "Tunggu Sek Tahh", true, false);

        Call<Dosen> call = service.insert_dosen(edtNama.getText().toString(), edtNidn.getText().toString(),
                edtAlamat.getText().toString(), edtEmail.getText().toString(), edtGelar.getText().toString(), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlvIYbRjkKCjZjzlX24fOM_P2_zvh4YQIAe6uNToYNXOGoaZ5lCw&s",
                "72170172");
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Data Berhasil Masuk", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CreateDosenActivity.this, RecViewDosen.class);
                startActivity(refresh);
                finish();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Error", Toast.LENGTH_SHORT);
            }
        });
    }
}
