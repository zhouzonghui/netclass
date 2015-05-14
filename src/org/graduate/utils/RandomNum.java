package org.graduate.utils;
/**
 * 产生一个6位随机数，用来作重置密码的临时密码
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
