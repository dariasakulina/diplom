package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.R;

public class NavigationPage {

    private final int MENU_BUTTON = R.id.main_menu_image_button;
    private final int QUOTES_BUTTON = R.id.our_mission_image_button;
    private final int BACK_BUTTON = R.id.about_back_image_button;

    private final String MENU_MAIN = "Главная";
    private final String MENU_NEWS = "Новости";
    private final String MENU_ABOUT = "О приложении";

    public void openNavigationMenu() {
        Allure.step("Открыть меню навигации (бургер)");
        onView(withId(MENU_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickMain() {
        Allure.step("Перейти в раздел «Главная»");
        onView(withText(MENU_MAIN))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickNews() {
        Allure.step("Перейти в раздел «Новости»");
        onView(withText(MENU_NEWS))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickAbout() {
        Allure.step("Перейти в раздел «О приложении»");
        onView(withText(MENU_ABOUT))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickBack() {
        Allure.step("Нажать кнопку «Назад»");
        onView(withId(BACK_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickQuotes() {
        Allure.step("Перейти в раздел «Наша миссия» (цитаты)");
        onView(withId(QUOTES_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkAllMenuItemsDisplayed() {
        Allure.step("Проверить наличие всех пунктов меню навигации");
        onView(withText(MENU_MAIN)).check(matches(isDisplayed()));
        onView(withText(MENU_NEWS)).check(matches(isDisplayed()));
        onView(withText(MENU_ABOUT)).check(matches(isDisplayed()));
    }

    public void checkNewsScreenIsOpened() {
        Allure.step("Проверить, что открыт экран «Новости»");
        onView(withText("Новости"))
                .check(matches(isDisplayed()));
    }

    public void checkAboutScreenIsOpened() {
        Allure.step("Проверить, что открыт экран «О приложении»");
        onView(withText("Версия:"))
                .check(matches(isDisplayed()));
    }

    public void checkQuotesScreenIsOpened() {
        Allure.step("Проверить, что открыт экран с цитатами");
        onView(withText("Главное - жить любя"))
                .check(matches(isDisplayed()));
    }
}
