package com.example.scanner;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intelligencetest.R;

public class GridMenu extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater,
    	    ViewGroup container, Bundle savedInstanceState)
    	    {
    	        View view = inflater.inflate(
    	            R.layout.gridview, container, false);
    	        
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(getActivity()));
		return gridView;
    }

    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            items.add(new Item("Image 1", R.drawable.sample_0));
            items.add(new Item("Image 2", R.drawable.sample_1));
            items.add(new Item("Image 3", R.drawable.sample_2));
            items.add(new Item("Image 4", R.drawable.sample_3));
            items.add(new Item("Image 5", R.drawable.sample_4));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
               v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
               v.setTag(R.id.picture, v.findViewById(R.id.picture));
               v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}
