package strathmore.com.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by User on 28/10/2017.
 */

public class EmployeeHandler extends SQLiteOpenHelper {

    //All Static variables
    //Database Version
    private static final int DATABASEVERSION = 1;

    //Database Name
    private static final String DATABASENAME = "Employee Holder";

    //Employee table name
    private static final String TABLEEMPLOYEE = "Employees'";

    //Employee Table Columns names
    private static final String KEYID = "id_no";
    private static final String KEYNAME = "name";
    private static final String GENDER = "gender";
    private static final String DEPARTMENT = "department";

    //All Static variables
//Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "Employee Holder";

    //Contacts table name
    private static final String TABLE_EMPLOYEE  = "Employees'";

    //Employee Table Columns names
    private static final String KEY_ID_NO = "id_no";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DEPARTMENT = "department";

    public EmployeeHandler(Context context){
        super (context, DATABASE_NAME ,null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE" + TABLE_EMPLOYEE +
                "("+KEY_ID_NO + "INTERGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_GENDER + "TEXT" + KEY_DEPARTMENT + "TEXT," + ")";
        db.execSQL (CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

//Create tables again
        onCreate (db);


    }

    //Adding new Employee
    public void addEmployee (Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values  = new ContentValues();
        values.put (KEY_NAME, employee.getName () ); //Employee Name
        values.put (KEY_GENDER, employee.getGender ()); //Employee Gender

//Inserting Row
        db.insert (TABLE_EMPLOYEE, null , values);
        db.close (); // Closing database connection
    }

    //Getting single Employee
    public Employee getEmployee (int id_no) {
        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor cursor = db.query (TABLE_EMPLOYEE ,new String [] { KEY_ID_NO, KEY_NAME, KEY_GENDER, KEY_DEPARTMENT }, KEY_ID_NO + "=?", new String [] {String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst ();

        Employee employee = new Employee(Integer.parseInt (cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString (3));
//return employee
        return employee;
    }

    //Getting All Employees
    public List<Employee> getAllEmployees () {
        List<Employee> EmployeeList= new ArrayList<Employee>();
//Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery (selectQuery, null);

//loopinh through all rows and adding to list
        if (cursor.moveToFirst ()) {
            do {
                Employee employee = new Employee("James", "Male", "Marketting");
                employee.setId (Integer.parseInt (cursor.getString(0)));
                employee.setName (cursor.getString (1));
                employee.setGender (cursor.getString (2));
                employee.setDepartment (cursor.getString(3));
//Adding employee to list
                EmployeeList.add(employee);
            }while (cursor.moveToNext ());
        }

//return employee list
        return EmployeeList;
    }

    //Getting employee Count
    public int getEmployeeCount () {
        String countQuery = "SELECT * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (countQuery, null);
        cursor.close();

//return count
        return cursor.getCount ();
    }

    //Updating single employee
    public int updateemployee (Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put (KEY_NAME, employee.getName());
        values.put (KEY_GENDER, employee.getGender ());
        values.put (KEY_DEPARTMENT, employee.getDepartment ());

//updating row
        return db.update (TABLE_EMPLOYEE, values, KEY_ID_NO + "=?",
                new String [] { String.valueOf (employee.getId ()) });
    }

    //Deleting single employee
    public void deleteEmployee (Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete (TABLE_EMPLOYEE, KEY_ID_NO + "=?",
                new String [] { String.valueOf (employee.getId ()) });
        db.close ();
    }

}
