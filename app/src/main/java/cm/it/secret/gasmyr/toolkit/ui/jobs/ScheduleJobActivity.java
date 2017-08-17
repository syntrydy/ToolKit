package cm.it.secret.gasmyr.toolkit.ui.jobs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.app.ToolkitConstants;
import cm.it.secret.gasmyr.toolkit.core.jobs.HelloJob;
import cm.it.secret.gasmyr.toolkit.core.jobs.MyGreeterJob;

public class ScheduleJobActivity extends AppCompatActivity {
    private Switch orangeHelloDay, orangeHelloNight;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        sharedPreferences = getSharedPreferences(ToolkitConstants.TOOLKIT_PREF_NAME, MODE_PRIVATE);
       boolean helloDayStatus= sharedPreferences.getBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_DAY_IS_ENABLE,false);
        boolean helloNightStatus= sharedPreferences.getBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_NIGHT_IS_ENABLE,false);
        orangeHelloDay = (Switch) findViewById(R.id.orangeHelloDayButton);
        orangeHelloDay.setChecked(helloDayStatus);
        orangeHelloNight = (Switch) findViewById(R.id.orangeHelloNightButton);
        orangeHelloNight.setChecked(helloNightStatus);
        editor = sharedPreferences.edit();
        orangeHelloDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_DAY_IS_ENABLE, true);
                    orangeHelloDay.setText("NO");
                    HelloJob.schedulePeriodic();
                } else {
                    editor.putBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_DAY_IS_ENABLE, false);
                    orangeHelloDay.setText("YES");
                }
                editor.apply();
            }
        });
        orangeHelloNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_NIGHT_IS_ENABLE, true);
                    orangeHelloNight.setText("NO");
                    HelloJob.schedulePeriodic();
                } else {
                    editor.putBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_NIGHT_IS_ENABLE, false);
                    orangeHelloNight.setText("YES");
                }
                editor.apply();
            }
        });
    }

}
