public class ArrayList<E> implements List<E> {

    private Object[] list;

    public ArrayList() {
        list = new Object[0];
    }


    @Override
    public void add(E element) {

        // создаем новый список с длиной, которая больше длины имеющегося списка на 1
        Object[] newList = new Object[list.length + 1];

        // копируем данные в новый список
        System.arraycopy(list, 0, newList, 0, list.length);

        // заполняем последнюю ячейку переданным значением
        newList[list.length] = element;
        list = newList;
    }

    @Override
    public E get(int index) {

        // проверяем введенное число
        if (index > list.length - 1 || index < 0){
            System.err.println("Число " + index + " не является индексом данного списка");
            return null; // я понимаю, что здесь можно выбросить исключение (или обернуть все в try).
            // Это единственный способ обойтись без null?
        }

        return (E) list[index];
    }

    // переменную вынесла за пределы внутреннего класса, чтобы она меняла свое значение при итерации
    // (не очень поняла этот момент)
    private int iteratorIndex = -1;

    private class ArrayListIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            if (iteratorIndex < list.length - 1) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            iteratorIndex++;
            return (E) list[iteratorIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

}
