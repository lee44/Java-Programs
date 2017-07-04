import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Joshua Lee
 * @SMC_ID 1372646
 * 
 * CS 20B Programming Project 6
 *
 */

public class TopologicalSorter 
{
	private int vertices;
	private List<Integer>[] adjacent;
	
	public TopologicalSorter(int numVertices) 
	{
		this.vertices = numVertices;
		this.adjacent = new ArrayList[numVertices];
	}
	
	public void addDependency(int from, int to) 
	{
		adjacent[from] = new ArrayList<Integer>();
		adjacent[from].add(to);
	}

	public void visit(int vertex, boolean[] visited, Stack<Integer> stack) 
	{
		if (visited[vertex] != true) 
		{
			visited[vertex] = false;

			if (adjacent[vertex] != null)
				for (int i : adjacent[vertex])
					visit(i, visited, stack);

			visited[vertex] = true;
			stack.push(vertex);
		}
	}	
	
	public List<Integer> topologicalSort() 
	{
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> list =new ArrayList<Integer>();
		
		for (int i = 0; i < vertices; i++)
			visited[i] = false;

		for (int i = 0; i < vertices; i++)
			if (visited[i] == false)
				visit(i, visited, stack);

		while (!stack.isEmpty())
			list.add(stack.pop());
		
		return list;
	}
}
