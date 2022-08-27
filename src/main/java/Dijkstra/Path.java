package Dijkstra;


import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
public class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины
        private int cost; // текущая дистанция от начальной вершины
        private List<Integer> parentCity; // текущий родитель вершины

        public Path(int cost) {
            this.cost = cost;
            this.parentCity = new ArrayList<>();
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public List<Integer> getParentCity() {
            return parentCity;
        }

        public void setParentCity(List<Integer> parentCity) {
            this.parentCity = parentCity;
        }
}

