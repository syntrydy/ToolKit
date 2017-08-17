package cm.it.secret.gasmyr.toolkit.ui.group.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEvent;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEventCode;
import es.dmoral.toasty.Toasty;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.MyViewHolder> {

    private List<PersonContact> contacts;
    private Context context;

    public GroupMemberAdapter(List<PersonContact> contacts, Context context) {
        this.contacts = new ArrayList<>(new HashSet<>(contacts));
        this.context = context;
    }

    @Override
    public GroupMemberAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_detail_element, parent, false);
        GroupMemberAdapter.MyViewHolder myViewHolder = new GroupMemberAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final GroupMemberAdapter.MyViewHolder holder, int position) {
        final PersonContact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRemovePersonFromGroup(contact.getId());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toasty.info(context, contacts.size() + " elements").show();
                return false;
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        public ImageButton remove;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.gp_name);
            phone = (TextView) view.findViewById(R.id.gp_phone);
            remove = (ImageButton) view.findViewById(R.id.gp_remove);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private void requestRemovePersonFromGroup(Long personId) {
        EventBus.getDefault().post(new GroupEvent(GroupEventCode.REMOVE_USER_FROM_GROUP, personId));
    }
}
