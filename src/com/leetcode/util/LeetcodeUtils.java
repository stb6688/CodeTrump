package com.leetcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeetcodeUtils {

	public static String readText(Object instance) {
		String clazz = instance.getClass().getSimpleName().toString();
		File file = new File("./text/" + clazz);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			return reader.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {;}
			}
		}
		return null;
	}
}
