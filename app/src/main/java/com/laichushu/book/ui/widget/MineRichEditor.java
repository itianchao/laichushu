package com.laichushu.book.ui.widget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import jp.wasabeef.richeditor.RichEditor;

/**
 * 重写方法 删除图片
 * Created by wangtong on 2016/11/21.
 */

public class MineRichEditor extends RichEditor {
    public MineRichEditor(Context context) {
        super(context);
    }

    public MineRichEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineRichEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        CustomInputConnection connection = new CustomInputConnection(this, false);
        outAttrs.inputType = InputType.TYPE_NULL;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_NONE;
        outAttrs.initialSelStart = -1;
        outAttrs.initialSelEnd = -1;
        return connection;
    }

    public class CustomInputConnection extends BaseInputConnection {

        public CustomInputConnection(View targetView, boolean fullEditor) {
            super(targetView, fullEditor);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            // magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            if (beforeLength == 1 && afterLength == 0) {
                // backspace
                return super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }
}
