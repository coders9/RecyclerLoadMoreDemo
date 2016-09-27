package com.recycler.loadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvItems = (RecyclerView) findViewById(R.id.recycler_view);

        final List<Contact> allContacts = Contact.createContactsList(10, 0);
        final ContactsAdapter adapter = new ContactsAdapter(allContacts);
        rvItems.setAdapter(adapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager(linearLayoutManager);
        rvItems.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                List<Contact> moreContacts = Contact.createContactsList(10, page);
                int curSize = adapter.getItemCount();
                allContacts.addAll(moreContacts);
                adapter.notifyItemRangeInserted(curSize, allContacts.size() - 1);
            }
        });
    }
}
