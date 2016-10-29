package hu.pendroid.dnga.vectorcar;

import android.util.Log;

import com.adrianrobotka.brick.Controller;
import com.adrianrobotka.brick.util.ProcessIndicator;

public class AppController extends Controller {
    private static final String LOGTAG = AppController.class.getSimpleName();
    private static AppController instance = null;

    private AppController() {
    }

    public static AppController getInstance() {
        if (instance != null)
            return instance;
        else
            return instance = new AppController();
    }

    @Override
    public void init(ProcessIndicator indicator) throws IllegalStateException {
        super.init(indicator);
        Log.d(LOGTAG, "AppController.init()");
    }

    protected void createModels() {

    }

    protected void createDrawers() {

    }

    protected void createModifiers() {

    }
}
