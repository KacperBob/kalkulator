import java.util.Scanner;

interface Kalkulator {
    void wykonajObliczenia();
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
        int wiek = Integer.parseInt(skaner.nextLine());
        setWiek(wiek);
        System.out.println("Czy pijesz Alkohol?: ");
        String alko = skaner.nextLine();
        System.out.println("Czy pijesz kawe?: ");
        String kawa = skaner.nextLine();
        System.out.println("Czy twoi krewni lysieja w przedwczesnym wieku?:");
        String krewni = skaner.nextLine();
        int szanse = 0;
        szanse += oblicz(alko);
        szanse += oblicz(kawa);
        szanse += oblicz(krewni);

        szanse += oblicz(getWiek(), alko);

        if (szanse >=3)
        System.out.println("twoje szanse na wyłysienie są duże, ale to nie znaczy, że muszisz wyłysieć. Zastanów się nad swoją dietą i stylem życia żeby do tego nie doprowadzić");
        else
            System.out.println("twoje szanse na wyłysienie są adekwatne do wieku i nie masz czym się martwić.");
    }

    private int oblicz(String odpowiedz) {
        switch (odpowiedz.toLowerCase()) {
            case "tak":
                return 1;
            case "nie":
                return 0;
            default:
                System.out.println("Nieznana odpowiedź, zakładam, że nie.");
                return 0;
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

    private int oblicz(int wiek, String odpowiedz) {
        int wynik = oblicz(wiek);
        wynik += oblicz(odpowiedz);
        return wynik;
    }
}