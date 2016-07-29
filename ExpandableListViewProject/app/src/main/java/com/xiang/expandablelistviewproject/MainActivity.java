package com.xiang.expandablelistviewproject;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView expandableListView= (ExpandableListView) findViewById(R.id.eListView);
        //创建baseExpandableListAdapter
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] logos = new int[]{R.drawable.man_1, R.drawable.man_2, R.drawable.man_4};
            String[] armType = new String[]{"紫色", "绿色", "灰色"};
            String[][] arms = new String[][]{
                    {"紫红", "紫南", "大紫"}, {"深绿", "浅绿", "淡绿"}, {"银灰色", "白灰色"}
            };


            private TextView getTextView() {
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }


            @Override
            public Object getGroup(int groupPosition) {
                return armType[groupPosition];
            }

            //返回包含组列表项的数量
            @Override
            public int getGroupCount() {
                return armType.length;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            //决定每个组选项的外观
            //返回view 对象将作为组列表项
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout linearLayout = new LinearLayout(MainActivity.this);

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(logos[groupPosition]);
                linearLayout.addView(imageView);

                TextView textView=getTextView();
                textView.setText(getGroup(groupPosition).toString());
                linearLayout.addView(textView);

                return linearLayout;
            }


            //  Child 获取指定组位置，指定子列表项处的子列表项数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            //返回特定组所包含的子列表项的数量
            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            //返回view 将作为特定组，特定位置的子列表项
            //决定子项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView=getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return  textView;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        expandableListView.setAdapter(adapter);
    }
}
