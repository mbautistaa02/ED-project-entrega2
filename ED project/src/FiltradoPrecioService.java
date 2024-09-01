import java.util.List;

public class FiltradoPrecioService {
    private BST bst;
    private AVL avl;

    public FiltradoPrecioService() {
        bst = new BST();
        avl = new AVL();
    }

    public void addPrendaBST(prenda prenda) {
        bst.insert(prenda);
    }

    public void addPrendaAVL(prenda prenda) {
        avl.insert(prenda);
    }

    public void filtradoPorPrecioBST(List<prenda> resultados, int minPrice, int maxPrice) {
        List<prenda> prendas = bst.filterByPrice(resultados,minPrice, maxPrice);
        printPrendas(prendas);
    }

    public void filtradoPorPrecioAVL(List<prenda> resultados,int minPrice, int maxPrice) {
        List<prenda> prendas = avl.filterByPrice(resultados,minPrice, maxPrice);
        printPrendas(prendas);
    }

    private void printPrendas(List<prenda> prendas) {
        int n = prendas.size();
        System.out.println(n+" Resultados encontrados");
        if (prendas.isEmpty()) {
            System.out.println("No se encontraron prendas en el rango de precio especificado.");
        } else {
            for (prenda prenda : prendas) {
                System.out.println(prenda);
            }
        }
    }

    public long[] testPerformance(List<prenda> prendas, int minPrice, int maxPrice, int N, List<prenda> resultados) {
        int bstInsertionTimeAVG = 0, 
            bstFilteringTimeAVG = 0, 
            bstTotalTimeAVG,
            avlInsertionTimeAVG = 0,  
            avlFilteringTimeAVG = 0,
            avlTotalTimeAVG;

        for(int i = 0; i < N; i++){
            // BST
            bst = new BST();
            long startTime = System.nanoTime();
            for (prenda prenda : prendas) {
                addPrendaBST(prenda);
            }
            long endTime = System.nanoTime();
            long bstInsertionTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            filtradoPorPrecioBST(resultados, minPrice, maxPrice);
            endTime = System.nanoTime();
            long bstFilteringTime = (endTime - startTime) / 1000000;

            bstInsertionTimeAVG += bstInsertionTime;
            bstFilteringTimeAVG += bstFilteringTime;

            // AVL
            avl = new AVL();
            startTime = System.nanoTime();
            for (prenda prenda : prendas) {
                addPrendaAVL(prenda);
            }
            endTime = System.nanoTime();
            long avlInsertionTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            filtradoPorPrecioAVL(resultados,minPrice, maxPrice);
            endTime = System.nanoTime();
            long avlFilteringTime = (endTime - startTime) / 1000000;

            avlInsertionTimeAVG += avlInsertionTime;
            avlFilteringTimeAVG += avlFilteringTime;
        }

        bstInsertionTimeAVG /= N;
        bstFilteringTimeAVG /= N;
        bstTotalTimeAVG = bstInsertionTimeAVG + bstFilteringTimeAVG;
        avlInsertionTimeAVG /= N;
        avlFilteringTimeAVG /= N;
        avlTotalTimeAVG = bstInsertionTimeAVG + bstFilteringTimeAVG;

        System.out.println("BST Insertion Time: " + bstInsertionTimeAVG + " ms");
        System.out.println("BST Filtering Time: " + bstFilteringTimeAVG + " ms");
        System.out.println("BST Total Time: " + bstTotalTimeAVG + " ms");

        System.out.println("AVL Insertion Time: " + avlInsertionTimeAVG + " ms");
        System.out.println("AVL Filtering Time: " + avlFilteringTimeAVG + " ms");
        System.out.println("AVL Total Time: " + avlTotalTimeAVG + " ms");

        long [] a = new long[] {bstInsertionTimeAVG, bstFilteringTimeAVG, bstTotalTimeAVG, avlInsertionTimeAVG, avlFilteringTimeAVG, avlTotalTimeAVG};
        return a;
    }
    public long[] testPerformance(List<prenda> prendas, int minPrice, int maxPrice, List<prenda> resultados) {
        return testPerformance(prendas, minPrice, maxPrice,1, resultados);
    }
}
