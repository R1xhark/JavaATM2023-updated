# Java ATM-Banking Simulátor

Jednoduchá Java aplikace simulující zakkladni ukony v bance. Tento projekt se zaměřuje na poskytnutí základního zážitku z používání bankomatu s důrazem na bezpečnost a kvalitní kódování.

## Popis projektu

Tato aplikace je rozdělena do dvou hlavních částí (Zatim):

1. **Správa klientů**: Tato část umožňuje vytváření nových klientů a jejich záznamu v databázi. Klienti jsou reprezentováni Javovou třídou "Klient", která obsahuje informace jako jméno, číslo karty a PIN. Metody jsou implementovány pro vytváření nových klientů a ukládání jejich údajů pomocí SQL příkazů do databázové tabulky. Tato část by měla být použita pouze pro počáteční naplnění databáze a neměla by být zahrnuta ve finální verzi bankomatové aplikace.

2. **ATM operace**: Tato část simuluje interakci s bankomatem. Umožňuje uživatelům získat informace o svém zůstatku a provádět operace jako výběry hotovosti. Aplikace poskytuje uživatelské rozhraní, které umožňuje snadnou interakci s bankomatovými funkcemi.

## Použití

1. **Klientův účet**: Spusťte aplikaci a vytvořte klienty pomocí části "Správa klientů". Toto je jednorázová konfigurace pro naplnění databáze údaji o klientech.

2. **Bankomat**: Proveďte různé operace bankomatu, jako je zobrazení zůstatku nebo výběr hotovosti, pomocí části "ATM operace". Ujistěte se, že zadáváte správný identifikační kód klienta a platný PIN pro přístup k účtu.

## Bezpečnost

Tato aplikace kladný důraz na bezpečnost klientů a ukládání citlivých informací, jako jsou PIN kódy. V případě reálného použití by bylo důležité implementovat další bezpečnostní opatření, jako je šifrování dat a ochrana před útoky.

## Autor

Tento projekt byl vytvořen Richard Dubny jako součást Java Portfolia. Pro více informací nás kontaktujte na richard@dubny.cz.

