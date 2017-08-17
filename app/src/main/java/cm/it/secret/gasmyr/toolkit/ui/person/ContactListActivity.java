package cm.it.secret.gasmyr.toolkit.ui.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.app.ToolKitApp;
import cm.it.secret.gasmyr.toolkit.service.PersonContactService;
import cm.it.secret.gasmyr.toolkit.ui.person.adapter.PersonListAdapter;
import cm.it.secret.gasmyr.toolkit.ui.person.event.PersonEvent;
import cm.it.secret.gasmyr.toolkit.ui.person.event.PersonEventCode;
import es.dmoral.toasty.Toasty;

public class ContactListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PersonListAdapter adapter;
    private PersonContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iniViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeletePersonMessageEvent(PersonEvent event) {
        if (event.getCode() == PersonEventCode.REMOVE_PERSON) {
            boolean isDeleted = contactService.deletePerson(event.getPersonId());
            if(isDeleted){
                adapter.setList(contactService.getAllPerson());
                adapter.notifyDataSetChanged();
                Toasty.success(getApplicationContext(),"Contact deleted").show();
            }else{
                Toasty.error(getApplicationContext(),"Something when wrong before deletion").show();
            }

        }
    }

    private void iniViews() {
        contactService = new PersonContactService(((ToolKitApp) getApplication()).getDaoSession());
        recyclerView = (RecyclerView) findViewById(R.id.contact_recycler_view);
        adapter = new PersonListAdapter(contactService.getAllPerson(), ContactListActivity.this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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
