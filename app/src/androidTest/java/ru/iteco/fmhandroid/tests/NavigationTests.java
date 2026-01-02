package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
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
    public void shouldDisplayAllNavigationMenuItems() {
        navigationPage.openNavigationMenu();
        navigationPage.checkAllMenuItemsDisplayed();
    }

    /**
     * NAV_002
     * Переход в "Новости"
     */
    @Test
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
    public void shouldOpenQuotesScreen() {
        navigationPage.clickQuotes();
        navigationPage.checkQuotesScreenIsOpened();
    }
}
