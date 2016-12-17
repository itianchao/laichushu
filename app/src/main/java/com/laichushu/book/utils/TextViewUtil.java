package com.laichushu.book.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by PCPC on 2016/12/17.
 */

public class TextViewUtil {
    private TextView textView;
    private int start = 0;
    private int end = 0;
    private String strs = "";
    private String TEXT_KEY = "";
    private SpannableStringBuilder style;
    private  NoUnderlineSpan style2;
    private PartOnClickListener partOnClickListener;
    private static TextViewUtil instance;

    private TextViewUtil() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static TextViewUtil getInstance() {
//        if (null == instance) {
//            // 同步处理避免同时生成多个实例
//            synchronized (TextViewUtil.class) {
//                if (null == instance) {
                    instance = new TextViewUtil();
//                }
//            }
//        }
        return instance;
    }

    public void setPartOnClickListener(TextView textView, int start, int end,
                                       PartOnClickListener partOnClickListener) {
        this.partOnClickListener = partOnClickListener;
        this.start = start;
        this.end = end;
        this.textView = textView;
        strs = textView.getText().toString();
        style = new SpannableStringBuilder(strs);
        style2 = new NoUnderlineSpan();//这句话的目的是去除特殊文字下划线效果
        style.setSpan(new ClickSpannable(partOnClickListener), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }

    /**
     * SpannableStringBuilder 点击事件
     */
    private class ClickSpannable extends ClickableSpan implements
            View.OnClickListener {

        private PartOnClickListener onClickListener;

        public ClickSpannable(PartOnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            onClickListener.partOnClick(widget);
        }
    }

    public interface PartOnClickListener {
        public void partOnClick(View v);
    }

    private class NoUnderlineSpan {
    }
}