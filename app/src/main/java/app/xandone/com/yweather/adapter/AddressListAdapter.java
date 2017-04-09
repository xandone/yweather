package app.xandone.com.yweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.xandone.com.yweather.R;


public class AddressListAdapter extends BaseAdapter {
	private ArrayList<String> list;
	private Context context;

	public static final int TYPE_ONE = 0;
	public static final int TYPE_TWO = 1;
	private static final int TYPE_COUNT = TYPE_TWO + 1;

	public AddressListAdapter(List<String> list, Context context) {
		this.list = (ArrayList<String>) list;
		this.context = context;
	}
	
	@Override
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		return position % 2 == 0 ? TYPE_ONE : TYPE_TWO;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int ViewType = getItemViewType(position);
		if (ViewType == TYPE_ONE) {
			return getViewOne(position, convertView, parent);
		} else {
			return getViewTwo(position, convertView, parent);
		}

	}

	private View getViewTwo(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.address_list_item2, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.addressItem.setText(list.get(position));
		return convertView;
	}

	private View getViewOne(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.addressItem.setText(list.get(position));
		return convertView;
	}

	private class ViewHolder {
		TextView addressItem;

		public ViewHolder(View view) {
			addressItem = (TextView) view.findViewById(R.id.addressItem);
		}
	}

}
