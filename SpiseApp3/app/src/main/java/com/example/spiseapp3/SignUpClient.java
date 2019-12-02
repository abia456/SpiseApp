package com.example.spiseapp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class SignUpClient extends AppCompatActivity {

    Button SignupClientbtn;
    Spinner sp;
    EditText FName;
    EditText SName;
    EditText Email;
    EditText RestAddress;
    EditText Phone;
    EditText Password;
    EditText resName;
    EditText city;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    private RadioGroup radioGroup;
    RadioButton filter;
    private FirebaseAuth firebaseAuth;
    DatabaseReference m2;
    String uid;

    Button add;
    EditText file;
    ImageView mImaheView;
    static final int  IMAGE_REQUEST=1;

    Uri mImageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);
        FName= findViewById(R.id.FNameClient);
        SName= findViewById(R.id.SNameClient);
        Email= findViewById(R.id.Email_Client);
        RestAddress = findViewById(R.id.RestaurantPostalAddress);
        Phone = findViewById(R.id.ClientPhoneNumber);
       Password = findViewById(R.id.ClientPassword);
       resName = findViewById(R.id.RestaurantName);
        city = findViewById(R.id.RestaurantCity);
        sp= findViewById(R.id.categorySignUpClient);
        radioGroup= findViewById(R.id.Price);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Client");
        databaseReference=FirebaseDatabase.getInstance().getReference("User");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");
        m2=FirebaseDatabase.getInstance().getReference("Upload");

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        file=findViewById(R.id.image);
        add=findViewById(R.id.SelectFile);
        mImaheView=findViewById(R.id.image_view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        SignupClientbtn   = findViewById(R.id.SignupClientbtn);
        SignupClientbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){


                final String FnameString= FName.getText().toString();
                final String SnameString= SName.getText().toString();
                final String EmailString= Email.getText().toString();
                final String RestAddressString= RestAddress.getText().toString();
                final String PhoneString= Phone.getText().toString();
                String PasswordString= Password.getText().toString();
                final String resNameString= resName.getText().toString();
                final String cityString= city.getText().toString();
                int pos=sp.getSelectedItemPosition();

                final String spString= (String) sp.getSelectedItem();
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                filter = (RadioButton) findViewById(selectedId);
                final String filterString= (String) filter.getText();

                if (FnameString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your first name", Toast.LENGTH_SHORT).show();
                }

                else if (SnameString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your second name", Toast.LENGTH_SHORT).show();
                }

                else if (EmailString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Email", Toast.LENGTH_SHORT).show();
                }

                else if (RestAddressString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Address", Toast.LENGTH_SHORT).show();
                }

                else if (PhoneString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Phone", Toast.LENGTH_SHORT).show();
                }

                else if (PasswordString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Password", Toast.LENGTH_SHORT).show();
                }

                else if (resNameString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Restaurant Name", Toast.LENGTH_SHORT).show();
                }

                else if (cityString.matches(""))
                {
                    Toast.makeText(SignUpClient.this, "You did not enter your Restaurant city", Toast.LENGTH_SHORT).show();
                }


            else {
                    firebaseAuth.createUserWithEmailAndPassword(EmailString,PasswordString)
                            .addOnCompleteListener(SignUpClient.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Client client=new Client(FnameString, SnameString, EmailString, RestAddressString,PhoneString,resNameString, cityString,spString, filterString, "Rs 200", "120 min", "5");


                                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        uploadFile();
                                        User u=new User(false);
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(u);
                                        FirebaseDatabase.getInstance().getReference("Client")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent = new Intent(SignUpClient.this,RestaurantOrderRequests.class);
                                                startActivity(intent);

                                            }
                                        });

                                    } else {

                                        Toast.makeText(SignUpClient.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }
            }

        });



    }

    private void uploadFile() {
        if(mImageuri!=null)
        {
            StorageReference filereference=storageReference.child("/"+uid+"/" + System.currentTimeMillis()
            + "." +getFileExtension(mImageuri));
            filereference.putFile(mImageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(SignUpClient.this, "Image upload successful!!", Toast.LENGTH_SHORT).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            Upload upload=new Upload(file.getText().toString().trim()
                            ,downloadUrl.toString());

                            FirebaseDatabase.getInstance().getReference("Upload")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpClient.this, "Error in uploading picture", Toast.LENGTH_SHORT).show();


                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress=(100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                        }
                    });
        }

    }

    private Object getFileExtension(Uri mImageuri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mImageuri));
    }

    private void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
       {
           mImageuri=data.getData();
           mImaheView.setImageURI(mImageuri);
       }
    }

}
