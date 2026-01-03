package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.pages.AboutPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
public class AboutTests {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private AboutPage aboutPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        navigationPage = new NavigationPage();
        aboutPage = new AboutPage();

        loginPage.openLoginScreenIfNeeded();
        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    /**
     * ABOUT_001
     * Проверка наличия всех элементов "О приложении"
     */

    @Test
    public void shouldDisplayAllAboutElements() {

        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();

        aboutPage.checkAllAboutElementsDisplayed();
        navigationPage.clickBack();
    }
}

