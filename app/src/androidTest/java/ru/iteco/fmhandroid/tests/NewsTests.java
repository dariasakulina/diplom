package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.NavigationPage;
import ru.iteco.fmhandroid.utils.TestData;

@RunWith(AndroidJUnit4.class)
@Epic("FMHAndroid")
@Feature("Новости")
public class NewsTests extends BaseTest {

    private NewsPage newsPage;
    private NavigationPage navigationPage;

    @Before
    public void setUp() {
        newsPage = new NewsPage();
        navigationPage = new NavigationPage();
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

        String title = TestData.uniqueTitle(TestData.NEWS_TITLE_CREATE_PREFIX);

        String description = TestData.uniqueDescription(TestData.NEWS_DESCRIPTION_CREATE_PREFIX);

        navigationPage.openNavigationMenu();
        navigationPage.clickNews();

        newsPage.clickEdit();
        newsPage.clickAddNews();

        newsPage.openCategoryDropdown();
        newsPage.selectCategory(TestData.NEWS_CATEGORY_ANNOUNCEMENT);

        newsPage.enterTitle(title);

        newsPage.confirmPublishDate();
        newsPage.confirmPublishTime();

        newsPage.enterDescription(description);

        newsPage.clickSave();

        newsPage.checkNewsWithTitleDisplayed(title);;
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

        newsPage.openCategoryDropdown();
        newsPage.selectCategory(TestData.NEWS_CATEGORY_BIRTHDAY);

        newsPage.clickSave();

        newsPage.checkNewsListIsDisplayed();

        newsPage.clickEditFirstNews();
        newsPage.checkCategoryFieldHasValue(TestData.NEWS_CATEGORY_BIRTHDAY);
    }
}
