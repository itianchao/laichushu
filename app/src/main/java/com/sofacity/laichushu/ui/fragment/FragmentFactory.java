package com.sofacity.laichushu.ui.fragment;

import com.sofacity.laichushu.ui.base.BaseFragment;

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
        }else {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new FindFragment();
                    break;
                case 2:
                    fragment = new WriteFragment();
                    break;
                case 3:
                    fragment = new MsgFragment();
                    break;
                case 4:
                    fragment = new MineFragment();
                    break;
            }
            // 存储到缓存
            mCaches.put(position, fragment);
            return fragment;
        }
    }
}
