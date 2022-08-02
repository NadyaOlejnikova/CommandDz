import java.util.Scanner;

/**
 * Решение задачи №1 по теме "Одномерные массивы".
 * Решение задачи №1 по теме "Исключения".
 */

public class Main {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.productBasket();
        main.saleProductBasket();
    }

    private void productBasket() {
        String[] products = {"Хлеб", "Молоко", "Яблоки"};
        int[] prices = {35, 87, 109};
        int[] productCount = new int[3];

        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт.");
        }
        while (true) {
            System.out.println("Выберите товар и количество или введите \"end\" ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                continue;
            }
            try {
                int productNumber = Integer.parseInt(parts[0]) - 1;
                if (productNumber > 2 || productNumber < 0) {
                    System.out.println("Вы ввели некорректное число продукта. Попробуйте снова!");
                    continue;
                }
                int productsCount = Integer.parseInt(parts[1]);
                if (productsCount > 100 || productsCount <= 0) {
                    System.out.println("Вы ввели некорректное кол-во продукта. Попробуйте снова!");
                    continue;
                }
                productCount[productNumber] += productsCount;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели текст заместо числа. Попробуйте снова!");
                continue;
            }
        }
        System.out.println("Ваша корзина:");
        int sum = 0;
        for (int i = 0; i < productCount.length; i++) {
            int allCountProduct = productCount[i];
            if (allCountProduct > 0) {
                int priceSumByProduct = prices[i] * allCountProduct;
                System.out.println(products[i] + " " + allCountProduct + " шт. в сумме " + priceSumByProduct + " руб.");
                sum += priceSumByProduct;
            }
        }
        System.out.println("Итого не акционных товаров: " + sum + " руб.");
    }

    private void saleProductBasket() {
        String[] saleProductsName = {"Шоколад", "Эклеры", "Печенье"};
        int[] saleProductsPrices = {49, 80, 120};
        int[] productCount = new int[3];
        int sumSale = 0;
        System.out.println("Уважаемые покупатели!В магазине действует акция: 3 товара по цене 2-х. Успей купить указанные товары!");
        System.out.println("Список возможных акционных товаров для покупки: ");
        for (int i = 0; i < saleProductsName.length; i++) {
            System.out.println((i + 1) + ". " + saleProductsName[i] + " " + saleProductsPrices[i] + " руб/шт.");
        }
        while (true) {
            System.out.println("Выберите товар и количество или введите \"end\" ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                continue;
            }
            int saleProductsCount;
            int saleProductNumber;
            try {
                saleProductNumber = Integer.parseInt(parts[0]) - 1; // выбор продукта
                if (saleProductNumber > 2 || saleProductNumber < 0) {
                    System.out.println("Вы ввели некорректное число продукта. Попробуйте снова!");
                    continue;
                }
                saleProductsCount = Integer.parseInt(parts[1]); //  выбор количества продуктов
                if (saleProductsCount > 100 || saleProductsCount <= 0) {
                    System.out.println("Вы ввели некорректное кол-во продукта. Попробуйте снова!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели текст заместо числа. Попробуйте снова!");
                continue;
            }
            if (saleProductsCount == 3) {
                sumSale += saleProductsPrices[saleProductNumber];
                productCount[saleProductNumber] += saleProductsCount;
            } else {
                int resultWhatDiscount = Math.floorDiv(saleProductsCount, 3);
                sumSale += (resultWhatDiscount * saleProductsPrices[saleProductNumber]);
                productCount[saleProductNumber] += saleProductsCount;
            }
        }
        System.out.println("Ваша акционная корзина: ");
        int allSumNotSale = 0;
        for (int i = 0; i < productCount.length; i++) {
            int allCountProduct = productCount[i];
            if (allCountProduct > 0) {
                int priceSumByProduct = saleProductsPrices[i] * allCountProduct;
                System.out.println(saleProductsName[i] + " " + allCountProduct + " шт. в сумме " + priceSumByProduct + " руб.");
                allSumNotSale += priceSumByProduct;
            }
        }
        System.out.println("Итого акционных товаров без учета скидки: " + allSumNotSale + " руб.");
        System.out.println("Итого к оплате с учетом скидки: " + (allSumNotSale - sumSale) + " руб.");
        System.out.println("Итого скидка составила: " + sumSale + " руб.");
    }
}
