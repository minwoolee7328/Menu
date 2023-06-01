import java.util.ArrayList;
public class Order {
    double temp = 0;

    // 영수증에 나올 것들
    // 주문번호
    // 주문내역
    // 총 값

    public void print(ArrayList<Product> shoppingBasketList){

        //똑같은 데이터가 있다면

        for (Product objArr : shoppingBasketList) {
            System.out.println(objArr.product +" | w "+ objArr.productPrice + " | "+ objArr.count +" | " + objArr.productExplain);
        }
    }
    public double addPrice(ArrayList<Product> shoppingBasketList) {

        for (Product objArr : shoppingBasketList) {
            temp = temp + (objArr.productPrice * objArr.count);
        }
        return temp;
    }

    public void reset(){
        this.temp = 0;
    }

}
