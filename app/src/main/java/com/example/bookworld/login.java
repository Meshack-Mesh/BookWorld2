package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpTextView, forgotPasswordTextView, Adminlogin;
    private CheckBox showPasswordCheckBox;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("users");

        emailEditText = findViewById(R.id.TextEmail);
        passwordEditText = findViewById(R.id.Password);
        loginButton = findViewById(R.id.buttonLogin);
        signUpTextView = findViewById(R.id.CreateAccount);
        Adminlogin = findViewById(R.id.AdminLogin);
        forgotPasswordTextView = findViewById(R.id.ForgotPassword);
        showPasswordCheckBox = findViewById(R.id.checkBoxShowPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    signInWithEmailPassword();
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, sign_in.class);
                startActivity(intent);
            }
        });

        Adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, adminLogin.class);
                startActivity(intent);
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Password_recovery.class);
                startActivity(intent);
            }
        });

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show password
                    passwordEditText.setTransformationMethod(null);
                } else {
                    // Hide password
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                }
                // Move cursor to the end of the text
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email address is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            isValid = false;
        }

        return isValid;
    }

    private void signInWithEmailPassword() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(login.this, "Login successful.", Toast.LENGTH_SHORT).show();
                            // Proceed to fetch user data from Firebase Realtime Database
                            fetchUserDataFromDatabase();
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() != null && task.getException() instanceof FirebaseAuthException) {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                handleAuthError(errorCode);
                            } else {
                                Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void handleAuthError(String errorCode) {
        switch (errorCode) {
            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(login.this, "User does not exist. Please sign up.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, sign_in.class);
                startActivity(intent);
                break;
            case "ERROR_WRONG_PASSWORD":
                passwordEditText.setError("Wrong password");
                break;
            default:
                Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void fetchUserDataFromDatabase() {
        String userId = mAuth.getCurrentUser().getUid();
        dbRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data retrieved successfully
                    // You can access user data using dataSnapshot.getValue() method
                    // For example:
                    // String username = dataSnapshot.child("username").getValue(String.class);
                    // Proceed to next activity (home activity)
                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                } else {
                    // User data not found
                    Toast.makeText(login.this, "User data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error fetching user data
                Toast.makeText(login.this, "Failed to fetch user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
