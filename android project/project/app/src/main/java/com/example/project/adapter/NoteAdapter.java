package com.example.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.NoteDetails;
import com.example.project.R;
import com.example.project.entity.Note;
import com.example.project.lifecycle.MainViewModel;
import com.example.project.utils.Helper;
import com.example.project.utils.NoteDBHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class NoteAdapter extends RecyclerView.Adapter {
    private List<Note> list;
    private Set<Note> selectList= new HashSet<>();
    private MainViewModel mainViewModel;

    private Activity activity;

    private ActionMode actionMode;

    boolean isEnable=false;
    boolean isSelectAll=false;

    NoteDBHelper noteDBHelper;

    public NoteAdapter(Activity activity, List<Note> list){
        this.list = new ArrayList<>(list);
        this.activity = activity;

        noteDBHelper = new NoteDBHelper(activity);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  parent.getContext().getSystemService(LayoutInflater.class).inflate(R.layout.note_list_item,parent, false);
        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(MainViewModel.class);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = list.get(position);
        View view = holder.itemView;

        ImageView imageView = view.findViewById(R.id.list_item_image);
        TextView title = view.findViewById(R.id.list_item_title);
        TextView description = view.findViewById(R.id.list_item_description);
        TextView createdAt = view.findViewById(R.id.list_item_created_at);
        ImageView checkbox = view.findViewById(R.id.list_item_checkbox);

        view.setOnLongClickListener(view1 -> {
            if(!isEnable){
                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater menuInflater= actionMode.getMenuInflater();
                        menuInflater.inflate(R.menu.menu,menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        isEnable=true;
                        ClickItem(checkbox, note);

                        mainViewModel.getValue().observe((LifecycleOwner) activity
                                , new Observer<Integer>() {
                                    @Override
                                    public void onChanged(Integer i) {
                                        // when text change
                                        // set text on action mode title
                                        if(i == 0){
                                            actionMode.finish();
                                            return;
                                        }
                                        actionMode.setTitle(String.format("%d Selected",i));
                                    }
                                });
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        if(id == R.id.menu_delete){
                            removeSelectedItem();
                            actionMode.finish();
                        } else if (id == R.id.menu_select_all) {
                            if(selectList.size()==getItemCount())
                            {
                                isSelectAll=false;
                                selectList.clear();
                            }
                            else
                            {
                                isSelectAll=true;
                                selectList.clear();
                                selectList.addAll(list);
                            }
                            mainViewModel.setValue(selectList.size());
                            notifyDataSetChanged();
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        actionMode = null;
                        isEnable=false;
                        isSelectAll=false;
                        selectList.clear();
                        notifyDataSetChanged();
                    }
                };

                actionMode = ((AppCompatActivity) view.getContext()).startActionMode(callback);
            }
            else {
                ClickItem(checkbox,note);
            }
            return true;
        });

        view.setOnClickListener(view1 -> {
            if(isEnable){
                ClickItem(checkbox, note);
            }
            else{
                Context context = view1.getContext();
                Intent intent = new Intent(context, NoteDetails.class);
                intent.putExtra("title",note.getTitle());
                intent.putExtra("description",note.getDescription());
                intent.putExtra("createdAt",note.getCreatedAt());
                context.startActivity(intent);
            }
        });

        if(isSelectAll)
        {
            checkbox.setVisibility(View.VISIBLE);
        }
        else
        {
            checkbox.setVisibility(View.GONE);
        }


        title.setText(note.getTitle());
        description.setText(note.getDescription());
        createdAt.setText("created at : "+ note.getCreatedAt());
        imageView.setImageResource(R.drawable.essay);

    }

    private void ClickItem(ImageView checkbox, Note note){
        if(checkbox.getVisibility() == View.GONE){
            checkbox.setVisibility(View.VISIBLE);
            selectList.add(note);
        }
        else{
            checkbox.setVisibility(View.GONE);
            selectList.remove(note);
        }
        mainViewModel.setValue(selectList.size());
    }

    public void removeSelectedItem(){
        // delete all selected item
        noteDBHelper.deleteAll(selectList);
        list.removeIf(selectList::contains);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class NoteViewHolder extends RecyclerView.ViewHolder{
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public Optional<ActionMode> getActionMode() {
        return Optional.ofNullable(actionMode);
    }
}
