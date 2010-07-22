/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CassiestGirl
 */
public class f2w {
    private Vector vec = null;
    public Vector hasil = null;
    
  /*  public static void main (String[] args) throws FileNotFoundException {
        f2w fw = new f2w();
        }*/
    
    public f2w() {
        vec = new Vector();
        countWord();
        bacaWS();
        readFolder();
    }
    
    private void countWord(){
        try {
            Map map = new TreeMap();
            Integer One = new Integer(1);
            File dir = new File("src/cfwsbaru/getword/");
            File[] files = dir.listFiles();
            for (File f : files) {
                java.util.Scanner input = new java.util.Scanner(f);
                input.useDelimiter("\n");
                String simpan = "";
                while (input.hasNext()) {
                    String kata = input.next();
                    simpan += kata;
                }
                // System.out.println(simpan);
                String[] hasil = simpan.split(" ");

                for (int i = 0; i < hasil.length; i++) {
                    if (hasil[i].length() > 0) {
                        Integer freq = (Integer) map.get(hasil[i]);
                        if (freq == null) {
                            freq = One;
                        } else {
                            int val = freq.intValue();
                            freq = new Integer(val + 1);
                        }
                        map.put(hasil[i], freq);
                    }
                }
           }
           //   System.out.println(map);
            ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
            //String[] resultw = new String[map.size()];
            ArrayList<String> resultw = new ArrayList<String>();
            //int[] resultc = new int [map.size()];
            // ArrayList<Integer> resultc = new ArrayList<Integer>();
            int i = 0;
            // int k = 0;
            for (Map.Entry<String, Integer> ent : entries) {
                if (ent.getValue() >= 3) {
                    //resultw[i++] = ;
                    resultw.add(ent.getKey());
                }
            }
            /*for (Map.Entry<String, Integer> ent1 : entries) {
            if (ent1.getValue() >= 2) {
            //resultc[k++] = ent1.getValue();
            resultc.add(ent1.getValue());
            }
            }*/
            String fi = "src/cfwsbaru/ws.txt";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(fi);
                for (int j = 0; j < resultw.size(); j++) {
                   String tulis = (String) resultw.get(j) + " ";
                   fos.write(tulis.getBytes());
                }
           } catch (Exception e) {
            }
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(f2w.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void bacaWS() {
        try {
            File file = new File("src/cfwsbaru/ws.txt");
            java.util.Scanner input = new java.util.Scanner(file);
            input.useDelimiter(" ");
            while (input.hasNext()) {
                String kata = input.next();
                if (kata.length() > 1) {
                    vec.add(kata);
                }
            }
            
        } catch (Exception e) { }
    }
    
    private void readFolder() {
        File dir = new File ("src/cfwsbaru/getword/");
        File files[] = dir.listFiles();
        for (File f : files) {
            removeWords(f);
            
        }
    }
    
    public void removeWords(File file) {
        try {
            java.util.Scanner in = new java.util.Scanner(file);
            Vector v = new Vector();
            in.useDelimiter(" ");
            while (in.hasNext()) {
                String word = in.next();
                if (word.length() > 1) {
                    v.add(word);
                }
            }
            hasil = new Vector();
            int i =0;
            while (i < v.size()) {
                String f2w = (String) v.get(i);
               // System.out.println ("---------");
               // System.out.println (f2w);
               // System.out.println("----------");
                for (int j =0; j < vec.size(); j++) {
                    String fw = (String) vec.get(j);
                  // System.out.println("$$$$$$$$$$$$$$");
                  //  System.out.println(fw);
                    if(f2w.equals(fw)) {
                        hasil.add(f2w);
                      //  System.out.println("^^^^^^^^^^");
                      //  System.out.println();
                        
                    }
                }
                i++;
            }
          /* for (int k =0; k < hasil.size(); k++) {
                System.out.println((String) hasil.get(k));
            }
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(file.getPath());*/
            tulisFile(file, hasil);
        } catch (Exception e) { }
    }
    private void tulisFile(File namaFile, Vector vec) {
        //Vector v = new Vector();
        String path = "src/cfwsbaru/frequent/" ;
        String pathAsli = path.replaceFirst(namaFile.getName(), "");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathAsli + "f2w_" + namaFile.getName());
            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < vec.size(); i++) {
                String tulis = (String) vec.get(i) + " ";
                fos.write(tulis.getBytes());
                }
                
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
        
    }
    
}





