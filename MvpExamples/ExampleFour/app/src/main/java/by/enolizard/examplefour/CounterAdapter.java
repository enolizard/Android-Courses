package by.enolizard.examplefour;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import by.enolizard.examplefour.models.Counter;
import by.enolizard.examplefour.presenters.CounterPresenter;
import by.enolizard.examplefour.views.CounterViewHolder;

public class CounterAdapter extends MvpRecyclerListAdapter<Counter, CounterPresenter, CounterViewHolder> {
    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CounterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_row, parent, false));
    }

    @NonNull
    @Override
    protected CounterPresenter createPresenter(@NonNull Counter counter) {
        CounterPresenter presenter = new CounterPresenter();
        presenter.setModel(counter);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Counter model) {
        return model.getId();
    }
}
