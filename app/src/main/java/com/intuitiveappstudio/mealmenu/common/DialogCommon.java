package com.intuitiveappstudio.mealmenu.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.intuitiveappstudio.mealmenu.R;


public class DialogCommon extends Dialog implements
        View.OnClickListener {

    private TextView textViewOk;
    private OnDoneClickListner onDoneClikedListner;

    public DialogCommon(Context context, String message) {
        super(context, R.style.dialog_style_simple);
        setContentView(R.layout.layout_dialog);
        ((TextView) findViewById(R.id.textview_error_message)).setText(message);
        textViewOk = ((TextView) findViewById(R.id.textview_popup_ok));
        textViewOk.setOnClickListener(this);

    }

    public DialogCommon(Context context, String message, OnDoneClickListner onDoneClikedListner) {
        super(context, R.style.dialog_style_simple);
        setContentView(R.layout.layout_dialog);
        ((TextView) findViewById(R.id.textview_error_message)).setText(message);
        textViewOk = ((TextView) findViewById(R.id.textview_popup_ok));
        textViewOk.setOnClickListener(this);
        this.onDoneClikedListner = onDoneClikedListner;

    }

    @Override
    public void onClick(View clickedView) {
        if (clickedView == textViewOk) {
            if (onDoneClikedListner != null)
                onDoneClikedListner.onDoneClicked();
            this.cancel();
        }

    }

    public void showCommonDialog() {
        this.show();
    }

    public interface OnDoneClickListner {
        public void onDoneClicked();
    }
}
