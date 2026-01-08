package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@Epic("FMHAndroid")
@Feature("Авторизация")
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
    @DisplayName("AUTH_002: Успешная авторизация с корректными данными")
    @Story("Позитивные сценарии")
    @Description("Проверка, что пользователь может авторизоваться с валидным логином и паролем.")
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
    @DisplayName("AUTH_003: Выход из аккаунта")
    @Story("Позитивные сценарии")
    @Description("Проверка, что пользователь может выйти из аккаунта и вернуться на экран авторизации.")
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
    @DisplayName("AUTH_008: Авторизация с некорректными данными")
    @Story("Негативные сценарии")
    @Description("Проверка отображения сообщения об ошибке при вводе неверного логина или пароля.")
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
    @DisplayName("AUTH_012: Авторизация с пустым паролем")
    @Story("Негативные сценарии")
    @Description("Проверка отображения сообщения об ошибке при пустом пароле.")
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
    @DisplayName("AUTH_013: Авторизация с пустым логином и паролем")
    @Story("Негативные сценарии")
    @Description("Проверка отображения сообщения об ошибке при отсутствии логина и пароля.")
    public void loginWithEmptyLoginAndPassword_shouldShowErrorToast() {
        loginPage.clickLoginButton();

        loginPage.checkToastMessage("Логин и пароль не могут быть пустыми");
    }
}