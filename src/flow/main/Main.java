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

import flow.demandGraph.DemandGraph;
import flow.flightGraph.Graph;
import flow.maxFlow.FlowNetwork;
import flow.maxFlow.FordFulkerson;

/**
 * Holds the main function, where the magic happens.
 */
public class Main {

	public static void main(String... args) {
			try {
				Graph inputGraph = new Graph(Utils.readFile());
				DemandGraph demandGraph = new DemandGraph(inputGraph.getGraph());
				if(!demandGraph.isSolvable()){
					System.out.println("The problem is not solvable");
					return;
				}
				
				FlowNetwork flowNetwork = new FlowNetwork(demandGraph.getGraph());
				
				FordFulkerson ff = new FordFulkerson(flowNetwork, demandGraph.source(), demandGraph.sink());
				if(demandGraph.getD() != ff.maxFlow()){
					System.out.println("More planes are needed");
					return;
				}
				
				Flights paths = new Flights(flowNetwork);
				paths.printFlights();
				
			} catch (Exception e) {
				System.err.println("Something went wrong");
				e.printStackTrace();
			}

	}
}
