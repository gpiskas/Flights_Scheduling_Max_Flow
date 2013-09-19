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

package flow.demandGraph;

public class Edge {

	private int capacity;
	private final String nextVertex;

	public Edge(String nextVertex, int capacity) {
		this.nextVertex = nextVertex;
		this.capacity = capacity;
	}

	public String getNext() {
		return nextVertex;
	}

	public int getCapacity() {
		return capacity;
	}

	public void decreaseCapacity(){
		capacity--;
	}

	@Override
	public String toString() {
		return nextVertex +" C: "+capacity;
	}
}
