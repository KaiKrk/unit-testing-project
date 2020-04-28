package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.Assert;
import static org.assertj.core.api.Assertions.*;


public class EcritureComptableTest {

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test        //New test to get total Debit
    public void getTotalDebit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Total Debit Test");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "17", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, "9"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "3", "22"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        assertThat(vEcriture.getTotalDebit()).isEqualTo(new BigDecimal(20));
    }

    @Test		//New test to get total Credit
    public void getTotalCredit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Total Credit Test");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "12", "50"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10.50" , null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "7", "25"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        assertThat(vEcriture.getTotalCredit()).isEqualTo(new BigDecimal(75));
    }


    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());

    }

}
