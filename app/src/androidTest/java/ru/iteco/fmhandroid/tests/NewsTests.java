package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage;
    private NewsPage newsPage;
    private NavigationPage navigationPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        newsPage = new NewsPage();
        navigationPage = new NavigationPage();

        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    @Test
    public void shouldShowMessageWhenCreatingEmptyNews() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.openAllNews();
        newsPage.clickEdit();
        newsPage.clickAddNews();
        newsPage.clickSave();

        newsPage.checkEmptyFieldsMessage();
    }

    @Test
    public void shouldEditNewsCategory() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.openAllNews();
        newsPage.selectFirstNews();
        newsPage.clickEdit();

        newsPage.changeCategoryToBirthday();
        newsPage.clickSave();

        newsPage.checkNewsListIsDisplayed();
    }
}
