import java.util.HashMap;
import java.util.Stack;
import java.util.ArrayList;

public class Main {
	public static void main(String...args) {
		HashMap<Integer, Integer[]> problem = new HashMap<Integer, Integer[]>();
		problem.put(1, new Integer[]{2, 3, 4});
		problem.put(2, new Integer[]{3, 4});
		problem.put(3, new Integer[]{4});
		problem.put(4, new Integer[]{});
		problem.put(5, new Integer[]{7, 8, 9});
		problem.put(7, new Integer[]{});
		problem.put(8, new Integer[]{9});
		problem.put(9, new Integer[]{});
		System.out.println(make(problem));
	}
	
	public static ArrayList<ArrayList<Integer>> make(HashMap<Integer, Integer[]> problem) {
		Stack<Integer> fringe = new Stack<Integer>();
		int length = problem.keySet().size();
		Integer[] problemKeys = problem.keySet().toArray(new Integer[length]);
		ArrayList<Integer> stillToCompile = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < problemKeys.length; i++) {
			stillToCompile.add(problemKeys[i]);
		}
		ArrayList<Integer> compiled = new ArrayList<Integer>();
		while( stillToCompile.size() > 0) {
			fringe.push(stillToCompile.get(0));
			ArrayList<Integer> onepath = new ArrayList<Integer>();
			while (!fringe.isEmpty()) {
				Integer top = fringe.pop();
				Integer[] successors = problem.get(top);
				if (canCompile(compiled, successors)) {
					compiled.add(top);
					stillToCompile.remove(top);
					onepath.add(top);
				} else {
					for(int i = 0; i < successors.length; i++) {
						if (!compiled.contains(successors[i])) {
							fringe.push(successors[i]);
						}
					}
				}
			}
			for(int j = 0; j < stillToCompile.size(); j++) {
				Integer element = stillToCompile.get(j);
				if (canCompile(compiled, problem.get(element))) {
					compiled.add(element);
					stillToCompile.remove(element);
					onepath.add(element);
				}
			}
			result.add(onepath);
		}
		return result;
	}
	
	static boolean canCompile(ArrayList<Integer> compiled, Integer[] successors) {
		if (successors.length == 0) {
			return true;
		}
		for(int i = 0; i < successors.length; i++) {
			if (!compiled.contains(successors[i])) {
				return false;
			}
		}
		return true;
	}
}
