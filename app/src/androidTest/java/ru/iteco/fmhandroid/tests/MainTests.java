package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
@LargeTest
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

        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    @Test
    public void shouldExpandAndCollapseNewsBlock() {

        navigationPage.checkNewsScreenIsOpened();

        mainPage.toggleNewsBlock(); // свернуть
        mainPage.toggleNewsBlock(); // развернуть

        mainPage.checkNewsBlockIsDisplayed();
    }

    @Test
    public void shouldOpenNewsFromAllNewsButtonAndReturnToMain() {

        mainPage.clickAllNews();

        navigationPage.checkNewsScreenIsOpened();

        navigationPage.openNavigationMenu();
        navigationPage.clickMain();

        mainPage.checkMainScreenIsOpened();
    }
}

