package com.laichushu.book.ui.fragment;

import com.laichushu.book.ui.base.BaseFragment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * fragment工厂类
 * Created by wangtong on 2016/10/17.
 */
public class FragmentFactory {
    private static Map<Integer, BaseFragment> mCaches = new LinkedHashMap<Integer, BaseFragment>();

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = mCaches.get(position);
        if (fragment != null) {
            return fragment;
        } else {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();//首页
                    break;
                case 1:
                    fragment = new FindFragment();//发现
                    break;
                case 2:
                    fragment = new WriteFragment();//写作
                    break;
                case 3:
                    fragment = new MsgFragment();//消息
                    break;
                case 4:
                    fragment = new MineFragment();//个人中心
                    break;
            }
            // 存储到缓存
            mCaches.put(position, fragment);
            return fragment;
        }
    }
}
