public class MinQuadHeap {
    prenda[] heapArray;
    int capacity;
    int current_heap_size;

    public MinQuadHeap() {
        capacity = 100;
        heapArray = new prenda[capacity];
        current_heap_size = 0;
    }

    protected void swap(prenda[] arr, int a, int b) {
        prenda temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Cálculo del padre en un heap cuaternario
    protected int parent(int key) {
        return (key - 1) / 4;
    }

    // Cálculo del primer hijo en un heap cuaternario
    protected int child(int key, int childIndex) {
        return 4 * key + childIndex;
    }

    public boolean insertKey(prenda key) {
        if (current_heap_size == capacity) {
            capacity *= 2;
            prenda[] newHeapArray = new prenda[capacity];
            System.arraycopy(heapArray, 0, newHeapArray, 0, current_heap_size);
            heapArray = newHeapArray;
        }

        int i = current_heap_size;
        heapArray[i] = key;
        current_heap_size++;
        SiftUp(i);

        return true;
    }

    public void SiftUp(int i) {
        while (i != 0 && heapArray[i].precio < heapArray[parent(i)].precio) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
    }

    public void decreaseKey(int key, prenda new_val) {
        heapArray[key] = new_val;

        while (key != 0 && heapArray[key].precio < heapArray[parent(key)].precio) {
            swap(heapArray, key, parent(key));
            key = parent(key);
        }
    }

    public prenda getMin() {
        return heapArray[0];
    }

    public prenda extractMin() {
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

    public void deleteKey(int key) {
        decreaseKey(key, null);
        extractMin();
    }

    private void SiftDown(int key) {
        int minindex = key;

        for (int j = 1; j <= 4; j++) {
            int childIndex = child(key, j);
            if (childIndex < current_heap_size && heapArray[childIndex].precio < heapArray[minindex].precio) {
                minindex = childIndex;
            }
        }

        if (minindex != key) {
            swap(heapArray, key, minindex);
            SiftDown(minindex);
        }
    }

    public boolean isEmpty() {
        return current_heap_size == 0;
    }
}
