import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner unitSpinner;
    private Button convertButton;
    private TextView resultValue;

    private String selectedUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.input_value);
        unitSpinner = findViewById(R.id.unit_spinner);
        convertButton = findViewById(R.id.convert_button);
        resultValue = findViewById(R.id.result_value);

        // Set up the spinner with conversion options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();
                if (!input.isEmpty()) {
                    double value = Double.parseDouble(input);
                    double result = convertUnits(value, selectedUnit);
                    resultValue.setText(String.valueOf(result));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double convertUnits(double value, String unit) {
        switch (unit) {
            case "Centimeters to Meters":
                return value / 100;
            case "Meters to Centimeters":
                return value * 100;
            case "Grams to Kilograms":
                return value / 1000;
            case "Kilograms to Grams":
                return value * 1000;
            default:
                return value;
        }
    }
}
