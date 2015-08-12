package br.com.eliete.robolectrictest;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testActivityFound() {
        assertNotNull(activity);
    }

    @Test
    public void testButtonTextViewMessage() {
        TextView textView = (TextView) activity.findViewById(R.id.section_label);
        assertNotNull("textview is null", textView);
        assertEquals("Fragment 1", textView.getText().toString());
    }

    @Test
    public void testButtonAction(){
        Button button = (Button) activity.findViewById(R.id.button);
        assertNotNull("button is null", button);
        button.performClick();
        TextView text = (TextView) activity.findViewById(R.id.section_label);
        assertEquals("Fragment 3", text.getText().toString());
    }

    @Test
    public void testMenuItem(){
        RoboMenuItem item = new RoboMenuItem() {
            public int getItemId() {
                return R.id.action_settings;
            }
        };
        activity.onOptionsItemSelected(item);
        TextView text = (TextView) activity.findViewById(R.id.section_label);
        assertEquals("Fragment 4", text.getText().toString());
    }

    @Test
    public void testNavigationDrawer(){

        activity.onNavigationDrawerItemSelected(0);

        TextView text = null;
        text = (TextView) activity.findViewById(R.id.section_label);
        assertEquals("Fragment 1", text.getText().toString());

        activity.onNavigationDrawerItemSelected(1);
        text = (TextView) activity.findViewById(R.id.section_label);
        assertEquals("Fragment 2", text.getText().toString());

        activity.onNavigationDrawerItemSelected(2);
        text = (TextView) activity.findViewById(R.id.section_label);
        assertEquals("Fragment 3", text.getText().toString());
    }

}
