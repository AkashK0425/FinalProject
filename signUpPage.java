package chenjundongted.com.finalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import chenjundongted.com.finalproject.Model.User;

public class signUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user = database.getReference("User");

        final AutoCompleteTextView signUpUinBox = (AutoCompleteTextView) findViewById(R.id.signUpUinBox);
        final AutoCompleteTextView signUpNameBox = (AutoCompleteTextView) findViewById(R.id.signUpNameBox);
        final AutoCompleteTextView signUpEmailBox = (AutoCompleteTextView) findViewById(R.id.signUpEmailBox);
        final AutoCompleteTextView signUpPassword = (AutoCompleteTextView) findViewById(R.id.signUpPasswordBox);
        final AutoCompleteTextView signUpRPasswordBox = (AutoCompleteTextView) findViewById(R.id.signUpRPasswordBox);

        Button signUpSubmitBtn = (Button) findViewById(R.id.signUpSubmitBtn);

        signUpSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!signUpPassword.getText().toString().equals(signUpRPasswordBox.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Two passwords are not equal!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dataSnapshot.child(signUpUinBox.getText().toString()).exists()) {
                                Toast.makeText(getApplicationContext(), "Account already existed!", Toast.LENGTH_SHORT).show();
                            } else {
                                User user2 = new User(signUpEmailBox.getText().toString(), signUpNameBox.getText().toString(), signUpPassword.getText().toString());
                                user.child(signUpUinBox.getText().toString()).setValue(user2);
                                Toast.makeText(getApplicationContext(), "Sign up successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
