package ukr.net.jaroshov.calculationmodulo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editN;
    EditText editNZK;
    EditText editPR;
    Button getResult;

    TextView result;

    ImageButton btnCopy;

    ClipboardManager clipboardManager;
    ClipData clipData;

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

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(view -> {
            String text = result.getText().toString();
            clipData = ClipData.newPlainText("text", text);
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(getApplicationContext(), getString(R.string.text_copied), Toast.LENGTH_SHORT).show();
        });
    }

    public void onMyButtonClick() {
        String mN = editN.getText().toString();
        String mNZK = editNZK.getText().toString();
        String mPR = editPR.getText().toString();

        if (mN.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.text_error)
                    + " " + getString(R.string.n), Toast.LENGTH_SHORT).show();
            return;
        } else if (mNZK.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.text_error)
                    + " " + getString(R.string.nzk), Toast.LENGTH_SHORT).show();
            return;
        } else if (mPR.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.text_error)
                    + " " + getString(R.string.pr), Toast.LENGTH_SHORT).show();
            return;
        }

        int N = Integer.parseInt(mN);

        int res = ((((Integer.parseInt(mNZK) + Integer.parseInt(mPR)) - 2000) % N) + 1);

        Toast toast = Toast.makeText(getApplicationContext(),
                getString(R.string.result_toast) + res + "!", Toast.LENGTH_LONG);
        toast.show();

        int m = Integer.parseInt(mNZK) + Integer.parseInt(mPR) - 2000;

        String text = "Z = mod " + mN + " (" + mNZK + " + " + mPR + " – 2000) + 1 = mod " + mN + " (" + m + ") + 1 = " + (m % N) + " + 1 = " + res;
        text = text + "\n";
        text = text + "\n";
        text = text + "Z = " + res;
        result.setText(text);

//        result.setText("Z = mod " + mN + " (" + mNZK + " + " + mPR + " – 2000) + 1 = mod " + mN + " (" + m + ") + 1 = " + (m % N) + " + 1 = " + res);
    }
}