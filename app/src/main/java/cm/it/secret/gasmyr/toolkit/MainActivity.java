package cm.it.secret.gasmyr.toolkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;

import cm.it.secret.gasmyr.toolkit.apiaibot.webservices.ApiClient;
import cm.it.secret.gasmyr.toolkit.apiaibot.webservices.ToolkitBotService;
import cm.it.secret.gasmyr.toolkit.app.LoadContactService;
import cm.it.secret.gasmyr.toolkit.app.ToolKitApp;
import cm.it.secret.gasmyr.toolkit.app.ToolkitConstants;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.jobs.MyGreeterJob;
import cm.it.secret.gasmyr.toolkit.service.GroupService;
import cm.it.secret.gasmyr.toolkit.ui.group.GroupsListActivity;
import cm.it.secret.gasmyr.toolkit.ui.jobs.ScheduleJobActivity;
import cm.it.secret.gasmyr.toolkit.ui.person.ContactListActivity;
import cm.it.secret.gasmyr.toolkit.util.Utils;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ToolKitApp toolKitApp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private GroupService groupService;
    ToolkitBotService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initApp();
        setupNavigationDrawer(toolbar);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupGreetingJobs() {
        MyGreeterJob.schedulePeriodic();
        Utils.startSpeakerService(getApplicationContext(), "Initialisation");
    }

    private void initApp() {
        toolKitApp = (ToolKitApp) getApplication();
        groupService = new GroupService(toolKitApp.getDaoSession());
        sharedPreferences = getSharedPreferences(ToolkitConstants.TOOLKIT_PREF_NAME, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(ToolkitConstants.IS_TOOLKIT_FIRST_LAUNCH, true)) {
            try {
                setupDefaultGroups();
                setupGreetingJobs();
                loadDefaultData();
                editor = sharedPreferences.edit();
                editor.putBoolean(ToolkitConstants.IS_TOOLKIT_FIRST_LAUNCH, false);
                editor.apply();
            } catch (Exception e) {

            }
        }
        apiInterface = ApiClient.getClient().create(ToolkitBotService.class);
    }

    private void loadDefaultData() {
        Intent intent = new Intent(MainActivity.this, LoadContactService.class);
        startService(intent);
    }

    private void setupNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void setupDefaultGroups() {
        groupService.addGroud(new Group(ToolkitConstants.TOOLKIT_GROUP_ORANGE, "all contact from Orange Network", new Date()));
        groupService.addGroud(new Group(ToolkitConstants.TOOLKIT_GROUP_MTN, "all contact from Mtn Network", new Date()));
        groupService.addGroud(new Group("FRIENDS", "friends contact", new Date()));
        groupService.addGroud(new Group("FAMILY", "my family members", new Date()));
    }

    public void launchGroupsModule(View view) {
        Intent intent = new Intent(getApplicationContext(), GroupsListActivity.class);
        startActivity(intent);
    }

    public void launchContactsModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
        startActivity(intent);
    }

    public void launchScheduleJobsModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ScheduleJobActivity.class);
        startActivity(intent);
    }

    public void launchTodosModule(View view) {
    }
}
