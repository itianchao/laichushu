package org.fbreader.util;

import com.laichushu.book.db.Idea_Table;
import com.laichushu.book.db.Idea_TableDao;
import com.laichushu.book.global.BaseApplication;

import org.geometerplus.fbreader.book.Bookmark;

import java.util.Collections;
import java.util.List;

/**
 * FBReader utils
 * Created by wangtong on 2017/2/4.
 */

public class FBReaderWindowUtil {

    private static Idea_TableDao dao = BaseApplication.getDaoSession(BaseApplication.getContext()).getIdea_TableDao();

    /**
     * 删除想法表 条目
     *
     * @param bookmark
     */
    public static void deleteBookmarkForIdea_Table(Bookmark bookmark) {
        List<Idea_Table> list = getIdeaList(bookmark);
        if (!list.isEmpty()) {
            Idea_Table idea = list.get(0);
            if (idea != null) dao.delete(idea);
        }
    }

    /**
     * 编辑想法
     *
     * @param bookmark
     * @param content
     */
    public static void saveBookmark(Bookmark bookmark, String content) {
        List<Idea_Table> list = getIdeaList(bookmark);
        if (!list.isEmpty()){
            Idea_Table idea_table = list.get(0);
            dao.deleteInTx(list);
            idea_table.setContent(content);
            dao.update(idea_table);
        }
    }

    /**
     * 获取想法集合
     * @param myBookmark
     * @return
     */
    private static List<Idea_Table> getIdeaList(Bookmark myBookmark) {
        String uid = myBookmark.getUid();
        List<Idea_Table> list = dao.queryBuilder().where(
                Idea_TableDao.Properties.Uid.eq(uid)
        ).build().list();
        return list != null ? list : Collections.<Idea_Table>emptyList();
    }
}
