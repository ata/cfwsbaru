/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cfwsbaru;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class getWords {

    private Vector vector = null;
    
   /* public static void main(String[] args) {
        getWords gw = new getWords();
    }*/

    public getWords() {
        File dir = new File ("src/cfwsbaru/result/");
        vector = new Vector();
        //simpanWords();
        bacaFolder(dir);
        }

    private void simpanWords(File file) {
        try {
            //File file = new File("src/cfwsbaru/result/preprocess_1.txt");
            java.util.Scanner list = new java.util.Scanner(file);
            while (list.hasNext()) {
                String wordlist = list.next();
                if (!vector.contains(wordlist)){
                vector.add(wordlist);
                
                /* if (!vector.equals(wordlist)) {
                    System.out.println(wordlist);
                    
                    }*/
                }
                }
            tulisFile(file, vector);
                vector.clear();
            
         } catch (Exception e) {
        }
    }
    private void tulisFile(File namaFile, Vector vec) {
        //Vector v = new Vector();
        String path = "src/cfwsbaru/getword/" ;
        String pathAsli = path.replaceFirst(namaFile.getName(), "");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathAsli + "new_" + namaFile.getName());
            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < vec.size(); i++) {
                String tulis = (String) vec.get(i) + " ";
                fos.write(tulis.getBytes());
                }
                //out.newLine();
            
        } catch (Exception ex) {
            Logger.getLogger(preprocessing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(preprocessing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //out.newLine();
        
    }
    
    private void bacaFolder(File dir) {
        //File dir = new File ("src/cfwsbaru/resources/");
        File files[] = dir.listFiles();
        for (File f : files) {
            simpanWords(f);
        }
    }
    
}
