public class Pair {
    private int firstInt, secondInt;

    public Pair(){
        firstInt = -1;
        secondInt = -1;
    }

    public Pair(int x, int y){
        firstInt = x;
        secondInt = y;
    }
    public int first(){
        return firstInt;
    }
    public int second(){
        return secondInt;
    }

    public void setFirst(int first){
        this.firstInt = first;
    }
    public void setSecond(int second){
        this.secondInt = second;
    }
    public boolean isEqualTo(Pair pair){
        return this.first() == pair.first() && this.second() == pair.second();
    }
}
