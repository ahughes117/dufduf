/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dufduf;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final String FILENAME = "C:\\Dropbox\\Camera Uploads";

    public static void main(String[] args) {
        try {
            Finder f = new Finder();
            File dir = new File(FILENAME);
            Map<String, List<String>> lists = new HashMap<String, List<String>>();

            f.find(lists, dir);

            int ctr = 0;
            for (List<String> list : lists.values()) {
                if (list.size() > 1) {
                    System.out.println("--");
                    for (String file : list) {
                        System.out.println(file);
                    }
                    ctr++;
                }
            }
            System.out.println("Duplicate Files Found: " + ctr);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
