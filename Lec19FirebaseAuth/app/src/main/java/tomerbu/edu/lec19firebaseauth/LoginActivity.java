package tomerbu.edu.lec19firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements OnSuccessListener<AuthResult>, OnFailureListener {

    //properties:
    private EditText etPassword;
    private EditText etEmail;
    //load this variable only when needed.
    //lazy loading - Design pattern.
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void login(View view) {

        String email = getEmail();
        String password = getPassword();

        if (!isEmailValid() | !isPasswordValid())
            return;

        //async
        showProgress(true);
        //stop showing

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(this,this)
                .addOnFailureListener(this, this);


    }

  //how do you eliminate copy paste?

    //registerORLogin(String command){
    //if command.equals("Login")...
    //}

    public void register(View view) {
        String email = getEmail();
        String password = getPassword();

        if (!isEmailValid() | !isPasswordValid())
            return;

        //async
        showProgress(true);
        //stop showing

        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(this,this)
                .addOnFailureListener(this, this);
    }

    private void showProgress(boolean show) {
        //put a progressbar in the activity_login.xml -> setVisibility(...)
        //lazy loading:
       if (dialog == null){
           dialog = new ProgressDialog(this);
           dialog.setTitle("Please Wait");
           dialog.setMessage("Connecting to server");
       }

       if (show)
           dialog.show();
       else
           dialog.dismiss();
    }

    //register or login was successful
    @Override
    public void onSuccess(AuthResult authResult) {
        //success
        showProgress(false);
        //todo: last seen

        //FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()
        //FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().



        Intent intent = new Intent(this, MainActivity.class);

        //גם אם היו לנו 10 activities בBack stack יהרוג את כולם
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //finish();
    }

    //register or login failed
    @Override
    public void onFailure(@NonNull Exception e) {
        //email not valid
        showProgress(false);
        //can't connect to the internet

        //TextView -> Error Message
        //Dialog for error message. (Animations + Kotlin)

        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    //הוספת מאזינים - להוסיף progress להוסיף דיאלוג עבור שגיאות - לראות UI מוכן.
    // לשמור מידע על משתמש כגון שם פרטי - טופס.
    // ללמוד לעבוד עם דטה-בייס בענן.
    //לא נרצה לגשת לשרת אם אין שם משתמש או סיסמא
    private boolean isEmailValid() {
        //Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()
        //בדיקת תקינות של טקסט - Regex
        //בדומה לשפת SQL שעוזרת לעשות שאילתות על טבלאות - יש שפת Regex
        boolean isValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();

        if (!isValid)
            etEmail.setError("Email must be valid"); // add an error message
        else
            etEmail.setError(null);//remove the error message if it's valid

        return isValid;
    }

    //must be at least 6 chars.
    private boolean isPasswordValid() {
        boolean isValid = getPassword().length() >= 6;

        if (!isValid)
            etPassword.setError("Password must contain at least 6 characters.");
        else
            etPassword.setError(null);

        return isValid;
    }


    private String getPassword() {
        return etPassword.getText().toString();
    }

    private String getEmail() {
        return etEmail.getText().toString();
    }

}