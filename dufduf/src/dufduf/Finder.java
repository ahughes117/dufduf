
package dufduf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class Finder
 * This class contains the main functionality for finding the duplicates.
 * 
 * @author Alex Hughes <ahughes@ahughes.org>
 */
public class Finder {
    
    private static MessageDigest md;
    
    public Finder() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-512");
    }
    
    public void find(Map<String, List<String>> lists, File directory) {
        for (File child : directory.listFiles()) {
            if (child.isDirectory()) {
                find(lists, child);
            } else {
                try {
                    FileInputStream fin = new FileInputStream(child);
                    byte data[] = new byte[(int) child.length()];
                    fin.read(data);
                    fin.close();
                    String hash = new BigInteger(1, md.digest(data)).toString(16);
                    List<String> list = lists.get(hash);
                    if (list == null) {
                        list = new LinkedList<String>();
                        lists.put(hash, list);
                    }
                    list.add(child.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException("cannot read file " + child.getAbsolutePath(), e);
                }
            }
        }
    }

}
