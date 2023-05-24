import java.util.Scanner;
import java.util.Locale;

interface Kalkulator {
    void wykonajObliczenia();
}

enum Odpowiedzi {
    TAK, NIE
}

public class SzansaNaWylysienie implements Kalkulator {
    private int wiek;

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        if (wiek <= 0) {
            throw new IllegalArgumentException("Wiek nie może być mniejszy lub równy 0");
        }
        this.wiek = wiek;
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new SzansaNaWylysienie();
        kalkulator.wykonajObliczenia();
    }

    @Override
    public void wykonajObliczenia() {
        Scanner skaner = new Scanner(System.in);
        System.out.println("Witaj w kalkulatorze szans na wyłysienie!");

        System.out.println("Podaj swój wiek: ");
        while (!skaner.hasNextInt()) {
            System.out.println("To nie jest poprawny wiek. Wprowadź wiek jako liczbę:");
            skaner.next();
        }
        int wiek = skaner.nextInt();
        skaner.nextLine();  // clear the buffer
        setWiek(wiek);
        System.out.println("Czy pijesz Alkohol? (tak/nie): ");
        Odpowiedzi alko = getOdpowiedz(skaner);
        System.out.println("Czy pijesz kawe? (tak/nie): ");
        Odpowiedzi kawa = getOdpowiedz(skaner);
        System.out.println("Czy twoi krewni lysieja w przedwczesnym wieku? (tak/nie):");
        Odpowiedzi krewni = getOdpowiedz(skaner);
        System.out.println("Czy często odczuwasz stres? (tak/nie): ");
        Odpowiedzi stres = getOdpowiedz(skaner);
        System.out.println("Czy utrzymujesz zdrową dietę? (tak/nie): ");
        Odpowiedzi dieta = getOdpowiedz(skaner);
        System.out.println("Czy regularnie uprawiasz sport? (tak/nie): ");
        Odpowiedzi sport = getOdpowiedz(skaner);
        System.out.println("Czy palisz? (tak/nie): ");
        Odpowiedzi palenie = getOdpowiedz(skaner);
        System.out.println("Czy regularnie się wysypiasz? (tak/nie): ");
        Odpowiedzi sen = getOdpowiedz(skaner);
        System.out.println("Czy przyjmujesz jakieś leki? (tak/nie): ");
        Odpowiedzi leki = getOdpowiedz(skaner);
        System.out.println("Czy pracujesz w stresującym środowisku? (tak/nie): ");
        Odpowiedzi praca = getOdpowiedz(skaner);

        int szanse = 0;
        szanse += oblicz(alko);
        szanse += oblicz(kawa);
        szanse += oblicz(krewni);
        szanse += oblicz(stres);
        szanse += oblicz(dieta, true);
        szanse += oblicz(sport, true);
        szanse += oblicz(palenie);
        szanse += oblicz(sen, true);
        szanse += oblicz(leki);
        szanse += oblicz(praca);
        szanse += oblicz(getWiek(), alko);

        String wynik;
        if (szanse >= 6) {
            wynik = "Twoje szanse na wyłysienie są duże, ale to nie znaczy, że musisz wyłysieć. Zastanów się nad swoją dietą i stylem życia, żeby do tego nie doprowadzić.";
        } else {
            wynik = "Twoje szanse na wyłysienie są adekwatne do wieku i nie masz czym się martwić.";
        }

        System.out.println(wynik);
    }

    private Odpowiedzi getOdpowiedz(Scanner skaner) {
        String odpowiedz;
        do {
            odpowiedz = skaner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (!(odpowiedz.equals("TAK") || odpowiedz.equals("NIE"))) {
                System.out.println("Niepoprawna odpowiedź. Wprowadź 'tak' lub 'nie':");
            }
        } while (!(odpowiedz.equals("TAK") || odpowiedz.equals("NIE")));
        return Odpowiedzi.valueOf(odpowiedz);
    }

    private int oblicz(Odpowiedzi odpowiedz) {
        return oblicz(odpowiedz, false);
    }

    private int oblicz(Odpowiedzi odpowiedz, boolean odwroc) {
        if (odwroc) {
            return (odpowiedz == Odpowiedzi.TAK) ? 0 : 1;
        } else {
            return (odpowiedz == Odpowiedzi.TAK) ? 1 : 0;
        }
    }

    private int oblicz(int wiek) {
        if (wiek >= 50) {
            return 2;
        } else if (wiek >= 30) {
            return 1;
        } else {
            return 0;
        }
    }

    private int oblicz(int wiek, Odpowiedzi odpowiedz) {
        int wynik = oblicz(wiek);
        wynik += oblicz(odpowiedz);
        return wynik;
    }
}
