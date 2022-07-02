package projects.campuspaths.datastructures;

import java.util.*;

/**
 * A Graph is a mutable directed, labeled graph. A typical Graph consists of
 * a set of identical nodes, which are represented as strings, and a set of edges.
 * For example,
 *      G = (nodes, edges) where nodes = {n1, n2, ...} and edges = {e1, e2, ...}
 *
 * @param <V> the type of nodes.
 * @param <E> the type of the label of edges.
 */
public class Graph<V, E> {
    // RI:  adjacencyList != null
    //      no null values and no identical values in adjacencyList.
    // AF(this) = a graph with nodes of this.adjacencyList.keys and edges of this.adjacencyList.values.
    private Map<V, List<Edge<V, E>>> adjacencyList;

    public static final boolean DEBUG = true;

    private void checkRep() {
        assert adjacencyList != null : "the adjacency list cannot be null.";
        if (DEBUG) {
            for (V node: adjacencyList.keySet()) {
                List<Edge<V, E>> edges = adjacencyList.get(node);
                for (int i = 0; i < edges.size(); i++) {
                    assert edges.get(i) != null : "Edges cannot be null";
                    assert i == edges.lastIndexOf(edges.get(i)) : "There cannot be identical edges in the map";
                }
            }
        }
    }

    /**
     * Construct a new Graph instance.
     * @spec.effects makes an empty graph.
     */
    public Graph() {
        adjacencyList = new HashMap<>();
        checkRep();
    }

    /**
     * Add a node to the graph.
     * @param node the new node being added to this graph
     * @spec.requires node != null
     * @spec.modifies this
     * @spec.effects add the node to this graph, if no node with this name already exists in this graph
     */
    public void addNode(V node) {
        checkRep();
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new LinkedList<>());
        }
        checkRep();
    }

    /**
     * Add an edge to the graph.
     * @param edge the new edge being added to this graph
     * @spec.requires edge != null &amp;&amp; edge.parent and edge.child are in this graph
     * @spec.modifies this
     * @spec.effects add the edge to this graph, if no edge with the same label has the same parent and child nodes
     */
    public void addEdge(Edge<V, E> edge) {
        checkRep();

        V parent = edge.getParent();
        if (!adjacencyList.get(parent).contains(edge)) {
            adjacencyList.get(parent).add(edge);
        }

        checkRep();
    }

    /**
     * Returns an unmodifiable list of node in the graph.
     * @return an unmodifiable list of node in the graph
     */
    public List<V> getNodes() {
        checkRep();
        return List.copyOf(adjacencyList.keySet());
    }

    /**
     * Return a list of child nodes of the given node.
     * @param parent the node whose children are looked for
     * @spec.requires parent != null
     * @throws NoSuchElementException if the parent node doesn't exist in this graph
     * @return all child nodes of the given parent node
     */
    public List<V> getChildren(V parent) {
        checkRep();
        if (!adjacencyList.containsKey(parent)) {
            throw new NoSuchElementException("The given parent node doesn't exist in the map");
        }
        List<V> children = new ArrayList<>();
        for (Edge<V, E> edge : adjacencyList.get(parent)) {
            children.add(edge.getChild());
        }

        checkRep();
        return children;
    }

    /**
     * Return a list of parent nodes of the given node
     * @param child the node whose parents are looked for
     * @spec.requires child != null
     * @throws NoSuchElementException if the child node doesn't exist in this graph
     * @return all parent nodes of the given child node
     */
    public List<V> getParents(V child) {
        checkRep();
        if (!adjacencyList.containsKey(child)) {
            throw new NoSuchElementException("The given child node doesn't exist in the map");
        }
        List<V> parents = new ArrayList<>();

        List<Edge<V, E>> edgesTo = getEdgesTo(child);
        for (Edge<V, E> edge: edgesTo) {
            if (!parents.contains(edge.getParent())) {
                parents.add(edge.getParent());
            }
        }

        checkRep();
        return parents;
    }

    /**
     * Return a list of edges pointing from the given parent node to the given child node.
     * @param parent the starting node of expected edges
     * @param child the end node of expected edges
     * @spec.requires parent != null &amp;&amp; child != null
     * @throws NoSuchElementException if the child or parent doesn't exist in this graph
     * @return a list of edges from the parent node to the child node
     */
    public List<Edge<V, E>> getEdges(V parent, V child) {
        checkRep();
        if (!adjacencyList.containsKey(parent)) {
            throw new NoSuchElementException("The given parent node doesn't exist in the map");
        }
        if (!adjacencyList.containsKey(child)) {
            throw new NoSuchElementException("The given child node doesn't exist in the map");
        }
        List<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E> edge: adjacencyList.get(parent)) {
            if (edge.getChild().equals(child)) {
                edges.add(edge);
            }
        }

        checkRep();
        return edges;
    }

    /**
     * Return a list of edges starting from the given node.
     * @param node the starting node
     * @spec.requires node != null
     * @throws NoSuchElementException if the node doesn't exist in this graph
     * @return a list of edges starting from the given node
     */
    public List<Edge<V, E>> getEdgesFrom(V node) {
        checkRep();
        if (!adjacencyList.containsKey(node)) {
            throw new NoSuchElementException("The given node doesn't exist in the map");
        }

        checkRep();
        return adjacencyList.get(node);
    }

    /**
     * Return a list of edges pointing to the given node.
     * @param node the destination node
     * @spec.requires node != null
     * @throws NoSuchElementException if the node doesn't exist in this graph
     * @return a list of edges pointing to the given node
     */
    public List<Edge<V, E>> getEdgesTo(V node) {
        checkRep();
        if (!adjacencyList.containsKey(node)) {
            throw new NoSuchElementException("The given child node doesn't exist in the map");
        }
        List<Edge<V, E>> edges = new ArrayList<>();
        for (V parent: adjacencyList.keySet()) {
            if (!parent.equals(node)) {
                for (Edge<V, E> edge : adjacencyList.get(parent)) {
                    if (edge.getChild().equals(node)) {
                        edges.add(edge);
                    }
                }
            }
        }

        checkRep();
        return edges;
    }

    /**
     * Remove the edge equal to the given edge.
     * @param edge the edge being removed
     * @spec.requires edge != null
     * @spec.modifies this
     * @spec.effects remove the same edge in the graph, if exists
     */
    public void removeEdge(Edge<V, E> edge) {
        checkRep();
        adjacencyList.get(edge.getParent()).remove(edge);
        checkRep();
    }

    /**
     * An Edge is an immutable directed, labeled edge, pointing from a source to a destination.
     * A typical Edge is a pair of nodes (node1, node2) with a label, representing
     * a labeled edge pointing from node1 to node2.
     * @param <V> the type of nodes on the ends of an edge.
     * @param <E> the type of the label of an edge.
     */
     public static class Edge<V, E> {
        // RI: parent != null && child != null && label != null
        // AF(this) = an edge from parent to child with label

        private final V parent;
        private final V child;
        private final E label;

        /**
         * Construct a new Edge instance.
         * @param parent the source from which the new edge starts
         * @param child the destination in which the new edge ends
         * @param label the label for the new edge
         * @spec.requires source != null &amp;&amp; dest != null &amp;&amp; label != null
         * @spec.effects make a new edge from start to end with the given label
         */
        public Edge(V parent, V child, E label) {
            this.parent = parent;
            this.child = child;
            this.label = label;
            checkRep();
        }

        private void checkRep() {
            assert parent != null && child != null && label != null : "Illegal edge arguments";
        }

        /**
         * Return the parent node of this edge.
         * @return this.parent
         */
        public V getParent() {
            checkRep();
            return parent;
        }

        /**
         * Return the child node of this edge.
         * @return this.child
         */
        public V getChild() {
            checkRep();
            return child;
        }

        /**
         * Return the label of this edge.
         * @return this.label
         */
        public E getLabel() {
            checkRep();
            return label;
        }

        @Override
        public int hashCode() {
            checkRep();
            return parent.hashCode() ^ child.hashCode() ^ label.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (! (obj instanceof Edge<?, ?>)) {
                return false;
            }
            Edge<?, ?> e = (Edge<?, ?>) obj;

            checkRep();
            return parent.equals(e.parent) && child.equals(e.child) && label.equals(e.label);
        }
    }
}
