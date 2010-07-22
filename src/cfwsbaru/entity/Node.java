/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru.entity;

import cfwsbaru.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CassiestGirl
 */
public class Node extends Connect{
    public Integer id = 0;
    public String words = "";
    public Integer wordLength = 0;
    public Integer documentCount = 0;
    //public ArrayList<Document> docs = new ArrayList<Document>();
    public List<Node> childs = new ArrayList<Node>();
    public Integer parentId = 0;


    public List<Node> getChilds(){
        try {
            ResultSet resultSet = query("SELECT * FROM node " + "WHERE parentId = '" + id + "'");
            while(resultSet.next()) {
                Node node = new Node();
                node.id = resultSet.getInt("id");
                node.words = resultSet.getString("words");
                node.wordLength = resultSet.getInt("wordLength");
                node.documentCount = resultSet.getInt("documentCount");
                node.parentId = resultSet.getInt("parentId");
                childs.add(node);
            }
            return childs;
        } catch (SQLException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean exist(){
        if(count("node", " words='"+words+"'") > 0){
            try {
                ResultSet resultSet = query("SELECT * FROM node "
                        + "WHERE words = '" + words + "'");
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    words = resultSet.getString("words");
                    wordLength = resultSet.getInt("wordLength");
                    documentCount = resultSet.getInt("documentCount");
                    parentId = resultSet.getInt("parentId");
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }


    public boolean update(){
        wordLength = words.split(" ").length;

        try {
            PreparedStatement statement = connection()
                    .prepareStatement("UPDATE node SET"
                    + "words = ?,"
                    + "wordLength = ?,"
                    + "documentCount = ?,"
                    + "parentId = ? "
                    + "WHERE id = ?");
            statement.setString(1, words);
            statement.setInt(2, wordLength);
            statement.setInt(3, documentCount);
            statement.setInt(4, parentId);
            statement.setInt(5, id);
            statement.execute();
            //statement.
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean save(){
        if(exist()){
            return false;
        }
        if(id != 0){
            return update();
        }
        wordLength = words.split(" ").length;
        
        try {
            PreparedStatement statement = connection()
                    .prepareStatement("INSERT INTO "
                    + "node(words, wordLength, documentCount, parentId)"
                    + " VALUES(?,?,?,?)");
            statement.setString(1, words);
            statement.setInt(2, wordLength);
            statement.setInt(3, documentCount);
            statement.setInt(4, parentId);
            //statement.execute();
            boolean result = statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return result;
            //statement.
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean  removeFirstWord(){
        String[] strs = words.split(" ");
        if(strs.length - 1 == 0){
            return  false;
        }
        String result = "";
        for(int i = 1; i < strs.length; i++){
            if(i == 1){
                result += strs[i];
            } else {
                result += " " + strs[i];
            }

        }
        words = result;
        return true;
    }

    public boolean addChild(Node node){
        node.parentId = id;
        return node.save();
    }

    public List<Node> createChildsFromWorlds(List<String> words){
        List<Node> nodes = new ArrayList<Node>();
        for(String word: words){
            Node node = new Node();
            node.words = word;
            addChild(node);
            nodes.add(node);
        }
        if(!nodes.isEmpty()){
            return  nodes;
        }
        return null;
    }
    /**
     * Jika word2 lebih pendek dari word1, maka akan langsung mengembalikan null
     * @param words1
     * @param words2
     * @return
     */
    private List<String> compareWords(String words1, String words2){
        String tail1 = null;
        String tail2 = null;
        List<String> result = new ArrayList<String>();
        String[] arrayWords1 = words1.split(" ");
        String[] arrayWords2 = words2.split(" ");
        if(arrayWords2.length < arrayWords1.length){
            return null;
        }
        
        int countSame = 0;
        int minLength = arrayWords1.length;
        for(int i = 0; i < minLength;i++){
            if(arrayWords1[i].equalsIgnoreCase(arrayWords2[i])){
                countSame++;
            } else {
                if(tail1 == null){
                    tail1 = arrayWords1[i];
                    tail2 = arrayWords2[i];
                } else {
                    tail1 += arrayWords1[i];
                    tail2 += arrayWords2[i];
                }
            }
        }
        if(countSame != 0){
            if(arrayWords1.length != countSame){
                result.add(tail1);
            }
            result.add(tail2);
            return result;
        }

        return null;
    }

    
    
    public List<Node> createChildsFromDocument(Document document){
        List<String> tails = compareWords(words, document.content);
        if(tails != null){
            return createChildsFromWorlds(tails);
        }
        return null;
    }

    public static boolean addDocument(Document document){
        Node node = new Node();
        node.words = document.content;
        node.save();
        (new NodeHasDocument(node.id, document.id)).save();
        if(node.removeFirstWord()){
            node.id = 0;
            document.content = node.words;
            return  addDocument(document);
        }
        return true;

    }
    

}
