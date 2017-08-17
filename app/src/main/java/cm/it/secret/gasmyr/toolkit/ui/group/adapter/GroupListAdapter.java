package cm.it.secret.gasmyr.toolkit.ui.group.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEvent;
import cm.it.secret.gasmyr.toolkit.ui.group.event.GroupEventCode;
import cm.it.secret.gasmyr.toolkit.util.DateUtils;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class GroupListAdapter extends RecyclerView.Adapter <GroupListAdapter.MyViewHolder>{
    private List<Group> groups=new ArrayList<>();
    private Context context;

    public GroupListAdapter(List<Group> groupList,Context context) {
        this.groups = groupList;
        this.context=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.group_list_item,parent,false);
        MyViewHolder myHoder = new MyViewHolder(view);
        return myHoder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Group group = groups.get(position);
        holder.name.setText(group.getName());
        holder.description.setText(group.getDescription());
        holder.date.setText(DateUtils.getDateFormatter().format(group.getDate()));
        holder.nbre.setText(group.getPeoples().size() +" M");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.itemView.setBackgroundColor(Color.BLUE);
                requestGoToGroupDetail(group.getId());
                return true;
            }
        });
        holder.deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.deleteGroup.setBackgroundColor(Color.RED);
                requestDeleteGroup(group.getId());
            }
        });

    }

    public void setList(List<Group> allGroups) {
        this.groups=allGroups;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, date,nbre;
        protected ImageButton deleteGroup;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            date = (TextView) view.findViewById(R.id.date);
            nbre = (TextView) view.findViewById(R.id.members);
            deleteGroup=(ImageButton)view.findViewById(R.id.deleteGroup);
        }
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
    private void requestGoToGroupDetail(Long groupId) {
        EventBus.getDefault().post(new GroupEvent(GroupEventCode.GOTO_GROUP_DETAIL,groupId));
    }

    private void requestDeleteGroup(Long groupId) {
        EventBus.getDefault().post(new GroupEvent(GroupEventCode.DELETE_GROUP,groupId));
    }
}
