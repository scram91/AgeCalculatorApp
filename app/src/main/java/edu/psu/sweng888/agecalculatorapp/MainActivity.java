package edu.psu.sweng888.agecalculatorapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText mInputFirstName;
    private EditText mInputLastName;
    private EditText mInputDateOfBirth;
    private Button mCalculateAgeButton;

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputFirstName = findViewById(R.id.first_name_field);
        mInputLastName = findViewById(R.id.last_name_field);
        mInputDateOfBirth = findViewById(R.id.birthday_field);
        mCalculateAgeButton = findViewById(R.id.calculate_age);
        mResultTextView = findViewById(R.id.result_text);

        mCalculateAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mInputFirstName.getText().toString();
                String lastName = mInputLastName.getText().toString();
                String dateOfBirthString = mInputDateOfBirth.getText().toString();

                int age = 0;
                try {
                    age = getUserAge(dateOfBirthString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                mResultTextView.setText(age);
                mResultTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    private int getUserAge(String dateOfBirthString) throws ParseException {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date inputDate = dateFormat.parse(dateOfBirthString);

        Format year = new SimpleDateFormat("yyyy", Locale.US);

        int inputYear = Integer.parseInt(year.format(new Date()));

        return currentYear - inputYear;
    }
}