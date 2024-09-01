public class MaxQuadHeap extends MinQuadHeap{
    public MaxQuadHeap() {
        super();
    }

    public void SiftUp(int i){
        while (i > 0 && heapArray[i].precio > heapArray[parent(i)].precio) {
            swap(heapArray,parent(i) ,i );
            i = parent(i);
        }
    }

    public prenda getMax() {
        return getMin();
    }

    public prenda extractMax() {

        if (current_heap_size <= 0) {
            return null;
        }

        if (current_heap_size == 1) {
            current_heap_size--;
            return heapArray[0];
        }

        prenda root = heapArray[0];
        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        SiftDown(0);

        return root;
    }
    private void SiftDown(int key) {
        int minindex = key;

        for (int j = 1; j <= 4; j++) {
            int childIndex = child(key, j);
            if (childIndex < current_heap_size && heapArray[childIndex].precio > heapArray[minindex].precio) {
                minindex = childIndex;
            }
        }

        if (minindex != key) {
            swap(heapArray, key, minindex);
            SiftDown(minindex);
        }
    }}


