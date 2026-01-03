package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    private final int version = R.id.about_version_title_text_view;

    private final int privacyPolicy = R.id.about_privacy_policy_label_text_view;

    private final int termsOfUse = R.id.about_terms_of_use_label_text_view;

    private final int company = R.id.about_company_info_label_text_view;

    public void checkVersionIsDisplayed() {
        onView(withId(version))
                .check(matches(isDisplayed()));
    }

    public void checkPrivacyPolicyIsDisplayed() {
        onView(withId(privacyPolicy))
                .check(matches(isDisplayed()));
    }

    public void checkTermsOfUseIsDisplayed() {
        onView(withId(termsOfUse))
                .check(matches(isDisplayed()));
    }

    public void checkDeveloperInfoIsDisplayed() {
        onView(withId(company))
                .check(matches(isDisplayed()));
    }

    public void checkAllAboutElementsDisplayed() {
        checkVersionIsDisplayed();
        checkPrivacyPolicyIsDisplayed();
        checkTermsOfUseIsDisplayed();
        checkDeveloperInfoIsDisplayed();
    }
}
