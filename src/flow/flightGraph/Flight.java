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

package flow.flightGraph;


/**
 *	Represents a single flight.
 *	Contains: origin, destination, departure and arrival time.
 */
public class Flight {

	private String origin, dest; 
	private Timestamp depTime;
	private Timestamp arrTime;

	/**
	 * Flight constructor.
	 * @param origin - Origin Airport
	 * @param depHour - Departure Hours
	 * @param depMin - Departure Minutes
	 * @param dest - Destination Airport
	 * @param arrHour - Arrival Hours
	 * @param arrMin - Arrival Minutes
	 */
	public Flight(String origin, int depHour, int depMin, String dest, int arrHour, int arrMin) {
		this.origin = origin;
		this.dest = dest;
		depTime = new Timestamp(depHour, depMin);
		arrTime = new Timestamp(arrHour, arrMin);
	}

	public String getOrigin() {
		return origin;
	}

	public String getDest() {
		return dest;
	}

	public Timestamp getDepTime() {
		return depTime;
	}

	public Timestamp getArrTime() {
		return arrTime;
	}


	/**
	 * Checks if the flight is reachable by flight fi.
	 * A flight is reachable by another if there is a gap of 180 minutes in between.
	 * @param fi - the flight to check reachability
	 * @return true if there is a gap of 180 minutes
	 */
	public boolean isReachableBy(Flight fi) { 
		return this.getDepTime().deferByMinutes(fi.getArrTime(),180);
	}

	/**
	 * Checks if the flight is reachable by flight fi, given that it's origin is the same as
	 * fi's destination and there is a gap of 60 minutes in between.
	 * @param fi - the flight to check reachability
	 * @return true if there is a gap of 60 minutes and airports match
	 */
	public boolean isSameAirportAndReachableBy(Flight fi) { 
		return this.getOrigin().equals(fi.getDest()) 
				&& this.getDepTime().deferByMinutes(fi.getArrTime(),60);
	}


}
