package strathmore.com.lab2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;

/**
 * Created by User on 28/10/2017.
 */

public class Organisation {
    //private variables
    int _postal_adrress;
    String _name;
    String _location;

    //Empty constructor
    public Organisation(String string, String cursorString){
    }
    //constructor
    public Organisation(int _postal_adrress, String name, String _location){
        this._postal_adrress=_postal_adrress;
        this._name=name;
        this._location=_location;
    }

    //constructor
    public Organisation(){
        this._name= _name;
        this._location=_location;
    }


    //getting Address
    public int getAddress(){
        return this._postal_adrress;
    }

    //setting Address
    public void setAddress(int address){
        this._postal_adrress= _postal_adrress;
    }

    //getting name
    public String getName(){
        return this._name;
    }

    //setting name
    public void setName (String name) {
        this._name=name;
    }

    //getting location
    public String getLocation(){
        return this._location;
    }


    //setting location
    public void setLocation (String _location) {this._name=_location; }



}


