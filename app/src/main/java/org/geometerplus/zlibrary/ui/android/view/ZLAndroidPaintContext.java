/*
 * Copyright (C) 2007-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.zlibrary.ui.android.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.laichushu.book.bean.otherbean.XYBookMarkListBean;
import com.laichushu.book.db.Idea_Table;
import com.laichushu.book.db.Idea_TableDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.book.BookmarkQuery;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.core.fonts.FontEntry;
import org.geometerplus.zlibrary.core.image.ZLImageData;
import org.geometerplus.zlibrary.core.options.ZLBooleanOption;
import org.geometerplus.zlibrary.core.util.SystemInfo;
import org.geometerplus.zlibrary.core.util.ZLColor;
import org.geometerplus.zlibrary.core.view.ZLPaintContext;
import org.geometerplus.zlibrary.text.view.ZLTextHighlighting;
import org.geometerplus.zlibrary.ui.android.image.ZLAndroidImageData;
import org.geometerplus.zlibrary.ui.android.util.ZLAndroidColorUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ZLAndroidPaintContext extends ZLPaintContext {
    public static ZLBooleanOption AntiAliasOption = new ZLBooleanOption("Fonts", "AntiAlias", true);
    public static ZLBooleanOption DeviceKerningOption = new ZLBooleanOption("Fonts", "DeviceKerning", false);
    public static ZLBooleanOption DitheringOption = new ZLBooleanOption("Fonts", "Dithering", false);
    public static ZLBooleanOption SubpixelOption = new ZLBooleanOption("Fonts", "Subpixel", false);

    private final Canvas myCanvas;
    private final Paint myTextPaint = new Paint();// 文字画笔
    private final Paint myLinePaint = new Paint();
    private final Paint myFillPaint = new Paint();
    private final Paint myOutlinePaint = new Paint();
    private Integer drawOffect = 10;

    public static final class Geometry {
        final Size ScreenSize;
        final Size AreaSize;
        final int LeftMargin;
        final int TopMargin;

        public Geometry(int screenWidth, int screenHeight, int width, int height, int leftMargin, int topMargin) {
            ScreenSize = new Size(screenWidth, screenHeight);
            AreaSize = new Size(width, height);
            LeftMargin = leftMargin;
            TopMargin = topMargin;
        }
    }

    private final Geometry myGeometry;
    private final int myScrollbarWidth;

    private ZLColor myBackgroundColor = new ZLColor(0, 0, 0);

    public ZLAndroidPaintContext(SystemInfo systemInfo, Canvas canvas, Geometry geometry, int scrollbarWidth) {
        super(systemInfo);
        myCanvas = canvas;
        myGeometry = geometry;
        myScrollbarWidth = scrollbarWidth;
        myTextPaint.setLinearText(false);
        myTextPaint.setAntiAlias(AntiAliasOption.getValue());
        if (DeviceKerningOption.getValue()) {
            myTextPaint.setFlags(myTextPaint.getFlags() | Paint.DEV_KERN_TEXT_FLAG);
        } else {
            myTextPaint.setFlags(myTextPaint.getFlags() & ~Paint.DEV_KERN_TEXT_FLAG);
        }
        myTextPaint.setDither(DitheringOption.getValue());
        myTextPaint.setSubpixelText(SubpixelOption.getValue());

        myLinePaint.setStyle(Paint.Style.STROKE);

        myFillPaint.setAntiAlias(AntiAliasOption.getValue());

        myOutlinePaint.setAntiAlias(true);
        myOutlinePaint.setDither(true);
        myOutlinePaint.setStrokeWidth(4);
        myOutlinePaint.setStyle(Paint.Style.STROKE);
        myOutlinePaint.setPathEffect(new CornerPathEffect(5));
        myOutlinePaint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1, 1}, .4f, 6f, 3.5f));
    }

    private static ZLFile ourWallpaperFile;
    private static Bitmap ourWallpaper;
    private static FillMode ourFillMode;

    @Override
    public void clear(ZLFile wallpaperFile, FillMode mode) {
        if (!wallpaperFile.equals(ourWallpaperFile) || mode != ourFillMode) {
            ourWallpaperFile = wallpaperFile;
            ourFillMode = mode;
            ourWallpaper = null;
            try {
                final Bitmap fileBitmap =
                        BitmapFactory.decodeStream(wallpaperFile.getInputStream());
                switch (mode) {
                    default:
                        ourWallpaper = fileBitmap;
                        break;
                    case tileMirror: {
                        final int w = fileBitmap.getWidth();
                        final int h = fileBitmap.getHeight();
                        final Bitmap wallpaper = Bitmap.createBitmap(2 * w, 2 * h, fileBitmap.getConfig());
                        final Canvas wallpaperCanvas = new Canvas(wallpaper);
                        final Paint wallpaperPaint = new Paint();

                        Matrix m = new Matrix();
                        wallpaperCanvas.drawBitmap(fileBitmap, m, wallpaperPaint);
                        m.preScale(-1, 1);
                        m.postTranslate(2 * w, 0);
                        wallpaperCanvas.drawBitmap(fileBitmap, m, wallpaperPaint);
                        m.preScale(1, -1);
                        m.postTranslate(0, 2 * h);
                        wallpaperCanvas.drawBitmap(fileBitmap, m, wallpaperPaint);
                        m.preScale(-1, 1);
                        m.postTranslate(-2 * w, 0);
                        wallpaperCanvas.drawBitmap(fileBitmap, m, wallpaperPaint);
                        ourWallpaper = wallpaper;
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        if (ourWallpaper != null) {
            myBackgroundColor = ZLAndroidColorUtil.getAverageColor(ourWallpaper);
            final int w = ourWallpaper.getWidth();
            final int h = ourWallpaper.getHeight();
            final Geometry g = myGeometry;
            switch (mode) {
                case fullscreen: {
                    final Matrix m = new Matrix();
                    m.preScale(1f * g.ScreenSize.Width / w, 1f * g.ScreenSize.Height / h);
                    m.postTranslate(-g.LeftMargin, -g.TopMargin);
                    myCanvas.drawBitmap(ourWallpaper, m, myFillPaint);
                    break;
                }
                case stretch: {
                    final Matrix m = new Matrix();
                    final float sw = 1f * g.ScreenSize.Width / w;
                    final float sh = 1f * g.ScreenSize.Height / h;
                    final float scale;
                    float dx = g.LeftMargin;
                    float dy = g.TopMargin;
                    if (sw < sh) {
                        scale = sh;
                        dx += (scale * w - g.ScreenSize.Width) / 2;
                    } else {
                        scale = sw;
                        dy += (scale * h - g.ScreenSize.Height) / 2;
                    }
                    m.preScale(scale, scale);
                    m.postTranslate(-dx, -dy);
                    myCanvas.drawBitmap(ourWallpaper, m, myFillPaint);
                    break;
                }
                case tileVertically: {
                    final Matrix m = new Matrix();
                    final int dx = g.LeftMargin;
                    final int dy = g.TopMargin % h;
                    m.preScale(1f * g.ScreenSize.Width / w, 1);
                    m.postTranslate(-dx, -dy);
                    for (int ch = g.AreaSize.Height + dy; ch > 0; ch -= h) {
                        myCanvas.drawBitmap(ourWallpaper, m, myFillPaint);
                        m.postTranslate(0, h);
                    }
                    break;
                }
                case tileHorizontally: {
                    final Matrix m = new Matrix();
                    final int dx = g.LeftMargin % w;
                    final int dy = g.TopMargin;
                    m.preScale(1, 1f * g.ScreenSize.Height / h);
                    m.postTranslate(-dx, -dy);
                    for (int cw = g.AreaSize.Width + dx; cw > 0; cw -= w) {
                        myCanvas.drawBitmap(ourWallpaper, m, myFillPaint);
                        m.postTranslate(w, 0);
                    }
                    break;
                }
                case tile:
                case tileMirror: {
                    final int dx = g.LeftMargin % w;
                    final int dy = g.TopMargin % h;
                    final int fullw = g.AreaSize.Width + dx;
                    final int fullh = g.AreaSize.Height + dy;
                    for (int cw = 0; cw < fullw; cw += w) {
                        for (int ch = 0; ch < fullh; ch += h) {
                            myCanvas.drawBitmap(ourWallpaper, cw - dx, ch - dy, myFillPaint);
                        }
                    }
                    break;
                }
            }
        } else {
            clear(new ZLColor(128, 128, 128));
        }
    }

    @Override
    public void clear(ZLColor color) {
        myBackgroundColor = color;
        myFillPaint.setColor(ZLAndroidColorUtil.rgb(color));
        myCanvas.drawRect(0, 0, myGeometry.AreaSize.Width, myGeometry.AreaSize.Height, myFillPaint);
    }

    @Override
    public ZLColor getBackgroundColor() {
        return myBackgroundColor;
    }

    public void fillPolygon(int[] xs, int[] ys) {
        final Path path = new Path();
        final int last = xs.length - 1;
        path.moveTo(xs[last], ys[last]);
        for (int i = 0; i <= last; ++i) {
            path.lineTo(xs[i], ys[i]);
        }
        myCanvas.drawPath(path, myFillPaint);
    }

    public void drawPolygonalLine(int[] xs, int[] ys) {
        final Path path = new Path();
        final int last = xs.length - 1;
        path.moveTo(xs[last], ys[last]);
        for (int i = 0; i <= last; ++i) {
            path.lineTo(xs[i], ys[i]);
        }
        myCanvas.drawPath(path, myLinePaint);
    }

    public void drawOutline(int[] xs, int[] ys) {
        final int last = xs.length - 1;
        int xStart = (xs[0] + xs[last]) / 2;
        int yStart = (ys[0] + ys[last]) / 2;
        int xEnd = xStart;
        int yEnd = yStart;
        if (xs[0] != xs[last]) {
            if (xs[0] > xs[last]) {
                xStart -= 5;
                xEnd += 5;
            } else {
                xStart += 5;
                xEnd -= 5;
            }
        } else {
            if (ys[0] > ys[last]) {
                yStart -= 5;
                yEnd += 5;
            } else {
                yStart += 5;
                yEnd -= 5;
            }
        }

        final Path path = new Path();
        path.moveTo(xStart, yStart);
        for (int i = 0; i <= last; ++i) {
            path.lineTo(xs[i], ys[i]);
        }
        path.lineTo(xEnd, yEnd);
        myCanvas.drawPath(path, myOutlinePaint);
    }

    @Override
    protected void setFontInternal(List<FontEntry> entries, int size, boolean bold, boolean italic, boolean underline, boolean strikeThrought) {
        Typeface typeface = null;
        for (FontEntry e : entries) {
            typeface = AndroidFontUtil.typeface(getSystemInfo(), e, bold, italic);
            if (typeface != null) {
                break;
            }
        }
        myTextPaint.setTypeface(typeface);
        myTextPaint.setTextSize(size);
        myTextPaint.setUnderlineText(underline);
        myTextPaint.setStrikeThruText(strikeThrought);
    }

    @Override
    public void setTextColor(ZLColor color) {
        if (color != null) {
            myTextPaint.setColor(ZLAndroidColorUtil.rgb(color));
        }
    }

    @Override
    public void setLineColor(ZLColor color) {
        if (color != null) {
            myLinePaint.setColor(ZLAndroidColorUtil.rgb(color));
            myOutlinePaint.setColor(ZLAndroidColorUtil.rgb(color));
        }
    }

    @Override
    public void setLineWidth(int width) {
        myLinePaint.setStrokeWidth(width);
    }

    @Override
    public void setFillColor(ZLColor color, int alpha) {
        if (color != null) {
            myFillPaint.setColor(ZLAndroidColorUtil.rgba(color, alpha));
        }
    }

    public int getWidth() {
        return myGeometry.AreaSize.Width - myScrollbarWidth;
    }

    public int getHeight() {
        return myGeometry.AreaSize.Height;
    }

    @Override
    public int getStringWidth(char[] string, int offset, int length) {
        boolean containsSoftHyphen = false;
        for (int i = offset; i < offset + length; ++i) {
            if (string[i] == (char) 0xAD) {
                containsSoftHyphen = true;
                break;
            }
        }
        if (!containsSoftHyphen) {
            return (int) (myTextPaint.measureText(new String(string, offset, length)) + 0.5f);
        } else {
            final char[] corrected = new char[length];
            int len = 0;
            for (int o = offset; o < offset + length; ++o) {
                final char chr = string[o];
                if (chr != (char) 0xAD) {
                    corrected[len++] = chr;
                }
            }
            return (int) (myTextPaint.measureText(corrected, 0, len) + 0.5f);
        }
    }

    @Override
    protected int getSpaceWidthInternal() {
        return (int) (myTextPaint.measureText(" ", 0, 1) + 0.5f);
    }

    @Override
    protected int getCharHeightInternal(char chr) {
        final Rect r = new Rect();
        final char[] txt = new char[]{chr};
        myTextPaint.getTextBounds(txt, 0, 1, r);
        return r.bottom - r.top;
    }

    @Override
    protected int getStringHeightInternal() {
        return (int) (myTextPaint.getTextSize() + 0.5f);
    }

    @Override
    protected int getDescentInternal() {
        return (int) (myTextPaint.descent() + 0.5f);
    }

    @Override
    public void drawString(int x, int y, char[] string, int offset, int length) {
        boolean containsSoftHyphen = false;
        for (int i = offset; i < offset + length; ++i) {
            if (string[i] == (char) 0xAD) {
                containsSoftHyphen = true;
                break;
            }
        }
        if (!containsSoftHyphen) {
            myCanvas.drawText(string, offset, length, x, y, myTextPaint);
        } else {
            final char[] corrected = new char[length];
            int len = 0;
            for (int o = offset; o < offset + length; ++o) {
                final char chr = string[o];
                if (chr != (char) 0xAD) {
                    corrected[len++] = chr;
                }
            }
            myCanvas.drawText(corrected, 0, len, x, y, myTextPaint);
        }
    }

    @Override
    public Size imageSize(ZLImageData imageData, Size maxSize, ScalingType scaling) {
        final Bitmap bitmap = ((ZLAndroidImageData) imageData).getBitmap(maxSize, scaling);
        return (bitmap != null && !bitmap.isRecycled())
                ? new Size(bitmap.getWidth(), bitmap.getHeight()) : null;
    }

    @Override
    public void drawImage(int x, int y, ZLImageData imageData, Size maxSize, ScalingType scaling, ColorAdjustingMode adjustingMode) {
        final Bitmap bitmap = ((ZLAndroidImageData) imageData).getBitmap(maxSize, scaling);
        if (bitmap != null && !bitmap.isRecycled()) {
            switch (adjustingMode) {
                case LIGHTEN_TO_BACKGROUND:
                    myFillPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
                    break;
                case DARKEN_TO_BACKGROUND:
                    myFillPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
                    break;
                case NONE:
                    break;
            }
            myCanvas.drawBitmap(bitmap, x, y - bitmap.getHeight(), myFillPaint);
            myFillPaint.setXfermode(null);
        }
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1) {
        final Canvas canvas = myCanvas;
        final Paint paint = myLinePaint;
        paint.setAntiAlias(false);
        canvas.drawLine(x0, y0, x1, y1, paint);
        canvas.drawPoint(x0, y0, paint);
        canvas.drawPoint(x1, y1, paint);
        paint.setAntiAlias(true);
    }

    private int offsetY;
    private int position = 0;
    private ArrayList<XYBookMarkListBean> xlist = new ArrayList<>();
    private FBReaderApp fbReaderApp = (FBReaderApp) FBReaderApp.Instance();
    public void drawLine(int[] xs, int[] ys, ZLTextHighlighting mbookmark) {
        if (fbReaderApp.getTextView().pagePosition().Current != position){
            position = fbReaderApp.getTextView().pagePosition().Current;
            xlist.clear();
        }
        offsetY = (int) myTextPaint.getTextSize();
        LinkedList<Integer> tmpXs = new LinkedList<>();
        LinkedList<Integer> tmpYs = new LinkedList<>();
        int nextX, nextY;

        for (int i = 0; i < xs.length - 1; i++) {
            nextX = xs[i + 1];
            nextY = ys[i + 1];
            if (xs[i] < nextX + 2 && xs[i] > nextX - 2 && ys[i] > nextY + 2) {
                tmpXs.add(xs[i]);
                tmpYs.add(ys[i]);
                i++;
            } else if (xs[i] < nextX + 2 && xs[i] > nextX - 2 && ys[i] < nextY - 2) {
                i++;
                tmpXs.add(xs[i]);
                tmpYs.add(ys[i]);
            } else {
                tmpXs.add(xs[i]);
                tmpYs.add(ys[i]);
            }
        }

        tmpXs.add(xs[xs.length - 1]);
        tmpYs.add(ys[ys.length - 1]);

        xs = new int[tmpXs.size()];
        ys = new int[tmpXs.size()];
        int count = 0;
        for (int xx : tmpXs) {
            xs[count++] = xx;
        }
        count = 0;
        for (int yy : tmpYs) {
            ys[count++] = yy;
        }

//        for(int i=0; i<xs.length ; i++){
//            myCanvas.drawCircle(xs[i], ys[i],3 ,myOutlinePaint);
//        }


        final int last = xs.length - 1;
        for (int i = 0; i < ys.length - 1; i++) {
            for (int j = 0; j < ys.length - i - 1; j++) {
                if (ys[j] > ys[j + 1]) {
                    int tmp = ys[j];
                    ys[j] = ys[j + 1];
                    ys[j + 1] = tmp;

                    int tmp2 = xs[j];
                    xs[j] = xs[j + 1];
                    xs[j + 1] = tmp2;
                }
            }
        }

        List<Integer> drawY = new ArrayList<>();
        List<Integer> templeft = new ArrayList<>();
        List<Integer> tempRight = new ArrayList<>();
        for (int i = 0; i <= last - 1; ++i) {
            if (ys[i] < ys[i + 1] + offsetY && ys[i] > ys[i + 1] - offsetY) {
                if (drawY.contains(ys[i] - 1)) { // ÒÑ¾­´æÔÚ
                    int index = drawY.indexOf(ys[i] - 1);
                    removeAtPosItem(xs[i], templeft, tempRight, index);
                    continue;
                } else if (drawY.contains(ys[i] + 1)) {
                    int index = drawY.indexOf(ys[i] + 1);
                    removeAtPosItem(xs[i], templeft, tempRight, index);
                } else if (drawY.contains(ys[i])) {
                    int index = drawY.indexOf(ys[i]);
                    removeAtPosItem(xs[i], templeft, tempRight, index);
                } else {
                    drawY.add(ys[i]);
                    if (xs[i] < xs[i + 1]) {
                        templeft.add(xs[i]);
                        tempRight.add(xs[i + 1]);
                    } else {
                        templeft.add(xs[i + 1]);
                        tempRight.add(xs[i]);
                    }
                }
            }
        }
        int tmp = myOutlinePaint.getColor();
        float tmpSize = myOutlinePaint.getStrokeWidth();
        myOutlinePaint.setColor(myFillPaint.getColor());
        myOutlinePaint.setStrokeWidth(5);
        for (int i = 0; i < drawY.size(); i++) {
//            LoggerUtil.e(templeft.get(i)+","+tempRight.get(i)+","+drawY.get(i)+"\n");
            if (i != 0 && drawY.get(i) - myTextPaint.getTextSize() > drawY.get(i - 1) || i == 0) { // ÅÅ³ýË«ÖØÏß
                myCanvas.drawLine(templeft.get(i), drawY.get(i) + drawOffect, tempRight.get(i), drawY.get(i) + drawOffect, myOutlinePaint);
//                if (i == 0) {
//                    myCanvas.drawLine(templeft.get(i), drawY.get(i) + drawOffect, tempRight.get(i), drawY.get(i) + drawOffect, myOutlinePaint);
//                } else if (i != drawY.size() - 1) {
//                    myCanvas.drawLine(37, drawY.get(i) + drawOffect, 1008, drawY.get(i) + drawOffect, myOutlinePaint);
//                } else {
//                }
            }
            //画标记
            if (i == drawY.size() - 1) {
                if (mbookmark != null) {
                    String number = dispose(mbookmark, tempRight.get(i) + 10, drawY.get(i) + 18);
                    if (number == null) {
                        return;
                    }
                    myOutlinePaint.setTextSize(20);
                    myOutlinePaint.setStrokeWidth(2);
                    if (number.length() > 1) {
                        myCanvas.drawText(number, tempRight.get(i) + 7, drawY.get(i) + 18, myOutlinePaint);
                    } else {
                        myCanvas.drawText(number, tempRight.get(i) + 10, drawY.get(i) + 18, myOutlinePaint);
                    }
                    myOutlinePaint.setStrokeWidth(5);
                    myOutlinePaint.setStyle(Paint.Style.STROKE);
                    myCanvas.drawCircle(tempRight.get(i) + 15, drawY.get(i) + 12, 18, myOutlinePaint);// 小圆
                }
            }
        }
        myOutlinePaint.setColor(tmp);
        myOutlinePaint.setStrokeWidth(tmpSize);
    }
    //处理
    private String dispose(ZLTextHighlighting h, int x, int y) {
        FBReaderApp fbReaderApp = (FBReaderApp) FBReaderApp.Instance();
        BookCollectionShadow collection = (BookCollectionShadow) fbReaderApp.Collection;
        List<Bookmark> allmark = new ArrayList<>();
        BookmarkQuery query = new BookmarkQuery(fbReaderApp.getCurrentBook(), 10000);
        List<Bookmark> bookmarks = collection.bookmarks(query);
        allmark.addAll(bookmarks);

        Bookmark mbookmark = null;
        for (Bookmark bookmark : allmark) {
            if (bookmark.getParagraphIndex() == h.getStartPosition().getParagraphIndex()
                    && bookmark.getCharIndex() == h.getStartPosition().getCharIndex()
                    && bookmark.getEnd().getParagraphIndex() == h.getEndPosition().getParagraphIndex()
                    && bookmark.getEnd().getCharIndex() == h.getEndPosition().getCharIndex()) {
                mbookmark = bookmark;
                break;
            }
        }
        if (mbookmark == null) {
            return null;
        }
        //=======================================================
        Idea_TableDao dao = BaseApplication.getDaoSession(BaseApplication.getContext()).getIdea_TableDao();
        List<Idea_Table> list = dao.queryBuilder().where(Idea_TableDao.Properties.BookId.eq(mbookmark.getBookId())
                , Idea_TableDao.Properties.Content.eq(mbookmark.getText())
                , Idea_TableDao.Properties.Uid.eq(mbookmark.getUid())).build().list();
        if (list == null || list.isEmpty()) {
            Idea_Table entity = new Idea_Table(null, (int) mbookmark.getBookId(), mbookmark.getUid(), mbookmark.getStyleId() + "", x, y, mbookmark.getText());
            dao.insert(entity);
        } else {
            Idea_Table idea_table = list.get(0);
            idea_table.setX(x);
            idea_table.setY(y);
            dao.update(idea_table);
        }
        int length = 1;
        if (!list.isEmpty()) {
            xlist.add(new XYBookMarkListBean(x,y,list.get(0)));
            length = list.get(0).getContent().length();
            if (length == 0){
                length = 1;
            }
        }
        FBReader.myMainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FBReader.myMainView.setShowIdea(false);
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    for (XYBookMarkListBean bean : xlist) {
                        int dip = UIUtil.dip2px(20);
                        if ((event.getX() >= bean.getX() - dip && event.getX() <= bean.getX()  + dip) && ((event.getY() >= bean.getY()  - dip && event.getY() <= bean.getY() + dip))){
                            FBReader.myMainView.setShowIdea(true);
                            ToastUtil.showToast(bean.getI().getContent());
                            return false;
                        }

                    }
                }
                return  FBReader.myMainView.onTouchEvent(event);
            }
        });
        return "1";
    }
    @Override
    public void drawLine(int[] xs, int[] ys) {

    }

    private void removeAtPosItem(int x, List<Integer> templeft, List<Integer> tempRight, int index) {
        if (x < templeft.get(index)) {
            templeft.remove(index);
            templeft.add(x);
            return;
        }
        if (x > tempRight.get(index)) {
            tempRight.remove(index);
            tempRight.add(x);
            return;
        }
    }

    @Override
    public void fillRectangle(int x0, int y0, int x1, int y1) {
        if (x1 < x0) {
            int swap = x1;
            x1 = x0;
            x0 = swap;
        }
        if (y1 < y0) {
            int swap = y1;
            y1 = y0;
            y0 = swap;
        }
        myCanvas.drawRect(x0, y0, x1 + 1, y1 + 1, myFillPaint);
    }

    @Override
    public void fillCircle(int x, int y, int radius) {
        myCanvas.drawCircle(x, y, radius, myFillPaint);
    }
//                if ((event.getX() > x - 20 && event.getX() < x + 20) && ((event.getY() > y - 10 && event.getY() < y + 10))) {
}
