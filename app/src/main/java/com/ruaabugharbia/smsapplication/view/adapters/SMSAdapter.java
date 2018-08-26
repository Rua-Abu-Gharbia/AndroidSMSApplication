package com.ruaabugharbia.smsapplication.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruaabugharbia.smsapplication.R;
import com.ruaabugharbia.smsapplication.models.SMSModel;

import java.util.List;

/**
 * Created by ruaabugharbia on 06-Aug-18.
 */


public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.MyViewHolder> {

    private Context mContext;
    private List<SMSModel> data;
    View.OnClickListener clickListener ;

    public SMSAdapter(Context context, int textViewResourceId, List<SMSModel> objects ){//, View.OnClickListener clickListener) {
        mContext = context;
        data = objects;
       // this.clickListener = clickListener;
    }

    public void removeItem(int position){
        data.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        holder.smsBodyTextView.setText(data.get(position).getSmsBody());
        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = holder.smsBodyTextView.getText().toString().trim();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mContext.startActivity(Intent.createChooser(sharingIntent, mContext.getResources().getString(R.string.share_using)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView  smsBodyTextView;
        final ImageView shareImageView;

        public MyViewHolder(View view) {
            super(view);
            smsBodyTextView = view.findViewById(R.id.text_sms_body);
            shareImageView = view.findViewById(R.id.imageView_share);
        }
    }
}
