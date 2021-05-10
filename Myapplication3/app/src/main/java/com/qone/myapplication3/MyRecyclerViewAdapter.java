package com.qone.myapplication3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//class recyclerView - useful to present flexibly data as a list
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Phone> phoneList;
    //LayoutInflater is useful to build Java Object based on XML files
    private LayoutInflater inflater;

    // data(subjectName and mark as a list) is passed into the constructor
    MyRecyclerViewAdapter(Activity context) {
        this.inflater = context.getLayoutInflater();
        this.phoneList = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.onerow, null);
        //holder is useful to save reference to component in individual rows
        return new ViewHolder(view);
    }

    // binds,connect the data to the TextView and RadioGroup in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv1.setTag(position);
        holder.tv2.setTag(position);

        Phone phone = phoneList.get(position);
        String producer = phone.getProducer();
        String model = phone.getModel();

        holder.tv1.setText(producer);
        holder.tv2.setText(model);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        if(phoneList!=null)
            return phoneList.size();
        return 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    public List<Phone> getMarksList(){return phoneList;}


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;

        public ViewHolder(@NonNull View Row) {
            super(Row);
            tv1 = Row.findViewById(R.id.textView);
            tv2 = Row.findViewById(R.id.textView2);
        }
    }
}
