package org.eyeseetea.malariacare.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Jose on 22/02/2015.
 */
public class Score {

    public static final int NUMERATOR=0;
    public static final int DENOMINATOR=1;

    Hashtable<Integer, float[]> scores;

    public Score()
    {
        scores = new Hashtable<Integer, float[]>();
    }


    public Score(List<Object[]> tabs)
    {
        for (int i=0;i< tabs.size();i++) {
            scores.put(((Integer) tabs.get(i)[0]), new float[2]);
        }
    }

    public void addTabScore(Integer tab)
    {
        scores.put(tab, new float[2]);
    }

    public void addValueNumerator(Integer tab, float value)
    {
        scores.get(tab)[NUMERATOR]+=value;
    }

    public void addValueDenominator(Integer tab, float value)
    {
        scores.get(tab)[DENOMINATOR]+=value;
    }

    public void addValuesNumDenum(Integer tab, float num, float denum)
    {
        scores.get(tab)[NUMERATOR]+=num;
        scores.get(tab)[DENOMINATOR]+=denum;
    }

    public void resetValuesNumDenum(Integer tab, float num, float denum)
    {
        scores.get(tab)[NUMERATOR]-=num;
        scores.get(tab)[DENOMINATOR]-=denum;
    }

    public void resetValueDenominator(Integer tab, float value)
    {
        scores.get(tab)[DENOMINATOR]-=value;
    }

    public void resetValueNumerator(Integer tab, float value)
    {
        scores.get(tab)[NUMERATOR]-=value;
    }

    public float getNumerator(Integer tab)
    {
        return scores.get(tab)[NUMERATOR];
    }

    public float getDenominator(Integer tab)
    {
        return scores.get(tab)[DENOMINATOR];
    }

    public float getPercent(Integer tab)
    {
        return ( getNumerator(tab)/getDenominator(tab))*100;
    }


}