package com.tc.lang.hv;

import java.util.EventListener;

public interface SelectedInfoChangeListener extends EventListener {

	public void selectedInfoChanged(SelectedInfoChangeEvent evt);
}
