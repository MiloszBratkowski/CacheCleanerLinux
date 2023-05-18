package pl.techbrat.linux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CacheManager {
    private static CacheManager instance;

    private int cache;

    public CacheManager() {
        instance = this;
        cache = 0;
    }

    public static CacheManager getInstance() {
        return instance;
    }

    public void checkCache() {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("free");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            br.readLine();
            s = br.readLine();
            while (s.contains("  ")) {
                s = s.replaceAll("  ", " ");
            }
            cache = Integer.parseInt(s.split(" ")[5]);
            System.out.println("Current cache: "+cache);
            p.waitFor();
            p.destroy();
        } catch (Exception e) {}
    }

    public int getCache() {
        return cache;
    }

    public void clearCache() {
        if (cache > 300000) {
            Process p;
            try {
                System.out.println("");
                System.out.println("Cashe has been cleared ("+cache+")");
                System.out.println("");
                p = Runtime.getRuntime().exec("echo 1 > /proc/sys/vm/drop_caches");
                p.waitFor();
                p.destroy();
            } catch (Exception e) {}
        }
    }
}
