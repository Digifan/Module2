package Dijkstra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {

    private final int INFINITY = 100000000; // это число у нас будет служить в качестве "бесконечности"
    private final City[] cityList; // список городов
    private final int[][] relationMatrix; // матрица связей городов
    private final int countOfCities; // текущее количество городов
    private List<Path> shortestPaths; // список данных выгодных путей
    private int currentCity; // текущий город
    private int startToCurrent; //стоимость до текущего города
    int startIndex;
    int finishIndex;

    public Graph() throws IOException {
        HashMap<String, Integer> cityIndex = new HashMap<>();
        Scanner in = new Scanner(new File("src/main/resources/input.txt"));
        int max_city = in.nextInt(); // максимальное количество городов
        in.nextLine();
        cityList = new City[max_city]; // список городов - инициализация
        countOfCities = max_city;
        relationMatrix = new int[max_city][max_city]; // матрица смежности - инициализация
        for (int i = 0; i < max_city; i++) {// матрица смежности заполняется
            for (int k = 0; k < max_city; k++) { // бесконечными ценами
                relationMatrix[i][k] = INFINITY;
            }
        }
        for (int i = 0; i < max_city; i++) {
            String name = in.nextLine();
            cityList[i] = new City(name);
            cityIndex.put(name, i);
            int neighbor = in.nextInt();
            for (int j = 0; j < neighbor; j++) {
                int index = in.nextInt();
                int cost = in.nextInt();
                relationMatrix[i][index - 1] = cost; // матрица смежности заполняется
            }
            in.nextLine();
        }

        int r = in.nextInt();
        in.nextLine();

        for (int j = 0; j < r; j++) {

            String[] startFinish = in.nextLine().trim().split(" ");

            startIndex = cityIndex.get(startFinish[0]);
            finishIndex = cityIndex.get(startFinish[1]);
            currentCity = startIndex;
            writeToFile(calculateGraph());
        }
    }
    public static void start() throws IOException {
        File output = new File("src/main/resources/output.txt");
        if (output.exists()) output.delete();
        new Graph();
    }
    private String calculateGraph() {

        int countOfCityInTree;  // количество рассмотренных городов в дереве
        shortestPaths = new ArrayList<>(); // задается пустым

        //выбор выгодного пути
        //  задание данных для стартового города
        cityList[startIndex].setInTree(true); // включение в состав дерева первого элемента
        countOfCityInTree = 1;

        // заполнение путей для городов, смежных с стартовым
        for (int i = 0; i < countOfCities; i++) {
            int tempDist = relationMatrix[startIndex][i];
            Path path = new Path(tempDist);
            path.getParentCity().add(startIndex);// первым родительским элементом, будет всегда стартовая вершина
            shortestPaths.add(path);
        }

        // пока все вершины не окажутся в дереве
        while (countOfCityInTree < countOfCities) { // выполняем, пока количество городов в дереве не сравняется с общим количеством городов
            int indexMin = getMin();//получаем индекс города с наименшей ценой из городов, еще не входящих в дерево
            int minDist = shortestPaths.get(indexMin).getCost();// минимальная цена города, из тех, которые ещё не в дереве

            if (minDist == INFINITY) {
                //System.out.println("В графе пристувствуют недостижимые вершины");
                break;// в случае если остались непройденными только недостижимые вершины, мы выходим из цикла
            } else {
                currentCity = indexMin; // переводим указатель currentVert к текущей вершине
                startToCurrent = shortestPaths.get(indexMin).getCost();// задаем дистанцию к текущей вершине
           }

            cityList[currentCity].setInTree(true);  //включение текущей вершины в дерево
            countOfCityInTree++; // увеличиваем счетчик вершин в дереве
            updateShortestPaths(); // обновление списка кратчайших путей
        }
        // выводим результаты
        StringBuilder result = new StringBuilder(shortestPaths.get(finishIndex).getCost() + " [");
        List<Integer> parents = shortestPaths.get(finishIndex).getParentCity();

        for (Integer parent : parents) {
            result.append(cityList[parent].getName()).append(", ");
        }
        result.append(cityList[finishIndex].getName()).append("]");

        for (int i = 0; i < countOfCities; i++) { // очиска дерева
            cityList[i].setInTree(false);
        }
        return result.toString();
    }

    private void writeToFile (String string) {

        try (FileWriter output = new FileWriter("src/main/resources/output.txt", true)) {
            output.write(string+"\n");
            output.flush();

            System.out.println(string);
        } catch (IOException e) {
            System.out.println("File write error" + e);
        }
    }
    private int getMin() {
        int minCost = INFINITY; // за точку старта взята "бесконечная" цена
        int indexMin = startIndex;
        for (int i = 0; i < countOfCities; i++) {// для каждой вершины
            if (!cityList[i].isInTree() && shortestPaths.get(i).getCost() < minCost) { // если вершина ещё не в дереве и её цена меньше старого минимума
                minCost = shortestPaths.get(i).getCost(); // задаётся новый минимум
                indexMin = i; // обновление индекса вершины, содержащую минимальную цену
            }
        }
        return indexMin; //возвращает индекс вершины с наименшей ценой, из вершин еще не входящих в дерево
    }

    private void updateShortestPaths() {
        int cityIndex = 0;
        while (cityIndex < countOfCities) { // перебор городов

            if (cityList[cityIndex].isInTree()) { // если город уже включен в дерево, он пропускается
                cityIndex++;
                continue;
            }
            // вычисление стоимости для одного элемента Path
            // получение ребра от currentCity к cityIndex
            int currentToFringe = relationMatrix[currentCity][cityIndex];
            // суммирование всех цен
            int startToFringe = startToCurrent + currentToFringe;
            // определение стоимости текущего элемента cityIndex
            int shortPathDistance = shortestPaths.get(cityIndex).getCost();

            // сравнение стоимости через currentCity с текущей стоимостью в вершине с индексом cityIndex
            if (startToFringe < shortPathDistance) {// если меньше, то у вершины под индексом cityIndex будет задан новый дешевый путь
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentCity).getParentCity());//создаём копию списка родителей вершины currentVert
                newParents.add(currentCity);// задаём в него и currentCity как предыдущий
                shortestPaths.get(cityIndex).setParentCity(newParents); // соохраняем новый маршут
                shortestPaths.get(cityIndex).setCost(startToFringe); // соохраняем новую стоимость
            }
            cityIndex++;
        }
    }
}

