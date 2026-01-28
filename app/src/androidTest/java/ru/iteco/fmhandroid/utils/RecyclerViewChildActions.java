package ru.iteco.fmhandroid.utils;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class RecyclerViewChildActions {

    public static ViewAction clickChildViewWithId(@IdRes int id) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed());
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(id);
                if (childView != null) {
                    childView.performClick();
                }
            }
        };
    }
}