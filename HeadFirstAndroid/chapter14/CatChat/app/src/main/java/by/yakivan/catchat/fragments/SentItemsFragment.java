package by.yakivan.catchat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.yakivan.catchat.R;

public class SentItemsFragment extends Fragment {

    public SentItemsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView titleTextView = getView().findViewById(R.id.title_frag);
        titleTextView.setText(R.string.title_sent_items);
    }
}
