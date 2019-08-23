package com.k17.ping;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by bazid on 21-01-2018.
 */

public class NewPost extends Fragment {


    Button btnPost;
    String userLoggedIn,Message,encodedString,imgPath,fileName;
    EditText txtmsg;
    boolean valid;
    ImageView ivSelected;
    Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String temp="";
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            if(selectedImage.EMPTY.equals(null)){
                temp="noselect";
            }
            else {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                //Toast.makeText(this,""+filePathColumn,Toast.LENGTH_LONG).show();
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);

                cursor.close();

                ivSelected.setImageBitmap(BitmapFactory.decodeFile(imgPath));

                String fileNameSegments[] = imgPath.split("/");
                fileName = fileNameSegments[fileNameSegments.length - 1];
                temp="select";
            }
        }
        if(temp.equals("select")) {
            BitmapFactory.Options options = null;
            options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            bitmap = BitmapFactory.decodeFile(imgPath, options);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Must compress the Image to reduce image size to make upload easy
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
            byte[] byte_arr = stream.toByteArray();
            // Encode Image to String
            encodedString = Base64.encodeToString(byte_arr, 0);

            Toast.makeText(getContext(), "Image Loaded", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_new_post, container, false);
        ivSelected=(ImageView)rootView.findViewById(R.id.ivImage) ;
        txtmsg=(EditText)rootView.findViewById(R.id.txtMessage);
        btnPost=(Button)rootView.findViewById(R.id.btnPost);

        Intent i=getActivity().getIntent();
        userLoggedIn=i.getStringExtra("loggedInUser");
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid=true;
                Message=txtmsg.getText().toString().trim();
                if(Message.isEmpty()){
                    valid=false;
                    txtmsg.setError("Message cannot be Empty");
                }
                if (ivSelected.getDrawable()==null){
                    valid=false;
                    Toast.makeText(getContext(),"Please upload an image",Toast.LENGTH_SHORT).show();
                }
                if(valid) {
                    new NewPostActivity(getContext()).execute(Message, userLoggedIn,encodedString,fileName);
                }

            }
        });

        ivSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }

        });

        return rootView;
    }

}
