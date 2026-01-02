package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import ru.iteco.fmhandroid.utils.ToastMatcher;

import static ru.iteco.fmhandroid.utils.WaitForViewAction.waitForView;

import ru.iteco.fmhandroid.R;

public class LoginPage {

    public void logoutIfLoggedIn() {
        try {
            onView(withId(R.id.authorization_image_button))
                    .perform(click());

            onView(withText("Выйти"))
                    .perform(click());
        } catch (Exception e) {
        }
    }

    public void openLoginScreenIfNeeded() {
        try {
            onView(isRoot()).perform(waitForView(3000));
            onView(withText("Авторизация"))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            onView(withId(R.id.authorization_image_button))
                    .perform(click());

            onView(withText("Выйти"))
                    .perform(click());

            onView(withText("Авторизация"))
                    .check(matches(isDisplayed()));
        }
    }

    public void login(String login, String password) {
        checkLoginScreenIsDisplayed();
        onView(isRoot()).perform(waitForView(3000));
        enterLogin(login);
        enterPassword(password);
        clickLoginButton();
        checkUserIsLoggedIn();
    }

    public void logout() {
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("Выйти"))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkToastMessage(String message) {
        onView(withText(message))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void enterLogin(String login) {
        onView(withHint("Логин"))
                .perform(replaceText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        onView(withHint("Пароль"))
                .perform(replaceText(password), closeSoftKeyboard());
    }

    public void clickLoginButton() {
        onView(withId(R.id.enter_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkUserIsLoggedIn() {
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()));
    }

    public void checkLoginScreenIsDisplayed() {
        onView(isRoot()).perform(waitForView(5000));
        onView(withText("Авторизация"))
                .check(matches(isDisplayed()));
    }
}
