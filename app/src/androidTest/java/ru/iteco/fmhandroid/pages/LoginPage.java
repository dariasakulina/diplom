package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ToastMatcher;

import static ru.iteco.fmhandroid.utils.WaitForViewAction.waitForView;

public class LoginPage {

    public void logoutIfLoggedIn() {
        Allure.step("Выйти из аккаунта (если пользователь залогинен)");
        try {
            onView(withId(R.id.authorization_image_button))
                    .perform(click());

            onView(withText("Выйти"))
                    .perform(click());
        } catch (Exception e) {
        }
    }

    public void openLoginScreenIfNeeded() {
        Allure.step("Открыть экран авторизации (если требуется)");
        try {
            onView(isRoot()).perform(waitForView(3000));
            onView(withText("Авторизация"))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            onView(withId(R.id.authorization_image_button))
                    .perform(click());

            onView(withText("Выйти"))
                    .perform(click());
            onView(isRoot()).perform(waitForView(3000));
            onView(withText("Авторизация"))
                    .check(matches(isDisplayed()));
        }
    }

    public void login(String login, String password) {
        Allure.step("Авторизация: логин='" + login + "', пароль='***'");
        onView(isRoot()).perform(waitForView(3000));
        checkLoginScreenIsDisplayed();
        enterLogin(login);
        enterPassword(password);
        clickLoginButton();
        checkUserIsLoggedIn();
    }

    public void logout() {
        Allure.step("Выйти из аккаунта (явный logout)");
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("Выйти"))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkToastMessage(String message) {
        Allure.step("Проверить toast-сообщение: '" + message + "'");
        onView(withText(message))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void enterLogin(String login) {
        Allure.step("Ввести логин: '" + login + "'");
        onView(withHint("Логин"))
                .perform(replaceText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        Allure.step("Ввести пароль: '***'");
        onView(withHint("Пароль"))
                .perform(replaceText(password), closeSoftKeyboard());
    }

    public void clickLoginButton() {
        Allure.step("Нажать кнопку «Войти»");
        onView(withId(R.id.enter_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkUserIsLoggedIn() {
        Allure.step("Проверить, что пользователь авторизован (видна кнопка профиля/авторизации)");
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()));
    }

    public void checkLoginScreenIsDisplayed() {
        Allure.step("Проверить, что отображается экран авторизации");
        onView(isRoot()).perform(waitForView(3000));
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
        onView(withText("Авторизация")).check(matches(isDisplayed()));
    }
}
