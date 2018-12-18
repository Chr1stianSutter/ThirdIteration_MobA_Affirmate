package com.example.christian.thirditeration_moba_affirmate;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

class AffirmationsAdapter extends RecyclerView.Adapter<AffirmationsAdapter.AffirmationViewHolder> {


    private ArrayList<AffirmationCard> affirmationCardsContentList;

    public AffirmationsAdapter(ArrayList<AffirmationCard> affirmationCardsContentList) {
        this.affirmationCardsContentList = affirmationCardsContentList;
    }

    @Override
    public int getItemCount() {
        return affirmationCardsContentList.size();
    }

    @Override
    public void onBindViewHolder(AffirmationViewHolder affirmationViewHolder, int i) {
        AffirmationCard acc = affirmationCardsContentList.get(i);
        //contactViewHolder.vName.setText(acc.name);
        //contactViewHolder.vSurname.setText(acc.surname);
        //contactViewHolder.vEmail.setText(acc.email);
        //contactViewHolder.vTitle.setText(acc.name + " " + acc.surname);

        //affirmationViewHolder.vCardScrollView.

        //affirmationViewHolder.vCardConstraintLayout.

        //affirmationViewHolder.vAffirmation.
        //affirmationViewHolder.vViewDivider.

        //affirmationViewHolder.vRemindMeLabel.


        //affirmationViewHolder.vGroup.
        //affirmationViewHolder.vFirstRadioButton.
        //affirmationViewHolder.vSecondRadioButton.
        //affirmationViewHolder.vThirdRadioButton.

        //affirmationViewHolder.vFirstReminderAtLabel.
        //affirmationViewHolder.vTextAndButtonGroup.
        //affirmationViewHolder.vFirstReminderTime.
        //affirmationViewHolder.vEditButton.

        //affirmationViewHolder.vSaveAndCancelButtonGroup.
        //affirmationViewHolder.vCancelButton.
        //affirmationViewHolder.vSaveButton.
    }

    @Override
    public AffirmationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_list_element, viewGroup, false);

        return new AffirmationViewHolder(itemView);
    }



    public class AffirmationViewHolder extends RecyclerView.ViewHolder {

        protected ScrollView vCardScrollView;

        protected ConstraintLayout vCardConstraintLayout;

        protected EditText vAffirmation;
        protected View vViewDivider;

        protected TextView vRemindMeLabel;

        protected RadioGroup vGroup;
        protected RadioButton vFirstRadioButton;
        protected RadioButton vSecondRadioButton;
        protected RadioButton vThirdRadioButton;

        protected TextView vFirstReminderAtLabel;
        protected LinearLayout vTextAndButtonGroup;
        protected TextView vFirstReminderTime;
        protected ImageView vEditButton;

        protected LinearLayout vSaveAndCancelButtonGroup;
        protected Button vCancelButton;
        protected Button vSaveButton;


        public AffirmationViewHolder(View v) {
            super(v);

            vCardScrollView = (ScrollView) v.findViewById(R.id.cardScrollView);

            vCardConstraintLayout = (ConstraintLayout) v.findViewById(R.id.cardConstraintLayout);

            vAffirmation = (EditText) v.findViewById(R.id.affirmationDetail);
            vRemindMeLabel = (TextView) v.findViewById(R.id.remindMeLabel);

            vViewDivider = (View) v.findViewById(R.id.viewDivider);

            vGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
            vFirstRadioButton = (RadioButton) v.findViewById(R.id.firstRadioButton);
            vSecondRadioButton = (RadioButton) v.findViewById(R.id.secondRadioButton);
            vThirdRadioButton = (RadioButton) v.findViewById(R.id.thirdRadioButton);

            vFirstReminderAtLabel = (TextView) v.findViewById(R.id.firstReminderAtLabel);
            vTextAndButtonGroup = (LinearLayout) v.findViewById(R.id.textAndButtonGroup);
            vFirstReminderTime = (TextView) v.findViewById(R.id.tvTime);
            vEditButton = (ImageView) v.findViewById(R.id.editImage);

            vSaveAndCancelButtonGroup = (LinearLayout) v.findViewById(R.id.saveAndCancelButtonGroup);
            vCancelButton = (Button) v.findViewById(R.id.cancelButtonPressed);
            vSaveButton = (Button) v.findViewById(R.id.saveButtonPressed);


        }
    }
}
