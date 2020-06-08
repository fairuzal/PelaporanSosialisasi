package com.example.pelaporansosialisasi;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pelaporansosialisasi.model.LaporanModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.squareup.picasso.Picasso;


public class StepThreeActivity extends FormBaseActivity {
    Button buttonCamera;
    ImageView imageView;
    EditText etNotulen, etKendala, etJmlDownloader;

    static final int CHOOSE_IMAGE = 1;
    Uri imgUrl;
    ProgressBar progressBar;

    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    StorageTask mStorageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three);
        buttonCamera = findViewById(R.id.pilih);
        imageView = findViewById(R.id.output);
        etNotulen = findViewById(R.id.etNotulen);
        etKendala = findViewById(R.id.etKendala);
        etJmlDownloader = findViewById(R.id.etDownloader);
        progressBar = findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference("pelaporansosialisasi");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("form");

        injectBackView();
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
        nextBtn.setText("Simpan");
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChoose();
            }
        });
    }

    private void showFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();
            Picasso.get().load(imgUrl).into(imageView);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (mStorageTask != null && mStorageTask.isInProgress()) {
                    Toast.makeText(StepThreeActivity.this, "Data Sedang Dikirim", Toast.LENGTH_SHORT).show();
                } else {
                    upload();
                }
                break;

            case R.id.btnBack:
                finish();
                break;
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void upload() {
        if(imgUrl!=null){
        final String spelapor = getIntent().getStringExtra("pelapor");
        final String spenyelenggara = getIntent().getStringExtra("penyelenggara");
        final String sjmlpeserta = getIntent().getStringExtra("jmlpeserta");
        final String sketpeserta = getIntent().getStringExtra("ketpeserta");
        final String sspinner1 = getIntent().getStringExtra("pendamping1");

        final String sacara = getIntent().getStringExtra("acara");
        final String slokasi = getIntent().getStringExtra("lokasi");
        final String skecamatan = getIntent().getStringExtra("kecamatan");
        final String skelurahan = getIntent().getStringExtra("kelurahan");
        final String stanggal = getIntent().getStringExtra("tanggal");
        final String sjam1 = getIntent().getStringExtra("jam1");
        final String sjam2 = getIntent().getStringExtra("jam2");

        final String snotulen = etNotulen.getText().toString();
        final String skendala = etKendala.getText().toString();
        final String sjmldownloader = etJmlDownloader.getText().toString();

        final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUrl));
        mStorageTask = fileReference.putFile(imgUrl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(0);
                            }
                        }, 500);
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String id = mDatabaseRef.push().getKey();
                                LaporanModel laporanModel = new LaporanModel(id, spelapor, sspinner1, spenyelenggara, sjmlpeserta, sketpeserta, sacara,
                                        slokasi, skecamatan, skelurahan, stanggal, sjam1, sjam2, snotulen, skendala, sjmldownloader, uri.toString());
                                mDatabaseRef.push().setValue(laporanModel);
                            }
                        });
                        Toast.makeText(StepThreeActivity.this, "Data Berhasi Disimpan", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StepThreeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressBar.setProgress((int) progress);
                    }
                });

    }else{
            Toast.makeText(StepThreeActivity.this,"Foto tidak ada", Toast.LENGTH_SHORT).show();
        }
    }


}
