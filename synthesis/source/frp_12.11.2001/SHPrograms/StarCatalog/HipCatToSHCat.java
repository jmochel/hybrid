/*
 * The contents of this file are subject to the Artistic
 * License (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.opensource.org/licenses/artistic-license.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is "Template Code Generator".
 *
 * The Initial Developer of the Original Code is Jim Jackl-Mochel.
 * Portions created by Jim Jackl-Mochel are
 * Copyright (C) 1998, 1999, 2000 Jim Jackl-Mochel.  All
 * Rights Reserved.
 *
 * Contributor(s):
 */

package org.mushin.sh.astronomy;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HipCatToSHCat
{
	static int recordSize = 449;

	public class StellarEntry
	{
	}

	public static void main(String args[])
    {
		byte[] readBfr = new byte[recordSize];

		try
		{
			FileInputStream in = new FileInputStream("hip_main.dat");

			in.read(readBfr);

			Character c = new Character( (char) readBfr[0]);

			System.out.println(c);
		} 
		catch (java.io.IOException error)
		{
		}
	}
}
