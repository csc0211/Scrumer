package com.tanyixiu.scrumer.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tanyixiu.scrumer.R;

public class ProjectEditActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_edit);
    }

    public void doCanceClick(View v) {
        this.finish();
    }

    public void doSaveClick(View v) {
        TextView tvName = (TextView) findViewById(R.id.projectedit_ed_name);
        TextView tvDescription = (TextView) findViewById(R.id.projectedit_ed_descirption);
        TextView tvProjectState = (TextView) findViewById(R.id.projectedit_ed_projectstate);

        String name = tvName.getText().toString();
        String description = tvDescription.getText().toString();
        String projectStateId = (String) tvProjectState.getTag();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "项目名称不能为空", Toast.LENGTH_LONG).show();
            return;
        }
//        if (TextUtils.isEmpty(projectStateId)) {
//            Toast.makeText(this, "项目状态不能为空", Toast.LENGTH_LONG).show();
//            return;
//        }
    }

    public void doStateClick(View v) {

    }
}
