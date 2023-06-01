
import java.util.ArrayList;
import java.util.Scanner;
//Menu 에서 Scanner 쓰기
public class Menu {
    Scanner sc = new Scanner(System.in);

    String[] menus  = {"Burgers", "Frozon Custard",  "Drinks", "Beer", "Order", "Cancel" };

    String[] menuExplain = {"햄버거 비프 통살을 다져만든 버거", "매장에서 신선하게 만드는 아이스크림",  "매장에서 직접 만드는 음료",  "뉴욕 브루클린 브루머리에서 양조한 맥주",
            "장바구니를 확인 후 주문합니다.",  "진행중인 주문을 취소합니다."};

    String[] menuChoice;

    double[] menuChoicePrice;

    String[] menuChoiceExplain;

    double menuSizeUp;

    private final  Product products;
    private final  Order order;
    public Menu(Product products, Order order){
        this.products = products;
        this.order = order;
    }

    //장바구니 역할
    ArrayList<Product>  shoppingBasketList = new ArrayList<Product>();

    ArrayList<Product> receiptList;
    ArrayList<Product>  receiptLists = new ArrayList<Product>();

    //대기번호 수
    int peopleNumber = 0;
    void showMenu (){
        System.out.println("[ SHAKESHACK MENU ]");
        for(int i = 0; i<menus.length;i++) {
            System.out.println(i+1 + ". " + menus[i] + " |" + menuExplain[i]);
            if(i == menus.length-3){
                System.out.println();

                System.out.println("[ ORDER MENU ]");
            }
        }
        //번호 선택;
        int number = sc.nextInt();

        //메뉴 번호
        if(number == 0){
            System.out.println("[ 총 판매금액 현황 ]");
            System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");

            for(int i = 0 ; i<receiptLists.size();i++){
                System.out.println(" - " + receiptLists.get(i).product + " |  W "+  receiptLists.get(i).productPrice);
            }
            System.out.printf("%s % .1f %s","현재까지 총 판매된 금액은",order.addPrice(receiptLists)," 입니다.");
            order.reset();
            System.out.println();
            System.out.println("1. 돌아가기");

            if(sc.nextInt()==1){
                showMenu ();
            }else {
                System.out.println("잘못 입력 하셨습니다. 3초후 메뉴로 돌아갑니다");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showMenu ();
            }


        }else if(number > 0 && number  <= menus.length-2){
            menuChoice(menus[number-1]);
        }else if(number == menus.length-1){

            //장바구니 확인
            System.out.println("장바구니확인");

            //장바구니가 비어있을 경우
            if(shoppingBasketList.size()==0){
                System.out.println("메뉴를 선택하세요");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showMenu ();
            }

                // 장바구니 내용 출력
                order.print(shoppingBasketList);
                order.reset();
                // Oder에 전달
                // 총 가격 출력
                System.out.println("[ Total ]");
                System.out.printf("% .1f",order.addPrice(shoppingBasketList));
                System.out.println();
                System.out.println("1. 주문    2. 메뉴판");

            // 주문 혹은 메뉴
            if(sc.nextInt() == 1){
                //큐에 넣는다
                System.out.println("주문이 완료되었습니다.");
                receiptList = (ArrayList<Product>) shoppingBasketList.clone();

                for(int i = 0; i<receiptList.size(); i++){
                    receiptLists.add(new Product(receiptList.get(i).product,receiptList.get(i).productPrice));
                }

                System.out.println("대기번호는 [ " +  receiptLists.size() + " ] 번 입니다.");
                System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //메뉴는 초기화
                //Order에 temp값 0으로변경
                shoppingBasketList.clear();
                order.reset();
                showMenu ();
            } else {
                showMenu ();
            }

        }else if(number == menus.length){
            //주문 취소
            System.out.println("주문취소");
            System.out.println("1. 확인   2. 취소");

            if(sc.nextInt()==1){

                shoppingBasketList.clear();
                showMenu ();

            } else {
                showMenu ();
            }

        }else{
            System.out.println("다른번호를 입력하셨습니다.");
            showMenu ();
        }

    }

    void menuChoice(String menuName){
        System.out.println("[ " + menuName +" MENU ]");

        //menuName의 값에 따라 배열 변경
        if(menuName.equals("Burgers")){
             menuChoice = new String[]{"ShackBurger", "SmokeShack", "Shroom Burger",
                     "Cheeseburger", "Hamburger"};
             menuChoicePrice = new double[]{6.9, 8.9, 9.4, 6.9, 5.4};
             menuSizeUp = 3.6;
             menuChoiceExplain = new String[]{"토마토, 양상추, 쉑소스가 토핑된 치즈버거",
                     "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
                     "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거",
                     "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
                     "비프패티를 기반으로 야채가 들어간 기본버거"};
        }else if(menuName.equals("Frozon Custard")){
            menuChoice = new String[] {"Chocolate Ice Cream","Vanilla Ice Cream"};
            menuChoicePrice = new double[] {1.5, 1.7};
            menuSizeUp = 0.5;
            menuChoiceExplain = new String[] {"달달한 초콜릿 아이스크림","부드러움 바닐라 아이스크림"};
        }else if(menuName.equals("Drinks")){
            menuChoice = new String[] {"Shack-made Lemonade","Frech Brewed Iced Tea"};
            menuChoicePrice = new double[] {3.9, 4.5};
            menuSizeUp = 1;
            menuChoiceExplain = new String[] {"매장에서 직접 만드는 상큼한 레몬에이드","직접 유기농 홍자를 우려낸 아이스티"};
        }else {
            menuChoice = new String[] {"ShackMelster Ale","MogpieBrewingCo"};
            menuChoicePrice = new double[] {9.8, 6.8};
            menuSizeUp = 2;
            menuChoiceExplain = new String[] {"뉴욕 브루클린 에서 양조한 에일 맥주","Pale Ale, Dreft"};
        }

        for(int i = 0; i<menuChoice.length;i++) {
            System.out.println(i+1 + ". " +menuChoice[i] + " | w " + menuChoicePrice[i] + " | " + menuChoiceExplain[i]);
        }

        int number = sc.nextInt()-1;

        // 메뉴당 추가옵션 넣기
        System.out.println("\""+ menuChoice[number] + " | w " + menuChoicePrice[number] + " | "+ menuChoiceExplain[number] +"\"");
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.println("1.  Single(W "+ menuChoicePrice[number]+")  2. Double(W " + (menuChoicePrice[number]+menuSizeUp) + ")");

        if(sc.nextInt()==2){
            //사이즈업
            print(menuChoice[number]+"(Double)", menuChoicePrice[number]+menuSizeUp, menuChoiceExplain[number]);
        }

        print(menuChoice[number], menuChoicePrice[number], menuChoiceExplain[number]);

    }

    void print(String  product,  double productPrice, String productExplain){
        System.out.println("\""+product + " | w " + productPrice + " | "+ productExplain +"\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        if(sc.nextInt() == 1){

            //장바구니에 담기
            //product 형으로 리스트에 담기
            //담을때 중복되는 값이 있는지
            if(products.menuCount(product,shoppingBasketList)){
                //값의 중복이 있을때
//                System.out.println("shoppingBasketList true" + shoppingBasketList.get(0).count);
                showMenu ();
            }

            shoppingBasketList.add(new Product(product,productPrice,productExplain));

//            System.out.println("shoppingBasketList false" + shoppingBasketList.get(0).count);

            // 담고 메뉴로 복귀
            showMenu ();

        }else {
            //메인화면으로 복귀
            showMenu ();
        }

    }

}
