
package com.vicki.mes.todo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseListAdapter
 * 
 * Base class for all list adapters for a specific Model class
 * 
 * @param <M>  Model class
 */
public abstract class BaseListAdapter<M> extends BaseAdapter {

	protected final Context context;
	protected final LayoutInflater inflater;
	protected final List<M> items = new ArrayList<M>();
	public BaseListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return this.items.size();
	}

	public void setItems(List<M> items) {
		this.items.clear();
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	public void addItem(M item) {
		this.items.add(item);
		notifyDataSetChanged();
	}

	public M getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int indexOf(M item) {
		return items.indexOf(item);
	}

	public void clearItems() {
		this.items.clear();
		notifyDataSetChanged();
	}

	public void removeItem(int position) {
		this.items.remove(position);
		notifyDataSetChanged();
	}

	public void removeItem(M item) {
		this.items.remove(item);
		notifyDataSetChanged();
	}
	
}
