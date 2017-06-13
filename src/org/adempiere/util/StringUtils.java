/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.util;

/**
 *	String Utils.
 *
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public final class StringUtils
{
	private StringUtils()
	{
	}

	/**
	 * Truncate string to a given length.
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static final String trunc(final String str, final int length)
	{
		if (str == null)
		{
			return str;
		}

		if (str.length() <= length)
		{
			return str;
		}

		return str.substring(0, length);
	}


	private static final int PAD_LIMIT = 8192;

	public static final String EMPTY = "";

	public static String repeat(String str, int repeat) {

		if (str == null) {
			return null;
		}
		if (repeat <= 0) {
			return EMPTY;
		}

		int inputLength = str.length();

		if (repeat == 1 || inputLength == 0) {
			return str;
		}

		if (inputLength == 1 && repeat <= PAD_LIMIT) {
			return padding(repeat, str.charAt(0));
		}

		int outputLength = inputLength * repeat;
		switch (inputLength) {
		case 1 :
			char ch = str.charAt(0);
			char[] output1 = new char[outputLength];
			for (int i = repeat - 1; i >= 0; i--) {
				output1[i] = ch;
			}
			return new String(output1);
		case 2 :
			char ch0 = str.charAt(0);
			char ch1 = str.charAt(1);
			char[] output2 = new char[outputLength];
			for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
				output2[i] = ch0;
				output2[i + 1] = ch1;
			}
			return new String(output2);
		default :
			StringBuffer buf = new StringBuffer(outputLength);
			for (int i = 0; i < repeat; i++) {
				buf.append(str);
			}
			return buf.toString();
		}
	}

	/**
	 * <p>Returns padding using the specified delimiter repeated
	 * to a given length.</p>
	 *
	 * <pre>
	 * StringUtils.padding(0, 'e')  = ""
	 * StringUtils.padding(3, 'e')  = "eee"
	 * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
	 * </pre>
	 *
	 * <p>Note: this method doesn't not support padding with
	 * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
	 * as they require a pair of <code>char</code>s to be represented.
	 * If you are needing to support full I18N of your applications
	 * consider using {@link #repeat(String, int)} instead. 
	 * </p>
	 *
	 * @param repeat  number of times to repeat delim
	 * @param padChar  character to repeat
	 * @return String with repeated character
	 * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
	 * @see #repeat(String, int)
	 * */

	private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {

		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		}

		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}

		return new String(buf);
	}

	/**
	 * make prefix
	 * @param name
	 * @return String
	 */
	public static String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}//makePrefix
}
