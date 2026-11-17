public class ContactList {

    private int       capacity    = 4;
    private int       size        = 0;
    private Contact[] contactlist = new Contact[4];

    public int getSize()     { return size; }
    public int getCapacity() { return capacity; }

    public void insertAtEnd(Contact c) {

        if (size == capacity) {
            resize();
        }

        if (size == capacity) {
            System.out.println("testing!!!!!!!!!!!!!!!!!!!!");
            return;
        }

        contactlist[size] = c;
        size++;
    }

    public Contact get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return contactlist[index];
    }

    public boolean isSortedById() {
        for (int i = 0; i < size - 1; i++) {
            if (contactlist[i].getId() > contactlist[i + 1].getId()) {
                return false;
            }
        }
        return true;
    }

    public int linearSearch(int id) {
        return Searcher.linearSearch(contactlist, size, id);
    }

    public int binarySearch(int id) {
        return Searcher.binarySearch(contactlist, size, id);
    }

    public int recursiveBinarySearch(int id) {
        return Searcher.recursiveBinarySearch(contactlist, id, 0, size - 1);
    }

    public void resize() {

            capacity = capacity * 2;

        Contact [] newContactlist = new Contact[capacity];

        for (int i = 0; i < size; i++ ){
           newContactlist[i] = contactlist[i];
        }

            contactlist = newContactlist;

    }

    public boolean insertAtPosition(int index, Contact c) {

        return false;
    }

    public boolean delete(int index) {

        return false;
    }
}
