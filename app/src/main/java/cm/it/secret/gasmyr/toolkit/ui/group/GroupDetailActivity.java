package cm.it.secret.gasmyr.toolkit.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.app.ToolKitApp;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.GroupMember;
import cm.it.secret.gasmyr.toolkit.service.GroupPersonServiceService;
import cm.it.secret.gasmyr.toolkit.service.GroupService;
import cm.it.secret.gasmyr.toolkit.service.PersonContactService;
import cm.it.secret.gasmyr.toolkit.ui.group.adapter.GroupMemberAdapter;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEvent;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEventCode;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupMessagesEditFragment;
import cm.it.secret.gasmyr.toolkit.util.DateUtils;
import es.dmoral.toasty.Toasty;

public class GroupDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupMemberAdapter adapter;
    private GroupService groupService;
    private GroupPersonServiceService groupPersonServiceService;
    private PersonContactService contactService;
    private Group group;
    private Long groupId;
    private TextView name,description,date;
    private ImageButton editmessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            groupId = Long.parseLong(bundle.getString(GroupsListActivity.GROUP_ID));
        }
        iniViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void iniViews(){
        groupService=new GroupService(((ToolKitApp)getApplication()).getDaoSession());
        contactService=new PersonContactService(((ToolKitApp)getApplication()).getDaoSession());
        groupPersonServiceService=new GroupPersonServiceService(((ToolKitApp)getApplication()).getDaoSession());
        recyclerView=(RecyclerView)findViewById(R.id.group_elements_recycler_view);
        name=(TextView)findViewById(R.id.detail_group_name) ;
        description=(TextView)findViewById(R.id.detail_group_description) ;
        date=(TextView)findViewById(R.id.detail_group_date) ;
        editmessages=(ImageButton) findViewById(R.id.edit_messages) ;
        group=groupService.getGroudById(groupId);
        name.setText(group.getName());
        description.setText(group.getDescription());
        date.setText(DateUtils.getDateFormatter().format(group.getDate()));
        adapter=new GroupMemberAdapter(group.getPeoples(),GroupDetailActivity.this);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(GroupDetailActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        editmessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessagesEditDialog();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GroupEvent event) {
        if(event.getCode()== GroupEventCode.REMOVE_USER_FROM_GROUP){
            GroupMember groupMember=groupPersonServiceService.delete(groupId,event.getGroupId());
            if(groupMember!=null){
                group=groupService.getGroudById(groupId);
                boolean removePeople = group.removePeople(contactService.getPersonById(groupMember.getPersonId()));
                if(removePeople){
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toasty.error(getApplicationContext(),"Error when removing user! ").show();
                }
                Toasty.info(getApplicationContext(),"User has been remove from group "+group.getName()).show();
            }else {
                Toasty.error(getApplicationContext(),"Something wrong happen.Please try again! ").show();
            }

        }
        if(event.getCode()== GroupEventCode.EDIT_GROUP_MESSAGES){
            group=groupService.getGroudById(groupId);
            group.setHelloDayMessage(event.getGroup().getHelloDayMessage());
            group.setHelloNightMessage(event.getGroup().getHelloNightMessage());
            groupService.updateGroup(group);
            Toasty.info(getApplicationContext(),"well done "+group.getName()).show();
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
    private void showMessagesEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        GroupMessagesEditFragment groupMessagesEditFragment = GroupMessagesEditFragment.newInstance("Edit Group messages", group.getHelloDayMessage(),group.getHelloNightMessage());
        groupMessagesEditFragment.show(fm, GroupMessagesEditFragment.class.getName());
    }

}
