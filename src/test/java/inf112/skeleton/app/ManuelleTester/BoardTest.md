# Tester i Board

## ***Movement tester***
    For å bevege seg rundt på spillet er piltastene UP, RIGHT, DOWN, LEFT implementert til dette formålet. 

**MovePlayerOneUpOnBoardTest:**
Dersom man trykker piltast opp, skal player one sin posisjon bevege seg en tile opp (y+1).

**MovePlayerOneRightOnBoardTest:**
Dersom man trykker piltast høyre, skal player one sin posisjon bevege seg en tile til høyre (x+1).

**MovePlayerOneDownOnBoardTest:**
Dersom man trykker piltast ned, skal player one sin posisjon bevege seg en tile ned (y-1).

**MovePlayerOneLeftOnBoardTest:**
Dersom man trykker piltast venstre, skal player one sin posisjon bevege seg en til til venstre (x-1).

**MovePlayerOneIntoPitTest:**
Dersom man styrer player one inn i en pit, skal player one flyttes til siste backup. (Dersom ingen checkpoints er nådd, skal player one sendes til startposisjonen.)

**MovePlayerOneOutOfBoardTest:**
Dersom man styrer player one ut av kanten til brettet, skal player one flyttes til siste backup. (Dersom ingen checkpoints er nådd, skal player one sendes til startposisjon.)

**MoveOtherPlayerOnBoardTest:**
Dersom player one beveger seg i retning player two, vil player two bli flyttet synkront i samme retning på brettet.

**MoveOtherPlayerAgainstWallTest:**
Dersom player two står mellom en vegg og player one, så vil ikke verken player one eller player two bevege seg, dersom player one beveger seg i retning mot veggen.

**MoveOtherPlayerIntoPitTest:**
Dersom player two står mellom en pit og player one, og player one beveger seg en tile i retning player two, så vil kun player two sendes til siste backup. (Dersom ingen checkpoints er nådd, skal player two sendes til sin startposisjon.)

**MoveOtherPlayerOutOfBoardTest:**
Dersom player two står mellom brettkanten og player one, og player one beveger seg i retning brettkanten, så vil kun player two sendes til siste backup. (Dersom ingen checkpoints er nådd, skal player two sendes til sin startposisjon.)


## ***Checkpoints tester***

    For å drive testing av checkpoints tar man i bruk tasten 'C' som bruker en metode som registerer om noen spiller står på en checkpoint. Dette vil simulere sjekkingen som utføres i slutten av en runde.

**UpdatePlayerBackupTest:**
Dersom player står på et checkpoint (tile med et lite nummerert flagg i øvre-venstrehjørnet) og trykker 'C', vil player oppdatere sin backup til lokasjonen til checkpointet. Dette kan testes videre ved å falle ned utenfor kanten eller i en pit. Da skal player flyttes til den nye backupen.

**FinishGameWhenEveryCheckpointReachedTest:**
Dersom player går kronologisk gjennom checkpointene ved å stå på checkpoint-tilen og trykke 'C', vil spille automatisk avsluttes, hvor vinner annoseres i console.

## ***Conveyorbelts tester***

    Til testing av conveyorbelts bruker man tasten 'B' til å simulere bevegelse av players som står på conveyorbelts. Denne sjekken gjøres normalt sett i slutten av runden.

**PlayerOnRegularConveyorBeltTest:**
Dersom player står på en single conveyorbelt tile, skal spiller bevege seg en tile i retningen pilen på tilen viser når man trykker 'B'.

**PlayerOnDoubleConveyorBeltTest:**
Dersom player står på en double conveyorbelt tile, skal spiller bevege seg to tiles i retningen pilen på tilen viser når man trykker 'B'.

**MorePlayersOnConveyorBeltsTest:**
Dersom flere players står på en conveyorbelt tile, enten om det er single eller double type, så skal alle players flytte seg samtidig deretter. I logikken er det en forskjell som ikke vises visuelt hvor spilleren på double flyttes først, før spillerne på single flyttes.


