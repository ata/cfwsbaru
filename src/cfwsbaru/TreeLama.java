/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CassiestGirl
 */
public class TreeLama {

    private static class node {
        private int node;

        public node() {
        }
    }
    node root;
    HashMap<pasanganNode, String> label = new HashMap<pasanganNode, String>();
    HashMap<String, String> doc = new HashMap<String, String>();
    File files[] = null;
    
    public TreeLama() {
        root = new node();
    }
    
    private void bacaDoc() {
        File dir = new File ("");
        files = dir.listFiles();
        for (File f : files) {
            try {
                java.util.Scanner input = new java.util.Scanner(f);
                input.useDelimiter("\n");
                String simpanAwl = "";
                while (input.hasNext()){
                    simpanAwl.concat(input.next());
                }
                doc.put(f.getName(), simpanAwl);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TreeLama.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private void constructTree() {
        root.node = 0;
        doc.get(files[0]);
    }

}
