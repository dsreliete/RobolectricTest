package br.com.eliete.robolectrictest;

import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static br.com.eliete.robolectrictest.RoboTestRunner.startFragment;
import static org.junit.Assert.assertNotNull;

/**
 * Created by eliete-luizalabs on 06/08/15.
 */
@RunWith(RoboTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FragmentTest {

    private Fragment fragment;

    @Before
    public void setUp() throws Exception {
        fragment = new MainActivity.PlaceholderFragment().newInstance(1);
        startFragment(fragment);
    }

    @Test
    public void testFragmentFound(){
        assertNotNull("Fragment is null", fragment);
    }

}
