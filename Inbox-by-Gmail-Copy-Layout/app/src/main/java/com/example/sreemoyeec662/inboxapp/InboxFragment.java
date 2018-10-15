package com.example.sreemoyeec662.inboxapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment implements InboxAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private InboxAdapter mInboxAdapter;
    private boolean isMultiSelect = false;
    private ActionMode mActionMode;
    private List<InboxMessageModel> selectedInboxMessageModelList = new ArrayList<>();

    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_inbox);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        List<InboxMessageModel> inboxMessageModelList = getInboxMessageList();
        mInboxAdapter = new InboxAdapter(getContext(), inboxMessageModelList, this, selectedInboxMessageModelList);
        mRecyclerView.setAdapter(mInboxAdapter);
        return rootView;
    }

    private List<InboxMessageModel> getInboxMessageList() {
        List<InboxMessageModel> inboxMessageModelList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            InboxMessageModel inboxMessageModel = new InboxMessageModel();
            inboxMessageModel.setId(i + 1);
            inboxMessageModel.setGmailItemLetter("U");
            inboxMessageModel.setGmailFrom(getResources().getString(R.string.gmail_from));
            inboxMessageModel.setGmailSubject(getResources().getString(R.string.gmail_subject));
            inboxMessageModel.setGmailMessage(getResources().getString(R.string.gmail_message));
            inboxMessageModel.setGmailTimestamp(getResources().getString(R.string.gmail_timestamp));
            inboxMessageModelList.add(inboxMessageModel);
        }
        return inboxMessageModelList;
    }

    @Override
    public void onItemClick(int position) {
        if (isMultiSelect)
            multiselect(position);
    }

    @Override
    public void onItemLongClick(int position) {
        if (!isMultiSelect) {
            selectedInboxMessageModelList = new ArrayList<>();
            isMultiSelect = true;
            if (mActionMode == null)
                mActionMode = getActivity().startActionMode(mActionModeCallback);
        }
        multiselect(position);
    }

    private void multiselect(int position) {
        if (mActionMode != null) {
            List<InboxMessageModel> inboxMessageModelList = getInboxMessageList();
            if (selectedInboxMessageModelList.contains(inboxMessageModelList.get(position))) {
                selectedInboxMessageModelList.remove(inboxMessageModelList.get(position));
            } else {
                selectedInboxMessageModelList.add(inboxMessageModelList.get(position));
            }
            if (selectedInboxMessageModelList.size() > 0)
                mActionMode.setTitle("" + selectedInboxMessageModelList.size());
            else {
                if (mActionMode != null)
                    mActionMode.finish();
            }
            mInboxAdapter.refreshAdapter(inboxMessageModelList, selectedInboxMessageModelList);
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_multi_select, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_pinned:
                    Toast.makeText(getActivity(), "Pinned is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_snooze:
                    Toast.makeText(getActivity(), "Snooze is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(getActivity(), "Delete is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_done:
                    Toast.makeText(getActivity(), "Done is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            selectedInboxMessageModelList = new ArrayList<>();
            mInboxAdapter.refreshAdapter(getInboxMessageList(), selectedInboxMessageModelList);
        }
    };
}
