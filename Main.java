package OilStation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OilStation station = new OilStation();
        station.startSystem();
    }
}

class OilStation {
    private final float GASOLINE_PRICE = 42.57f;
    private final float DIESEL_PRICE = 45.67f;
    private final float LPG_PRICE = 28.78f;

    private float gasolineTank = 1000.0f;
    private float dieselTank = 1000.0f;
    private float lpgTank = 1000.0f;

    private float cashRegister = 0.0f;

    private Scanner scanner;

    public OilStation() {
        this.scanner = new Scanner(System.in);
    }

    public void startSystem() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            System.out.print("Seçiminiz: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Lütfen geçerli bir sayı girin!\n");
                scanner.next(); 
                continue;
            }

            int selection = scanner.nextInt();
            scanner.nextLine(); 

            switch (selection) {
                case 1:
                    buyFuelForStation(); 
                    break;
                case 2:
                    sellFuelToCustomer(); 
                    break;
                case 3:
                    displayTankStatus();
                    break;
                case 4:
                    displayCashRegister();
                    break;
                case 5:
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.\n");
                    break;
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n----- OIL STATION YÖNETİM SİSTEMİ -----");
        System.out.println("1- İstasyona Yakıt Al (Depoyu Doldur)");
        System.out.println("2- Müşteriye Yakıt Sat");
        System.out.println("3- Depo Durumunu Görüntüle");
        System.out.println("4- Kasa Durumunu Görüntüle");
        System.out.println("5- Çıkış");
    }

    private void sellFuelToCustomer() {
        System.out.println("\n--- Yakıt Satışı ---");
        char fuelType = getFuelTypeSelection();
        if (fuelType == 'X') return; 

        float pricePerLiter = getPriceForFuel(fuelType);
        float requestedLiters = calculateLitersToDispense(pricePerLiter);

        if (requestedLiters <= 0) return;

        if (checkTankCapacity(fuelType, requestedLiters)) {
            deductFromTank(fuelType, requestedLiters);
            float totalCost = requestedLiters * pricePerLiter;
            cashRegister += totalCost; 

            System.out.printf("İşlem Başarılı! %.2f Litre yakıt verildi. Kasaya %.2f ₺ eklendi.\n", requestedLiters, totalCost);
        } else {
            System.out.println("HATA: Depoda yeterli yakıt yok!");
        }
    }

    private void buyFuelForStation() {
        System.out.println("\n--- İstasyona Yakıt Alımı ---");
        char fuelType = getFuelTypeSelection();
        if (fuelType == 'X') return;

        System.out.print("Kaç litre yakıt alacaksınız?: ");
        float litersToBuy = scanner.nextFloat();
        
        float cost = litersToBuy * getPriceForFuel(fuelType);

        addToTank(fuelType, litersToBuy);
        cashRegister -= cost; 

        System.out.printf("İşlem Başarılı! Depoya %.2f Litre eklendi. Kasadan %.2f ₺ ödendi.\n", litersToBuy, cost);
    }

    private char getFuelTypeSelection() {
        System.out.print("Yakıt Tipi Seçin - (G)asoline, (D)iesel, (L)PG: ");
        char type = scanner.next().toUpperCase().charAt(0);
        if (type == 'G' || type == 'D' || type == 'L') {
            return type;
        }
        System.out.println("Geçersiz yakıt tipi!");
        return 'X';
    }

    private float getPriceForFuel(char type) {
        switch (type) {
            case 'G': return GASOLINE_PRICE;
            case 'D': return DIESEL_PRICE;
            case 'L': return LPG_PRICE;
            default: return 0.0f;
        }
    }

    private float calculateLitersToDispense(float currentPrice) {
        System.out.print("(L)itre bazlı mı yoksa (T)utar bazlı mı işlem yapacaksınız?: ");
        char selection = scanner.next().toUpperCase().charAt(0);

        if (selection == 'L') {
            System.out.print("Kaç litre?: ");
            return scanner.nextFloat();
        } else if (selection == 'T') {
            System.out.print("Kaç TL'lik?: ");
            float amount = scanner.nextFloat();
            return amount / currentPrice; 
        } else {
            System.out.println("Geçersiz seçim!");
            return 0.0f;
        }
    }

    private boolean checkTankCapacity(char type, float litersNeeded) {
        switch (type) {
            case 'G': return gasolineTank >= litersNeeded;
            case 'D': return dieselTank >= litersNeeded;
            case 'L': return lpgTank >= litersNeeded;
            default: return false;
        }
    }

    private void deductFromTank(char type, float liters) {
        if (type == 'G') gasolineTank -= liters;
        else if (type == 'D') dieselTank -= liters;
        else if (type == 'L') lpgTank -= liters;
    }

    private void addToTank(char type, float liters) {
        if (type == 'G') gasolineTank += liters;
        else if (type == 'D') dieselTank += liters;
        else if (type == 'L') lpgTank += liters;
    }

    private void displayTankStatus() {
        System.out.println("\n--- Depo Durumu ---");
        System.out.printf("Benzin (Gasoline) : %.2f Litre\n", gasolineTank);
        System.out.printf("Dizel (Diesel)    : %.2f Litre\n", dieselTank);
        System.out.printf("LPG               : %.2f Litre\n", lpgTank);
    }

    private void displayCashRegister() {
        System.out.println("\n--- Kasa Durumu ---");
        System.out.printf("Güncel Bakiye: %.2f ₺\n", cashRegister);
    }
}
