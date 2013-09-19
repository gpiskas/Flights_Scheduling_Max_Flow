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

import java.util.ArrayList;
import java.util.HashMap;

import flow.main.Utils;

/**
 * Represents the graph and provides methods to extend it and serve the
 * algorithms that follow.
 */
public class Graph {

	private HashMap<String, ArrayList<Route>> graph;
	private ArrayList<Flight> flights;
	private ArrayList<Route> routes;

	/**
	 * Graph constructor and editor. The graph uses a HashMap representation.
	 * @param flights - the initial input to create the graph
	 */
	public Graph(ArrayList<Flight> flights) {
		this.flights = flights;
		graph = new HashMap<String, ArrayList<Route>>();

		initializeGraph();
		extendGraph();
	}

	/**
	 * Constructs the initial form of the graph and also adds the "S" and "T" verticles.
	 */
	private void initializeGraph() {
		ArrayList<Route> sRoutes = new ArrayList<Route>();

		for (Flight f : flights) {
			String origKey = f.getOrigin() + "_" + f.getDepTime();
			String destKey = f.getDest() + "_" + f.getArrTime();
			routes = new ArrayList<Route>();
			
			// Adds (f.getOrigin(),f.getDest()) edge.
			if (graph.containsKey(origKey)) {
				routes.addAll(graph.get(origKey));
			}
			routes.add(new Route(destKey, 1, 1));
			graph.put(origKey, routes);

			// Adds (S,f.getOrigin()) edge.
			Route sRoute = new Route(origKey, 0, 1);
			if (!sRoutes.contains(sRoute)) {
				sRoutes.add(sRoute);
			}
			
			// Adds (f.getDest(),T) edge.
			ArrayList<Route> tRoutes = new ArrayList<Route>();
			tRoutes.add(new Route("T", 0, 1));
			graph.put(destKey, tRoutes);	
		}

		// Adds (S,T) edge.
		sRoutes.add(new Route("T", 0, Utils.PLANES_AVAILABLE));
		graph.put("S", sRoutes);
		

	}

	/**
	 * Extends the graph, adding reachable routes.
	 */
	private void extendGraph() {
		for (Flight fi : flights) {
			for (Flight fj : flights) {
				if (fi != fj) {
					// Checks if Flight fj is reachable by Flight fi using two rules
					if (fj.isSameAirportAndReachableBy(fi) || fj.isReachableBy(fi)) {
						String key = fi.getDest() + "_" + fi.getArrTime();
						routes = new ArrayList<Route>();
						
						// Adds (fi.getDest(),fj.getOrigin()) edge.
						if (graph.containsKey(key)) {
							routes.addAll(graph.get(key));
						}
						routes.add(new Route(fj.getOrigin() + "_" + fj.getDepTime(), 0, 1));
						graph.put(key, routes);
					}
				}
			}
		}
	}

	
	/**
	 * Returns the graph.
	 * @return - the graph HashMap
	 */
	public HashMap<String, ArrayList<Route>> getGraph() {
		return graph;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : graph.keySet()) {
			sb.append(key + " -> " + graph.get(key) + "\n");
		}
		return sb.toString();
	}
}
