package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    @Test
    public void sequenceEcritureComptableToStringTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2020);
        sequence.setDerniereValeur(1);

        Assert.assertEquals(sequence.toString(),"SequenceEcritureComptable{codeJournal='AC', annee=2020, derniereValeur=1}");
    }
}
