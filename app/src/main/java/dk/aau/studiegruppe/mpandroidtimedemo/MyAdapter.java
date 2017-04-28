package dk.aau.studiegruppe.mpandroidtimedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marti on 4/27/2017.
 */

public class MyAdapter extends ArrayAdapter<StateVO> {
    private static final String TAG = "lala" ;
    private Context mContext;
    private ArrayList<StateVO> listState;
    private MyAdapter myAdapter;
    private boolean isFromView = false;
    public String[] checkedHolder = new String[10];
    ArrayList<Integer> hej = new ArrayList<>();
    public MyAdapter(MainActivity context, int resource, ArrayList<StateVO> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = objects;
        this.myAdapter = this;
        for (int i = 0; i < this.getCount(); i++)
        {
            listState.get(i).setSelected(false);
        }
    }

    public void deleteAll(){
        for (int i = 0; i < this.getCount(); i++)
        {
            listState.get(i).setSelected(false);
        }
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        deleteAll();
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                Log.i(TAG, String.valueOf(getPosition));
               // checkedHolder[position] = String.valueOf(getPosition);
                hej.add(getPosition);

                if (isChecked)
                {
                    listState.get(position).setSelected(true);
                }
                else
                {
                    listState.get(position).setSelected(false);
                }


            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
    public ArrayList<StateVO> listenNine(){

      return listState;
    }
}