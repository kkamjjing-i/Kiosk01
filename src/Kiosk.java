import java.util.Scanner;

public class Kiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        Menu[] menus = {
                new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거"),
                new Menu("Frozen Custard", "매장에서 신선하게 만드는 아이스크림"),
                new Menu("Drinks", "매장에서 직접 만드는 음료"),
                new Menu("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주")
        };

        Product[] products = {
                new Product("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9),
                new Product("SmokeShack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9),
                new Product("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4),
                new Product("Cheeseburger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9),
                new Product("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4)
        };

        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menus.length; i++) {
                System.out.println((i + 1) + ". " + menus[i].name + "\t| " + menus[i].description);
            }

            System.out.println("[ ORDER MENU ]");
            System.out.println("5. Order\t| 장바구니를 확인 후 주문합니다.");
            System.out.println("6. Cancel\t| 진행중인 주문을 취소합니다.");

            System.out.print("\n메뉴를 선택하세요: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    displayProductMenu(products, order);
                    break;
                case 5:
                    displayOrderScreen(order);
                    break;
                case 6:
                    cancelOrder(order);
                    break;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    private static void displayProductMenu(Product[] products, Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SHAKESHACK BURGER에 오신걸 환영합니다.");
        System.out.println("아래 상품 메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        while (true) {
            System.out.println("[ Burgers MENU ]");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i].name + "\t| W " + products[i].price+ " | " + products[i].description);
            }

            System.out.print("\n상품을 선택하세요 (0. 이전 메뉴): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= products.length) {
                // 상품 선택 화면 출력
                addToCartConfirmation(products[choice - 1], order);
            } else {
                System.out.println("올바른 상품을 선택하세요.");
            }
        }
    }

    private static void addToCartConfirmation(Product product, Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(product.name + " | W " + product.price + " | " + product.description);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t2. 취소");

        int choice = scanner.nextInt();

        if (choice == 1) {
            order.addToCart(product);
            System.out.println(product.name + "가 장바구니에 추가되었습니다.\n");
        }
    }

    private static void displayOrderScreen(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("아래와 같이 주문 하시겠습니까?\n");

        // 장바구니 목록 출력
        displayCart(order);

        System.out.println("[ Total ]");
        System.out.println("W " + order.calculateTotal() + "\n");
        System.out.println("1. 주문\t2. 메뉴판");

        int choice = scanner.nextInt();

        if (choice == 1) {
            // 주문 완료 화면 출력
            completeOrder(order);
        } else if (choice == 2) {
            // 다시 메인 메뉴로 돌아감
        } else {
            System.out.println("올바른 선택을 하세요.");
        }
    }

    private static void completeOrder(Order order) {
        System.out.println("주문이 완료되었습니다!");

        // 대기 번호 발급
        int orderNumber = generateOrderNumber();
        System.out.println("대기번호는 [ " + orderNumber + " ] 번 입니다.");

        // 3초 대기 후 메인 메뉴로 돌아감
        try {
            Thread.sleep(3000); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 주문 완료 후 장바구니 비우기
        order.clearCart();
    }

    private static int generateOrderNumber() {
        // 대기 번호 : 1~100까지 랜덤 출력
        return (int)(Math.random()*100)+1;
    }

    private static void cancelOrder(Order order) {
        order.clearCart();
        System.out.println("주문이 취소되었습니다.\n");
    }

    private static void displayCart(Order order) {
        System.out.println("[ 장바구니 ]");
        for (Product product : order.cart) {
            System.out.println(product.name + " | W " + product.price + " | " + product.description);
        }
        System.out.println();
    }
}

