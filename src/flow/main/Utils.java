/*
* Flights Scheduling using Max Flow
* Copyright (C) 2013 George Piskas, Theodoros Theodoridis
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* Contact: geopiskas@gmail.com
*/

package flow.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import flow.flightGraph.Flight;

public class Utils {

	public static int PLANES_AVAILABLE;
	public static int MAINTENACE_TIME = 1;

	public static final String FILENAME = "flights.txt";

	/**
	 * Reads the input file stored in Utils.FILENAME and returns all the flights
	 * in an ArrayList.
	 * 
	 * @return - the flight bundle read
	 * @throws Exception when any kind of read error happens.
	 */
	public static ArrayList<Flight> readFile() throws Exception {
		ArrayList<Flight> flights = new ArrayList<Flight>();

		Pattern intPattern = Pattern.compile("\\d+");
		Pattern stringPattern = Pattern.compile("[a-zA-Z]+");
		Matcher m;

		String orig, dest;
		int deH, deM, arH, arM;
		BufferedReader in;
		String line = null;
		try {
			in = new BufferedReader(new FileReader(Utils.FILENAME));

			line = in.readLine();
			m = intPattern.matcher(line);
			m.find();
			Utils.PLANES_AVAILABLE = Integer.parseInt(m.group());
			while ((line = in.readLine()) != null) {
				m = intPattern.matcher(line);
				m.find();
				deH = Integer.parseInt(m.group());
				m.find();
				deM = Integer.parseInt(m.group());
				m.find();
				arH = Integer.parseInt(m.group());
				m.find();
				arM = Integer.parseInt(m.group());

				m = stringPattern.matcher(line);
				m.find();
				orig = m.group();
				m.find();
				dest = m.group();

				flights.add(new Flight(orig, deH, deM, dest, arH, arM));
			}
			in.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(Utils.FILENAME + " does not exist.");
		} catch (IOException e) {
			throw new IOException("File reading error: "+Utils.FILENAME);
		} catch (IllegalStateException e) {
			throw new IllegalStateException("File patter error");
		}

		if (!flights.isEmpty()) {
			return flights;
		} else {
			throw new NullPointerException("No flights given.");
		}
	}

}