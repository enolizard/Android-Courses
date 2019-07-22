package by.enolizard.examplefour.views;

import by.enolizard.examplefour.models.Counter;

public interface CounterView {
    void setCounterName(String name);

    void setCounterValue(int value);

    void setMinusButtonEnabled(boolean enabled);

    void setPlusButtonEnabled(boolean enabled);

    void goToDetailView(Counter counter);
}
