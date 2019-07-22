package by.enolizard.examplefour.views;

import java.util.List;

import by.enolizard.examplefour.models.Counter;

public interface MainView {
    void showCounters(List<Counter> counters);

    void showLoading();

    void showEmpty();
}
