package com.example.appfutbol.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appfutbol.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FragmentUploadImage extends Fragment {
    private ImageView imageView;
    private Button btnSelet, btnLoad;
    private Uri uri;
    private StorageReference storageReference;
    private ProgressDialog dialog;
    public FragmentUploadImage() {
        // Required empty public constructor
    }

    public static FragmentUploadImage newInstance(String param1, String param2) {
        FragmentUploadImage fragment = new FragmentUploadImage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upload_image, container, false);

        imageView = view.findViewById(R.id.firebaseImagen);
        btnLoad = view.findViewById(R.id.uploadImagenbtn);
        btnSelet = view.findViewById(R.id.selectImagenbtn);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,100);
            }
        });

        btnSelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(getContext());
                dialog.setTitle(" SUBIDO IMAGEN");
                dialog.show();


                SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
                Date now  = new Date();
                String fileName = format.format(now);


             storageReference  = FirebaseStorage.getInstance().getReference("image/"+fileName);
             storageReference.putFile(uri)
                     .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             imageView.setImageURI(null);
                             Toast.makeText(getContext(),"SUBIDO",Toast.LENGTH_LONG).show();
                             if (dialog.isShowing())
                                 dialog.dismiss();

                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             if (dialog.isShowing())
                                 dialog.dismiss();
                             Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
                         }
                     });
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==100 && data != null && data.getData() != null){
            uri = data.getData();
            imageView.setImageURI(uri);

        }
    }
}