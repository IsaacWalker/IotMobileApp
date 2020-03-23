package com.example.iotmobileapp.gdpr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iotmobileapp.R;

public class AccessRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_request);

        recyclerView = findViewById(R.id.sar_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AccessRequestAdapter mAdapter = new AccessRequestAdapter();
        recyclerView.setAdapter(mAdapter);

    }

    private class AccessRequestAdapter extends RecyclerView.Adapter<AccessRequestAdapter.SubjectDataView>
    {
        public SubjectData[] subjectData;


        @NonNull
        @Override
        public SubjectDataView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subject_data_list_row, parent, false);
            return new SubjectDataView(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SubjectDataView holder, int position) {
            holder.nameView.setText("Name");
            holder.nameValView.setText("Value");
            holder.categoryView.setText("Category");
            holder.categoryValView.setText("Value");
            holder.dateCollView.setText("Collection Time");
            holder.dateCollValView.setText("Value");
            holder.dateDelView.setText("Deletion Date");
            holder.dateDelValView.setText("Value");
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        public class SubjectDataView extends RecyclerView.ViewHolder
        {
            public TextView nameView,nameValView, categoryView, categoryValView, dateDelView, dateDelValView,
                    dateCollView, dateCollValView;

            public SubjectDataView(View v) {
                super(v);
                nameView = v.findViewById(R.id.sd_name);
                nameValView = v.findViewById(R.id.sd_name_val);
                categoryView = v.findViewById(R.id.sd_category);
                categoryValView = v.findViewById(R.id.sd_category_val);
                dateDelView = v.findViewById(R.id.sd_deletion_date);
                dateDelValView = v.findViewById(R.id.sd_deletion_date_val);
                dateCollView = v.findViewById(R.id.sd_collection_date);
                dateCollValView = v.findViewById(R.id.sd_collection_date_val);
            }
        }

    }

}
