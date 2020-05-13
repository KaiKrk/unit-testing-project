package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class SequenceEcritureComptableRMTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void mapRow() throws SQLException {
        Mockito.when(resultSet.getString("journal_code")).thenReturn("AC");
        Mockito.when(resultSet.getInt("annee")).thenReturn(2020);
        Mockito.when(resultSet.getInt("derniere_valeur")).thenReturn(7);

        SequenceEcritureComptableRM sequenceEcritureComptableRM = new SequenceEcritureComptableRM();
        SequenceEcritureComptable resultSequenceEcritureComptableRM = sequenceEcritureComptableRM.mapRow(resultSet,0);

        Assert.assertEquals(resultSequenceEcritureComptableRM.getCodeJournal(),"AC");
        Assert.assertTrue(resultSequenceEcritureComptableRM.getAnnee()==2020);
        Assert.assertTrue(resultSequenceEcritureComptableRM.getDerniereValeur()==7);
    }
}
