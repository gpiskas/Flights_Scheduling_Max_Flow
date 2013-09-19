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

package flow.maxFlow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class runs the Ford-Fulkerson algorithm on a FlowNetwork {@link flow.maxFlow.FlowNetwork FlowNetwork}
 *
 */
public class FordFulkerson {
	private int maxFlow;
	private FlowNetwork network;
	private String source;
	private String sink;
	private HashMap<String, FlowEdge> edgeTo;

	
	/**
	 * Runs the algorithm
	 * @param G The Flow Network
	 * @param source The source from which the algorithm searches
	 * @param sink The sink to which the algorithm searches to
	 */
	public FordFulkerson(FlowNetwork G, String source, String sink) {
		network = G;
		this.source = source;
		this.sink = sink;
		maxFlow = 0;

		while (hasAugmentingPath()) {
			/*Calculate the bottleneck of the path */
			int bottleneck = Integer.MAX_VALUE;
			for (String v = sink; !v.equals(source); v = edgeTo.get(v).other(v)) {
				bottleneck = Math.min(bottleneck, edgeTo.get(v)
						.residualCapacityTo(v));
			}
			/* Add residual flow equal to the bottleneck on each edge */
			for (String v = sink; !v.equals(source); v = edgeTo.get(v).other(v)) {
				edgeTo.get(v).addResidualFlowTo(v, bottleneck);
			}
			
			/*Update the maximum flow */
			maxFlow += bottleneck;
		}

	}

	/**
	 * Searches whether there exists an augmenting path from the source to the sink
	 * and the set of vertices that consist this path are mapped to the edges that connect
	 * them with the previous vertex on that path.
	 * @return if there exists an augmenting path (boolean)
	 */
	public boolean hasAugmentingPath() {
		edgeTo = new HashMap<String, FlowEdge>();
		HashSet<String> marked = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		
		/*Begin from the source*/
		q.add(source);
		marked.add(source);
		while (!q.isEmpty()) {
			String v = q.remove();
			/* For each edge adjacent to v */
			for (FlowEdge e : network.adjList(v)) {
				/* If the other end of this edge is capable of carrying more flow and 
				 * it has not been visited yet, add it to the queue of potential path members */
				String other = e.other(v);
				if (e.residualCapacityTo(other) > 0) {
					if (!marked.contains(other)) {
						edgeTo.put(other, e);
						marked.add(other);
						q.add(other);
					}
				}
			}
		}
		/*If the sink was visited then there exists an augmenting path */
		return marked.contains(sink);
	}
	
	/**
	 * @return the flow network on which the algorithm was run
	 */
	public FlowNetwork network(){
		return network;
	}

	/**
	 * @return The max flow as calculated by the algorithm
	 */
	public int maxFlow() {
		return maxFlow;
	}

}
