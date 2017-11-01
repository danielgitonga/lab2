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
 * Created by User on 30/10/2017.
 */

public class OrganisationHandler extends SQLiteOpenHelper {

    //All Static variables
    //Database Version
    private static final int DATABASEVERSION = 1;

    //Database Name
    private static final String DATABASENAME = "Organisation Holder";

    //Employee table name
    private static final String TABLEORGRANISATION = "Organisation";

    //Employee Table Columns names
    private static final String KEYPOSTAL_ADDRESS = "postal_address";
    private static final String KEYNAME = "name";
    private static final String KEYLOCATION = "location";


    //Organisation table name
    private static final String TABLE_ORGANISATION  = "Organisation'";

    //All Static variables
//Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "Employee Holder";

    //Contacts table name
    private static final String TABLE_EMPLOYEE  = "Employees'";

    //Employee Table Columns names
    private static final String KEY_POSTAL_ADDRESS = "_postal_address";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";


    public OrganisationHandler(Context context){
        super (context, DATABASE_NAME ,null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE" + TABLE_ORGANISATION +
                "("+KEY_POSTAL_ADDRESS + "INTERGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_LOCATION + "TEXT" + ")";
        db.execSQL (CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_ORGANISATION);

//Create tables again
        onCreate (db);


    }

    //Adding new Organisation
    public void addEmployee (Organisation organisation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values  = new ContentValues();
        values.put (KEY_NAME, organisation.getName () ); //Organisation Name
        values.put (KEY_LOCATION, organisation.getLocation ()); //Organisation Location

//Inserting Row
        db.insert (TABLE_ORGANISATION, null , values);
        db.close (); // Closing database connection
    }

    //Getting single Organisation
    public Organisation getOrganisation (int postal_address) {
        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor cursor = db.query (TABLE_ORGANISATION ,new String [] { KEY_POSTAL_ADDRESS, KEY_NAME, KEY_LOCATION,}, KEY_POSTAL_ADDRESS + "=?", new String [] {String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst ();

        Organisation organisation = new Organisation(Integer.parseInt (cursor.getString(0)), cursor.getString(1), cursor.getString(2));
 //return organisation
        return organisation;
    }

    //Getting All Employees
    public List<Organisation> getAllOrganisation () {
        List<Organisation> OrganisationList= new ArrayList<Organisation>();
//Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ORGANISATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery (selectQuery, null);

//loopinh through all rows and adding to list
        if (cursor.moveToFirst ()) {
            do {
                Organisation organisation = new Organisation();
                organisation.setAddress (Integer.parseInt (cursor.getString(0)));
                organisation.setName (cursor.getString (1));
                organisation.setLocation (cursor.getString (2));

//Adding to list
                OrganisationList.add(organisation);
            }while (cursor.moveToNext ());
        }

//return list
        return OrganisationList;
    }

    //Getting Count
    public int getOrganisationCount () {
        String countQuery = "SELECT * FROM " + TABLE_ORGANISATION;
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (countQuery, null);
        cursor.close();

//return count
        return cursor.getCount ();
    }

    //Updating single Organisation
    public int updateorganisation (Organisation organisation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put (KEY_NAME, organisation.getName());
        values.put (KEY_LOCATION, organisation.getLocation ());

//updating row
        return db.update (TABLE_ORGANISATION, values, KEY_POSTAL_ADDRESS + "=?",
                new String [] { String.valueOf (organisation.getAddress ()) });
    }

    //Deleting single organisation
    public void deleteOrganisation (Organisation organisation) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete (TABLE_ORGANISATION, KEY_POSTAL_ADDRESS + "=?",
                new String [] { String.valueOf (organisation.getAddress ()) });
        db.close ();
    }


}
