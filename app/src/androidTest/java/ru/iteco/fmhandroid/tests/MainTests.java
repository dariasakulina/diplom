package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
@LargeTest
@Epic("FMHAndroid")
@Feature("Главная")
public class MainTests {

    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private MainPage mainPage;

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        navigationPage = new NavigationPage();
        mainPage = new MainPage();

        loginPage.openLoginScreenIfNeeded();
        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    /**
     * MAIN_002
     * Сворачивание/разворачивание блока новостей
     */

    @Test
    @DisplayName("MAIN_002: Свернуть и развернуть блок новостей на главной")
    @Story("Работа с блоком новостей")
    @Description("Проверяем, что блок новостей на главном экране сворачивается и разворачивается по кнопке.")
    public void shouldExpandAndCollapseNewsBlock() {

        navigationPage.checkNewsScreenIsOpened();

        mainPage.toggleNewsBlock(); // свернуть
        mainPage.toggleNewsBlock(); // развернуть

        mainPage.checkNewsBlockIsDisplayed();
    }

    /**
     * MAIN_003
     * Переход в "Новости" через "Все новости" и возврат на главную
     */

    @Test
    @DisplayName("MAIN_003: Открыть «Все новости» и вернуться на главную")
    @Story("Переходы между экранами")
    @Description("Переходим в раздел новостей через кнопку «Все новости» на главной и возвращаемся обратно через меню.")
    public void shouldOpenNewsFromAllNewsButtonAndReturnToMain() {

        mainPage.clickAllNews();

        navigationPage.checkNewsScreenIsOpened();

        navigationPage.openNavigationMenu();
        navigationPage.clickMain();

        mainPage.checkMainScreenIsOpened();
    }
}

