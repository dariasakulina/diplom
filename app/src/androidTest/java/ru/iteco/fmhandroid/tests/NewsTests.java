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
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NavigationPage;

@RunWith(AndroidJUnit4.class)
@Epic("FMHAndroid")
@Feature("Новости")
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
    @DisplayName("NEWS_001: Создание новости с корректными данными")
    @Story("Позитивные сценарии")
    @Description("Проверка создания новости с заполнением обязательных полей: категория, дата, время и описание.")
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
    @DisplayName("NEWS_003: Создание пустой новости")
    @Story("Негативные сценарии")
    @Description("Проверка отображения сообщения об ошибке при попытке сохранить новость без заполнения полей.")
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
    @DisplayName("NEWS_004: Редактирование новости (смена категории)")
    @Story("Редактирование новостей")
    @Description("Проверка редактирования существующей новости с изменением категории.")
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
