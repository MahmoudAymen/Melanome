package com.example.aymen.projet;

/**
 * Created by aymen on 30/04/2017.
 */
public class information {
    int id;
    byte[] image;
    double val1;
    double val2;
    double val3;
    double val4;
    double val5;
    double val6;
    double val7;
    int result;
    public information(byte[] image,double val1,double val2,double val3,double val4,double val5,double val6,double val7,int result)
    {

        this.image=image;
        this.val1=val1;
        this.val2=val2;
        this.val3=val3;
        this.val4=val4;
        this.val5=val5;
        this.val6=val6;
        this.val7=val7;
        this.result=result;

    }
    public information(){}
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {

        return image;
    }

    public void setImage(byte []image) {

        this.image = image;
    }

    public double getVal1() {

        return val1;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }

    public double getVal2() {
        return val2;
    }

    public void setVal2(double val2) {

        this.val2 = val2;
    }

    public double getVal3() {

        return val3;
    }

    public void setVal3(double val3)
    {
        this.val3 = val3;
    }

    public double getVal4() {

        return val4;
    }

    public void setVal4(double val4) {

        this.val4 = val4;
    }

    public double getVal5() {

        return val5;
    }

    public void setVal5(double val5) {

        this.val5 = val5;
    }

    public double getVal6() {

        return val6;
    }

    public void setVal6(double val6) {
        this.val6 = val6;
    }

    public double getVal7() {
        return val7;
    }

    public void setVal7(double val7) {
        this.val7 = val7;
    }

    public int getResult() {

        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}

