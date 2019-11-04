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

public class ContentAdapter extends RecyclerView.Adapter< ContentAdapter.ProgramViewHolder > {

    private final List<ContentItem> items;

    public interface OnItemClickListener {
        void onItemClick(ContentItem item);
    }

    // The ItemViewHolder cache the views for a Program List entry.
    // We create a class called ItemViewHolder that extends Recycler.ViewHolder
    // We hold one TextView and one ImageView
    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        final ImageView programImageView;
        final TextView  programNameView;

        // Create a ProgramViewHolder constructor
        public ProgramViewHolder(View itemView) {
            super(itemView);
            this.programNameView  = itemView.findViewById(R.id.programName);
            this.programImageView = itemView.findViewById(R.id.image);
        }
        // This bind operation instantiates itemView entries (loads images)
        public void bind(final ContentItem item, final OnItemClickListener listener) {
            this.programNameView.setText(item.getProgramName());
            Picasso.get().load(item.getImageURL()).into(this.programImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d("gf","onClick");
                    listener.onItemClick(item);
                }
            });
        }

    }

    // Adapter constructor
    public ContentAdapter(List<ContentItem> items, OnItemClickListener listener) {
        this.items = items;
    }
 
    @Override public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_list_row, parent, false);
        return new ProgramViewHolder(v);
    }
    // TODO Move the DetailActivity launch to a more convenient place
    // onBindViewHolder fetches the Program image and sets up a click listener
    // for browsing the detail page of a radio channel..
    @Override public void onBindViewHolder(final ProgramViewHolder holder, final int position) {
        final ContentItem item=items.get(position);
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
 
    static class ViewHolder extends RecyclerView.ViewHolder {
 
        final TextView name;
        final ImageView image;
 
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.programName);
            image = itemView.findViewById(R.id.image);
        }
 
        public void bind(final ContentItem item, final OnItemClickListener listener) {
            name.setText(item.getProgramName());
            Picasso.get().load(item.getImageURL()).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}