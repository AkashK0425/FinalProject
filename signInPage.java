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

public class signInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        final AutoCompleteTextView signInUinBox = (AutoCompleteTextView) findViewById(R.id.signInUinBox);

        final AutoCompleteTextView signInPasswordBox = (AutoCompleteTextView) findViewById(R.id.signInPasswordBox);

        Button signInSubmitBtn = (Button) findViewById(R.id.signInSubmitBtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user = database.getReference("User");

        signInSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(signInUinBox.getText().toString()).exists()) {
                            User newUser = dataSnapshot.child(signInUinBox.getText().toString()).getValue(User.class);
                            if (newUser.getPassword().equals(signInPasswordBox.getText().toString())) {
                                Toast.makeText(getApplicationContext(), "Waiting for process!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Succeed!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "UIN or Password isn't correct!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not founded!", Toast.LENGTH_SHORT).show();
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
