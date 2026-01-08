package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {

    private final int version = R.id.about_version_title_text_view;

    private final int privacyPolicy = R.id.about_privacy_policy_label_text_view;

    private final int termsOfUse = R.id.about_terms_of_use_label_text_view;

    private final int company = R.id.about_company_info_label_text_view;

    public void checkVersionIsDisplayed() {
        Allure.step("Проверить, что отображается версия приложения");
        onView(withId(version))
                .check(matches(isDisplayed()));
    }

    public void checkPrivacyPolicyIsDisplayed() {
        Allure.step("Проверить, что отображается ссылка на политику конфиденциальности");
        onView(withId(privacyPolicy))
                .check(matches(isDisplayed()));
    }

    public void checkTermsOfUseIsDisplayed() {
        Allure.step("Проверить, что отображается ссылка на пользовательское соглашение");
        onView(withId(termsOfUse))
                .check(matches(isDisplayed()));
    }

    public void checkDeveloperInfoIsDisplayed() {
        Allure.step("Проверить, что отображается информация о разработчике");
        onView(withId(company))
                .check(matches(isDisplayed()));
    }

    public void checkAllAboutElementsDisplayed() {
        Allure.step("Проверить все элементы экрана «О приложении»");
        checkVersionIsDisplayed();
        checkPrivacyPolicyIsDisplayed();
        checkTermsOfUseIsDisplayed();
        checkDeveloperInfoIsDisplayed();
    }
}
