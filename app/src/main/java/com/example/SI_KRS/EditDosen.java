package com.example.SI_KRS;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SI_KRS.Model.Dosen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDosen extends AppCompatActivity {
    EditText eIdDsn, eNamaDsn, eNIDN, eAlamatDsn, eEmailDsn, eGelarDsn;
    Intent mIntent = getIntent();
    DataDosenService service;
    ProgressDialog progressDialog;
    String Nama, NIDN, Alamat, Email, Gelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dosen);
        this.setTitle("SI KRS - Hai Admin");

        Button btnEdit = (Button)findViewById(R.id.btnEdosen);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditDosen.this);

                builder.setMessage("Apakah yakin untuk menyimpan?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EditDosen.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestUpdateDosen();
                                ValidasiData();
                            }
                        });

                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }

    private void ValidasiData() {
        Nama = eNamaDsn.getText().toString();
        NIDN  = eNIDN.getText().toString();
        Alamat = eAlamatDsn.getText().toString();
        Gelar = eGelarDsn.getText().toString();
        Email = eEmailDsn.getText().toString();

        if(TextUtils.isEmpty(Nama)){
            eNamaDsn.setError("Nama tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(NIDN)){
            eNIDN.setError("NIDN tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Alamat)){
            eAlamatDsn.setError("Alamat tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Gelar)){
            eGelarDsn.setError("Gelar tidak boleh Kosong");
        }
        if(TextUtils.isEmpty(Email)){
            eEmailDsn.setError("Email tidak boleh Kosong");
        }
    }

    private void requestUpdateDosen(){
        eIdDsn = (EditText)findViewById(R.id.eIdDsn);
//        String strId = eIdDsn.getText().toString();
//
//        if(TextUtils.isEmpty(strId)) {
//            eIdDsn.setError("Silahkan Mengisi ID Dosen");
//            return;
//        }
        eNamaDsn = (EditText)findViewById(R.id.eNamaDsn);
//        String strNama = eNamaDsn.getText().toString();
//
//        if(TextUtils.isEmpty(strNama)) {
//            eNamaDsn.setError("Silahkan Mengisi Nama Dosen");
//            return;
//        }
        eNIDN = (EditText)findViewById(R.id.eNIDN);
//        String strNIDN = eNIDN.getText().toString();
//
//        if(TextUtils.isEmpty(strNIDN)) {
//            eNIDN.setError("Silahkan Mengisi NIDN Dosen");
//            return;
//        }
        eAlamatDsn = (EditText)findViewById(R.id.eAlamatDsn);
//        String strAlamat = eAlamatDsn.getText().toString();
//
//        if(TextUtils.isEmpty(strAlamat)) {
//            eAlamatDsn.setError("Silahkan Mengisi Alamat Dosen");
//            return;
//        }
        eEmailDsn = (EditText)findViewById(R.id.eEmailDsn);
//        String strEmail = eEmailDsn.getText().toString();
//
//        if(TextUtils.isEmpty(strEmail)) {
//            eEmailDsn.setError("Gunakan Email Fakultas");
//            return;
//        }
        eGelarDsn = (EditText)findViewById(R.id.eGelarDsn);
//        String strGelar = eGelarDsn.getText().toString();
//
//        if(TextUtils.isEmpty(strGelar)) {
//            eGelarDsn.setError("Silahkan Mengisi Gelar Dosen");
//            return;
//        }

        eIdDsn.setText(mIntent.getStringExtra("id"));
        eIdDsn.setTag(eIdDsn.getKeyListener());
        eIdDsn.setKeyListener(null);
        eNamaDsn.setText(mIntent.getStringExtra("nama"));
        eNIDN.setText(mIntent.getStringExtra("nidn"));
        eAlamatDsn.setText(mIntent.getStringExtra("alamat"));
        eEmailDsn.setText(mIntent.getStringExtra("email"));
        eGelarDsn.setText(mIntent.getStringExtra("gelar"));

        service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
        progressDialog =  ProgressDialog.show(this, null, "Tunggu Lur...", true, false);

        Call<Dosen> call =  service.update_dosen(eIdDsn.getText().toString(),eNamaDsn.getText().toString(),eNIDN.getText().toString(),
                eAlamatDsn.getText().toString(),eEmailDsn.getText().toString(),eGelarDsn.getText().toString(),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlvIYbRjkKCjZjzlX24fOM_P2_zvh4YQIAe6uNToYNXOGoaZ5lCw&s",
                "72170172");
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                progressDialog.dismiss();
                Toast.makeText(EditDosen.this,"Berhasil Edit",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(EditDosen.this, RecViewDosen.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditDosen.this,"Error",Toast.LENGTH_SHORT);
            }
        });
    }
}
