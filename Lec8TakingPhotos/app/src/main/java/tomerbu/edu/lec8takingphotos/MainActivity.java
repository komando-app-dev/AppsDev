package tomerbu.edu.lec8takingphotos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private ImageView ivPhoto;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPhoto = findViewById(R.id.ivPhoto);
        mLayout = findViewById(R.id.mLayout);

        findViewById(R.id.btnTakePhoto).setOnClickListener(v -> takePhoto());

        findViewById(R.id.btnShare).setOnClickListener(v -> shareImage());
    }

    void shareImage() {
        //look at my score:
        //share image


        //Builder pattern for share intents:
        File localFile = new File(getFilesDir(), "123.jpg");
        //Content provider:
        Uri shareUri = FileProvider.getUriForFile(this, "tomerbu.edu.lec8takingphotos.fileprovider", localFile);

        Intent intent =
                ShareCompat.IntentBuilder
                        .from(this).setStream(shareUri)
                        .setText("Look It!😀√💖")
                        .setSubject("Sharing This")
                        .setEmailTo(new String[]{"tomer.bu@gmail.com"})
                        .setType("*/*")//content-type
                        .getIntent();

        //Mime Type = file type (jpg,
        //text and images

        startActivity(intent);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//No URi - Common intents
        //new File - j.jpg

        //good file name:
        File f = createImage();


        //FileUri:file://
        //export a path for sharing:
        Uri uri = FileProvider.getUriForFile(this, "tomerbu.edu.lec8takingphotos.fileprovider", f);

        System.out.println("Uri: " + uri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //data = null

        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);//ctrl+alt+c
    }

    //File currentFile;

    //generate a new File Path
    private File createImage() {
        //internal files
        //internal cache
        //external card
        //external cache

        //context.getFilesDir()
        //File folder = getFilesDir();
        //data/data/
        String uuid = UUID.randomUUID().toString();
        //Time stamp
        //sdflkfsdljwqerjkh4gjllkxcvmwl3ktrnlk

        File f = new File(getFilesDir(), "123.jpg");
        //data/data/tomerbu.edu.lec8takingphotos/1.jpg
        System.out.println(f.getAbsolutePath());//data/data/
        return f;
    }

    //onActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get the real image:
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {


            File f = new File(getFilesDir(), "123.jpg");//could call createImage()

            //Bitmap from file:

            //BitmapFactory Bitmap Utils
            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

            ivPhoto.setImageBitmap(bitmap);
        }

        //debug, learn:
        //Set<String> key = data.getExtras().keySet();
        //Object obj = data.getExtras().get("data");//object.class.simpleName

        //thumbnail:
        if (data == null || !data.hasExtra("data")) {
            return;
        }
        Bitmap bitmap = data.getParcelableExtra("data");
        ivPhoto.setImageBitmap(bitmap);
        //taking photos android:
    }
}