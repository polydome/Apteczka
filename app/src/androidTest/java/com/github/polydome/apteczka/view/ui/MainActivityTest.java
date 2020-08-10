package com.github.polydome.apteczka.view.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.github.polydome.apteczka.Apteczka;
import com.github.polydome.apteczka.R;
import com.github.polydome.apteczka.data.db.AppDatabase;
import com.github.polydome.apteczka.data.test.RoomTest;
import com.github.polydome.apteczka.data.test.TestDataModule;
import com.github.polydome.apteczka.di.component.DaggerApplicationComponent;
import com.github.polydome.apteczka.di.module.ApplicationModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest extends RoomTest<AppDatabase> {
    public MainActivityTest() {
        super(AppDatabase.class);
    }

    final String EAN = "2398712763722";

    @Before
    public void setUp() {
        rebuildDatabase();
        ((Apteczka) ApplicationProvider.getApplicationContext()).setApplicationComponent(
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(ApplicationProvider.getApplicationContext()))
                        .dataModule(new TestDataModule(getDatabase()))
                        .build()
        );
    }

    @Test
    public void createMedicine_eanNotExists_medicineCreated() throws InterruptedException {
        ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.add_medicine)).perform(click());

        onView(withId(R.id.ean_input)).perform(typeText(EAN));

        onView(withText(equalToIgnoringCase("add"))).perform(click());

        Thread.sleep(500);

        getDatabase().medicineDao().exists(EAN).test().assertValue(true);
    }

    @Test
    public void createMedicine_eanNotExists_editMedicineLaunched() throws InterruptedException {
        Intents.init();
        ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.add_medicine)).perform(click());

        onView(withId(R.id.ean_input)).perform(typeText(EAN));

        onView(withText(equalToIgnoringCase("add"))).perform(click());

        Thread.sleep(500);

        intended(hasComponent(EditMedicineActivity.class.getName()));
    }
}