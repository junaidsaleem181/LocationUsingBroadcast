package org.example.geofencing;

public class SavedLocation {
    String address="Street 1, BOR Society, Lahore, Punjab, Pakistan";
    public boolean checkedInToFast(String add)
    {
        String[] params=address.split(",");
        String[] matchers=add.split(",");
        if((params[0].equals(matchers[0])) && (params[1].equals(matchers[1])) && (params[2].equals(matchers[2])))
        {
            return true;
        }
        return false;
    }
}
