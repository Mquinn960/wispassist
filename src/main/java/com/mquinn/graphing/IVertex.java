package main.java.com.mquinn.graphing;

import java.util.List;

public interface IVertex {

    int getID();
    void setID(int newId);

    void addEdge(Edge edge);
    void removeEdge(Edge edge);

    List<Edge> getEdges();

}
