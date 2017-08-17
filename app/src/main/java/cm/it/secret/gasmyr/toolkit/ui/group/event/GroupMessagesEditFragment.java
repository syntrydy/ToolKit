package cm.it.secret.gasmyr.toolkit.ui.group.event;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupMessagesEditFragment extends DialogFragment {

    private EditText helloDayMessage;
    private EditText helloNightMessage;
    private Button quit, save;

    public GroupMessagesEditFragment() {
    }

    public static GroupMessagesEditFragment newInstance(String title, String hello, String night) {
        GroupMessagesEditFragment frag = new GroupMessagesEditFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        args.putString("HELLO", hello);
        args.putString("NIGHT", night);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_messages_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getArguments().getString("TITLE", "Edit group messages"));
        String hello=getArguments().getString("HELLO", "Bonjour et bonne journée");
        String night=getArguments().getString("NIGHT", "Bonsoir à vous");
        helloDayMessage = (EditText) view.findViewById(R.id.dialog_day);
        helloDayMessage.setText(hello);
        helloNightMessage = (EditText) view.findViewById(R.id.dialog_night);
        helloNightMessage.setText(night);
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
                String dmessage=helloDayMessage.getText().toString();
                if(dmessage!=null){
                    requestEditGroupMessages(dmessage,helloNightMessage.getText().toString().trim());
                    dismiss();
                }
            }
        });
        helloNightMessage.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setCancelable(false);
    }

    private void requestEditGroupMessages(String hello, String night) {
        GroupEvent event= new GroupEvent();
        event.setCode(GroupEventCode.EDIT_GROUP_MESSAGES);
        event.setGroup(new Group(hello,night));
        EventBus.getDefault().post(event);
    }


}
