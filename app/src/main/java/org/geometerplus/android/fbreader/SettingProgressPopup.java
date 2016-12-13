package org.geometerplus.android.fbreader;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.laichushu.book.R;
import com.laichushu.book.utils.ToastUtil;

import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.text.view.ZLTextView;
import org.geometerplus.zlibrary.text.view.ZLTextWordCursor;

/**
 * 设置进度
 */
final class SettingProgressPopup extends ZLApplication.PopupPanel {
    final static String ID = "SettingProgressPopup";

    private volatile SettingProgressWindow myWindow;
    private volatile FBReader myActivity;
    private volatile RelativeLayout myRoot;
    private ZLTextWordCursor myStartPosition;
    private final FBReaderApp myFBReader;
    private volatile boolean myIsInProgress;
    private ZLTextView.PagePosition pagePosition;
    private SeekBar slider;
    private ZLTextView view;

    SettingProgressPopup(FBReaderApp myFBReader) {
        super(myFBReader);
        this.myFBReader = myFBReader;
    }

    public void setPanelInfo(FBReader activity, RelativeLayout root) {
        myActivity = activity;
        myRoot = root;
    }

    public void runNavigation() {
        if (myWindow == null || myWindow.getVisibility() == View.GONE) {
            myIsInProgress = false;
            Application.showPopup(ID);
        }
    }

    @Override
    protected void show_() {
        if (myActivity != null) {
            createPanel(myActivity, myRoot);
        }
        if (myWindow != null) {
            myWindow.show();
            setupNavigation();
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
        myWindow = null;
        activity.getLayoutInflater().inflate(R.layout.txtprogressmenu_layout, root);
        myWindow = (SettingProgressWindow) root.findViewById(R.id.setting_progress);
        final SeekBar slider = (SeekBar) myWindow.findViewById(R.id.txtprogress_seekbar);//进度条
//		final TextView text = (TextView) myWindow.findViewById(R.id.navigation_text);//进度显示文字
        final ImageView pre_character = (ImageView) myWindow.findViewById(R.id.iv_pre);//前一章
		final ImageView next_character = (ImageView) myWindow.findViewById(R.id.iv_next);//后一章
        pre_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage(pagePosition.Current - 15);
            }
        });

        next_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.getModel().getParagraphsNumber();
                gotoPage(pagePosition.Current + 15);
            }
        });
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private void gotoPage(int page) {
                final ZLTextView view = myFBReader.getTextView();
                if (page == 1) {
                    view.gotoHome();
                } else {
                    view.gotoPage(page);
                }
            }

            private void gotoPagePer(int page) {
                final ZLTextView view = myFBReader.getTextView();
//                if (page == 0) {
//                    view.gotoHome();
//                } else {
                view.gotoPageByPec(page);
                pagePosition = view.pagePosition();//获取当前页码
//                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                myIsInProgress = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                myFBReader.getViewWidget().reset();
                myFBReader.getViewWidget().repaint();
                myIsInProgress = false;
                //y 松手直接进行跳转
//                final ZLTextWordCursor position = myStartPosition; // 返回到起始位置
                if (myStartPosition != null &&
                        !myStartPosition.equals(myFBReader.getTextView().getStartCursor())) {
                    myFBReader.addInvisibleBookmark(myStartPosition);
                    myFBReader.storePosition();
                }
                myStartPosition = null;
//                myKooReader.clearTextCaches();
//                myKooReader.getViewWidget().reset();
//                myKooReader.getViewWidget().repaint();
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
//                    final int page = progress + 1;
//                    final int pagesNumber = seekBar.getMax() + 1;
//                    gotoPage(page);
                    gotoPagePer(progress);
                    ToastUtil.showToast(makeProgressTextPer(myFBReader.getTextView().pagePositionPec()));
//                    text.setText(makeProgressText(page, pagesNumber));
                }
            }
        });
    }
    private String makeProgressTextPer(String progress) {
        final StringBuilder builder = new StringBuilder();
        builder.append(progress);
        final TOCTree tocElement = myFBReader.getCurrentTOCElement();
        if (tocElement != null) {
            builder.append("  ");
            builder.append(tocElement.getText());
        }
        return builder.toString();
    }
    private void gotoPage(int page) {
        view = myFBReader.getTextView();
        if (page == 1) {
            view.gotoHome();
        } else {
            view.gotoPage(page);
        }
//        myKooReader.clearTextCaches();
        ToastUtil.showToast(makeProgressTextPer(myFBReader.getTextView().pagePositionPec()));
        pagePosition = view.pagePosition();
        slider.setProgress(view.pagePosition1());
        myFBReader.getViewWidget().reset();
        myFBReader.getViewWidget().repaint();
    }
    private void setupNavigation() {
        slider = (SeekBar) myWindow.findViewById(R.id.txtprogress_seekbar);
//        final TextView text = (TextView) myWindow.findViewById(R.id.navigation_text);

        final ZLTextView textView = myFBReader.getTextView();
//		final ZLTextView.PagePosition pagePosition = textView.pagePosition();
        pagePosition = textView.pagePosition();
        String progress = textView.pagePositionPec();
//		if (slider.getMax() != pagePosition.Total - 1 || slider.getProgress() != pagePosition.Current - 1) {
//			slider.setMax(pagePosition.Total - 1);
//			slider.setProgress(pagePosition.Current - 1);
//			text.setText(makeProgressTextPer(progress));
//		}
        slider.setMax(textView.pagePosition2());
        slider.setProgress(textView.pagePosition1());
//        text.setText(makeProgressTextPer(progress));
    }
}