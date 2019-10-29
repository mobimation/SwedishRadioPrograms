package com.mobimation.swedishradio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<DataModel> dataModelArrayList;

    public ListAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            try {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.program_list_row,
                        null, true);

                holder.image = convertView.findViewById(R.id.image);
                holder.programName = convertView.findViewById(R.id.programName);

                convertView.setTag(holder);
            }
            catch(NullPointerException e) {
                System.out.print("NullPointerException in getView");
                e.printStackTrace(System.out);
            }
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(dataModelArrayList.get(position).getImageURL()).into(holder.image);
        holder.programName.setText(dataModelArrayList.get(position).getProgramName());

        return convertView;
    }

    private class ViewHolder {
        TextView programName;
        ImageView image;
    }
}
