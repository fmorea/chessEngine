package backend;

public class PosizioneSpeculativa {
    private int x;
    private int y;

    private int x0;
    private int y0;

    public void set(int y0, int x0, int y, int x){
        this.y = y;
        this.x0 = x0;
        this.x = x;
        this.y0 = y0;
    }
    // Overriding toString() method of String class
    @Override
    public String toString() {
        return "{ ["+x0+","+y0+"]>>["+x+","+y+"] }\n";
    }
}
