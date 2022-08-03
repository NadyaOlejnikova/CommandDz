import java.util.Scanner;

/**
 * Решение задачи №1 по теме "Одномерные массивы".
 * Решение задачи №1 по теме "Исключения".
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Яблоки"};
        int[] prices = {35, 87, 109};
        int[] productCount = new int[3];

        String[] saleProducts = {"Шоколад", "Чипсы", "Мед"};
        int[] salePrices = {90, 99, 130};
        int[] saleProductCount = new int[3];

        System.out.println("Список не акционных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт.");
        }
        System.out.println("Уважаемые покупатели!В магазине действует акция: 3 товара по цене 2-х. Успей купить указанные товары!");
        System.out.println("Список акционных товаров для покупки: ");
        for (int i = 0; i < saleProducts.length; i++) {
            System.out.println((i + 1) + ". " + saleProducts[i] + " " + salePrices[i] + " руб/шт.");
        }
        while (true) {
            System.out.println("Выберите товар и количество или введите \"end\" ");
            String input = scanner.nextLine();
            System.out.println("Выберите товар по акции и количество или введите \"end\" ");
            String saleInput = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            String[] parts = input.split(" ");
            String[] saleParts = saleInput.split(" ");
            if (parts.length != 2) {
                continue;
            }
            int productNumber;
            int saleProductNumber;
            int productsCount;
            int saleProductsCount;
            try {
                productNumber = Integer.parseInt(parts[0]) - 1;
                saleProductNumber = Integer.parseInt(saleParts[0]) - 1;
                if (productNumber > 2 || productNumber < 0 || saleProductNumber > 2 || saleProductNumber < 0) {
                    System.out.println("Вы ввели некорректное число продукта. Попробуйте снова!");
                    continue;
                }
                productsCount = Integer.parseInt(parts[1]);
                saleProductsCount = Integer.parseInt(saleParts[1]);
                if (productsCount > 100 || saleProductsCount > 100) {
                    System.out.println("Вы ввели некорректное кол-во продукта. Попробуйте снова!");
                    continue;
                }
                productCount[productNumber] += productsCount;
                saleProductCount[saleProductNumber] += saleProductsCount;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели текст вместо числа. Попробуйте снова ввести товары!");
                continue;
            }
        }
        System.out.println("Ваша продуктовая корзина: ");
        int saleSum = 0;// cумма по акционным товарам вся
        int salePriceSumByProduct;
        int promotion;// сумма товара со скидкой
        int discountAmount;// сумма скидки по товару
        int allSaleByProduct = 0;
        for (int j = 0; j < saleProductCount.length; j++) {
            int saleCountProducts = saleProductCount[j]; // запоминаем в массиве число продукта
            discountAmount = (saleProductCount[j] / 3) * salePrices[j];
            promotion = (salePrices[j] * saleProductCount[j]) - discountAmount;
            salePriceSumByProduct = promotion;
            if (saleCountProducts > 0) {
                System.out.println(saleProducts[j] + " " + saleCountProducts + " шт. в сумме " + salePriceSumByProduct + " руб.");
                System.out.println("вычтен товар по АКЦИИ   " + saleProducts[j] + " " + (saleProductCount[j] / 3) + "шт. в сумме " + discountAmount);
                saleSum += salePriceSumByProduct;
                allSaleByProduct += discountAmount;
            } else if (saleCountProducts < 0) {
                System.out.println("Сумма товаров некорректна ! Отрицательное число  " + saleProducts[j] + "  " + saleCountProducts + " шт."); // участник А, при отрицательном числе
            }
        }
        int sum = 0;
        int allCountProduct;
        int priceSumByProduct;
        for (int i = 0; i < productCount.length; i++) {
            allCountProduct = productCount[i];
            priceSumByProduct = prices[i] * allCountProduct;
            if (allCountProduct > 0) {
                System.out.println(products[i] + " " + allCountProduct + " шт. в сумме " + priceSumByProduct + " руб.");
                sum += priceSumByProduct;
            } else if (allCountProduct < 0) {
                System.out.println("Сумма товаров некорректна ! Отрицательное число  " + products[i] + "  " + allCountProduct); // участник А, при отрицательном числе
            }
        }
        int inTotal = sum + saleSum;
        System.out.println("Итого с учетом скидки: " + inTotal + " руб." + "скидка составила:  " + allSaleByProduct);
        scanner.close();
    }
}
