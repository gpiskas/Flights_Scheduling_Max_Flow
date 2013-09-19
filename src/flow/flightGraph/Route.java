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

public class Route {

	private String dest;
	private int lowerBound, capacity;

	/**
	 * Route constructor.
	 * @param dest
	 * @param lowerBound
	 * @param capacity
	 */
	public Route(String dest, int lowerBound, int capacity) {
		this.dest = dest;
		this.lowerBound = lowerBound;
		this.capacity = capacity;
	}

	public String getDest() {
		return dest;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this.dest.equals(((Route) o).dest)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return dest + "|L:" + lowerBound + " " + "C:" + capacity;
	}
}