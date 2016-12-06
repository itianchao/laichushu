package org.geometerplus.android.fbreader;

import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.laichushu.book.R;

import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.fbreader.options.ColorProfile;
import org.geometerplus.zlibrary.core.application.ZLApplication;

/**
 * 设置模式
 */
final class SettingModlePopup extends ZLApplication.PopupPanel {
    final static String ID = "SettingModlePopup";

    private volatile SettingModleWindow myWindow;
    private volatile FBReader myActivity;
    private volatile RelativeLayout myRoot;
    private final FBReaderApp myFBReader;

    SettingModlePopup(FBReaderApp kooReader) {
        super(kooReader);
        myFBReader = kooReader;
    }

    public void setPanelInfo(FBReader activity, RelativeLayout root) {
        myActivity = activity;
        myRoot = root;
    }

    public void runNavigation() {
        if (myWindow == null || myWindow.getVisibility() == View.GONE) {
            Application.showPopup(ID);
        }
    }

    @Override
    protected void show_() {
        myActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        if (myActivity != null) {
            createPanel(myActivity, myRoot);
        }
        if (myWindow != null) {
            myWindow.show();

        }
    }

    @Override
    protected void hide_() {
        if (myWindow != null) {
            myWindow.hide();
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected void update() {
        if (myWindow != null) {

        }
    }

    private void createPanel(FBReader activity, RelativeLayout root) {
        if (myWindow != null && activity == myWindow.getContext()) {
            return;
        }
        activity.getLayoutInflater().inflate(R.layout.txtlightmenu, root);
        myWindow = (SettingModleWindow) root.findViewById(R.id.setting_panel);
        RadioButton sunRbn = (RadioButton)myWindow.findViewById(R.id.rbn_sun) ;
        RadioButton moonRbn = (RadioButton)myWindow.findViewById(R.id.rbn_moon);
        moonRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFBReader.ViewOptions.ColorProfileName.setValue(ColorProfile.NIGHT);
                myFBReader.getViewWidget().reset();
                myFBReader.getViewWidget().repaint();
            }
        });

        sunRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFBReader.ViewOptions.ColorProfileName.setValue(ColorProfile.DAY);
                myFBReader.getViewWidget().reset();
                myFBReader.getViewWidget().repaint();
            }
        });
    }

}