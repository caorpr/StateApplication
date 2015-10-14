package ctec.stateapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.content.Intent;
import android.view.View;

import ctec.stateapplication.model.AndroidSaveState;


public class FrontPageActivity extends Activity
{
    private Button nextScreenButton;
    private EditText nameText;
    private EditText ageText;
    private CheckBox isTiredBox;

    private AndroidSaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        saveState = (AndroidSaveState) getApplication();

        nextScreenButton = (Button) findViewById(R.id.submitButton);
        nameText = (EditText) findViewById(R.id.nameText);
        ageText = (EditText) findViewById(R.id.ageText);
        isTiredBox = (CheckBox) findViewById(R.id.isTiredBox);

        grabInput();

        setupListeners();
    }

    private void grabInput()
    {
        String name = nameText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        boolean isTired = isTiredBox.isChecked();

        saveState.setUserName(name);
        saveState.setAge(age);
        saveState.setIsTired(isTired);

    }

    private void setupListeners()
    {
        nextScreenButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View clickView)
            {
                //grab user info
                grabInput();
                //Open new screen
                Intent changeScreen = new Intent(clickView.getContext(), BackPageActivity.class);
                startActivityForResult(changeScreen, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
