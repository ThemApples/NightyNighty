package com.example.bitternight;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear;
        RelativeLayout relative;
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public TextView mTextViewLine3;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
            mTextViewLine3 = itemView.findViewById(R.id.textview_line_3);
            linear = itemView.findViewById(R.id.linear);
            relative = itemView.findViewById(R.id.expandable_layout);

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExampleItem items = mExampleList.get(getAdapterPosition());
                    items.setExpandable(!items.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            linear.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int position = getAdapterPosition();
                    MainActivity.newPosition = position;
                    return false;
                }
            });

        }
    }
    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
        holder.mTextViewLine3.setText(currentItem.getLine3());
        boolean isExpandable = mExampleList.get(position).isExpandable();
        holder.relative.setVisibility(isExpandable ? View.VISIBLE: View.GONE);
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
