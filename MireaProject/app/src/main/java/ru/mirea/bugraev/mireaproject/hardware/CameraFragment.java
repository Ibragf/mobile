package ru.mirea.bugraev.mireaproject.hardware;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.mirea.bugraev.mireaproject.MainActivity;
import ru.mirea.bugraev.mireaproject.R;

public class CameraFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    private static final int CAMERA_REQUEST = 10;
    File photoFile;
    Button camera;
    ImageView imageView;
    Uri imageUri;
    boolean isWork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_camera,null);

        imageView=v.findViewById(R.id.cameraImage);
        camera=v.findViewById(R.id.cameraButton);

        int cameraPermissionStatus= ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA);
        int storagePermissionStatus=ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(cameraPermissionStatus==PackageManager.PERMISSION_GRANTED && storagePermissionStatus==PackageManager.PERMISSION_GRANTED)
        {
            isWork=true;
        }
        else
        {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION_CAMERA);
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("SD-info", "isWork: "+isWork);
                Log.d("SD-info", "camera: "+cameraIntent.resolveActivity(getActivity().getPackageManager()));
                if(cameraIntent.resolveActivity(getActivity().getPackageManager())!=null && isWork==true)
                {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                        Log.d("SD-info", "sd-card success");
                        try {
                            photoFile=createImageFile();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    imageUri=Uri.fromFile(photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    getActivity().startActivityForResult(cameraIntent,CAMERA_REQUEST);
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
            imageView.setImageURI(imageUri);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_.jpg";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(storageDirectory,imageFileName);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_PERMISSION_CAMERA)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                isWork=true;
            }
            else
            {
                isWork=false;
            }
        }
    }
}