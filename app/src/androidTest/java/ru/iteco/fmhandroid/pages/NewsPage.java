package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import static ru.iteco.fmhandroid.utils.RecyclerViewChildActions.clickChildViewWithId;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import java.util.Random;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ToastMatcher;

public class NewsPage {

    private final Matcher<View> editNewsButton =
            allOf(
                    withId(R.id.edit_news_material_button),
                    isDisplayed()
            );

    private final Matcher<View> addNewsButton =
            allOf(
                    withId(R.id.add_news_image_view),
                    isDisplayed()
            );

    private final int saveButton = R.id.save_button;

    private final int categoryField = R.id.news_item_category_text_input_layout;

    private final int dropDown = R.id.text_input_end_icon;

    private final int newsList = R.id.news_list_recycler_view;

    private final int descriptionField = R.id.news_item_description_text_input_edit_text;

    private final int publishDateField = R.id.news_item_publish_date_text_input_edit_text;

    private final int publishTimeField = R.id.news_item_publish_time_text_input_edit_text;

    private final int OK = android.R.id.button1;

    public void clickEdit() {
        Allure.step("Нажать кнопку «Редактировать новости» (панель управления)");
        onView(editNewsButton).perform(click());
    }

    public void clickEditFirstNews() {
        Allure.step("Нажать иконку редактирования у первой новости");
        onView(withId(newsList))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(
                                0,
                                clickChildViewWithId(R.id.edit_news_item_image_view)
                        )
                );
    }

    public void clickAddNews() {
        Allure.step("Нажать кнопку «Добавить новость» (+)");
        onView(addNewsButton).perform(click());
    }

    public void clickSave() {
        Allure.step("Нажать кнопку «Сохранить»");
        onView(withId(saveButton)).perform(click());
    }

    public void selectFirstNews() {
        Allure.step("Выбрать первую новость в списке");
        onView(withId(newsList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public void openCategoryDropdown() {
        Allure.step("Открыть выпадающий список категорий (нажать на стрелку)");
        onView(allOf(
                withId(dropDown),
                isDescendantOfA(withId(categoryField)),
                isDisplayed()
        )).perform(click());
    }

    public void selectCategory(String category) {
        Allure.step("Выбрать категорию: " + category);
        onView(withText(category))
                .inRoot(isPlatformPopup())
                .perform(click());
    }

    public void changeCategoryRandomly() {
        Allure.step("Сменить категорию новости случайным образом");
        openCategoryDropdown();
        selectRandomCategory(4);
    }

    private void selectRandomCategory(int categoriesCount) {
        int position = new Random().nextInt(categoriesCount);
        Allure.step("Сменить категорию новости случайным образом");
        onData(anything())
                .inRoot(isPlatformPopup())
                .atPosition(position)
                .perform(click());
    }

    public void confirmPublishDate() {
        Allure.step("Заполнить поле «Дата публикации» (открыть и подтвердить по умолчанию)");
        onView(withId(publishDateField))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(OK))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void confirmPublishTime() {
        Allure.step("Заполнить поле «Время» (открыть и подтвердить по умолчанию)");
        onView(withId(publishTimeField))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(OK))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void enterDescription(String text) {
        Allure.step("Заполнить поле «Описание»");
        onView(withId(descriptionField))
                .check(matches(isDisplayed()))
                .perform(replaceText(text), closeSoftKeyboard());
    }

    public void checkEmptyFieldsMessage() {
        Allure.step("Проверить, что отображается сообщение (toast) «Заполните пустые поля»");
        onView(withText("Заполните пустые поля"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkNewsListIsDisplayed() {
        Allure.step("Проверить, что отображается список новостей");
        onView(withId(newsList)).check(matches(isDisplayed()));
    }
}
