package com.example.testperson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    ArrayList<Person> pers;


    public PersonAdapter(ArrayList<Person> pers) {
        this.pers = pers;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView surname;
        TextView position;
        TextView gender;
        public PersonViewHolder (View view)
        {
            super (view);
            name = (TextView) view.findViewById(R.id.name);
            surname = (TextView) view.findViewById(R.id.surname);
            position = (TextView) view.findViewById(R.id.position);
            gender = (TextView) view.findViewById(R.id.gender);
        }
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parrent, int viewTipe)
    {
        View view = LayoutInflater.from(parrent.getContext()).inflate(R.layout.data_list, parrent, false);
        return new PersonViewHolder(view);
    }
    @Override
    public void onBindViewHolder (@NonNull PersonViewHolder holder, int pos){
        holder.name.setText(pers.get(pos).getName());
        holder.surname.setText(pers.get(pos).getSurname());
        holder.position.setText(pers.get(pos).getPosition());
        holder.gender.setText(pers.get(pos).getGender());
    }
    @Override
    public int getItemCount()
    {
        return pers.size();
    }
}