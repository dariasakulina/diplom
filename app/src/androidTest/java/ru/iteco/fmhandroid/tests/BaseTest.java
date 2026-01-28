package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Before;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.TestData;

@RunWith(AndroidJUnit4.class)
@Epic("FMHAndroid")
public abstract class BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    protected LoginPage loginPage;

    @Before
    public void baseSetUp() {
        loginPage = new LoginPage();
        if (!loginPage.isLoggedIn()) {
            loginPage.openLoginScreenIfNeeded();
            loginPage.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        }
    }
    }