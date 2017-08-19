package com.tc.lang;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class SearchModel implements ComboBoxModel {

	private List<ListDataListener> ldlList;
	private List<SearchItem> itemList;
	private List<SearchItem> visibleItemList;
	private SearchItem selectedItem;

	public SearchModel() {
		ldlList = new ArrayList<ListDataListener>();
		itemList = new ArrayList<SearchItem>();
		visibleItemList = new ArrayList<SearchItem>();
	}

	public void filter(String filterText) {
		visibleItemList.clear();
		for (SearchItem item : itemList) {
			if (item.getKey().trim().contains(filterText.trim())) {
				visibleItemList.add(item);
			}
		}
		for (ListDataListener listener : ldlList) {
			ListDataEvent evt = new ListDataEvent(this,
					ListDataEvent.CONTENTS_CHANGED, 0,
					visibleItemList.size() - 1);
			listener.contentsChanged(evt);
		}
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		ldlList.add(arg0);
	}

	@Override
	public SearchItem getElementAt(int arg0) {
		return visibleItemList.get(arg0);
	}

	@Override
	public int getSize() {
		return visibleItemList.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		ldlList.remove(arg0);
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		if (arg0 != null) {
			selectedItem = (SearchItem) arg0;
		} else {
			selectedItem = null;
		}
	}

	public void setItemṣ̣(List<SearchItem> itemList) {
		this.itemList = itemList;
		filter("");
	}

}
