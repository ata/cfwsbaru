/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru.entity;

import cfwsbaru.Connect;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CassiestGirl
 */
public class Document extends Connect{
    public Integer id = 0;
    public String fileName = "";
    public String content ="";

    

    public boolean exist(){
        if(count("document", " fileName='"+fileName+"'") > 0){
            try {
                ResultSet resultSet = query("SELECT * FROM document "
                        + "WHERE fileName = '" + fileName + "'");
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    fileName = resultSet.getString("fileName");
                    content = resultSet.getString("content");
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    
    
    public boolean save(){
        if(exist()){
            return false;
        }
        try {
            PreparedStatement statement = connection()
                    .prepareStatement("INSERT INTO document(fileName, content)"
                    + " VALUES(?,?)");
            statement.setString(1, fileName);
            statement.setString(2, content);
            boolean result = statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    /**
     * Mengambil Document Dari file
     * @param file
     * @return Document
     */
    public static  Document load(File file){
        Document doc = new Document();
        doc.fileName = file.getName();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" ");
            while (scanner.hasNext()) {
                doc.content += " " + scanner.next();
            }
            doc.content = doc.content.replaceAll("null ","");
            doc.content = doc.content.trim();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    /**
     * Mengambil banyak file dari folder
     * @param folderPath
     * @return ArrayList<Document>
     */
    public static List<Document> loads(String folderPath){
        File folder = new File(folderPath);
        ArrayList<Document> docs = new ArrayList<Document>();
        for(File file: folder.listFiles()){
            docs.add(load(file));
        }
        return docs;
    }

    public static void saveAll(List<Document> documents){
        for(Document document: documents){
            document.save();
        }
        
    }

    public static List<Document> findAll(){
        ResultSet result = Connect.getInstance().query("SELECT * FROM document");
        List<Document> documents = new ArrayList<Document>();
        try {
           
            while (result.next()) {
                Document document = new Document();
                document.id = result.getInt("id");
                document.fileName = result.getString("fileName");
                document.content = result.getString("content");
                documents.add(document);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documents;
    }

}
