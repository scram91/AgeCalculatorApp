package edu.psu.sweng888.agecalculatorapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private EditText mInputFirstName;
    private EditText mInputLastName;
    private EditText mInputDateOfBirth;
    private Button mCalculateAgeButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputFirstName = findViewById(R.id.first_name_field);
        mInputLastName = findViewById(R.id.last_name_field);
        mInputDateOfBirth = findViewById(R.id.birthday_field);
        mCalculateAgeButton = findViewById(R.id.calculate_age);

        mCalculateAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mInputFirstName.getText().toString();
                String lastName = mInputLastName.getText().toString();
                String dateOfBirthString = mInputDateOfBirth.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                try {
                    Calendar inputDate = Calendar.getInstance();
                    inputDate.setTime(Objects.requireNonNull(dateFormat.parse(dateOfBirthString)));

                    int age = getUserAge(inputDate);
                    CharSequence result_toast = String.valueOf(age);
                    Toast.makeText(MainActivity.this,
                            result_toast,
                            Toast.LENGTH_LONG).show();
                } catch (ParseException e) {
                    Toast.makeText(MainActivity.this,
                            R.string.invalid_date_toast,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int getUserAge(Calendar dateOfBirth) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int inputYear = dateOfBirth.get(Calendar.YEAR);
        Log.d(TAG, "Current Year value: " + currentYear);
        Log.d(TAG, "Year value: " + inputYear);

        if(inputYear > currentYear){
            Toast.makeText(MainActivity.this,
                    R.string.invalid_date_toast,
                    Toast.LENGTH_LONG).show();
        }
        int age = currentYear - inputYear;

        int currentMonth = cal.get(Calendar.MONTH);
        int inputMonth = dateOfBirth.get(Calendar.MONTH);
        Log.d(TAG, "Current Month value: " + (currentMonth + 1));
        Log.d(TAG, "Month value: " + (inputMonth + 1));
        if(inputMonth > currentMonth){
            age--;
        }else if(inputMonth == currentMonth){
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            int inputDay = dateOfBirth.get(Calendar.DAY_OF_MONTH);
            Log.d(TAG, "Current Day value: " + currentDay);
            Log.d(TAG, "Day value: " + inputDay);
            if (inputDay > currentDay){
                age--;
            }
        }
        if(inputMonth > 11){
            Toast.makeText(MainActivity.this,
                    R.string.invalid_date_toast,
                    Toast.LENGTH_LONG).show();
        }
        return age;
    }
}