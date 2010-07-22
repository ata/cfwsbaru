/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class hdProcess {
    
    public static void main(String[] args) {
        hdProcess hdp = new hdProcess();
    }
    
    public hdProcess() {
        listFolder();
    }
    
    private void listFolder() {
        File list[] = null;
        File mydir = new File ("c:/");
        File[] listdir = mydir.listFiles();
        for (File dir : listdir) {
            if (dir.isDirectory()) {
                list = dir.listFiles();
            }
        }
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
    
    

}
