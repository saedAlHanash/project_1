package com.example.myapplication.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import com.example.myapplication.APIs.API;
import com.example.myapplication.APIs.ApiClint;
import com.example.myapplication.APIs.ApiResponse;
import com.example.myapplication.AppConfig;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGroupDialog {

    Context context;
    TextView tvDialogTxt;
    Button button;
   public Dialog dialog;
    @LayoutRes
    int res;

    public AddGroupDialog(Context context, int res) {
        this.context = context;
        this.res = res;
        try {
            this.dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_group);
            tvDialogTxt = dialog.findViewById(R.id.dialog_group_add_text);
            button = dialog.findViewById(R.id.dialog_group_add_add_btn);
        }catch (Exception e){
            Toast.makeText(context, "som thing wrong", Toast.LENGTH_SHORT).show();

        }
        show();
    }

    public TextView getTvDialogTxt() {
        return tvDialogTxt;
    }

    public void show() {
        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

   public void listener (Listener listener) {
        button.setOnClickListener(v -> {
            listener.onAddButtonClickListener();
        });

    }

   public interface  Listener{
        void onAddButtonClickListener();
    }

}
