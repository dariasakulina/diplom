package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;

import static ru.iteco.fmhandroid.utils.WaitForViewAction.waitForView;

import ru.iteco.fmhandroid.R;

public class MainPage {

    private final int NEWS_TOGGLE_BUTTON = R.id.expand_material_button;
    private final int ALL_NEWS_BUTTON = R.id.all_news_text_view;
    private final String MAIN_NEWS = "Новости";

    public void toggleNewsBlock() {
        Allure.step("Развернуть / свернуть блок новостей на главном экране");
        onView(withId(NEWS_TOGGLE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkNewsBlockIsDisplayed() {
        Allure.step("Проверить, что блок новостей отображается на главном экране");
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()));
    }

    public void checkMainScreenIsOpened() {
        Allure.step("Проверить, что открыт главный экран приложения");
        onView(withText(MAIN_NEWS)).check(matches(isDisplayed()));
        onView(withId(ALL_NEWS_BUTTON)).check(matches(isDisplayed()));
    }

    public void clickAllNews() {
        Allure.step("Нажать кнопку «Все новости» на главном экране");
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
