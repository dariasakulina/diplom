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

        loginPage.openLoginScreenIfNeeded();
        loginPage.login("login2", "password2");
    }

    @After
    public void tearDown() {
        loginPage.logoutIfLoggedIn();
    }

    /**
     * NEWS_001
     * Создание новости
     */

    @Test
    public void shouldCreateNewsWithValidData() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.clickEdit();
        newsPage.clickAddNews();

        newsPage.openCategoryDropdown();
        newsPage.selectCategory("Объявление");

        newsPage.confirmPublishDate();
        newsPage.confirmPublishTime();

        newsPage.enterDescription("Добавление новости");

        newsPage.clickSave();

        newsPage.checkNewsListIsDisplayed();
    }

    /**
     * NEWS_003
     * Создание "пустой" новости
     */

    @Test
    public void shouldShowMessageWhenCreatingEmptyNews() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.clickEdit();
        newsPage.clickAddNews();
        newsPage.clickSave();

        newsPage.checkEmptyFieldsMessage();
    }

    /**
     * NEWS_004
     * Редактирование новости (смена категории)
     */

    @Test
    public void shouldEditNewsCategory() {

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.clickEdit();
        newsPage.selectFirstNews();
        newsPage.clickEditFirstNews();

        newsPage.changeCategoryRandomly();
        newsPage.clickSave();

        newsPage.checkNewsListIsDisplayed();
    }
}
