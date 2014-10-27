package com.tc.lang.hv;

import java.util.EventListener;

public interface SearchModeChangeListener extends EventListener {

	public void searchModeChanged(SearchModeChangeEvent evt);

}
