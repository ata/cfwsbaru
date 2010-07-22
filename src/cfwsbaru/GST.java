/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import cfwsbaru.entity.Node;
import cfwsbaru.entity.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GST {
    public static Integer no = 0;
    public static Node root = null;
    /*
    public static  void add(Document doc){
        System.out.println(doc.no);
        String content = doc.content;
        int count = wordCount(content);
        while(count > 0){
            no++;
            Node node = new Node(no, content);
            node.addDoc(doc);
            node.firtdoc = doc;
            root.addChild(node);
            content = removeFirstWord(content);
            count = wordCount(content);
            System.out.println(doc.no);
        }
        
    }
    
    public static String removeFirstWord(String str){
        List<String> tmp = Arrays.asList(str.split(" "));
        ArrayList<String> words = new ArrayList<String>(tmp);
        words.remove(0);
        return array2string(words.toArray());
    }
    
    public static String array2string(Object[] words){
        String result = "";
        for(Object word: words){
            result += " " + (String) word;
        }
        return result;
    }
    public static int wordCount(String str){
        return str.trim().split(" ").length;
    }
    
    public static List<String> pembanding(String lama, String baru){
        List<String> list_lama = Arrays.asList(lama.split(" "));
        List<String> list_baru = Arrays.asList(baru.split(" "));
        
        int length = list_baru.size();
        if(list_lama.size() < length){
            length = list_lama.size();
        }
        
        int jumlah_sama = 0;
        for(int i = 0; i < length; i++){
            if(list_baru.get(i).equalsIgnoreCase(list_lama.get(i))){
                list_baru.remove(i);
                list_lama.remove(i);
                jumlah_sama++;
            } else {
                break;
            }
        }
        List<String> result = new ArrayList<String>();
        if(jumlah_sama != 0){
            
            if(list_lama.size() > 0){
                result.add(array2string(list_lama.toArray()));
            }
            if(list_baru.size() > 0){
                result.add(array2string(list_baru.toArray()));
            }
            return  result;
        }
        return result;
    }
    
    
    public static void main(String[] args){
        root = new Node(0);
        for(Document doc: Document.loads("src/cfwsbaru/frequent/")){
            add(doc);
        }
        System.out.println(root);
        
    }
     * 
     */
}
