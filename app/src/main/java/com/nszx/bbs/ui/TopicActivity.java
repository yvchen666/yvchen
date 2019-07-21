package com.nszx.bbs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nszx.bbs.R;
import com.nszx.bbs.adapter.ListViewAdapter;
import com.nszx.bbs.bean.List_Bean;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends Fragment {
    private String TAG  = "TopicActivity";
    @Nullable

    private List<List_Bean> list_beans = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topic_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListViewAdapter adapter = new ListViewAdapter(getActivity(), R.layout.listview_custom, list_beans);
        ListView listView = getActivity().findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        // 定义一个List集合
        initListView();
    }
    private void initListView() {
        List_Bean bean = new List_Bean(R.drawable.head_portrait, R.drawable.like, "This is a title Hello my friend!"
                , "记住该记住的，忘记该忘记的。改变能改变的，接受不能改变的。我们应向前看");
        for (int i = 0; i <= 10; i++) {
            list_beans.add(bean);
        }


    }
}
