package cm.it.secret.gasmyr.toolkit.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.app.ToolKitApp;
import cm.it.secret.gasmyr.toolkit.service.GroupService;
import cm.it.secret.gasmyr.toolkit.ui.group.adapter.GroupListAdapter;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEvent;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEventCode;
import es.dmoral.toasty.Toasty;

public class GroupsListActivity extends AppCompatActivity {
    public static final String GROUP_ID = "GROUPID";

    private RecyclerView recyclerView;
    private static GroupListAdapter adapter;
    private GroupService groupService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iniViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGroupAddDialog();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void iniViews() {
        groupService = new GroupService(((ToolKitApp) getApplication()).getDaoSession());
        recyclerView = (RecyclerView) findViewById(R.id.group_recycler_view);
        adapter = new GroupListAdapter(groupService.getAllGroups(), GroupsListActivity.this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(GroupsListActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void showGroupAddDialog() {
        FragmentManager fm = getSupportFragmentManager();
        GroupAddFragment groupAddFragment = GroupAddFragment.newInstance("New Group");
        groupAddFragment.show(fm, GroupAddFragment.class.getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GroupEvent event) {
        if (event.getCode() == GroupEventCode.GOTO_GROUP_DETAIL) {
            Intent intent = new Intent(GroupsListActivity.this, GroupDetailActivity.class);
            intent.putExtra(GROUP_ID, "" + event.getGroupId());
            startActivity(intent);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewGroupMessageEvent(GroupEvent event) {
        if (event.getCode() == GroupEventCode.ADD_NEW_GROUP) {
            groupService.addGroud(event.getGroup());
            adapter.setList(groupService.getAllGroups());
            adapter.notifyDataSetChanged();
            Toasty.success(getApplicationContext(),"Group added").show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteGroupMessageEvent(GroupEvent event) {
        if (event.getCode() == GroupEventCode.DELETE_GROUP) {
            groupService.deleteGroup(event.getGroupId());
            adapter.setList(groupService.getAllGroups());
            adapter.notifyDataSetChanged();
            Toasty.success(getApplicationContext(),"Group deleted").show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
