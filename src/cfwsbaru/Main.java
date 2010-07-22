/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import cfwsbaru.entity.Document;
import cfwsbaru.entity.Node;
import java.util.List;

/**
 *
 * @author ata
 */
public class Main {
    public static void main(String[] args){
        //testNewDocument();
        testDocument();
        ///testNewNode();
        //testAddDocument();

    }

    public static void testAddDocument(){
        Document doc = new Document();
        doc.fileName = "namafile.txt";
        doc.content = "Halow saya lapar sekali";
        doc.save();
        Node.addDocument(doc);
    }
    
    public static void testNewNode(){
        Node node = new Node();
        node.words = "Saya sangat lapar";
        node.save();
        System.out.println(node.id);
    }
    public static void testDocument(){
        System.out.println("Test Load Document");
        List<Document> documents = Document.loads("src/cfwsbaru/frequent/");
        Document.saveAll(documents);
        for(Document doc: Document.findAll()){
            System.out.println(doc.id);
            System.out.println(doc.fileName);
            System.out.println(doc.content);
            Node.addDocument(doc);
        }
    }

    public static void testNewDocument(){
        Document document = new Document();
        document.fileName = "Asal Aja";
        document.content = "Asal Juga";
        document.save();;
        System.out.println(document.id);
        
    }
}
