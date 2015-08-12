package br.com.eliete.robolectrictest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.junit.runners.model.InitializationError;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

/**
 * Created by eliete-luizalabs on 11/08/15.
 */
@Config(manifest=Config.NONE, constants = BuildConfig.class, sdk = 21)
public class RoboTestRunner extends RobolectricTestRunner {

    public RoboTestRunner( Class<?> testClass ) throws InitializationError
    {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = System.getProperty("android.manifest");
        if (config.manifest().equals(Config.DEFAULT) && manifestProperty != null) {
            String resProperty = System.getProperty("android.resources");
            String assetsProperty = System.getProperty("android.assets");
            return new AndroidManifest(Fs.fileFromPath(manifestProperty), Fs.fileFromPath(resProperty),
                    Fs.fileFromPath(assetsProperty));
        }
        AndroidManifest appManifest = super.getAppManifest(config);
        return appManifest;
    }

    public static void startFragment( Fragment fragment )
    {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).attach().create().visible().get();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}
