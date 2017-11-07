package com.Game.MainScreen;

import java.util.Comparator;

import com.Game.Model.YSortable;

public class WorldObjectYComparator implements Comparator<YSortable> {

	@Override
	public int compare(YSortable o1, YSortable o2) {
		if (o1.GetWorldY() < o2.GetWorldY()) {
			return -1;
		} else if (o1.GetWorldY() > o2.GetWorldY()) {
			return 1;
		}
		return 0;
	}
}

	


