package com.shem.utils;

import android.content.SharedPreferences;

public class ABTestingUtils {

    private interface ABTestingEnum {
		double getValue();
	}

    private static ABTestingUtils instance;

    private ABTestingUtils() {
		super();
	}
	
	public static ABTestingUtils getInstance() {
		if (instance == null) {
			instance = new ABTestingUtils();
		}
		return instance;
	}

    protected  <T extends Enum<T> & ABTestingEnum> T getABTestingType(SharedPreferences sharedPref, Class<T> type, String key) {
		T val = null;
		String savedValue = sharedPref.getString(key, null);
		if (savedValue == null) {
			double random = Math.random();
			double sum = 0;
			for (int i = 0; i < type.getEnumConstants().length; i++) {
				val = type.getEnumConstants()[i];
				sum += val.getValue();
				if (sum > random) {
					break;
				}
			}
			storeABTestingType(sharedPref, key, val.name());
		} else {
			try {
				val = Enum.valueOf(type, savedValue);
			} catch (Exception ex) {
				// For error cases
				val = type.getEnumConstants()[0];
				storeABTestingType(sharedPref, key, val.name());
			}
		}
		return val;
	}
	
	private void storeABTestingType(SharedPreferences sharedPref, String key, String value) {
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(key, value);
		editor.commit();
	}

}
