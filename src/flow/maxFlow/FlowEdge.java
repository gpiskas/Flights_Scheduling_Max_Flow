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

/**
 * This class represents an edge connecting two vertices of a flow network.
 */
public class FlowEdge {

	private final String from;
	private final String to;
	private final int capacity;
	private int flow;
	
	
	/**
	 * Constructs a FlowEdge
	 * @param from ID of the source vertex
	 * @param to ID of the destination vertex
	 * @param capacity the flow capacity of the edge
	 */
	public FlowEdge(String from, String to , int capacity){
		if (capacity < 0)
			throw new IllegalArgumentException("The capacity can't be negavive");
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		flow = 0;
	}
	
	/**
	 * @return ID of the source vertex
	 */
	public String from(){
		return from;
	}
	
	/**
	 * @return ID of the destination vertex
	 */
	public String to(){
		return to;
	}
	
	/**
	 * @return capacity of the edge
	 */
	public int capacity(){
		return capacity;
	}
	
	/**
	 * @return current flow running threw the edge
	 */
	public int flow(){
		return flow;
	}
	
	/**
	 * Returns the ID of the opposite of the given vertex
	 * @param v ID of the opposite vertex
	 * @return opposite of given vertex
	 */
	public String other( String v){
		if ( v.equals(from))
			return to;
		if (v.equals(to))
			return from;
		throw new IllegalArgumentException("The given vertex is not connected to this edge");
	}
	
	/**
	 * Returns the residual capacity to the given vertex
	 * @param v Id of the vertex 
	 * @return residual capacity to given vertex
	 */
	public int residualCapacityTo(String v){
		if ( v.equals(from))
			return flow;
		if ( v.equals(to))
			return capacity - flow;
		throw new IllegalArgumentException("The given vertex is not connected to this edge");
	}
	
	/**
	 * Adds flow to the given vertex.
	 * If the given vertex is the destination of the edge, then the flow of the edge is increased.
	 * Otherwise, it is decreased (flow "returns")
	 * @param v ID of vertex
	 * @param amount Amount of residual flow to be added
	 */
	public void addResidualFlowTo(String v, int amount){
		if (v.equals(from))
			flow -=amount;
		else
			if ( v.equals(to))
				flow +=amount;
			else
				throw new IllegalArgumentException("The given vertex is not connected to this edge");			
	}
}
