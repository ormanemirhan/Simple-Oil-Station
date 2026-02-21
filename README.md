# ⛽ Oil Station (Akaryakıt İstasyonu Yönetim Sistemi)

Bu proje, Java ile geliştirilmiş konsol tabanlı bir akaryakıt istasyonu simülasyonudur. Proje, Nesne Yönelimli Programlama (OOP) prensiplerine (özellikle Kapsülleme - Encapsulation) uygun olarak tasarlanmış olup temiz ve modüler bir yapıya sahiptir.

## 🚀 Özellikler

- **Nesne Yönelimli Mimari (OOP):** Tüm istasyon mantığı `OilStation` sınıfı içerisinde kapsüllenmiş (encapsulated) durumdadır. Dışarıdan yetkisiz müdahalelere (örneğin kasa bakiyesinin doğrudan değiştirilmesi) kapalıdır.
- **Esnek Satış ve Alım:** - Müşterilere "Litre" veya "Tutar (TL)" bazlı yakıt satışı yapılabilir.
  - İstasyon, kendi depolarını doldurmak için yakıt satın alabilir (Gider işlemi).
- **Otomatik Envanter ve Kasa Yönetimi:** Yapılan her işlemde depolar (Benzin, Dizel, LPG) ve Kasa (Bakiye) otomatik olarak güncellenir.
- **Hata Kontrolü (Validation):** - Yetersiz depo kapasitesi kontrolleri.
  - Hatalı kullanıcı girişlerine (sayı yerine harf girilmesi vb.) karşı programın çökmesini engelleyen güvenlik önlemleri.

## 🛠️ Kullanılan Teknolojiler

- **Dil:** Java
- **Kavramlar:** Sınıflar (Classes), Nesneler (Objects), Kapsülleme (Encapsulation), Kontrol Yapıları (`switch-case`, `while`, `if-else`), Kullanıcı Girdisi İşleme (`Scanner`).

## 💻 Nasıl Çalıştırılır?

Projeyi çalıştırmak için sisteminizde **Java (JDK)** kurulu olması gerekmektedir.

1. Dosyaları bilgisayarınıza indirin veya kopyalayın.
2. Terminali (veya Komut İstemcisini) açın ve dosyaların bulunduğu dizine gidin.
3. Kodu derlemek için:
   ```bash
   javac Main.java
