package sorindev.find;

/**
 * Created by ionut on 8/31/2017.
 */

public class Address {
    public int index;
    public String nume;
    public String meniu;
    public int meniul_zileiA;
    public String meniul_zilei;
    public String date_contact;
    public int non_stop;
    public float latit;
    public float longit;

    public Address(int index, String nume, String meniu, int meniul_zileiA, String meniul_zilei, String date_contact, int non_stop, float latit, float longit ){
        this.index = index;
        this.nume = nume;
        this.meniu = meniu;
        this.meniul_zileiA = meniul_zileiA;
        this.meniul_zilei = meniul_zilei;
        this.date_contact = date_contact;
        this.non_stop = non_stop;
        this.latit = latit;
        this.longit = longit;
    }
}
