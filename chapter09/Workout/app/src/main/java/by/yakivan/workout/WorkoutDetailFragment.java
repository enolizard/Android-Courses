package by.yakivan.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private int workoutId;

    public WorkoutDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            Workout workout = Workout.workouts[workoutId];
            TextView titleView = view.findViewById(R.id.workoutTitle);
            TextView descriptionView = view.findViewById(R.id.workoutDescription);

            titleView.setText(workout.getName());
            descriptionView.setText(workout.getDescription());
        }
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
