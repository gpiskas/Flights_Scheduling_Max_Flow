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

import java.util.LinkedList;
import java.util.List;

import flow.maxFlow.FlowEdge;
import flow.maxFlow.FlowNetwork;

public class Flights {

	List<List<String>> flights;

	public Flights(FlowNetwork network) {
		flights = new LinkedList<List<String>>();

		Iterable<FlowEdge> adjS = network.adjList("S");

		for (FlowEdge edge : adjS) {
			String other = edge.other("S");
			if (!other.equals("S*") && edge.residualCapacityTo(other) == 0) {
				List<String> temp = new LinkedList<String>();
				temp.add(other);
				flights.add(temp);
			}
		}
		for (List<String> list : flights) {
			expandFlightList(list, network);
		}
	}

	private void expandFlightList(List<String> list, FlowNetwork network) {
		String ID = list.get(0);
		while (!ID.equals("T")) {
			for (FlowEdge edge : network.adjList(ID)) {
				String other = edge.other(ID);
				if (edge.from().equals(ID)
						&& edge.residualCapacityTo(other) == 0) {
					ID = other;
					if(!ID.equals("T"))
						list.add(ID);
					break;
				}
			}
		}

	}
	
	public void printFlights(){
		System.out.println("Number of planes used: " + flights.size());
		
		for (List<String> path : flights){
			System.out.println("Flight path:");
			for (String id : path)
				System.out.print(id + " ");
			System.out.println();
		}
	}
}
