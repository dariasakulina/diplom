package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class LoginTests {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        loginPage.openLoginScreenIfNeeded();
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    /**
     * AUTH_002
     * Авторизация с корректными учетными данными
     */
    @Test
    public void loginWithValidCredentials_shouldLoginSuccessfully() {
        loginPage.enterLogin("login2");
        loginPage.enterPassword("password2");
        loginPage.clickLoginButton();
        loginPage.checkUserIsLoggedIn();
    }

    /**
     * AUTH_003
     * Выход из аккаунта
     */
    @Test
    public void logout_shouldReturnToLoginScreen() {
        loginPage.enterLogin("login2");
        loginPage.enterPassword("password2");
        loginPage.clickLoginButton();

        loginPage.logout();

        loginPage.checkLoginScreenIsDisplayed();
    }

    /**
     * AUTH_008
     * Авторизация с некорректными учетными данными
     */
    @Test
    public void loginWithInvalidCredentials_showsInvalidCredentialsMessage() {
        loginPage.enterLogin("wrong_login");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();

        loginPage.checkToastMessage(
                "Неверный логин или пароль"
        );
    }

    /**
     * AUTH_012
     * Авторизация с "пустым" паролем
     */
    @Test
    public void loginWithEmptyPassword_shouldShowErrorMessage() {
        loginPage.enterLogin("login2");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        loginPage.checkToastMessage("Логин и пароль не могут быть пустыми");
    }

    /**
     * AUTH_013
     * Авторизация с "пустыми" учетными данными
     */
    @Test
    public void loginWithEmptyLoginAndPassword_shouldShowErrorToast() {
        loginPage.clickLoginButton();

        loginPage.checkToastMessage(
                "Логин и пароль не могут быть пустыми"
        );
    }
}