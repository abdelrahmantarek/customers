package todriver.sendrequest.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import todriver.sendrequest.Library.Clasess.SharePref;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.MasterPermission;
import todriver.sendrequest.R;
import todriver.sendrequest.Tools;
import todriver.sendrequest.maps.MapsActivity;

public class LoginActivity extends AppCompatActivity {



    private EditText mLoginEmail;
    private EditText mLoginPassword;

    private Button mLogin_btn;


    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;
	
	private ProgressBar progress_bar;
    private View parent_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
  parent_view = findViewById(android.R.id.content);



       

  progress_bar = findViewById(R.id.progress_bar);
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

         View  parent_view = findViewById(android.R.id.content);



         mLogin_btn = findViewById(R.id.login_btn);
         mLoginEmail = findViewById(R.id.login_email);
         mLoginPassword = findViewById(R.id.login_pass);

        Tools.setSystemBarColor(this, R.color.blue_grey_900);


        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mLoginEmail.getText().toString();
                String password = mLoginPassword.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){




                    MasterPermission masterPermission = new MasterPermission(LoginActivity.this);

                    if (!masterPermission.isLocation()){

                        masterPermission.addPermissionLocation(new MasterPermission.EventLocationListener() {
                            @Override
                            public void on_Location_Ok() {

                            }
                        });

                        return;
                    }



                    mLogin_btn.setVisibility(View.GONE);
                    progress_bar.setVisibility(View.VISIBLE);

                    loginUser(email, password);

                }

            }
        });


    }



    private void loginUser(String email, String password) {

        F.firebaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    F.Users().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.hasChild(F.Uid())){

                                if (dataSnapshot.child(F.Uid()).hasChild("name")){


                                    F.Users().child(F.Uid()).child("Token").setValue(F.getDaviceToken()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {


                                            SharePref sharePref = new SharePref(LoginActivity.this);
                                            sharePref.set("UID",F.Uid());


                                            Intent mainIntent = new Intent(LoginActivity.this, MapsActivity.class);
                                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(mainIntent);
                                            finish();


                                        }
                                    });

                                }else {

                                    Toast.makeText(LoginActivity.this, "هذا البريد غير مسجل لدينا", Toast.LENGTH_SHORT).show();
                                    mLogin_btn.setVisibility(View.VISIBLE);
                                    progress_bar.setVisibility(View.GONE);
                                    FirebaseAuth.getInstance().signOut();
                                }



                            }else {

                    mLogin_btn.setVisibility(View.VISIBLE);
                    progress_bar.setVisibility(View.GONE);
                    FirebaseAuth.getInstance().signOut();
                     Toast.makeText(LoginActivity.this, "هذا البريد غير مسجل لدينا", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });






                } else {

            
                    mLogin_btn.setVisibility(View.VISIBLE);
                    progress_bar.setVisibility(View.GONE);
                    String task_result = task.getException().getMessage().toString();

                    Toast.makeText(LoginActivity.this, "Error : " + task_result, Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}
