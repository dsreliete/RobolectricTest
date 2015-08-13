package br.com.eliete.robolectrictest;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity activity;
    private Fragment fragment;
    private TextView textView;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testActivityFound() {
        assertNotNull(activity);
    }

    @Test
    public void testTextViewMessage() {
        textView = (TextView) activity.findViewById(R.id.section_label);
        assertNotNull("textview is null", textView);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 1", textView.getText().toString());
    }

    @Test
    public void testButtonAction(){
        Button button = (Button) activity.findViewById(R.id.button);
        assertEquals(button.getVisibility(), View.VISIBLE);
        assertNotNull("button is null", button);
        button.performClick();

        textView = (TextView) activity.findViewById(R.id.section_label);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 3", textView.getText().toString());
    }

    @Test
    public void testMenuItem(){
        RoboMenuItem item = new RoboMenuItem() {
            public int getItemId() {
                return R.id.action_settings;
            }
        };
        activity.onOptionsItemSelected(item);

        fragment = activity.getSupportFragmentManager().findFragmentByTag("Section 4");
        assertNotNull("f is null", fragment);

        textView = (TextView) fragment.getView().findViewById(R.id.section_label);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 4", textView.getText().toString());
    }

    @Test
    public void testNavigationDrawer(){

        activity.onNavigationDrawerItemSelected(0);
        textView = (TextView) activity.findViewById(R.id.section_label);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 1", textView.getText().toString());

        activity.onNavigationDrawerItemSelected(1);
        textView = (TextView) activity.findViewById(R.id.section_label);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 2", textView.getText().toString());

        activity.onNavigationDrawerItemSelected(2);
        textView = (TextView) activity.findViewById(R.id.section_label);
        assertEquals(textView.getVisibility(), View.VISIBLE);
        assertEquals("Fragment 3", textView.getText().toString());
    }

    public void testFragmentsStart(){

        fragment= new MainActivity.PlaceholderFragment().newInstance(1);
        assertNotNull("f is null", fragment);
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        textView = (TextView) fragment.getView().findViewById(R.id.section_label);
        assertEquals("Fragment 1", textView.getText().toString());

        fragment = new MainActivity.PlaceholderFragment().newInstance(2);
        assertNotNull("f is null", fragment);
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        textView = (TextView) fragment.getView().findViewById(R.id.section_label);
        assertEquals("Fragment 2", textView.getText().toString());

        fragment = new MainActivity.PlaceholderFragment().newInstance(3);
        assertNotNull("f is null", fragment);
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        textView = (TextView) fragment.getView().findViewById(R.id.section_label);
        assertEquals("Fragment 3", textView.getText().toString());
    }

}
