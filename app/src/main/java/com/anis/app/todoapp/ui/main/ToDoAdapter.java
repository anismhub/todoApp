package com.anis.app.todoapp.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anis.app.todoapp.database.ToDo;
import com.anis.app.todoapp.databinding.ItemTodoBinding;
import com.anis.app.todoapp.ui.insert.ToDoAddUpdateActivity;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private final ArrayList<ToDo> listToDos = new ArrayList<>();

    private static OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    void setListToDos(List<ToDo> listToDos) {
        this.listToDos.clear();
        this.listToDos.addAll(listToDos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTodoBinding binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ToDoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ToDoViewHolder holder, int position) {
        holder.bind(listToDos.get(position));
    }

    @Override
    public int getItemCount() {
        return listToDos.size();
    }

    static class ToDoViewHolder extends RecyclerView.ViewHolder {
        final ItemTodoBinding binding;

        ToDoViewHolder(ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ToDo toDo) {
            binding.tvDesc.setText(toDo.getDesc());

            binding.icCheck.setOnClickListener(v -> {
                onItemClickCallback.onItemClicked(toDo);
            });

            binding.icEdit.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ToDoAddUpdateActivity.class);
                intent.putExtra(ToDoAddUpdateActivity.EXTRA_TODO, toDo);
                v.getContext().startActivity(intent);
            });
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ToDo toDo);
    }
}
