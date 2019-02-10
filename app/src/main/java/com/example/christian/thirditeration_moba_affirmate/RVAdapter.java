package com.example.christian.thirditeration_moba_affirmate;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AffirmationViewHolder>{

    private OnItemClickListener mListener;
    public static TinyDB refreshedMyTinyDB;


    public interface OnItemClickListener{
        void  onItemClick(int position);
        void onEnabledDisabledButtonClick(int position);
        void onEditButtonClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class AffirmationViewHolder extends  RecyclerView.ViewHolder{

        LinearLayout outerLinearLayout;
        CardView cv;
        TextView affirmationText;
        View dividerView;
        TextView remindMeTextView;
        TextView reminderTimeTextView;
        LinearLayout innerLinearLayout;
        Button enableDisableButton;
        Button editCardButton;
        ImageView isEnabledIndicator;

        AffirmationViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView2);
            outerLinearLayout = (LinearLayout) itemView.findViewById(R.id.outerLinearLayout1);
            affirmationText = (TextView) itemView.findViewById(R.id.TextViewMakeBold);
            dividerView = (View) itemView.findViewById(R.id.dividerView);
            remindMeTextView = (TextView) itemView.findViewById(R.id.firstRemindMe);
            reminderTimeTextView = (TextView) itemView.findViewById(R.id.firstTime);
            innerLinearLayout = (LinearLayout) itemView.findViewById(R.id.innerLinearLayout1);
            enableDisableButton = (Button) itemView.findViewById(R.id.disableButtonPressed);
            editCardButton = (Button) itemView.findViewById(R.id.editButtonPressed);
            isEnabledIndicator = (ImageView) itemView.findViewById(R.id.enabledIndicatorImageView);


            enableDisableButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEnabledDisabledButtonClick(position);

                        }
                    }
                }
            });

            editCardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditButtonClick(position);
                        }
                    }
                }
            });


        }

    }

    List<Affirmation> affirmations;
    RVAdapter(List<Affirmation> affirmations){
        this.affirmations = affirmations;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AffirmationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_blueprint, viewGroup, false);
        AffirmationViewHolder avh = new AffirmationViewHolder(v, mListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(AffirmationViewHolder affirmationViewHolder, int i) {
        TinyDB tinydb;

        affirmationViewHolder.affirmationText.setText(affirmations.get(i).affirmation);

        if(affirmations.get(i).isEnabled == true){
            affirmationViewHolder.enableDisableButton.setText("Disable");
            affirmationViewHolder.cv.setBackgroundColor(Color.parseColor("#32ffb700"));
            affirmationViewHolder.isEnabledIndicator.setVisibility(ImageView.VISIBLE);

            tinydb = MainActivity.getTinydb();
            Affirmation updatedAffirmation = new Affirmation(affirmations.get(i).affirmation, affirmations.get(i).remindOnceADay, affirmations.get(i).remindTwiceADay, affirmations.get(i).remindThriceADay, affirmations.get(i).firstReminderTime, affirmations.get(i).isEnabled, affirmations.get(i).affirmationKeyString);
            tinydb.putObject(affirmations.get(i).affirmationKeyString, updatedAffirmation);
            refreshedMyTinyDB = tinydb;

        }else if (affirmations.get(i).isEnabled == false){
            affirmationViewHolder.enableDisableButton.setText("Enable");
            affirmationViewHolder.cv.setBackgroundColor(Color.parseColor("#ffffff"));
            affirmationViewHolder.isEnabledIndicator.setVisibility(ImageView.INVISIBLE);

            tinydb = MainActivity.getTinydb();
            Affirmation updatedAffirmation = new Affirmation(affirmations.get(i).affirmation, affirmations.get(i).remindOnceADay, affirmations.get(i).remindTwiceADay, affirmations.get(i).remindThriceADay, affirmations.get(i).firstReminderTime, affirmations.get(i).isEnabled, affirmations.get(i).affirmationKeyString);
            tinydb.putObject(affirmations.get(i).affirmationKeyString, updatedAffirmation);
            refreshedMyTinyDB = tinydb;
        }

        if (affirmations.get(i).remindOnceADay == true){

            affirmationViewHolder.remindMeTextView.setText("Remind me: Once a day");

        }else if (affirmations.get(i).remindTwiceADay == true){

            affirmationViewHolder.remindMeTextView.setText("Remind me: Twice a day");

        } else if (affirmations.get(i).remindThriceADay == true){

            affirmationViewHolder.remindMeTextView.setText("Remind me: Thrice a day");

        } else {
            affirmationViewHolder.remindMeTextView.setText("PROBLEM WITH STRING/BOOLEAN");
        }

        affirmationViewHolder.reminderTimeTextView.setText(affirmations.get(i).firstReminderTime);


    }

    @Override
    public int getItemCount() {
        return affirmations.size();
    }
/*
    public static TinyDB getRefreshedMyTinyDB(){
        return refreshedMyTinyDB;
    }
*/





}
