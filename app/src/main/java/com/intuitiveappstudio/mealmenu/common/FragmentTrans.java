package com.intuitiveappstudio.mealmenu.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.intuitiveappstudio.mealmenu.R;

public class FragmentTrans {
    public static void addFragment(Fragment f, FragmentActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, f);
        ft.commit();
    }

    public static void replaceFragmentwithOutStack(Fragment f, FragmentActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, f);
        ft.commit();
    }

    public static void replaceFragmentwithStack(Fragment f, FragmentActivity activity, String key) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, f);
        ft.addToBackStack(key);
        ft.commit();
    }

    public static void replaceFragmentwithStringBundle(Fragment f, FragmentActivity activity, String key, String value) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, f);
        Bundle args = new Bundle();
        args.putString(key, value);
        f.setArguments(args);
        ft.commit();
    }

    public static void replaceFragmentwithIntegerBundle(Fragment f, FragmentActivity activity, String key, int value) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, f);
        Bundle args = new Bundle();
        args.putInt(key, value);
        f.setArguments(args);
        ft.commit();
    }
}
