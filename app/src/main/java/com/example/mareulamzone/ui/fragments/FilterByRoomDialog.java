package com.example.mareulamzone.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.adapters.MyMeetingsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class FilterByRoomDialog extends DialogFragment {

    public ArrayList<Integer> selectedItems = new ArrayList<>();
    private MeetingApiService apiService = DI.getMeetingApiService();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedItems.clear();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filtrer par salle").setMultiChoiceItems(R.array.salle_de_r_union, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    selectedItems.add(which + 1);
                }
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        RecyclerView rv = getActivity().findViewById(R.id.list);
                        MyMeetingsRecyclerViewAdapter mAdapter2 = new MyMeetingsRecyclerViewAdapter( getContext(),apiService.filterMeetingRoomIdList(selectedItems));
                        rv.setAdapter(mAdapter2);
                        mAdapter2.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
