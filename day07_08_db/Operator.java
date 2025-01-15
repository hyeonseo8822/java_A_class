package day07_08_db;

public class Operator {
    Database db = new Database();
    Login lo = null;
    public static void main(String[] args){
        Operator opt = new Operator();
        opt.lo= new Login(opt);
    }
}