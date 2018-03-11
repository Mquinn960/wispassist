package main.java.com.mquinn.graphing;

import java.util.List;

public interface IVertex {

    int id = 0;
    List<Edge> getEdges();

    int getID();
    void setID(int newId);

    void addEdge(Edge edge);
    void removeEdge(Edge edge);

}
