package com.mobimation.swedishradio;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * The ProgramAdapter adapts Program information entries to list view data.
 * The internal ProgramViewHolder creates view data from retrieved text data.
 */
public class ProgramAdapter extends RecyclerView.Adapter< ProgramAdapter.ProgramViewHolder > {

    private final List<ProgramItem> items;

    public interface OnItemClickListener {
        void onItemClick(ProgramItem item);
    }

    /**
     * The ProgramViewHolder caches the views for a Program List entry.
     * We hold one TextView and one ImageView item
     **/
    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        final ImageView programImageView;
        final TextView  programNameView;

        // ProgramViewHolder constructor
        public ProgramViewHolder(View itemView) {
            super(itemView);
            this.programNameView  = itemView.findViewById(R.id.programName);
            this.programImageView = itemView.findViewById(R.id.image);
        }
    }

    // Adapter constructor
    public ProgramAdapter(List<ProgramItem> items, OnItemClickListener listener) {
        this.items = items;
    }
 
    @Override public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_list_row, parent, false);
        return new ProgramViewHolder(v);
    }
    // TODO Move the DetailActivity launch to a more convenient place
    // onBindViewHolder fetches the Program image and sets up a click listener
    // for browsing the detail page of a radio channel (see DetailActivity).
    @Override public void onBindViewHolder(final ProgramViewHolder holder, final int position) {
        final ProgramItem item=items.get(position);
        holder.programNameView.setText(item.getProgramName());
        Picasso.get().load(item.getImageURL()).into(holder.programImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Log.d("gf","pos="+position);
                String url=item.getProgramUrl();
                Intent i = new Intent(holder.itemView.getContext(), DetailActivity.class);
                i.putExtra("programurl",url);  // Pass url to detail page
                holder.itemView.getContext().startActivity(i);
                Log.d("gf","Starting Activity for URL="+url);
            }
        });
    }
 
    @Override public int getItemCount() {
        return items.size();
    }
}