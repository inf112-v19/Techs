# Test BoardCards og GameController

    For å velge kort bruker man tallene 1-9 på tastaturet. Man velger da 5 kort og trykker deretter Enter og bruker Space for å utføre et og et kort i kronologisk rekkefølge. Når alle kortene er brukt, velger man på 5 nye kort og repeterer.

## ***Tall tester***

**ChosenCardNumberTest:**
Når man trykker tallene 1-9 på tastaturet vil tallene i nedere-høyrehjørne flytte seg oppå kortene man har plukket ut, slik at rekkefølgen av hvilke kort som spilles skal bli tydelig.

**NumbersReturnToCornerTest:**
Etter at man har plukket ut 5 kort og trykket Enter, så skal tallene flytte seg tilbake til nedere-høyrehjørne.

## ***Kort tester***

**RandomlyGeneratedCardsTest:**
Når man har plukket ut 5 kort og trykket enter, så vil 5 nye tilfeldige kort dukke opp på skjermen.

## ***Player bevegelse med kort tester:***

**PlayerMovementThroughCardsTest:**
Se at spilleren flytter seg korrekt etter hvilket type kort man har plukket ut.

**ChronologicallyCardsTest:**
Se at rekkefølgen på kortene man har fulgt følges når man trykker Enter for å utføre bevegelse på player.
