import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionText, resultText;
    private RadioGroup answersGroup;
    private RadioButton answer1, answer2, answer3, answer4;
    private Button submitButton, nextButton;

    private String[] questions = {
            "What is the capital of France?",
            "What is the largest planet in our solar system?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the boiling point of water?"
    };

    private String[][] answers = {
            {"Paris", "London", "Rome", "Berlin"},
            {"Earth", "Jupiter", "Saturn", "Mars"},
            {"William Shakespeare", "Charles Dickens", "J.K. Rowling", "George Orwell"},
            {"90°C", "100°C", "80°C", "110°C"}
    };

    private String[] correctAnswers = {"Paris", "Jupiter", "William Shakespeare", "100°C"};
    private int currentQuestion = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.question_text);
        resultText = findViewById(R.id.result_text);
        answersGroup = findViewById(R.id.answers_group);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        submitButton = findViewById(R.id.submit_button);
        nextButton = findViewById(R.id.next_button);

        loadQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answersGroup.get
