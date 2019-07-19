package by.enolizard.exampleone.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.enolizard.exampleone.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public MainAdapter(List<String> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(String item);
    }

    private List<String> items;
    private Listener listener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_main_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final String item = items.get(position);
        holder.textView.setText(item);
        holder.textView.setOnClickListener(v -> listener.onItemClicked(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MainViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
