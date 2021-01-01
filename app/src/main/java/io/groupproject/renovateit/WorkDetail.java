package io.groupproject.renovateit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import io.groupproject.renovateit.Common.Common;
import io.groupproject.renovateit.Database.Database;
import io.groupproject.renovateit.Model.Order;
import io.groupproject.renovateit.Model.Work;

public class WorkDetail extends AppCompatActivity {

    TextView work_name, work_price, work_description;
    ImageView work_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String workId = "";
    Work currentWork;


    FirebaseDatabase database;
    DatabaseReference works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        works = database.getReference("Work");

        //init view
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        workId,
                        currentWork.getName(),
                        numberButton.getNumber(),
                        currentWork.getPrice(),"0")
                          //No Discount
                );
                Toast.makeText(WorkDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        work_name = findViewById(R.id.work_name);
        work_image = findViewById(R.id.img_food);
        work_description = findViewById(R.id.work_description);
        work_price = findViewById(R.id.work_price);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        // get food id from intent
        if (getIntent() != null)
            workId = getIntent().getStringExtra("WorkId");
        if (!workId.isEmpty() && workId != null)
                getDetailFood(workId);

    }

    private void getDetailFood(String workId) {
        works.child(workId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentWork = dataSnapshot.getValue(Work.class);

                // setting the image from firebase into appbar;
                Picasso.with(getBaseContext()).load(currentWork.getImage()).into(work_image);
                //set title in appbar
                collapsingToolbarLayout.setTitle(currentWork.getName());

                work_price.setText(currentWork.getPrice());
                work_description.setText(currentWork.getDescription());
                work_name.setText(currentWork.getName());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}