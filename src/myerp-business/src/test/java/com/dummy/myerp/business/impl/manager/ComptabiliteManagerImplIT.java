package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.myerp.testbusiness.BusinessTestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ComptabiliteManagerImplIT extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @Test
    public void getListCompteComptableTest(){
        List<CompteComptable> compteComptableList = manager.getListCompteComptable();
        Assert.assertTrue(!compteComptableList.isEmpty());
    }

    @Test
    public void getListJournalComptable(){
        List<JournalComptable> journalComptableList = manager.getListJournalComptable();
        Assert.assertTrue(!journalComptableList.isEmpty());
    }

    @Test
    public void getListEcritureComptableTest() {
        List<EcritureComptable> ecritureComptableList = manager.getListEcritureComptable();
        Assert.assertTrue(!ecritureComptableList.isEmpty());
    }
}
