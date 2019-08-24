package todriver.sendrequest.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import todriver.sendrequest.Library.Clasess.SharePref;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.MasterPermission;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText mDisplayName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mCreateBtn;


    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //Toolbar Set


        progressBar = findViewById(R.id.progress_bar);

        mRegProgress = new ProgressDialog(this);



        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        // Android Fields

        mDisplayName =  findViewById(R.id.login_name);
        mEmail =  findViewById(R.id.login_email);


        mPassword =  findViewById(R.id.login_pass);
        mCreateBtn =  findViewById(R.id.login_btn);


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getText().toString();
                String email = mEmail.getText().toString();


                String password = mPassword.getText().toString();

                if (password.length() < 8){

                    Toast.makeText(RegisterActivity.this, "كلمة السر يجب ان لا تقل عن 8 احرف وارقام", Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email)  || !TextUtils.isEmpty(password)){




                    MasterPermission masterPermission = new MasterPermission(RegisterActivity.this);

                    if (!masterPermission.isLocation()){

                        masterPermission.addPermissionLocation(new MasterPermission.EventLocationListener() {
                            @Override
                            public void on_Location_Ok() {

                            }
                        });

                        return;
                    }



                    mCreateBtn.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                    register_user(display_name, email, password);

                }



            }
        });


    }

    private void register_user(final String display_name, String email, String password) {

        F.firebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mCreateBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    String device_token = FirebaseInstanceId.getInstance().getToken();


                    setData(task,display_name);


                } else {

                    mCreateBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(RegisterActivity.this, task.getException().getMessage()+"", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void setData(final Task<AuthResult> task,String name) {


        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("status", "Hi there I'm using this app.");
        userMap.put("image", "default");
        userMap.put("name", name);
        userMap.put("thumb_image", "default");
        userMap.put("Token",F.getDaviceToken());
        userMap.put("uid", F.Uid());
        userMap.put("phone", "phone");
        userMap.put("email", task.getResult().getUser().getEmail());
        userMap.put("serial", Build.SERIAL);


        F.Users().child(F.Uid()).setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                F.reference().child("costumers").child(F.Uid()).setValue(F.Uid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        if(task.isSuccessful()){
                            mCreateBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);

                            SharePref sharePref = new SharePref(RegisterActivity.this);
                            sharePref.set("UID",F.Uid());

                            Intent mainIntent = new Intent(RegisterActivity.this, MapsActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                        }else {
                            mCreateBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });





    }


}
