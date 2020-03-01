package com.soushetty.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewitemActivity extends AppCompatActivity {

    public static final String REPLY_INTENT ="hello_code" ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
        editText=findViewById(R.id.editbox);

        final Button button=findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                if(TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED,intent);
                }else{
                    String todostring=editText.getText().toString();
                    intent.putExtra(REPLY_INTENT,todostring);
                    setResult(RESULT_OK,intent);
                }
                finish();
            }

        });

    }
}
