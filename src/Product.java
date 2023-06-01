import java.util.ArrayList;

public class Product{

    String product;
    double productPrice;
    String productExplain;

    int count = 1;
    public Product(){}

    public Product(String product, double productPrice) {
        this.product = product;
        this.productPrice = productPrice;
    }
    public Product(String product, double productPrice, String productExplain) {
        this.product = product;
        this.productPrice = productPrice;
        this.productExplain = productExplain;
    }

    public boolean menuCount(String product, ArrayList<Product> products){

        // 전에 있는 장바구니에서 같은 목록이 있는지 검사
        for(int i = 0; i<products.size(); i++){
            if(product == products.get(i).product){
                //이미있던 메뉴의 count를 ++
                ++products.get(i).count;
                return true;
            }
        }

        return false;
    }

}
