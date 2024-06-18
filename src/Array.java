public class Array {
    private int[] items;
    private int count;

    public Array(int length) {
        items = new int[length];
        count = 0;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    public void insert(int val) {
        if(count != items.length){
            items[count] = val;
            count++;
        }
        else{
            int[] newItems;
            newItems = new int[count*2];
            for(int i = 0; i < count; i++){
                newItems[i] = items[i];
            }
            newItems[count] = val;
            items = newItems;
            count++;
        }



    }
    public int indexOf(int val){
        for(int i = 0; i < count; i++){
            if(items[i] == val){
                return i;
            }
        }
        return -1;
    }
    public void removeAt(int index){
        if(index < 0 || index > count){
            throw new IllegalArgumentException("Invalid index");
        }
        for(int i = index; i < count; i++){
            items[i] = items[i + 1];
        }
        count--;

    }
    public int max(){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < count; i++){
            if(items[i] > max){
                max = items[i];
            }
        }
        return max;
    }

    public void reverse(){
        for(int i = 0; i < count/2; i++){
            int temp = items[i];
            items[i] = items[count - 1 - i];
            items[count - 1 - i] = temp;

        }
    }
    public void insertAt(int index, int item){
        if(count != items.length){
            System.out.println("if");
            for (int i = count; i > index; i--) {
                items[i] = items[i-1];
            }
            items[index] = item;
            count++;

        }
        else{
            System.out.println("else");
            int[] newItems;
            newItems = new int[count*2];
            for(int i = 0; i < count; i++){
                newItems[i] = items[i];
            }
            items = newItems;

            for (int i = count; i > index; i--) {
                items[i] = items[i-1];
            }
            count++;
            items[index] = item;
        }
    }
    public Array intersect(Array val){
        Array result = new Array(count);
        for(int i = 0; i < count; i++){
            for(int j = 0; j < val.getCount(); j++){
                if(items[i] == val.getItems()[j] && result.indexOf(items[i]) == -1){
                    result.insert(items[i]);
                }
            }
        }
        return result;
    }

    public int getCount() {
        return count;
    }

    public int[] getItems() {
        return items;
    }
}
