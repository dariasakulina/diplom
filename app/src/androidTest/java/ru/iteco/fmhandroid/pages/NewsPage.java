package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ToastMatcher;

public class NewsPage {

    // Кнопка "Все новости"
    private final Matcher<View> allNewsButton =
            withId(R.id.all_news_text_view);

    // Кнопка "Редактировать"
    private final Matcher<View> editNewsButton =
            withId(R.id.edit_news_material_button);

    // Кнопка "+"
    private final Matcher<View> addNewsButton =
            withId(R.id.add_news_image_view);

    // Кнопка "Сохранить"
    private final Matcher<View> saveButton =
            withId(R.id.save_button);

    // Поле "Категория"
    private final Matcher<View> categoryField =
            withId(R.id.news_item_category_text_auto_complete_text_view);

    // Экран списка новостей
    private final Matcher<View> newsList =
            withId(R.id.news_list_recycler_view);

    /* ---------------- actions ---------------- */

    public void openAllNews() {
        onView(allNewsButton).perform(click());
    }

    public void clickEdit() {
        onView(withId(R.id.container_list_news_include))
                .check(matches(isDisplayed()));

        onView(editNewsButton)
                .perform(click());
    }

    public void clickAddNews() {
        onView(addNewsButton).perform(click());
    }

    public void clickSave() {
        onView(saveButton).perform(click());
    }

    public void selectFirstNews() {
        onView(newsList)
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public void changeCategoryToBirthday() {
        onView(categoryField).perform(click());
        onView(withText("День рождения")).perform(click());
    }

    /* ---------------- checks ---------------- */

    public void checkEmptyFieldsMessage() {
        onView(withText("Заполните пустые поля"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkNewsListIsDisplayed() {
        onView(newsList).check(matches(isDisplayed()));
    }
}
