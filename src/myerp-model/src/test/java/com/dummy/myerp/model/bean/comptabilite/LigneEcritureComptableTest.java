package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LigneEcritureComptableTest {

    @Test
    public void ligneEcritureComptableToStringTest(){
        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
        ligneEcritureComptable.setCompteComptable(null);
        ligneEcritureComptable.setLibelle("Achat");
        ligneEcritureComptable.setCredit(BigDecimal.valueOf(25));
        ligneEcritureComptable.setDebit(null);

        Assert.assertEquals(ligneEcritureComptable.toString(),"LigneEcritureComptable{compteComptable=null, libelle='Achat', debit=null, credit=25}");
    }
}
