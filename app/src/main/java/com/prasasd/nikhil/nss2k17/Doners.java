package com.prasasd.nikhil.nss2k17;

import java.security.PublicKey;

/**
 * Created by Nikhil08 on 1/10/2018.
 */

public class Doners {
    String donerId;
    String donerName;
    String donerBloodGroup;
    String donerNum;

    public Doners(){ }

    public Doners(String DonerId,String DonerName,String DonerBloodGroup,String DonerNum){
        this.donerId = DonerId;
        this.donerName= DonerName;
        this.donerBloodGroup= DonerBloodGroup;
        this.donerNum= DonerNum;
    }

    public String  getDonerId(){
        return donerId;
    }
    public String getDonerName(){
        return donerName;
    }
    public String getDonerBloodGroup(){
        return donerBloodGroup;
    }
    public String getDonerNum(){
        return donerNum;
    }
}
