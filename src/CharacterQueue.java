import java.util.LinkedList;

/**
 * 字符队列 处理字符分割逻辑
 */
public class CharacterQueue {

    private LinkedList<Character> cache = new LinkedList<>();

    public CharacterQueue() {
        return;
    }

    public void add(char[] in, int size) {
        if (size > in.length) {
            System.out.println("size 大于 输入字符数组大小");
            return;
        }
        for (int index = 0; index < size; index++) {
            cache.add(in[index]);
        }
    }

    public String get() {
        int index = 0;
        for (index = 0; index < cache.size(); index++) {
            if (cache.get(index).equals('$')) {
                break;
            }
        }
        if (index < cache.size()) {
            char[] data = new char[index];  //$字符不需要
            for (int i = 0; i < index; i++) {
                data[i] = cache.pop();
            }
            cache.pop();   //删除$
            return new String(data);
        }
        return null;
    }
}
