/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru.entity;

import cfwsbaru.Connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ata
 */
public class NodeHasDocument extends Connect{
    public Integer nodeId;
    public Integer documentId;

    public NodeHasDocument(Integer nodeId, Integer documentId) {
        this.nodeId = nodeId;
        this.documentId = documentId;
    }

    
    
    public boolean exist(){
        if(count("node_has_document", " nodeId = "+nodeId+" AND documentId = "+documentId) > 0){
            return true;
        }
        return false;
    }

    public boolean save(){
        if(exist()){
            return false;
        }

        try {
            PreparedStatement statement = connection()
                    .prepareStatement("INSERT INTO "
                    + "node_has_document(nodeId, documentId)"
                    + " VALUES(?,?)");
            statement.setInt(1, nodeId);
            statement.setInt(2, documentId);
            return statement.execute();

            //statement.
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    

}
