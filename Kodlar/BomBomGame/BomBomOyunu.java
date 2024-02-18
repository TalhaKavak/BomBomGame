package BomBomGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BomBomOyunu {
    public static void main(String[] args) {
    // Oyun haritasını txt dosyasından okuyarak harita dizisini oluştur    
    char[][] harita = oyunHaritasiniOku("BomBomGame\\Oyunharitasi.txt");
    System.out.println("Oyun başladı!");

    // Oyun döngüsü
    while(true) {
        // Oyun haritasını ekrana göster
        oyunHaritasiniGoster(harita);

        // Kullanıcıdan satır ve sütun koordinatlarını al
        System.out.print("Lütfen satır için koordinat girin :");
        int satir = kullanicidanKoordinatAl();
        System.out.print("Lütfen sütun için koordinat girin :");
        int sutun = kullanicidanKoordinatAl();

        // Geçersiz koordinatları kontrol et
        if (!gecerliKoordinatlar(satir, sutun)) {
            System.out.println("Geçersiz koordinat girdiniz");
            continue;
        }

        // Daha önce işaretlenmiş koordinatları kontrol et
        if (harita[satir][sutun] == 'x') {
            System.out.println("Daha önce burayı işaretlediniz, lütfen farklı bir koordinat girin!");
            continue;
        }

        // Oyuncu oyunu sonlandırmak istiyorsa
        if (satir == 0 && sutun == 0) {
            System.out.println("Oyun sonlandırılıyor...");
            System.out.println("Güle güle...");
            break;
        }

        // Seçilen karakter ile aynı olan diğer karakterleri işaretle
        char temp = harita[satir][sutun];
        boolean isaretlendiMi = kontrolEt(harita, satir, sutun, temp);

        // Aynı karakterler işaretlendiyse bilgi mesajı yazdır
        if (isaretlendiMi) {
            System.out.println("Aynı karakterler işaretlendi!");
        } else {
            System.out.println("Bu koordinat aynı karakterler içermiyor.");
        }
    }        
}

// Aynı karakterleri kontrol eden recursive metot
public static boolean kontrolEt(char[][] harita, int i, int j, char temp) {
    // Geçersiz durumları kontrol et
    if (i < 0 || i > 9 || j < 0 || j > 9 || harita[i][j] != temp) {
        return false;
    }

    // Seçilen karakteri işaretle
    harita[i][j] = 'x';

    // Yukarı, sağa, aşağı ve sola doğru kontrolü recursive olarak yap
    boolean yukari = kontrolEt(harita, i - 1, j, temp);
    boolean sag = kontrolEt(harita, i, j + 1, temp);
    boolean asagi = kontrolEt(harita, i + 1, j, temp);
    boolean sol = kontrolEt(harita, i, j - 1, temp);

    // Herhangi bir yönde bağlantılı karakter bulunduysa true döndür
    return true;
}

// Geçerli koordinatları kontrol eden metot
public static boolean gecerliKoordinatlar(int satir, int sutun) {
    return satir >= 0 && satir <= 9 && sutun >= 0 && sutun <= 9;
}

// Kullanıcıdan tamsayı koordinat alarak döndüren metot
public static int kullanicidanKoordinatAl() {
    Scanner giris = new Scanner(System.in);
    return giris.nextInt();
}

// Oyun haritasını dosyadan okuyan metot
public static char[][] oyunHaritasiniOku(String dosyaAdi) { 
        try {
            Scanner scanner = new Scanner(new File(dosyaAdi));
            char[][] harita = new char[10][10];

            // Dosyadan okunan karakterleri diziye yerleştir
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    harita[i][j] = scanner.next().charAt(0);
                }
            }

            scanner.close();
            return harita;
        } catch (FileNotFoundException e) {
            // Dosya bulunamazsa hatayı yazdır ve null döndür
            e.printStackTrace();
            return null;
        }
    }

    // Oyun haritasını ekrana yazdıran metot
    public static void oyunHaritasiniGoster(char[][] harita) { 
        System.out.println("Oyun Haritası:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Dizideki her karakteri ekrana yazdır
                System.out.print(" "+harita[i][j] + " ");
            }
            System.out.println();
        }
    }

}
