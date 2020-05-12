package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    @Test
    public void sequenceEcritureComptableToStringTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC",2020,1);
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2020);
        sequence.setDerniereValeur(1);
        Assert.assertEquals(sequence.toString(),"SequenceEcritureComptable{codeJournal='"+sequence.getCodeJournal()+"', annee="+sequence.getAnnee()+", derniereValeur="+sequence.getDerniereValeur()+"}");
    }
}
