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
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
@Epic("FMHAndroid")
@Feature("Навигация")
public class NavigationTests {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage;
    private NavigationPage navigationPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        navigationPage = new NavigationPage();

        loginPage.openLoginScreenIfNeeded();
        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    /**
     * NAV_001
     * Проверка наличия всех элементов меню
     */
    @Test
    @DisplayName("NAV_001: Проверка наличия всех пунктов меню")
    @Story("Отображение элементов навигации")
    @Description("Проверка, что в навигационном меню отображаются пункты: Главная, Новости, О приложении.")
    public void shouldDisplayAllNavigationMenuItems() {
        navigationPage.openNavigationMenu();
        navigationPage.checkAllMenuItemsDisplayed();
    }

    /**
     * NAV_002
     * Переход в "Новости"
     */
    @Test
    @DisplayName("NAV_002: Переход в раздел «Новости»")
    @Story("Переходы по меню")
    @Description("Проверка перехода в раздел «Новости» через навигационное меню.")
    public void shouldOpenNewsFromNavigationMenu() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkNewsScreenIsOpened();
    }

    /**
     * NAV_003
     * Переход в "О приложении"
     */
    @Test
    @DisplayName("NAV_003: Переход в раздел «О приложении»")
    @Story("Переходы по меню")
    @Description("Проверка перехода в раздел «О приложении» с учетом ограничения навигации из экрана новостей.")
    public void shouldOpenAboutFromNavigationMenu() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkNewsScreenIsOpened();

        navigationPage.openNavigationMenu();
        navigationPage.clickMain();

        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();
        navigationPage.checkAboutScreenIsOpened();
        navigationPage.clickBack();
    }

    /**
     * NAV_004
     * Открытие вкладки с цитатами
     */
    @Test
    @DisplayName("NAV_004: Открытие вкладки с цитатами")
    @Story("Дополнительные экраны")
    @Description("Проверка открытия экрана с цитатами по нажатию на кнопку «Наша миссия» (бабочка).")
    public void shouldOpenQuotesScreen() {
        navigationPage.clickQuotes();
        navigationPage.checkQuotesScreenIsOpened();
    }
}
