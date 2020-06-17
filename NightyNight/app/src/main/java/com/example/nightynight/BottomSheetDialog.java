package com.example.nightynight;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mlistener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        TextView t2 = v.findViewById(R.id.bottomSheetContent);

        t2.setText(MainActivity.bottomContent);
        return v;
    }

    public interface BottomSheetListener
    {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try
        {
            mlistener = (BottomSheetListener)context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + "must implement bottomsheetlistener");
        }
    }

}
