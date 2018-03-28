package com.example.aymen.projet;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by aymen on 30/04/2017.
 */
public class Knn {
    int taille;
    int Result;
    Knn1 knn1;

    ArrayList<Integer> ID ;

    public Knn( int taille,Context context) {
        this.taille = taille;

        knn1=new Knn1(context);
    }

    public double distance(double[] vect1, double[] vect2) {
        double res = 0;
        double k;
        double kcarre;
        double rest=0;

        for (int i = 0; i < 7; i++) {
            k=vect1[i]-vect2[i];
            kcarre=Math.pow(k,2);
            rest+=kcarre;
        }
        res=Math.sqrt(rest);
        return res;
    }


    public int calculDistance(double[]f) {
        ID= new ArrayList<Integer>();
        HashMap<Integer, Double> hashMap = new HashMap<Integer, Double>();
        ValueComparator comparateur = new ValueComparator(hashMap);
        double[] v;
        knn1.open();
        List<information>DD=knn1.getAllInformation();
        Iterator<information> iterator=DD.iterator();
        while (iterator.hasNext())
        {
            information information=iterator.next();
            v=new double[]{information.getVal1(),information.getVal2(),information.getVal3(),information.getVal4(),information.getVal5(),information.getVal6(),information.getVal7()};

            double d= distance(f,v);
            Log.d("id "," tout id  : "+information.getId());
            Log.d("id "," distance ==  : "+d);
             Log.d("id ","Resultat == "+information.getResult());
            hashMap.put(information.getId(),d);
        }


        String kk = hashMap.toString();
        Log.d("test ", " string 9bal tree ==>  " + kk);

        TreeMap<Integer,Double> mapTriee = new TreeMap<Integer,Double>(comparateur);
        mapTriee.putAll(hashMap);
        Set l = mapTriee.keySet();
        String k = l.toString();
        Log.d("test ", " string ba3ed tree ==>  " + k);
        Object[]x=l.toArray();


        ID.add((Integer)x[0]);
           Log.d("TOP ", "ID 1 == " + ID.get(0));
      ID.add((Integer) x[1]);
         Log.d("TOP ", "ID 2 == " + ID.get(1));
       ID.add((Integer) x[2]);
        Log.d("TOP ", "ID 3 == " + ID.get(2));
        ID.add((Integer) x[3]);
        Log.d("TOP ", "ID 4 == " + ID.get(3));
        ID.add((Integer) x[4]);
        Log.d("TOP ", "ID 5 == " + ID.get(4));




        int rr;
        int r1=knn1.result(ID.get(0));
        int r2=knn1.result(ID.get(1));
        int r3=knn1.result(ID.get(2));
        int r4=knn1.result(ID.get(3));
        int r5=knn1.result(ID.get(4));
        int R=r1+r2+r3+r4+r5;
        if(R>=3)
            rr=1;
        else
            rr=0;
        this.Result=rr;
        return rr;


    }

    public ArrayList<Integer>GetTOP()
    {
        return this.ID;
    }


    class ValueComparator implements Comparator<Integer> {
        Map<Integer, Double> base;

        public ValueComparator(HashMap<Integer, Double> base) {
            this.base = base;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if(base.get(o1)>=base.get(o2)) {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }

}
