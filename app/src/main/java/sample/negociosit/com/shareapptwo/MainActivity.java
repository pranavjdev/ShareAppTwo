package sample.negociosit.com.shareapptwo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView = null;

    private TextView statusTextView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        statusTextView = (TextView) findViewById(R.id.statusText);
        Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PackageManager packageManager = getPackageManager();
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo("sample.negociosit.com.shareappone",PackageManager.GET_META_DATA);

                    readFile(applicationInfo.dataDir +"/files/app1.txt");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void readFile(String filePath){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while((read = fileInputStream.read())!=-1){
                stringBuffer.append((char)read);
            }
            textView.setText(stringBuffer.toString());
            statusTextView.setTextColor(Color.GREEN);
            statusTextView.setText("File loaded successfully");
        } catch (FileNotFoundException e) {
            statusTextView.setTextColor(Color.RED);
            statusTextView.setText("Error " + e.getMessage());
        } catch (IOException e) {
            statusTextView.setTextColor(Color.RED);
            statusTextView.setText("Error "+e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
