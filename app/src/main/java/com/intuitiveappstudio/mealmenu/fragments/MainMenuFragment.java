package com.intuitiveappstudio.mealmenu.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.intuitiveappstudio.mealmenu.R;
import com.intuitiveappstudio.mealmenu.adapters.HorizontalListView;
import com.intuitiveappstudio.mealmenu.adapters.MyCustomAdapter;
import com.intuitiveappstudio.mealmenu.common.AppConstants;
import com.intuitiveappstudio.mealmenu.common.AppLog;
import com.intuitiveappstudio.mealmenu.common.FragmentTrans;
import com.intuitiveappstudio.mealmenu.controllers.MainMenuController;
import com.intuitiveappstudio.mealmenu.models.ListModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainMenuFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<ListModel> listContent;
    private HorizontalListView listview,listviewCircle;
    private PopupWindow popupWindow;

    TextView tv_menu, tv_language, tv_quit;
    ImageView iv_arrow_left, iv_arrow_right,iv_detail_background,bg_lower_arrow_left,
            bg_lower_arrow_right,iv_lower_left_arrow,iv_lower_right_arrow;
    FrameLayout  fLleft,fLRight,flMain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        initialize(rootView);

        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(getActivity(), R.layout.list_row, listContent);
        listview.setAdapter(myCustomAdapter);
        MyCustomAdapter mCircleAdapter=new MyCustomAdapter(getActivity(),R.layout.list_item_circle,listContent);
        listviewCircle.setAdapter(mCircleAdapter);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
           if(adapterView.getId()==R.id.listview){
               AppLog.toast(getActivity(), listContent.get(position).getTitle().toString());
               setLayoutBackground();
           }
        else{
               AppLog.toast(getActivity(), listContent.get(position).getTitle().toString());
               DetailFragment detailFragment= new DetailFragment();
               FragmentTrans.replaceFragmentwithStack(detailFragment,getActivity(),"detailFragment");
           }
    }

    private void initialize(View view) {
        //list view rounded
        listview = (HorizontalListView) view.findViewById(R.id.listview);
        listview.setOnItemClickListener(this);
        //list view circle
        listviewCircle=(HorizontalListView)view.findViewById(R.id.listviewCircle);
        listviewCircle.setOnItemClickListener(this);
        //text view main menu
        tv_menu = (TextView) view.findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(this);
        //text view language
        tv_language = (TextView) view.findViewById(R.id.tv_language);
        tv_language.setOnClickListener(this);
        //text view quit
        tv_quit = (TextView) view.findViewById(R.id.tv_quit);
        tv_quit.setOnClickListener(this);
        //upper left arrow
        iv_arrow_left = (ImageView) view.findViewById(R.id.iv_arrow_left);
        iv_arrow_left.setOnClickListener(this);
        //upper right arrow
        iv_arrow_right = (ImageView) view.findViewById(R.id.iv_arrow_right);
        iv_arrow_right.setOnClickListener(this);
        //lower bg
        iv_detail_background=(ImageView) view.findViewById(R.id.iv_detail_background);
        //lower arrow backgrounds
        bg_lower_arrow_left=(ImageView) view.findViewById(R.id.bg_lower_arrow_left);
        bg_lower_arrow_right=(ImageView) view.findViewById(R.id.bg_lower_arrow_right);
        //lower left arrows
        iv_lower_left_arrow=(ImageView) view.findViewById(R.id.iv_lower_left_arrow);
        iv_lower_left_arrow.setOnClickListener(this);
        //lower right arrow
        iv_lower_right_arrow=(ImageView) view.findViewById(R.id.iv_lower_right_arrow);
        iv_lower_right_arrow.setOnClickListener(this);
        //frame
        fLleft=(FrameLayout)view.findViewById(R.id.fLleft);
        fLRight=(FrameLayout)view.findViewById(R.id.fLRight);
        flMain=(FrameLayout)view.findViewById(R.id.flMain);

        listContent = MainMenuController.getSingletonInstance().getContent();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_menu:
                setLayoutBackground();
                break;
            case R.id.tv_language:
                tvLanguage(v);
                break;
            case R.id.tv_quit:
                getActivity().finish();
                System.exit(0);
                break;
            case R.id.iv_arrow_left:
                listview.scrollTo(-200);
                break;

            case R.id.iv_arrow_right:

                //listview.scrollTo(200);
                break;
            case R.id.iv_lower_left_arrow:
                break;
            case R.id.iv_lower_right_arrow:
                break;
            default:
                break;
        }
    }

    private void tvLanguage(View v){
        PopupWindow popUp = popupWindowsort();
        popUp.showAsDropDown(v, 0, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setLayoutBackground(){
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            flMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_detail_grid_menu));
        } else {
            flMain.setBackground(getResources().getDrawable(R.drawable.bg_detail_grid_menu));
        }
        iv_detail_background.setVisibility(View.VISIBLE);
        fLleft.setVisibility(View.VISIBLE);
        fLRight.setVisibility(View.VISIBLE);
        listviewCircle.setVisibility(View.VISIBLE);
    }

    private PopupWindow popupWindowsort() {
        popupWindow = new PopupWindow(getActivity());
        ArrayList<String> langList = new ArrayList<String>();
        langList.add("English");
        langList.add("Arabic");
        langList.add("French");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                langList);
        ListView listViewSort = new ListView(getActivity());
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListener());
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(listViewSort);
        return popupWindow;
    }
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (position == 0) {
                    MainMenuController.getSingletonInstance().changeLang(AppConstants.ENGLISH, getActivity());
                } else if (position == 1) {
                    MainMenuController.getSingletonInstance().changeLang(AppConstants.ARABIC, getActivity());
                } else {
                    MainMenuController.getSingletonInstance().changeLang(AppConstants.FRENCH, getActivity());
                }
                dismissPopup();
            }

        };
    }
    private void dismissPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
    private void startScroll(AbsListView view)
    {
        try
        {
            Field field = android.widget.AbsListView.class.getDeclaredField("mFlingRunnable");
            field.setAccessible(true);
            Object flingRunnable = field.get(view);
            if (flingRunnable != null)
            {
                Method method = Class.forName("android.widget.AbsListView$FlingRunnable").getDeclaredMethod("endFling");
                method.setAccessible(true);
                method.invoke(flingRunnable);
            }
        }
        catch (Exception e) {

        }
    }
}
