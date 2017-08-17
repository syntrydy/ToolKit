package cm.it.secret.gasmyr.toolkit.ui.person.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.ui.person.event.PersonEvent;
import cm.it.secret.gasmyr.toolkit.ui.person.event.PersonEventCode;
import es.dmoral.toasty.Toasty;

/**
 * Created by gasmyr.mougang on 6/4/17.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.MyViewHolder> {

    private List<PersonContact> personContactList = new ArrayList<>();
    private Context context;

    public PersonListAdapter(List<PersonContact> personContacts, Context context) {
        this.personContactList = personContacts;
        this.context = context;
    }

    public void setList(List<PersonContact> allPerson) {
        this.personContactList = allPerson;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        protected ImageButton remove;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.person_name);
            phone = (TextView) view.findViewById(R.id.person_phone);
            remove = (ImageButton) view.findViewById(R.id.removePerson);
        }
    }

    @Override
    public PersonListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_list_item, parent, false);
        PersonListAdapter.MyViewHolder myViewHolder = new PersonListAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final PersonListAdapter.MyViewHolder holder, int position) {
        final PersonContact contact = personContactList.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.remove.setBackgroundColor(Color.RED);
                requestDeletePerson(contact.getId());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toasty.info(context, personContactList.size() + " elements").show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return personContactList.size();
    }

    private void requestDeletePerson(Long id) {
        EventBus.getDefault().post(new PersonEvent(PersonEventCode.REMOVE_PERSON, id));
    }
}
