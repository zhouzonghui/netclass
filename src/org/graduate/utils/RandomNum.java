package org.graduate.utils;
/**
 * ����һ��6λ������������������������ʱ����
 * @author Owner
 */
public class RandomNum {
	public static String makeRandom() {
		String s = "";
		while (s.length() < 6)
			s += (int) (Math.random() * 10);
		return s;
	}
}
