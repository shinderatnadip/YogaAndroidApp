package com.ratnadip.yogafitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ratnadip.yogafitness.Interface.ItemClickListener;
import com.ratnadip.yogafitness.Model.Exercises;
import com.ratnadip.yogafitness.R;
import com.ratnadip.yogafitness.ViewExercise;

import java.util.List;

/**
 * Created by Ratnadip on 08-Apr-18.
 */


class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imageView;
    public TextView textView;


    private ItemClickListener itemClickListener;



    public RecyclerViewHolder(View itemView) {
        super(itemView);

        imageView =(ImageView)itemView.findViewById(R.id.ex_img);
        textView =(TextView) itemView.findViewById(R.id.ex_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition());
    }
}

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<Exercises> exercisesList;
    private Context context;

    public RecyclerViewAdapter(List<Exercises> exercisesList, Context context) {
        this.exercisesList = exercisesList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_exercise,parent,false);
        return new RecyclerViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.imageView.setImageResource(exercisesList.get(position).getImage_id());
        holder.textView.setText(exercisesList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                //call to new activity

                Intent intent = new Intent(context, ViewExercise.class);
                intent.putExtra("image_id",exercisesList.get(position).getImage_id());
                intent.putExtra("name",exercisesList.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }
}
