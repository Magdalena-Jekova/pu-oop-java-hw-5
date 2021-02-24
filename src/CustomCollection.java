public class CustomCollection<T> {

    private Object[] collection;
    private int pointer = 0;

    public CustomCollection() {
        this.collection = new Object[5];
    }

    /**
     * Метод, с който добавяме нови елементи към колекцията.
     */
    public void add(Object element) {
        this.collection[this.pointer++] = element;
    }

    public T get(int index) {
        return (T)this.collection[index];
    }
}