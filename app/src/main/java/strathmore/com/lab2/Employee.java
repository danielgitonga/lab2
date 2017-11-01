package strathmore.com.lab2;

/**
 * Created by User on 28/10/2017.
 */

public class Employee {
    //private variables
    int _id_no;
    String _name;
    String _gender;
    String _department;

    //Empty constructor
    public Employee(int i, String string, String cursorString){
    }
    //constructor
    public Employee(int id_no, String name, String _gender, String _department){
        this._id_no=_id_no;
        this._name=name;
        this._gender=_gender;
        this._department= _department;
    }

    //constructor
    public Employee(String james, String male, String marketting){
        this._name= _name;
        this._gender=_gender;
        this._department= _department;
    }


    //getting id number
    public int getId(){
        return this._id_no;
    }

    //setting id number
    public void setId(int id){
        this._id_no= id;
    }

    //getting name
    public String getName(){
        return this._name;
    }

    //setting name
    public void setName (String name) {
        this._name=name;
    }

    //getting gender
    public String getGender(){
        return this._gender;
    }


    //setting gender
    public void setGender (String _gender) {this._name=_gender; }


    //getting department
    public String getDepartment () { return this._department; }

    //setting department
    public void setDepartment (String _department){this._department= _department;
    }
}

