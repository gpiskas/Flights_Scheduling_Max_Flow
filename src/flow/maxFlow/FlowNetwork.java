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
import java.util.LinkedList;
import java.util.List;

import flow.demandGraph.Edge;
import flow.demandGraph.Vertex;

/**
 * This class represents a flow network.
 */
public class FlowNetwork {
	
	/**
	 *  A mapping from vertex IDs to Lists of edges connected to them.
	 */
	private HashMap<String,List<FlowEdge>> adjList;
	
	
	/**
	 * Constructs a FlowNetwork
	 * @param graph A {@link flow.demandGraph.Vertex Vertex} and {@link analysis.Edge Edge} representation of the graph.
	 */
	public FlowNetwork ( HashMap<Vertex, List<Edge>> graph){		
		adjList = new HashMap<String,List<FlowEdge>>();
		for (Vertex v : graph.keySet()){
			LinkedList<FlowEdge> list = new LinkedList<FlowEdge>();
			adjList.put(v.getId(), list);
		}
		
		for (Vertex v : graph.keySet()){
			List<Edge> list = graph.get(v);
			for (Edge e : list){
				addEdge(new FlowEdge(v.getId(), e.getNext(), e.getCapacity()));
			}
		}
	}
	
	/**
	 * Adds the given FlowEdge the the adjacency lists of both its connected vertices.
	 */
	public void addEdge(FlowEdge e){
		String from = e.from();
		String to = e.to();
		adjList.get(from).add(e);
		adjList.get(to).add(e);
	}
	
	
	/**
	 * Returns the adjacency list of the given vertex.
	 */
	public Iterable<FlowEdge> adjList(String v){
		return adjList.get(v);
	}
}
