package me.tatocaster.buildnumberoverlaylibrary;

import android.content.Context;
import android.content.Intent;

import me.tatocaster.buildnumberoverlaylibrary.exceptions.NumberOverlayException;
import me.tatocaster.buildnumberoverlaylibrary.utils.Validation;

/**
 * Created by tatocaster on 1/28/17.
 */

public class NumberOverlay {
    private static final String TAG = NumberOverlay.class.getCanonicalName();
    private static Context applicationContext;

    private static final String MULTIPLE_INSTANCE_ERROR_STRING = "Can not initialize multiple times!";


    /**
     * instance
     */
    private static NumberOverlay instance = null;
    private static boolean initialized = false;

    private NumberOverlay() {

    }

    /**
     * initialize this in Application.
     *
     * @param applicationContext The application context
     */
    public static synchronized void initialize(Context applicationContext) {
        if (instance != null || initialized) {
            throw new NumberOverlayException(MULTIPLE_INSTANCE_ERROR_STRING);
        }
        initialized = true;
        instance = new NumberOverlay();
        NumberOverlay.applicationContext = applicationContext;

        Intent intent = new Intent(getApplicationContext(), AccessPermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }


    /**
     * Indicates whether the app has been initialized.
     *
     * @return true if initialized, false if not
     */
    public static synchronized boolean isInitialized() {
        return initialized;
    }

    /**
     * The getter for the context of the current application.
     *
     * @return The context of the current application.
     */
    public static Context getApplicationContext() {
        Validation.sdkInitialized();
        return applicationContext;
    }

}
