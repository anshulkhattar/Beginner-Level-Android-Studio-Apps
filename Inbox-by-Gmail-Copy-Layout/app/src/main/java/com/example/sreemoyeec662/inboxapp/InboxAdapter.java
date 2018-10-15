package com.example.sreemoyeec662.inboxapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sreemoyeec662 on 31/03/2018.
 */

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    private Context mContext;
    private List<InboxMessageModel> mInboxMessageModelList;
    private List<InboxMessageModel> mSelectedInboxMessageModelList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public InboxAdapter(Context mContext, List<InboxMessageModel> mInboxMessageModelList, OnItemClickListener mOnItemClickListener, List<InboxMessageModel> mSelectedInboxMessageModelList) {
        this.mContext = mContext;
        this.mInboxMessageModelList = mInboxMessageModelList;
        this.mOnItemClickListener = mOnItemClickListener;
        this.mSelectedInboxMessageModelList = mSelectedInboxMessageModelList;
    }

    @Override
    public InboxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inbox_list_row, parent, false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InboxViewHolder holder, int position) {
        InboxMessageModel inboxMessageModel = mInboxMessageModelList.get(position);
        holder.mGmailItemLetter.setText(inboxMessageModel.getGmailItemLetter());
        holder.mFrom.setText(inboxMessageModel.getGmailFrom());
        holder.mSubject.setText(inboxMessageModel.getGmailSubject());
        holder.mMessage.setText(inboxMessageModel.getGmailMessage());
        holder.mTimeStamp.setText(inboxMessageModel.getGmailTimestamp());
        if (mSelectedInboxMessageModelList.contains(inboxMessageModel)) {
            holder.mRelativeLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));
        } else {
            holder.mRelativeLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state));
        }
    }

    @Override
    public int getItemCount() {
        return mInboxMessageModelList.size();
    }

    public void refreshAdapter(List<InboxMessageModel> inboxMessageModelList, List<InboxMessageModel> mSelectedInboxMessageModelList) {
        this.mInboxMessageModelList = inboxMessageModelList;
        this.mSelectedInboxMessageModelList = mSelectedInboxMessageModelList;
        this.notifyDataSetChanged();
    }

    class InboxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private RelativeLayout mRelativeLayout;
        private TextView mGmailItemLetter;
        private TextView mFrom;
        private TextView mSubject;
        private TextView mMessage;
        private TextView mTimeStamp;

        InboxViewHolder(View view) {
            super(view);
            mRelativeLayout = view.findViewById(R.id.rl_list_item);
            mGmailItemLetter = view.findViewById(R.id.gmail_item_letter);
            mSubject = view.findViewById(R.id.tv_subject);
            mFrom = view.findViewById(R.id.tv_from);
            mMessage = view.findViewById(R.id.tv_message);
            mTimeStamp = view.findViewById(R.id.tv_timestamp);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnItemClickListener.onItemClick(position);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            mOnItemClickListener.onItemLongClick(position);
            return true;
        }
    }
}
