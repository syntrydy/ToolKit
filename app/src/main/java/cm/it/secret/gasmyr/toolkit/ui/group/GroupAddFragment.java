package cm.it.secret.gasmyr.toolkit.ui.group;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEvent;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEventCode;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupAddFragment extends DialogFragment {

    private EditText groupName;
    private EditText groupDescription;
    private Button quit, save;

    public GroupAddFragment() {
    }

    public static GroupAddFragment newInstance(String title) {
        GroupAddFragment frag = new GroupAddFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getArguments().getString("TITLE", "New group"));
        groupName = (EditText) view.findViewById(R.id.dialog_day);
        groupDescription = (EditText) view.findViewById(R.id.dialog_night);
        quit = (Button) view.findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=groupName.getText().toString();
                if(name!=null){
                    requestAddNewGroup(name,groupDescription.getText().toString().trim());
                    dismiss();
                }
            }
        });
        groupName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setCancelable(false);
    }

    private void requestAddNewGroup(String name, String description) {
        GroupEvent event= new GroupEvent();
        event.setCode(GroupEventCode.ADD_NEW_GROUP);
        event.setGroup(new Group(name,description, new Date()));
        EventBus.getDefault().post(event);
    }

}
