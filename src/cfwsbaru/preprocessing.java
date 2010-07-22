/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class preprocessing {
    
    //private File file = null;
    private Vector vector = null;
    
    
    public static void main (String[] args) {
        //preprocessing pre = new preprocessing();
    }

    public preprocessing(File dir) {
        vector = new Vector();
        simpanList();
        bacaFolder(dir);
        //bacaFile();
    }
    
    private void simpanList () {
        try {
            File file = new File("src/cfwsbaru/stopwords_list.txt");
            java.util.Scanner input = new java.util.Scanner(file);
            input.useDelimiter(" ");
            while (input.hasNext()) {
                String kata = input.next();
                if (kata.length() > 1) {
                    vector.add(kata);
                }
            }
            /*for (int i = 0; i < vector.size(); i++) {
                
                    System.out.println(vector.get(i));
                    System.out.println("###########################################");
            }*/
        } catch (Exception e) {}
    }
    
    private void bacaFile (File file) {
        try {
            //File file = new File("src/cfwsbaru/resources/1.txt");
            java.util.Scanner input = new java.util.Scanner(file);
            Vector ve = new Vector();
            input.useDelimiter("\n");
            String simpan = "";
            while (input.hasNext()) {
                String kata = input.next();
                //ve.add(kata);
                simpan += kata;
                
            }
            //System.out.println(simpan);
            String[] hasil = simpan.split("[\\p{Punct}\\p{Digit}]");
            
            for (int i = 0; i < hasil.length; i ++) {
                String[] hasil2 = hasil[i].split("\\s");
                
                for (int j = 0; j < hasil2.length; j++)
                    if (hasil2[j].length() > 1) {
                        //System.out.println(hasil2[j]);
                        ve.add(hasil2[j].toLowerCase());
                    }
            }
            
            //System.out.println("VECTOR SIZE " + vector.size());
            
            for (int i = 0; i < vector.size(); i++) {
                String penghilang = (String) vector.get(i);
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
                    //System.out.println(penghilang);
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
                int j = 0;
                while (j < ve.size()) {//ve.get(j) != null) {
                    String hilang = (String) ve.get(j);
                    //System.out.println(hilang);
                    if (hilang.startsWith(penghilang)) {
                        
                        //System.out.println("HAPUS*******************************");
                        ve.removeElementAt(j);
                    } else 
                        j++;
                }
            }
            System.out.println("SELESAI");
            
            for (int i = 0; i < ve.size(); i++) {
                System.out.println((String)ve.get(i));
            }
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(file.getPath());
            
            tulisFile(file, ve);
            /*for (int i = 0; i < vector.size(); i++) {
                //input.useDelimiter((String)vector.get(i));
                input = input.skip((String)vector.get(i));
            }
            while (input.hasNext()) {
                String kata = input.next();
                System.out.print(kata);
            }*/
        } catch (Exception e) {}
    }
    
    private void tulisFile(File namaFile, Vector vec) {
        //Vector v = new Vector();
        String path = "src/cfwsbaru/result/" ;
        String pathAsli = path.replaceFirst(namaFile.getName(), "");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathAsli + "proc_" + namaFile.getName());
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
            bacaFile(f);
        }
    }
}
