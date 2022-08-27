package Dijkstra;

public class City {
    private String name;
    private boolean isInTree;

    public City(String name) {
        this.name = name;
        this.isInTree = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}

