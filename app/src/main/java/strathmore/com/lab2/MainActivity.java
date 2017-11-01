package strathmore.com.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        EmployeeHandler db = new EmployeeHandler(this);
        /**
         * CRUD Operations
         * */

        Log.d("Insert:","Inserting..");
        db.addEmployee (new Employee ("James", "Male", "Marketting"));
        db.addEmployee (new Employee ("Anna", "Female", "Finance"));
        db.addEmployee (new Employee ("Robert", "Male", "Human Resources"));
        db.addEmployee (new Employee ("Shiela", "Female", "IT"));

        Log.d ("Reading: ", "Reading all contacts..");
        List<Employee> Employee = db.getAllEmployees ();
        for (Employee  cn : Employee ) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Gender: " + cn.getGender() + " ,Department" + cn.getDepartment();

            Log.d("Name: ", log);
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
