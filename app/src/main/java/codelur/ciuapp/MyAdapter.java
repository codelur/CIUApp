package codelur.ciuapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> itemsArrayList;
    Spinner gradeSpinner;

    public MyAdapter(Context context, ArrayList<Item> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);
        gradeSpinner = (Spinner) rowView.findViewById(R.id.spinnerGrade);

        //Listener for changes on ListView Spinners
        final int itemSelected = position;
        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemsArrayList.get(itemSelected).setGrade(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTitle());
        valueView.setText(itemsArrayList.get(position).getDescription());
        gradeSpinner.setSelection(itemsArrayList.get(position).getGrade());

        // 5. retrn rowView
        return rowView;
    }

    public ArrayList<Item> getItems(){
        return this.itemsArrayList;
    }
}
