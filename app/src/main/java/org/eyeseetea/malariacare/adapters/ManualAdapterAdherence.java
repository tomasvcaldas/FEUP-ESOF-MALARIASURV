package org.eyeseetea.malariacare.adapters;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.eyeseetea.malariacare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 03/03/2015.
 */
public class ManualAdapterAdherence {

    private final Activity context;
    private final List<Integer> idsCombos;

    // This adapter is for filling the dropdown lists in the Adherence tab with 2 options: [Yes, No]
    // FIXME: This class can be merged with ManualIQAAdapter. i.e. creating an abstract class implemented by both ManualIQA and ManualAdherence
    public ManualAdapterAdherence(Activity context)
    {
        this.context = context;
        this.idsCombos = new ArrayList<Integer>();

        Spinner view = (Spinner) context.findViewById(R.id.act1);

        // We hard-code the ids that contribute to this score calculus
        idsCombos.add(R.id.act1);

        // For each dropdown list we add its content (in every dropdown there will be 2 options, yes/no).
        for (Integer idCombo: idsCombos) {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.yesno, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ((Spinner) context.findViewById(idCombo)).setAdapter(adapter);
        }
    }
}