package io.groupproject.renovateit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import io.groupproject.renovateit.Interface.ItemClickListener;
import io.groupproject.renovateit.Model.Work;
import io.groupproject.renovateit.ViewHolder.WorkViewHolder;

public class WorkList extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference workList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String categoryId;
    FirebaseRecyclerAdapter<Work, WorkViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);

        database = FirebaseDatabase.getInstance();
        workList = database.getReference("Work");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_work);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)

            categoryId = getIntent().getStringExtra("CategoryId");

        if (!categoryId.isEmpty() && categoryId != null) {
            Toast.makeText(WorkList.this,""+categoryId,Toast.LENGTH_SHORT).show();
            loadListWork(categoryId);
        }

    }

    private void loadListWork(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Work, WorkViewHolder>(Work.class,
                R.layout.work_item,
                WorkViewHolder.class,
                workList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(WorkViewHolder viewHolder, Work work, final int position) {
                viewHolder.work_name.setText(work.getName());
                Picasso.with(getBaseContext()).load(work.getImage()).into(viewHolder.work_image);
                final Work local = work;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       Toast.makeText(WorkList.this, "" + local.getName(), Toast.LENGTH_SHORT).show();
                        Intent foodDetail =  new Intent(WorkList.this, WorkDetail.class);
                        foodDetail.putExtra("FoodId", adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });

            }

        };
        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
