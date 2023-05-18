package pl.techbrat.linux;

public class Main {
    public static void main(String[] args) {
        new CacheManager();

        MainListener mainListener = new MainListener();
        new Thread(mainListener).start();
    }
}