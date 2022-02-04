package ukr.net.jaroshov.calculationmodulo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editN;
    EditText editNZK;
    EditText editPR;
    Button getResult;

    TextView result;
    TextView result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editN = findViewById(R.id.editN);
        editNZK = findViewById(R.id.editNZK);
        editPR = findViewById(R.id.editPR);

        getResult = findViewById(R.id.buttonResult);
        getResult.setOnClickListener(view -> onMyButtonClick());

        result = findViewById(R.id.textResult);
        result2 = findViewById(R.id.textResult2);
    }

    public void onMyButtonClick() {
        String mN = editN.getText().toString();
        String mNZK = editNZK.getText().toString();
        String mPR = editPR.getText().toString();
        int N = Integer.parseInt(mN);

        int res = ((((Integer.parseInt(mNZK) + Integer.parseInt(mPR)) - 2000) % N) + 1);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Ваш номер задания " + res + "!", Toast.LENGTH_LONG);
//        toast.setGravity(17, 0, 0);
        toast.show();

        int m = Integer.parseInt(mNZK) + Integer.parseInt(mPR) - 2000;
        result.setText("Z = mod "+mN+" ("+mNZK+" + "+mPR+" – 2000) + 1 = mod "+mN + " ("+ m +") + 1 = " + (m % N) + " + 1 = " + res);
        result2.setText("Z = " + res);
    }
}