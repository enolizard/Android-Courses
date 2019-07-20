package by.enolizard.exampletwo.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.enolizard.exampletwo.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<UserModel> data = new ArrayList<>();

    public void setData(List<UserModel> userModels) {
        data.clear();
        data.addAll(userModels);
        notifyDataSetChanged();
    }

    // RecyclerView.Adapter abstract class
    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // custom RecyclerView.ViewHolder for UserAdapter
    class UserHolder extends RecyclerView.ViewHolder {

        private TextView text;

        UserHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.txt_item_text);
        }

        void bind(UserModel userModel) {
            text.setText(String.format("id: %s, name: %s, email: %s", userModel.getId(), userModel.getName(), userModel.getEmail()));
        }
    }
}
