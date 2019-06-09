package by.yakivan.bitsandpizzas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PizzaFragment extends Fragment {

    public PizzaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_pizza,
                container,
                false
        );

        String[] pizzaNames = new String[Pizza.pizzas.length];
        int[] pizzaImageIds = new int[pizzaNames.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
            pizzaImageIds[i] = Pizza.pizzas[i].getImageResourseId();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        pizzaRecycler.setAdapter(new CaptionedImageAdapter(pizzaNames, pizzaImageIds));
        pizzaRecycler.setLayoutManager(layoutManager);

        return pizzaRecycler;
    }
}
