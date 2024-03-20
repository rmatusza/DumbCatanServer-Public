package com.dumbcatan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardGraphUtil {
	
	public BoardGraphUtil() {
		
	}
	
	private static HashMap<Integer, HashMap<String, Object>> boardGraph = new HashMap<>();
	
	private static List<Integer> nodesWithOnlyTwoNeighborsList = Arrays.asList(0,2,4,5,7,9,10,12,14,15,17,19,20,22,24,25,27,29);
	private static Set<Integer> nodesWithOnlyTwoNeighbors = new HashSet<>(nodesWithOnlyTwoNeighborsList);
	private static int [][] neighbors = {		
			{1, 29},
			{0, 2, 30},
			{1, 3},
			{2,4,32},
			{3,5},
			{4,6},
			{5,7,33},
			{6,8},
			{7,9,35},
			{8,10},
			{9,11},
			{10,12,36},
			{11,13},
			{12,14,38},
			{13,15},
			{14,16},
			{15,17,39},
			{16,18},
			{17,19,41},
			{18,20},
			{19, 21},
			{20, 22, 42},
			{21, 23},
			{22, 24, 44},
			{23, 25},
			{24, 26},
			{25, 27, 45},
			{26, 28},
			{27, 29, 47},
			{28, 0},
			{47, 31, 1},
			{30, 32, 48},
			{31, 33, 3},
			{32, 34, 6},
			{33, 35, 49},
			{34, 36, 8},
			{35, 37, 11},
			{36, 38, 50},
			{37, 39, 13},
			{38, 40, 16},
			{39, 41, 51},
			{40, 42, 18},
			{41, 43, 21},
			{42, 44, 52},
			{43, 45, 23},
			{44, 46, 26},
			{45, 47, 53},
			{30, 46, 28},
			{53, 49, 31},
			{50, 48, 34},
			{49, 51, 37},
			{50, 52, 40},
			{51, 53, 43},
			{52, 48, 46},
	};
	
	static {
		for(int i=0; i<neighbors.length; i+=1) {
			HashMap<String, Object> entry = new HashMap<>();
			
			if(nodesWithOnlyTwoNeighbors.contains(i)) {
				ArrayList<HashMap<String, Object>> roadConnectionsList = new ArrayList<>();
				HashMap<String, Object> connection = new HashMap<>();
				connection.put("color", null);
				connection.put("connecting_node", null);
				roadConnectionsList.add(connection);
				roadConnectionsList.add((HashMap<String, Object>) connection.clone());
				
				entry.put("neighbors", neighbors[i]);
				entry.put("structure", null);
				entry.put("color", null);
				entry.put("road_connections", roadConnectionsList);
				
				boardGraph.put(i, entry);
			} else {
				ArrayList<HashMap<String, Object>> roadConnectionsList = new ArrayList<>();
				HashMap<String, Object> connection = new HashMap<>();
				connection.put("color", null);
				connection.put("connecting_node", null);
				roadConnectionsList.add(connection);
				roadConnectionsList.add((HashMap<String, Object>) connection.clone());
				roadConnectionsList.add((HashMap<String, Object>) connection.clone());
				
				entry.put("neighbors", neighbors[i]);
				entry.put("structure", null);
				entry.put("color", null);
				entry.put("road_connections", roadConnectionsList);
				
				boardGraph.put(i, entry);
			}
		}
	}
	
	public HashMap<Integer, HashMap<String, Object>> getBoardGraph() {
		return boardGraph;
	}
	
}
